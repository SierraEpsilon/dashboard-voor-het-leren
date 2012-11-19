package dashboard.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.*;
import dashboard.registry.CourseRegistry;
import dashboard.registry.StudentRegistry;
import dashboard.model.CourseContract;
import dashboard.model.Student;

public class SettingServlet extends HttpServlet {

	private static final long serialVersionUID = 383365373572564568L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String[] courses = req.getParameter("courses").split(";");
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		ArrayList<CourseContract> courseList = new ArrayList<CourseContract>();
		for(int i = 0; i < courses.length; i++)
			courseList.add(new CourseContract(CourseRegistry.getCourse(courses[i])));
		student.setCourses(courseList);
		resp.sendRedirect("/track");
	}
}