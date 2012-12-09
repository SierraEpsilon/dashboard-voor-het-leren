package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.NameAlreadyInUseException;
import dashboard.model.Location;
import dashboard.model.Student;


public class LocationAddServlet extends HttpServlet{
	
	private static final long serialVersionUID = 8568509987539754981L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		if(student == null)
			resp.sendRedirect("/login");
		else
			resp.sendRedirect("/jsp/location/add_location.jsp?std="+ student.getUserName());
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		String name = (String) req.getParameter("namesend");
		double longitude = Double.parseDouble(req.getParameter("longitude"));
		double latitude = Double.parseDouble(req.getParameter("latitude"));
		//assume Google has accuracy of 0 meter
		try{
			student.addStarredLocation(new Location(longitude,latitude,name,0));
			session.setAttribute("student", student);
			resp.sendRedirect("/jsp/location/add_location.jsp?std="+ student.getUserName() + "&&msg=Locatie werd correct toegevoegd.");
		}
		catch (NameAlreadyInUseException e){
			session.setAttribute("student", student);
			resp.sendRedirect("/jsp/location/add_location.jsp?std="+ student.getUserName() + "&&msg=De locatie '" + name + "' bestaat al!");
		}
	}
}