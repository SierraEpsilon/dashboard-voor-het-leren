package dashboard.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.registry.StudentRegistry;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2516739187837047286L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		
		if(StudentRegistry.isValidlogIn(username, password)){
			session.setAttribute("student", StudentRegistry.getUserByUserName(username));
			resp.sendRedirect("/homepage.jsp");
		} else {
			resp.sendRedirect("/error.jsp");
		}
	}
}
