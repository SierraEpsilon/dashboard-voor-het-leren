package dashboard.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password = req.getParameter("password");
		
		try{
			userRegistry.addUser(new User(username,email,firstName,lastName,password));
			resp.sendRedirect("/registrationsuccess.jsp");
		} catch (usernameInUseException){
			resp.sendRedirect("/error.jsp");
		} catch (invalidUsernameException){
			resp.sendRedirect("/error.jsp");
		} catch (emailInUseException){
			resp.sendRedirect("/error.jsp");
		} catch (invalidEmailException){
			resp.sendRedirect("/error.jsp");
		} catch (invalidPasswordException){
			resp.sendRedirect("/error.jsp");
		}
	}
}
