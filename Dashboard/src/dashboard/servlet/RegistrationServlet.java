package dashboard.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dashboard.error.*;

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
		} catch (UserNameInUseException e){
			resp.sendRedirect("/error.jsp");
		} catch (InvalidUserNameException e){
			resp.sendRedirect("/error.jsp");
		} catch (EmailInUseException e){
			resp.sendRedirect("/error.jsp");
		} catch (InvalidEmailException e){
			resp.sendRedirect("/error.jsp");
		} catch (InvalidPasswordException e){
			resp.sendRedirect("/error.jsp");
		}
	}
}
