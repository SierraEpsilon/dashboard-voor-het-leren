package dashboard.registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
		achievementList.addAll(getTimeStudiedAchievements());
	}
	
	private static ArrayList<TimeStudied> getTimeStudiedAchievements(){
		ArrayList<TimeStudied> timeStudiedList = new ArrayList<TimeStudied>();
		
		TimeStudied tst1 = new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying noob", "Studeer 30 minuten in totaal", null, 1800);
		TimeStudied tst2 = new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying novice", "Studeer 3 uur in totaal", null, 10800);
		TimeStudied tst3 = new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying apprentice", "Studeer 12 uur in totaal", null, 43200);
		TimeStudied tst4 = new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying journeyman", "Studeer 60 uur in totaal", null, 216000);
		TimeStudied tst5 = new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying master", "Studeer 120 uur in totaal", null, 432000);
		TimeStudied tst6 = new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying pro", "Studeer 600 uur in totaal", null, 2160000);
		timeStudiedList.addAll(Arrays.asList(tst1,tst2,tst3,tst4,tst5,tst6));
		
		Iterator<Course> it = CourseRegistry.getAllCourses().iterator();
		while(it.hasNext()){
			Course course = it.next();
			TimeStudied ts1 = new TimeStudied("TIME_STUDIED_" + course.name() + "_1", course.getName() + " noob", "Studeer 5 minuten voor " + course.getName(), course, 300);
			TimeStudied ts2 = new TimeStudied("TIME_STUDIED_" + course.name() + "_2", course.getName() + " novice", "Studeer 30 minuten voor " + course.getName(), course, 1800);
			TimeStudied ts3 = new TimeStudied("TIME_STUDIED_" + course.name() + "_3", course.getName() + " apprentice", "Studeer 2 uur voor " + course.getName(), course, 7200);
			TimeStudied ts4 = new TimeStudied("TIME_STUDIED_" + course.name() + "_4", course.getName() + " journeyman", "Studeer 10 uur voor " + course.getName(), course, 36000);
			TimeStudied ts5 = new TimeStudied("TIME_STUDIED_" + course.name() + "_5", course.getName() + " master", "Studeer 20 uur voor " + course.getName(), course, 72000);
			TimeStudied ts6 = new TimeStudied("TIME_STUDIED_" + course.name() + "_6", course.getName() + " pro", "Studeer 100 uur voor " + course.getName(), course, 360000);
			timeStudiedList.addAll(Arrays.asList(ts1,ts2,ts3,ts4,ts5,ts6));
		}
		return timeStudiedList;
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
