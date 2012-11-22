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
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		String action = req.getParameter("submit");
		if(action.contains("remove_")){
			String vak = action.replace("remove_","");
			remove(vak,student,req,resp);
		}
	}
	
	private void remove(String vak,Student student,HttpServletRequest req, HttpServletResponse resp) 
			throws IOException{
		try {
			student.removeCourse(vak);
		} catch (NoSuchCourseException e) {
			resp.sendRedirect("/error.jsp?msg= tried to remove an unexisting course");
		}
	}
}