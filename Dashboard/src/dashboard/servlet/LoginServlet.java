package dashboard.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try{
			userRegistry.login(username,password);
			resp.sendRedirect("/homepage.jsp");
		} catch(usernameNotFoundException){
			resp.sendRedirect("/error.jsp");
		} catch(wrongPasswordException){
			resp.sendRedirect("/error.jsp");
		}
	}
}
