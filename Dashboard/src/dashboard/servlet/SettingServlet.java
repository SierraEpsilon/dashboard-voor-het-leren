package dashboard.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.*;
import dashboard.registry.StudentRegistry;
import dashboard.model.Student;

public class SettingServlet extends HttpServlet {

	private static final long serialVersionUID = 383365373572564568L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String[] courses = req.getParameter("courses").split(";");
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		student.get
	}
}