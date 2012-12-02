package dashboard.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.registry.StudentRegistry;
import dashboard.util.Statistics;

public class StatServlet extends HttpServlet {

	private static final long serialVersionUID = -6566808443793850095L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		if(student==null){
			resp.sendRedirect("/login.jsp?msg=Beveiligde pagina");
		}else{
			String reqCourse = req.getParameter("course");
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
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter writer = resp.getWriter();        
	        writer.println(root.toString());
		}
	}
	public JSONArray hashToArray(Map mp){
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
}
