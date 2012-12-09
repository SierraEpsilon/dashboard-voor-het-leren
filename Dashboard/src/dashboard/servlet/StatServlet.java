package dashboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import dashboard.error.NoSuchCourseException;
import dashboard.model.*;
import dashboard.registry.CourseRegistry;
import dashboard.util.RegistryInitializer;
import dashboard.util.Statistics;

public class StatServlet extends HttpServlet {

	private static final long serialVersionUID = -6566808443793850095L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(!RegistryInitializer.initialized()){
			RegistryInitializer.initialize(session.getServletContext());
		}
		Student student = (Student)session.getAttribute("student");
		if(student==null){
			resp.sendRedirect("/jsp/login/login.jsp?msg=Beveiligde pagina");
		}else{
			resp.setContentType("application/json");
			String reqCourse = req.getParameter("course");
			String gen = req.getParameter("gen");
			if(gen==null&&reqCourse==null){
				resp.sendRedirect("/jsp/stats/all.jsp");
			}else{
				JSONArray root = new JSONArray();
				try {
					if(reqCourse==null){
					//general
						ArrayList<StudyMoment> moments = student.getStudyMoments();
						if(moments.size()==0){
							//write error
							LinkedHashMap<String,JSONArray> noData = new LinkedHashMap<String,JSONArray>();
							noData.put("...", new JSONArray().put("Nog geen studiemomenten"));
							root.put(createCat("Geen gegevens","text","Nog geen studiemomenten",null,noData));
						}else{
							//tijdsverdeling over courses
							LinkedHashMap<String,JSONArray> time = new LinkedHashMap<String,JSONArray>();
							time.put("Laatste Week", hashToArray(Statistics.getCourseTimes(Statistics.getMomentsWeek(moments),student.getCourses())));
							time.put("Laatste Maand", hashToArray(Statistics.getCourseTimes(Statistics.getMomentsMonth(moments),student.getCourses())));
							time.put("Alles", hashToArray(Statistics.getCourseTimes(moments,student.getCourses())));
							String name = "Tijdsverdeling over vakken";
							String type = "pie";
							String desc = "De verdeling van de tijd in de gegeven periode over verschillende vakken";
							JSONObject options = new JSONObject();
							root.put(createCat(name,type,desc,options,time));
							//tijdsverdeling over locaties
							LinkedHashMap<String,JSONArray> locs = new LinkedHashMap<String,JSONArray>();
							locs.put("Laatste Week", hashToArray(Statistics.getTimeByLoc(Statistics.getMomentsWeek(moments),student)));
							locs.put("Laatste Maand", hashToArray(Statistics.getTimeByLoc(Statistics.getMomentsMonth(moments),student)));
							locs.put("Alles", hashToArray(Statistics.getTimeByLoc(moments,student)));
							name = "Tijdsverdeling over locaties";
							type = "pie";
							desc = "De verdeling van de tijd in de gegeven periode over verschillende locaties";
							options = new JSONObject();
							root.put(createCat(name,type,desc,options,locs));
							//week
							JSONArray weekEff = new JSONArray(makeRelInPerc(Statistics.getTimeByDayInWeek(moments)));
							String[] labels = new String[7];
							labels[0] = "Ma";
							labels[1] = "Din";
							labels[2] = "Wo";
							labels[3] = "Do";
							labels[4] = "Vr";
							labels[5] = "Zat";
							labels[6] = "Zon";
							JSONArray labelArr = new JSONArray(labels);
							JSONArray dataArr = new JSONArray();
							dataArr.put(weekEff);
							dataArr.put(labelArr);
							LinkedHashMap<String,JSONArray> verd = new LinkedHashMap<String,JSONArray>();
							verd.put("", dataArr);
							options = new JSONObject();
							options.put("xlabel","Procentuele inspanning");
							desc = "Vergelijking van hoeveel er relatief op elke dag gestudeerd werd";
							root.put(createCat("Verdeling inspanningen over de week","bar",desc,options,verd));
							/*
							JSONObject cat = new JSONObject();
							cat.put("name", "test");
							cat.put("type", "pie");
							JSONObject options = new JSONObject();
							options.put("ylabel", "test");
							cat.put("options", options);
							cat.put("desc", "Uitleg komt hier");
							JSONArray datas = new JSONArray();
							JSONObject data1 = new JSONObject();
							JSONObject data2 = new JSONObject();
							data1.put("name", "Laatste week");
							data2.put("name", "Laatste maand");
							ArrayList<StudyMoment> moments = student.getStudyMoments();
							HashMap<String,Long> map = Statistics.getCourseTimes(moments,student.getCourses());
							JSONArray temp = new JSONArray().put(hashToArray(map));
							data1.put("data", temp);
							data2.put("data", temp);
							datas.put(data1);
							datas.put(data2);
							cat.put("data", datas);
							root.put(cat);
							ArrayList<StudyMoment> moments = student.getStudyMoments();
							//verdeling tijd
							HashMap<String,Long> map = Statistics.getCourseTimes(moments,student.getCourses());
							JSONObject verdt = new JSONObject();
							root.put(createCat("Verdeling tijd","pie",new JSONArray().put(hashToArray(map))));
							//week
							JSONArray weekEff = new JSONArray(makeRelInPerc(Statistics.getTimeByDayInWeek(moments)));
							String[] labels = new String[7];
							labels[0] = "Ma";
							labels[1] = "Din";
							labels[2] = "Wo";
							labels[3] = "Do";
							labels[4] = "Vr";
							labels[5] = "Zat";
							labels[6] = "Zon";
							JSONArray labelArr = new JSONArray(labels);
							JSONArray dataArr = new JSONArray();
							dataArr.put(weekEff);
							dataArr.put(labelArr);
							root.put(createCat("Verdeling inspanningen over de week","bar",dataArr));
							*/
						}
					}else{
					//course
						try{
							Course course = CourseRegistry.getCourse(reqCourse);
							ArrayList<StudyMoment> moments = Statistics.filterMomentsByCourse(student.getStudyMoments(), course);
							if(moments.size()==0){
								//write error
								LinkedHashMap<String,JSONArray> noData = new LinkedHashMap<String,JSONArray>();
								noData.put("...", new JSONArray().put("Nog geen studiemomenten voor dit vak"));
								root.put(createCat("Geen gegevens","text","Nog geen studiemomenten voor dit vak",null,noData));
							}else{
								//tijdsverdeling over locaties
								LinkedHashMap<String,JSONArray> locs = new LinkedHashMap<String,JSONArray>();
								locs.put("Laatste Week", hashToArray(Statistics.getTimeByLoc(Statistics.getMomentsWeek(moments),student)));
								locs.put("Laatste Maand", hashToArray(Statistics.getTimeByLoc(Statistics.getMomentsMonth(moments),student)));
								locs.put("Alles", hashToArray(Statistics.getTimeByLoc(moments,student)));
								String name = "Tijdsverdeling over locaties";
								String type = "pie";
								String desc = "De verdeling van de tijd in de gegeven periode over verschillende locaties";
								JSONObject options = new JSONObject();
								root.put(createCat(name,type,desc,options,locs));
								//week
								JSONArray weekEff = new JSONArray(makeRelInPerc(Statistics.getTimeByDayInWeek(moments)));
								String[] labels = new String[7];
								labels[0] = "Ma";
								labels[1] = "Din";
								labels[2] = "Wo";
								labels[3] = "Do";
								labels[4] = "Vr";
								labels[5] = "Zat";
								labels[6] = "Zon";
								JSONArray labelArr = new JSONArray(labels);
								JSONArray dataArr = new JSONArray();
								dataArr.put(weekEff);
								dataArr.put(labelArr);
								LinkedHashMap<String,JSONArray> verd = new LinkedHashMap<String,JSONArray>();
								verd.put("", dataArr);
								options = new JSONObject();
								options.put("xlabel","Procentuele inspanning");
								desc = "Vergelijking van hoeveel er relatief op elke dag gestudeerd werd";
								root.put(createCat("Verdeling inspanningen over de week","bar",desc,options,verd));
								//credit progress
								desc = "Vergelijking van hoeveel er relatief op elke dag gestudeerd werd";
								options = new JSONObject();
								verd = new LinkedHashMap<String,JSONArray>();
								desc = "Een normstudent moet per studiepunt 25 à 30 uren studeren. ";
								desc +="Hier zie je je vooruitgang in vergelijking tot het ideaal van 28 uren per studiepunt.";
								verd.put("Individueel", new JSONArray().put(Statistics.creditProgress(course, student)));
								verd.put("Gemiddeld", new JSONArray().put(Statistics.averageCreditProgress(course)));
								root.put(createCat("Vooruitgang studiepunten","prog",desc,options,verd));
							}
						}catch(NoSuchCourseException e){
							PrintWriter writer = resp.getWriter();        
					        writer.println("Course not found.");
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PrintWriter writer = resp.getWriter();        
		        writer.println(root.toString());
			}
		}
	}
	private JSONObject createCat(String name,String type,Object data) throws JSONException{
		JSONObject catRoot = new JSONObject();
		catRoot.put("name", name);
		JSONArray graphs = new JSONArray();
		JSONObject graph = new JSONObject();
		graph.put("type",type);
		graph.put("data",data);
		graphs.put(graph);
		catRoot.put("graphs", graphs);
		return catRoot;
	}
	private JSONObject createCat(String name,String type,String desc,JSONObject options,LinkedHashMap<String,JSONArray> data) throws JSONException{
		JSONObject catRoot = new JSONObject();
		catRoot.put("name", name);
		catRoot.put("desc", desc);
		catRoot.put("type", type);
		catRoot.put("options", options);
		JSONArray dataArr = new JSONArray();
		Set<String> names = data.keySet();
		Iterator<String> it = names.iterator();
		while(it.hasNext()){
			String keyName = it.next();
			JSONObject temp = new JSONObject();
			temp.put("name", keyName);
			temp.put("data",data.get(keyName));
			dataArr.put(temp);			
		}
		catRoot.put("data",dataArr);
		return catRoot;
	}
	private JSONArray hashToArray(Map mp){
		JSONArray ret = new JSONArray();
		Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	    	JSONArray sub = new JSONArray();
	        Map.Entry pairs = (Map.Entry)it.next();
	        sub.put(pairs.getKey());
	        sub.put(pairs.getValue());
	        ret.put(sub);
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    return ret;
		
	}
	private long[] makeRelInPerc(long[] input){
		long total = 0;
		long[] ret = new long[input.length];
		for(int i=0;i<input.length;i++)
			total += input[i];
		for(int j=0;j<input.length;j++){
			long test = input[j]*100/total;
			ret[j] = input[j]*100/total;
		}
		return ret;
		
	}
}
