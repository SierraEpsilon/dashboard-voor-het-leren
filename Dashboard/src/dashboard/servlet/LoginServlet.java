package dashboard.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.model.OwnOfy;
import dashboard.model.Student;
import dashboard.registry.StudentRegistry;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2516739187837047286L;

	/**
	 * Called when a user tries to log in on the login.jsp page
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		//TODO
		//DIT MOET ER LATER UIT
		//ZEER BELANGERIJK
		if(username.equals("delete") && password.equals("delete"))
			OwnOfy.clearStudents();
		//KIJK HIERBOVEN, DIT MOET ERUIT
		//NIET OVERKIJKEN
		else if(StudentRegistry.isValidlogIn(username, password)){//check whether username and password are correct
			Student user = StudentRegistry.getUserByUserName(username);
			session.setAttribute("student", user);//set the current student to the one who is trying to log in
			resp.sendRedirect("/track.jsp");
		} else {
			resp.sendRedirect("/error.jsp");
		}
	}
}
