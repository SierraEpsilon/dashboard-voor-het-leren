package dashboard.registry;

import java.util.ArrayList;
import java.util.Arrays;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.achievement.*;

public class AchievementRegistry {

	private static ArrayList<Achievement> achievementList;
	
	static{
		fillAchievementList();
	}
	
	/**
	 * fills the achievement list with all possible achievements
	 */
	private static void fillAchievementList(){
		achievementList.addAll(Arrays.asList(TimeStudied.values()));
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
