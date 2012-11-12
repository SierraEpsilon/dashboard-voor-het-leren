package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet {

	/**
	 * Called when a user chooses to log out.
	 * @param req 
	 * @param resp 
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession();
		session.setAttribute("student",null);
		resp.sendRedirect("/login.jsp");
	}
}
