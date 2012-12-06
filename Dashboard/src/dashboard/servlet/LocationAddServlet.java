package dashboard.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.InvalidAmountException;
import dashboard.error.InvalidEndDateException;
import dashboard.error.InvalidStudyMomentException;
import dashboard.error.NoSuchCourseException;
import dashboard.model.Course;
import dashboard.model.Location;
import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.registry.CourseRegistry;


public class LocationAddServlet extends HttpServlet{
	
	private static final long serialVersionUID = 8568509987539754981L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		if(student == null)
			resp.sendRedirect("/login");
		else
			resp.sendRedirect("/jsp/location/add_location.jsp");
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		String name = (String) req.getParameter("name");
		double longitude = Double.parseDouble(req.getParameter("latitude"));
		double latitude = Double.parseDouble(req.getParameter("longitutde"));
		student.addStarredLocation(new Location(longitude,latitude,name));	
	}
}