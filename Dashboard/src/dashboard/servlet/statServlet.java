package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class statServlet extends HttpServlet {

	private static final long serialVersionUID = -6566808443793850095L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("student") != null)
			resp.sendRedirect("/stats.jsp");
		else
			resp.sendRedirect("/error.jsp?msg=not logged in");
	}
}
