package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = -3978136481562961650L;

	/**
	 * Called when a user chooses to log out.
	 * @param req 
	 * @param resp 
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession();
		session.setAttribute("student",null);
		session.setAttribute("startTracking",null);
		session.setAttribute("course", null);
		resp.sendRedirect("/login.jsp");
	}
}
