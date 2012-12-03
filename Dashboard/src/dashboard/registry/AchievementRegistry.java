package dashboard.registry;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.achievement.*;
import dashboard.util.CalendarToDateConverter;

public class AchievementRegistry {

	private static ArrayList<Achievement> achievementList;
	
	static{
		achievementList = new ArrayList<Achievement>();
		try {
			fillAchievementList();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * fills the achievement list with all possible achievements		
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	private static void fillAchievementList() throws JDOMException, IOException{
		achievementList.addAll(getTimeStudiedAchievements());
		File f = new File("/WEB-INF/achievements.xml");
		Document doc = new SAXBuilder().build(f);
		Element root = doc.getRootElement();
		for(Element ae: root.getChildren("achievement")){
			String id = ae.getChildText("id");
			String name = ae.getChildText("name");
			String desc = ae.getChildText("desc");
			Course course = CourseRegistry.getCourseByID(ae.getChildText("Course"));
			String icon = ae.getChildText("icon");
			switch(ae.getAttributeValue("type")){
			case "TimeStudied": 
				float seconds = Float.parseFloat(ae.getChildText("seconds"));
				achievementList.add(new TimeStudied(id,name,desc,course,icon,seconds));
				break;
			case "StudiedInPeriod":
				Date startDate = readDate(ae, "start");
				Date endDate = readDate(ae, "end");
				achievementList.add(new StudiedInPeriod(id,name,desc,course,icon,startDate,endDate));
				break;
			case "RepeatingStudiedInPeriod":
				Date startDate2 = readDate(ae,"start");
				Date endDate2 = readDate(ae, "end");
				long repeatingTime = getRepeatingTime(ae.getChildText("repeating"));
				achievementList.add(new RepeatingStudiedInPeriod(id,name,desc,course,icon,startDate2,endDate2,repeatingTime));
				break;
			default:
				break;
			}
		}
	}
	
	private static Date readDate(Element ae, String prefix){
		return CalendarToDateConverter.getDate(Integer.parseInt(ae.getChildText(prefix + "year")),
				Integer.parseInt(ae.getChildText(prefix + "month")),Integer.parseInt(ae.getChildText(prefix + "day")),
				Integer.parseInt(prefix + "hour"),Integer.parseInt(prefix + "minute"),Integer.parseInt(prefix + "second"));
	}
	
	private static long getRepeatingTime(String s){
		switch(s){
		case "daily":
			return 86400000l;
		case "weekly":
			return 604800000l;
		case "monthly":
			return 26298000000l;
		case "yearly":
			return 315576000000l;
		default:
			return 0;
		}
	}

	private static ArrayList<TimeStudied> getTimeStudiedAchievements(){
		ArrayList<TimeStudied> timeStudiedList = new ArrayList<TimeStudied>();
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
