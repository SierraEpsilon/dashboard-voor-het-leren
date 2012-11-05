package dashboard.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dashboard.registry.UserRegistry;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try{
			UserRegistry.login(username,password);
			resp.sendRedirect("/homepage.jsp");
		} catch(Exception e){
			resp.sendRedirect("/error.jsp");
		}
	}
}
