package dashboard.servlet;


import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.model.CourseContract;
import dashboard.model.Student;
import dashboard.registry.CourseRegistry;


public class CourseAddServlet extends HttpServlet {
	

	private static final long serialVersionUID = 696129415243839733L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		String action = req.getParameter("submit");
		CourseContract course = new CourseContract(CourseRegistry.getCourse(action));
		student.addCourse(course);
		resp.sendRedirect("/settings.jsp");
	}
			

}
