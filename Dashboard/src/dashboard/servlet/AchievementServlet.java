package dashboard.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.model.Student;
import dashboard.registry.AchievementRegistry;

public class AchievementServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6149032334789839087L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession();
		Student student = (Student)session.getAttribute("student");
		if(student==null){
			resp.sendRedirect("/jsp/login/login.jsp");
		}
		session.setAttribute("achievementMap", AchievementRegistry.getAchievements(student));
		resp.sendRedirect("/jsp/achievement/list.jsp");
	}
}
