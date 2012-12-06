package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.InvalidPasswordException;
import dashboard.error.NoSuchCourseException;
import dashboard.model.Student;

public class SettingServlet extends HttpServlet {

	private static final long serialVersionUID = 383365373572564568L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		if(student == null)
			resp.sendRedirect("/login");
		else
			resp.sendRedirect("/jsp/settings/userinfo.jsp");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		String action = req.getParameter("submit");
		if(action.contains("remove_")){
			String vak = action.replace("remove_","");
			remove(vak,student,req,resp);
			session.setAttribute("student",student);
			resp.sendRedirect("/jsp/settings/courses.jsp");
		} else if(action.equals("voeg")){
			resp.sendRedirect("/add_course");
		} else if(action.equals("namechange")){
			String firstName = req.getParameter("firstname");
			String lastName = req.getParameter("lastname");
			if(firstName.equals("") || lastName.equals(""))
				resp.sendRedirect("/jsp/settings/userinfo.jsp?msg=Vul alle velden in!");
			else{
				student.setFirstName(firstName);
				student.setLastName(lastName);
				session.setAttribute("student",student);
				resp.sendRedirect("/jsp/settings/message.jsp?msg=Naam");
			}
		} else if(action.equals("passchange")){
			String pass1 = req.getParameter("pass1");
			String pass2 = req.getParameter("pass2");
			String pass3 = req.getParameter("pass3");
			if(pass1.equals("") || pass2.equals("") || pass3.equals(""))
				resp.sendRedirect("/jsp/settings/message.jsp?msg=Vul alle velden in!");
			else if(student.isCorrectPassword(pass1) && (pass2.equals(pass3))){
				try {
					student.setPassword(pass2);
					session.setAttribute("student",student);
					resp.sendRedirect("/jsp/settings/message.jsp?msg=Password");
				} catch (InvalidPasswordException e) {
					resp.sendRedirect("/jsp/settings/password.jsp?msg=Ongeldig wachtwoord.");;
				}
			} else {
				resp.sendRedirect("/jsp/settings/password.jsp?msg=Foute wachtwoorden.");
			}
		}
	}
	
	private void remove(String vak,Student student,HttpServletRequest req, HttpServletResponse resp) 
			throws IOException{
		try {
			student.removeCourse(vak);
		} catch (NoSuchCourseException e) {
			resp.sendRedirect("/jsp/error.jsp?msg=Dit vak bestond niet.");
		}
	}
}