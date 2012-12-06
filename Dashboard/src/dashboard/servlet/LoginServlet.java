package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.model.Student;
import dashboard.registry.StudentRegistry;
import dashboard.util.RegistryInitializer;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2516739187837047286L;

	/**
	 * Called when a user tries to log in on the login.jsp page
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(!RegistryInitializer.initialized()){
			RegistryInitializer.initialize(session.getServletContext());
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(StudentRegistry.isValidlogIn(username, password)){//check whether username and password are correct
			Student user = StudentRegistry.getUserByUserName(username);
			if(user==null){
				user = StudentRegistry.getUserByMail(username);
			}
			session.setAttribute("student", user);//set the current student to the one who is trying to log in
			if(user.getCourses().isEmpty()){
				session.setAttribute("student_temp", user);
				resp.sendRedirect("/jsp/login/warning.jsp");
			} else 
				resp.sendRedirect("/track");
		} else {
			resp.sendRedirect("/jsp/login/login.jsp?msg=De opgegeven combinatie van gebruikersnaam en paswoord is niet geldig.");
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("student") != null)
			resp.sendRedirect("/track");
		else
			resp.sendRedirect("/jsp/login/login.jsp");
	}
	
}
