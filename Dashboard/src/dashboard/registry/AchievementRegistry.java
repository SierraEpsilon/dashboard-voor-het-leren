package dashboard.registry;
import java.util.ArrayList;
import java.util.Arrays;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.achievement.*;

public class AchievementRegistry {

	private static ArrayList<Achievement> achievementList;
	
	static{
		achievementList = new ArrayList<Achievement>();
		fillAchievementList();
	}
	
	/**
	 * fills the achievement list with all possible achievements		
	 */
	private static void fillAchievementList(){
		ArrayList<TimeStudied> timeStudiedList = new ArrayList<TimeStudied>();
		timeStudiedList.addAll(Arrays.asList(
				new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying noob", "Study for 5 minutes for any course.", null, 300),
				new TimeStudied("TIME_STUDIED_TOTAL_2", "Studying apprentice", "Study for 10 minutes for any course.", null, 600),
				new TimeStudied("TIME_STUDIED_TOTAL_3", "Studying noob", "Study for 15 minutes for any course.", null, 900)));
		achievementList.addAll(timeStudiedList);
	}
	
	/**
	 * returns an arraylist with the achievements applicable to the student
	 * @param student
	 * @return
	 */
	public static ArrayList<Achievement> getAchievements(Student student){
		ArrayList<Course> courseList = student.getCourseList();
		ArrayList<Achievement> personalAchievementList = new ArrayList<Achievement>();
		for(Achievement achievement: achievementList){
			if(achievement.getCourse() == null || courseList.contains(achievement.getCourse())){
				personalAchievementList.add(achievement);
			}
		}
		return personalAchievementList;
	}
}
