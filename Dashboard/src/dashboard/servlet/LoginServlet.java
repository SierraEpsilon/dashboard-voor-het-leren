package dashboard.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dashboard.error.InvalidLogInDataException;
import dashboard.registry.UserRegistry;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if(UserRegistry.isValidlogIn(username, password))
			resp.sendRedirect("/homepage.jsp");
		else
			resp.sendRedirect("/error.jsp");
	}
}
