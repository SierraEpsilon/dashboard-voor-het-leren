package dashboard.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.error.AlreadyEndedException;
import dashboard.model.*;
import dashboard.registry.StudentRegistry;

public class TrackingServlet extends HttpServlet{

	private static final long serialVersionUID = -786837324508180891L;

	/**
	 * Called when a user starts or stops tracking a study moment
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");//get the current user
		
		if(student.getCurrentStudyMoment() == null){//if the student is not studying yet
			Course course = new Course("analyse");
			Date start = new Date();
			student.setCurrentStudyMoment(new StudyMoment(start,course));//create a new study moment
			session.setAttribute("startTracking", start.toString());
			resp.sendRedirect("/track.jsp?mode=stop");
		} else {//if the student was already studying
			try {
				student.getCurrentStudyMoment().endMoment(new Date(), Integer.parseInt(req.getParameter("amount")),req.getParameter("kind"));//end the current studymoment
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (AlreadyEndedException e) {
				e.printStackTrace();
			}
		}
	} 
	
}
