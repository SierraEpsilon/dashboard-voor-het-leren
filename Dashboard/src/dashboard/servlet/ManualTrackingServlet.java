package dashboard.servlet;

import java.io.IOException;
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

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		
		if(student!=null){
			Date startD = new Date((String) req.getParameter("startdate") +" " + req.getParameter("starttime"));
			Date endD = new Date((String) req.getParameter("enddate") + " "+ req.getParameter("endtime"));
			int amount = (Integer) Integer.parseInt(req.getParameter("amount"));
			
			try {
				Course usedCourse = CourseRegistry.getCourse((String) req.getParameter("courseinput"));
				String kind = (String) req.getParameter("kind");
				student.addStudyMoment(new StudyMoment(startD, endD, usedCourse, amount, kind));
				resp.sendRedirect("add_manual.jsp");
			} catch (InvalidEndDateException e) {
				resp.sendRedirect("/error.jsp?msg=You appear to be a time traveler?!");
			} catch (InvalidAmountException e) {
				resp.sendRedirect("/error.jsp?msg=You can't have studied that kind of pages!");
			} catch (InvalidStudyMomentException e) {
				resp.sendRedirect("/error.jsp?msg=You were already studying at the time!");
			} catch (NoSuchCourseException e1) {
				resp.sendRedirect("/error.jsp?msg=no such course!");
			}
			
			
		} else {
			resp.sendRedirect("/login.jsp");
		}
	}
}