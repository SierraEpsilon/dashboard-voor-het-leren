package dashboard.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.AlreadyEndedException;
import dashboard.error.InvalidAmountException;
import dashboard.error.InvalidEndDateException;
import dashboard.error.InvalidStudyMomentException;
import dashboard.error.NoSuchCourseException;
import dashboard.error.NotStudyingException;
import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.registry.CourseRegistry;

public class TrackingServlet extends HttpServlet{

	private static final long serialVersionUID = -786837324508180891L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		
		if(student!=null){
			if(student.getCurrentStudyMoment() == null){
				session.setAttribute("startTracking", null);
				session.setAttribute("course", null);
				resp.sendRedirect("/jsp/track/start.jsp");
			} else {
				session.setAttribute("startTracking", student.getCurrentStudyMoment().getStart());
				session.setAttribute("course", student.getCurrentStudyMoment().getCourse());
				resp.sendRedirect("/jsp/track/stop.jsp");
			}
		} else {
			resp.sendRedirect("/jsp/login/login.jsp");
		}
	}
	/**
	 * Called when a user starts or stops tracking a study moment
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");//get the current user
		
		if(req.getParameter("submit").equals("Start") && student.getCurrentStudyMoment() == null){//if the student is not studying yet
			Course course = null;
			try {
				course = CourseRegistry.getCourse((String) req.getParameter("courseinput"));
			} catch (NoSuchCourseException e) {
				e.printStackTrace();
			}
			Date start = new Date();
			student.setCurrentStudyMoment(new StudyMoment(start,course));//create a new study moment
			session.setAttribute("startTracking", start);
			session.setAttribute("course", course);
			resp.sendRedirect("/jsp/track/stop.jsp");
		} else {//if the student was already studying
			if(req.getParameter("submit").equals("Stop")){
				try {
					student.endStudying(new Date(), Integer.parseInt(req.getParameter("amount")),req.getParameter("kind"));
					session.setAttribute("startTracking", null);
					session.setAttribute("course", null);
					resp.sendRedirect("/track");//end the current studymoment
				} catch (NumberFormatException e) {
					resp.sendRedirect("/jsp/error.jsp?msg=That's no integer");
				} catch (AlreadyEndedException e) {
					resp.sendRedirect("/jsp/error.jsp?msg=Oops! you already stopped that moment");
				} catch (InvalidEndDateException e) {
					resp.sendRedirect("/jsp/error.jsp?msg=You appear to be a time traveler?!");
				} catch (InvalidAmountException e) {
					resp.sendRedirect("/jsp/error.jsp?msg=You can't have studied that kind of pages!");
				} catch (InvalidStudyMomentException e) {
					resp.sendRedirect("/jsp/error.jsp?msg=Overlap");
					e.printStackTrace();
				}
			} else if(req.getParameter("submit").equals("Cancel")){//cancel the study moment
				try {
					student.cancelCurrentStudyMoment();
					session.setAttribute("startTracking", null);
					session.setAttribute("course", null);
					resp.sendRedirect("/track");
				} catch (NotStudyingException e) {
					resp.sendRedirect("/jsp/error.jsp?msg=Awkward, you were not studying!");
				}
			} else {
				resp.sendRedirect("/jsp/error.jsp");
			}
		}
	} 
	
}
