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
import dashboard.model.Student;
import dashboard.registry.CourseRegistry;
import dashboard.model.StudyMoment;
import dashboard.model.Course;


public class ManualTrackingServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		
		if(student!=null){
			Date startDate = new Date(session.getAttribute("startdate") +" " + session.getAttribute("starttime"));
			Date endDate = new Date(session.getAttribute("enddate") + " "+ session.getAttribute("endtime"));
			
			int amount = (Integer) session.getAttribute("amount");
			
			Course usedCourse = CourseRegistry.getCourse((String) session.getAttribute("course"));
			
			String kind = (String) session.getAttribute("kind");
			
			try {
				student.addStudyMoment(new StudyMoment(startDate, endDate, usedCourse, amount, kind));
			} catch (InvalidEndDateException e) {
				resp.sendRedirect("/error.jsp?msg=You appear to be a time traveler?!");
			} catch (InvalidAmountException e) {
				resp.sendRedirect("/error.jsp?msg=You can't have studied that kind of pages!");
			} catch (InvalidStudyMomentException e) {
				resp.sendRedirect("/error.jsp?msg=You were already studying at the time!");
			}
			
			
		} else {
			resp.sendRedirect("/login.jsp");
		}
	}
	
}