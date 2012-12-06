package dashboard.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.registry.StudentRegistry;
import dashboard.util.RegistryInitializer;


public class LiveFeedServlet extends HttpServlet {
	

	private static final long serialVersionUID = 696129415243839733L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(!RegistryInitializer.initialized()){
			RegistryInitializer.initialize(session.getServletContext());
		}
		Student currentStudent = (Student)session.getAttribute("student");
		if(currentStudent!=null){
			Course course = currentStudent.getCurrentStudyMoment().getCourse();
			
			JSONObject obj=new JSONObject();
			try {
				if(course!=null)
					obj.put("studyMates", StudentRegistry.getActiveUsersbyCourse(course).size());
				obj.put("AllMates", StudentRegistry.getActiveUsers().size());
				resp.setContentType("application/json");
				PrintWriter out = resp.getWriter();
				out.print(obj);
				out.flush();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			

}
