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

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		
		if(student.getCurrentStudyMoment() == null){
			Course course = new Course("analyse");
			Date start = new Date();
			student.setCurrentStudyMoment(new StudyMoment(start,course));
			session.setAttribute("startTracking", start.toString());
			resp.sendRedirect("/track.jsp?mode=stop");
		} else
			try {
				student.getCurrentStudyMoment().endMoment(new Date(), Integer.parseInt(req.getParameter("amount")),req.getParameter("kind"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (AlreadyEndedException e) {
				e.printStackTrace();
			}
	} 
	
}
