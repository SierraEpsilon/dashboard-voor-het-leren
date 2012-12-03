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
			resp.sendRedirect("/jsp/login.jsp?msg=Beveiligde pagina");
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
						//verdeling tijd
						HashMap<String,Long> map = Statistics.getCourseTimes(student.getStudyMoments(),student.getCourses());
						JSONObject verdt = new JSONObject();
						verdt.put("name", "Verdeling tijd");
						JSONArray verdtgraphs = new JSONArray();
						JSONObject verdtgraph1 = new JSONObject();
						verdtgraph1.put("type","pie");
						verdtgraph1.put("data", new JSONArray().put(hashToArray(map)));
						verdtgraphs.put(verdtgraph1);
						verdt.put("graphs",verdtgraphs);
						root.put(verdt);
						//week
						JSONObject week = new JSONObject();
						week.put("name", "Tijdsverdeling per week");
						JSONArray weekgraphs = new JSONArray();
						JSONObject weekgraph1 = new JSONObject();
						weekgraph1.put("type","bar");
						JSONArray weekTimes = new JSONArray(makeRelInPerc(Statistics.getTimeByDayInWeek(student.getStudyMoments())));
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
						dataArr.put(weekTimes);
						dataArr.put(labelArr);
						weekgraph1.put("data", dataArr);
						weekgraphs.put(weekgraph1);
						week.put("graphs",weekgraphs);
						root.put(week);
						//locatie
						JSONObject loc = new JSONObject();
						loc.put("name", "Locaties");
						JSONArray locgraphs = new JSONArray();
						JSONObject locgraph1 = new JSONObject();
						locgraph1.put("type","text");
						locgraph1.put("data", "test");
						locgraphs.put(locgraph1);
						loc.put("graphs",locgraphs);
						root.put(loc);
					}else{
						//course
						try{
							Course course = CourseRegistry.getCourse(reqCourse);
							ArrayList<StudyMoment> moments = Statistics.filterMomentsByCourse(student.getStudyMoments(), course);
							if(moments.size()==0){
								//write error
								JSONObject err = new JSONObject();
								err.put("name", "Geen gegevens");
								JSONArray graphs = new JSONArray();
								JSONObject graph1 = new JSONObject();
								graph1.put("type","text");
								graph1.put("data", "Nog geen studiemomenten voor dit vak.");
								graphs.put(graph1);
								err.put("graphs",graphs);
								root.put(err);
							}else{
								//week
								JSONObject week = new JSONObject();
								week.put("name", "Tijdsverdeling per week");
								JSONArray weekgraphs = new JSONArray();
								JSONObject weekgraph1 = new JSONObject();
								weekgraph1.put("type","bar");
								JSONArray weekTimes = new JSONArray(makeRelInPerc(Statistics.getTimeByDayInWeek(moments)));
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
								dataArr.put(weekTimes);
								dataArr.put(labelArr);
								weekgraph1.put("data", dataArr);
								weekgraphs.put(weekgraph1);
								week.put("graphs",weekgraphs);
								root.put(week);
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
		for(int j=0;j<input.length;j++)
			ret[j] = input[j]/total*100;
		return ret;
		
	}
}
