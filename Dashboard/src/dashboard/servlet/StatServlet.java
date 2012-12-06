package dashboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import dashboard.util.Statistics;

public class StatServlet extends HttpServlet {

	private static final long serialVersionUID = -6566808443793850095L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		if(student==null){
			resp.sendRedirect("/jsp/login/login.jsp?msg=Beveiligde pagina");
		}else{
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
					}else{
					//course
						try{
							Course course = CourseRegistry.getCourse(reqCourse);
							ArrayList<StudyMoment> moments = Statistics.filterMomentsByCourse(student.getStudyMoments(), course);
							if(moments.size()==0){
								//write error
								root.put(createCat("Geen gegevens","text","Nog geen studiemomenten voor dit vak."));
							}else{
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
