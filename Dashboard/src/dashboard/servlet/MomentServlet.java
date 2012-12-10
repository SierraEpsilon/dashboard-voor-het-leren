package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.InvalidPasswordException;
import dashboard.error.NoSuchCourseException;
import dashboard.model.Student;
import dashboard.util.RegistryInitializer;

public class MomentServlet extends HttpServlet {

	private static final long serialVersionUID = 383365373572664568L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(!RegistryInitializer.initialized()){
			RegistryInitializer.initialize(session.getServletContext());
		}
		Student student = (Student)session.getAttribute("student");
		if(student == null)
			resp.sendRedirect("/login");
		else
			resp.sendRedirect("/jsp/moments/list.jsp");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		String action = req.getParameter("submit");
		if(action.contains("remove_")){
			String momentString = action.replace("remove_","");
			student.removeMoment(momentString);
			session.setAttribute("student",student);
			resp.sendRedirect("/jsp/moments/list.jsp");
		} 
	}
}