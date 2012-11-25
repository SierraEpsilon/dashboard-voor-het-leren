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
				new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying noob", "Study for 15 minutes for any course.", null, 900),
				new TimeStudied("TIME_STUDIED_TOTAL_2", "Studying apprentice", "Study for 60 minutes for any course.", null, 3600),
				new TimeStudied("TIME_STUDIED_TOTAL_3", "Studying pro", "Study for 180 minutes for any course.", null, 10800),
				new TimeStudied("TIME_STUDIED_ANALYSE1_1", "Analyse 1 noob", "Study for 5 minutes for Analyse 1.", Course.H01A0B, 300),
				new TimeStudied("TIME_STUDIED_ANALYSE1_2", "Analyse 1 apprentice", "Study for 10 minutes for Analyse 1.", Course.H01A0B, 600),
				new TimeStudied("TIME_STUDIED_ANALYSE1_3", "Analyse 1 pro", "Study for 15 minutes for Analyse 1.", Course.H01A0B, 900)));
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
