package dashboard.registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
		achievementList.addAll(getStudiedInPeriodAchievements());
		achievementList.addAll(getTimeStudiedAchievements());
	}
	
	private static ArrayList<StudiedInPeriod> getStudiedInPeriodAchievements() {
		ArrayList<StudiedInPeriod> studiedInPeriodList = new ArrayList<StudiedInPeriod>();
		
		StudiedInPeriod sip1 = new StudiedInPeriod("STUDIED_IN_PERIOD_1", "Christmas student", "Studeer op kerstmis 2012", null, "noob.png", new Date(1356390000000l), new Date(1356476400000l));
		StudiedInPeriod sip2 = new StudiedInPeriod("STUDIED_IN_PERIOD_2", "Christmas student old", "Studeer op kerstmis 2011", null, "noob.png", new Date(1324767600000l), new Date(1324854000000l));
		studiedInPeriodList.addAll(Arrays.asList(sip1,sip2));
		
		return studiedInPeriodList;
	}

	private static ArrayList<TimeStudied> getTimeStudiedAchievements(){
		ArrayList<TimeStudied> timeStudiedList = new ArrayList<TimeStudied>();
		
		TimeStudied tst1 = new TimeStudied("TIME_STUDIED_TOTAL_1", "Studying noob", "Studeer 30 minuten in totaal", null, "noob.png",1800);
		TimeStudied tst2 = new TimeStudied("TIME_STUDIED_TOTAL_2", "Studying novice", "Studeer 3 uur in totaal", null, "novice.png",10800);
		TimeStudied tst3 = new TimeStudied("TIME_STUDIED_TOTAL_3", "Studying apprentice", "Studeer 12 uur in totaal", null, "apprentice.png",43200);
		TimeStudied tst4 = new TimeStudied("TIME_STUDIED_TOTAL_4", "Studying expert", "Studeer 60 uur in totaal", null, "expert.png",216000);
		TimeStudied tst5 = new TimeStudied("TIME_STUDIED_TOTAL_5", "Studying pro", "Studeer 120 uur in totaal", null, "pro.png",432000);
		TimeStudied tst6 = new TimeStudied("TIME_STUDIED_TOTAL_6", "Studying zombie", "Studeer 600 uur in totaal", null, "zombie.png",2160000);
		timeStudiedList.addAll(Arrays.asList(tst1,tst2,tst3,tst4,tst5,tst6));
		
		Iterator<Course> it = CourseRegistry.getAllCourses().iterator();
		while(it.hasNext()){
			Course course = it.next();
			TimeStudied ts1 = new TimeStudied("TIME_STUDIED_" + course.name() + "_1", course.getName() + " noob", "Studeer " + 2 * course.getCredit() + " minuten voor " + course.getName(), course, "noob.png",120 * course.getCredit());
			TimeStudied ts2 = new TimeStudied("TIME_STUDIED_" + course.name() + "_2", course.getName() + " novice", "Studeer " + 10 * course.getCredit() + " minuten voor " + course.getName(), course, "novice.png",600 * course.getCredit());
			TimeStudied ts3 = new TimeStudied("TIME_STUDIED_" + course.name() + "_3", course.getName() + " apprentice", "Studeer " + 1 * course.getCredit() + " uur voor " + course.getName(), course, "apprentice.png",3600 * course.getCredit());
			TimeStudied ts4 = new TimeStudied("TIME_STUDIED_" + course.name() + "_4", course.getName() + " expert", "Studeer " + 4 * course.getCredit() + " uur voor " + course.getName(), course, "expert.png",14400 * course.getCredit());
			TimeStudied ts5 = new TimeStudied("TIME_STUDIED_" + course.name() + "_5", course.getName() + " pro", "Studeer " + 10 * course.getCredit() + " uur voor " + course.getName(), course, "pro.png",36000 * course.getCredit());
			TimeStudied ts6 = new TimeStudied("TIME_STUDIED_" + course.name() + "_6", course.getName() + " zombie", "Studeer " + 35 * course.getCredit() + " uur voor " + course.getName(), course, "zombie.png",126000 * course.getCredit());
			timeStudiedList.addAll(Arrays.asList(ts1,ts2,ts3,ts4,ts5,ts6));
		}
		return timeStudiedList;
	}
	
	/**
	 * returns a hashmap containing a course key corresponding to an ArrayList containing all achievements bound to that course applicable to the student
	 * @param student
	 * @return
	 */
	public static HashMap<Course,ArrayList<Achievement>> getAchievements(Student student){
		HashMap<Course,ArrayList<Achievement>> achievementMap = new HashMap<Course,ArrayList<Achievement>>();
		ArrayList<Achievement> noCourseAchievementList = new ArrayList<Achievement>();
		for(Achievement achievement: achievementList){
			if(achievement.getCourse()==null){
				noCourseAchievementList.add(achievement);
			} else if(student.getCourseList().contains(achievement.getCourse())){
				if(achievementMap.containsKey((achievement).getCourse())){
					achievementMap.get(achievement.getCourse()).add(achievement);
				} else {
					ArrayList<Achievement> newAchievementList = new ArrayList<Achievement>();
					newAchievementList.add(achievement);
					achievementMap.put(achievement.getCourse(),newAchievementList);
				}
			}
		}
		achievementMap.put(null,noCourseAchievementList);
		return achievementMap;
	}
	
	public static ArrayList<Achievement> getCompletedAchievements(Student student){
		ArrayList<Achievement> completedAchievementList = new ArrayList<Achievement>();
		for(Achievement achievement: achievementList){
			if(achievement.getProgress(student)>=1){
				completedAchievementList.add(achievement);
			}
		}
		return completedAchievementList;
	}
	
	public static Achievement getByID(String id){
		Iterator<Achievement> it = achievementList.iterator();
		while(it.hasNext()){
			Achievement achievement = it.next();
			if(achievement.getId().equals(id)){
				return achievement;
			}
		}
		return null;
	}
}
