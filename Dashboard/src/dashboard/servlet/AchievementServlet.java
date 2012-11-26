package dashboard.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.achievement.Achievement;
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
			resp.sendRedirect("/login.jsp");
		}
		session.setAttribute("achievementMap", getAchievements(student));
		resp.sendRedirect("/achievements.jsp");
	}
	
	private HashMap<Course,ArrayList<Achievement>> getAchievements(Student student){
		HashMap<Course,ArrayList<Achievement>> achievementMap = new HashMap<Course,ArrayList<Achievement>>();
		Iterator<Course> it = student.getCourseList().iterator();
		while(it.hasNext()){
			Course course = it.next();
			ArrayList<Achievement> tempAchievementList = new ArrayList<Achievement>();
			Iterator<Achievement> ait = AchievementRegistry.getAchievements(student).iterator();
			while(ait.hasNext()){
				Achievement achievement = ait.next();
				if(achievement.getCourse() == course){
					tempAchievementList.add(ait.next());
				}
			}
			achievementMap.put(course,tempAchievementList);
		}
		return achievementMap;
	}
}
