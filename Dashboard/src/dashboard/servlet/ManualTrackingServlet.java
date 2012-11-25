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
import dashboard.model.*;
import dashboard.registry.CourseRegistry;


public class ManualTrackingServlet extends HttpServlet{
	
	private static final long serialVersionUID = 8568509987539754981L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		if(student == null)
			resp.sendRedirect("/login");
		else
			resp.sendRedirect("/add_manual.jsp");
	}
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		
			Date startDate = new Date();
			try {
				startDate = df.parse((String) req.getParameter("startdate") +" " + (String)req.getParameter("starttime"));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			Date endDate = new Date();
			try {
				endDate = df.parse((String) req.getParameter("enddate") +" " + (String) req.getParameter("endtime"));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
			int amount = (Integer) Integer.parseInt(req.getParameter("amount"));
			
			try {
				Course usedCourse = CourseRegistry.getCourse((String) req.getParameter("courseinput"));
				String kind = (String) req.getParameter("kind");
				student.addStudyMoment(new StudyMoment(startDate, endDate, usedCourse, amount, kind));
				resp.sendRedirect("menu.jsp");
			} catch (InvalidEndDateException e) {
				resp.sendRedirect("/error.jsp?msg=You appear to be a time traveler?!");
			} catch (InvalidAmountException e) {
				resp.sendRedirect("/error.jsp?msg=You can't have studied that kind of pages!");
			} catch (InvalidStudyMomentException e) {
				resp.sendRedirect("/error.jsp?msg=You were already studying at the time!");
			} catch (NoSuchCourseException e1) {
				resp.sendRedirect("/error.jsp?msg=no such course!");
			}
			
	}
}