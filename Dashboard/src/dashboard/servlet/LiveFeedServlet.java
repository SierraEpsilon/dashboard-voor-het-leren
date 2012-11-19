package dashboard.servlet;

import java.util.*;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.registry.StudentRegistry;
import dashboard.util.OwnOfy;


public class LiveFeedServlet extends HttpServlet {
	

	private static final long serialVersionUID = 696129415243839733L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student currentStudent = (Student)session.getAttribute("student");
		Course course = currentStudent.getCurrentStudyMoment().getCourse();
		
		session.setAttribute("studyMates", StudentRegistry.getActiveUsersbyCourse(course).size());
		
	
	}
			

}
