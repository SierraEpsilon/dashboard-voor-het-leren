package dashboard.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.model.*;
import dashboard.registry.StudentRegistry;

public class TrackingServlet extends HttpServlet{

	private static final long serialVersionUID = -786837324508180891L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		
		if(student.getCurrentStudyMoment() == null){
			Course course = (Course)req.getParameter("course");
			student.setCurrentStudyMoment(new StudyMoment(new Date(),course)));
		} else
			student.getCurrentStudyMoment().endMoment(new Date(), req.getParameter("kind"), req.getParameter("amount"));
	} 
	
}
