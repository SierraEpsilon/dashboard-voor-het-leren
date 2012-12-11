package dashboard.registry;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletContext;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import dashboard.error.NoSuchAchievementException;
import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.model.NewAchievement;
import dashboard.model.achievement.*;
import dashboard.util.CalendarToDateConverter;

public class AchievementRegistry {

	private static ArrayList<Achievement> achievementList;
	private static boolean started;
	
	static{
		achievementList = new ArrayList<Achievement>();
	}
	
	/**
	 * fills the achievement list with all possible achievements		
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	private static void fillAchievementList(ServletContext context) throws JDOMException, IOException{
		achievementList.addAll(getTimeStudiedAchievements());
		InputStream is = context.getResourceAsStream("/WEB-INF/achievements.xml");
		Document doc = new SAXBuilder().build(is);
		Element root = doc.getRootElement();
		for(Element ae: root.getChildren("achievement")){
			achievementList.add(getAchievementFromElement(ae));
		}
	}
	
	private static Achievement getAchievementFromElement(Element ae){
		try{
			return getByID(ae.getChildText("id"));
		} catch(NoSuchAchievementException e){
			String id = ae.getChildText("id");
			String name = ae.getChildText("name");
			String desc = ae.getChildText("desc");
			Course course = CourseRegistry.getCourseByID(ae.getChildText("Course"));
			String icon = ae.getChildText("icon");
			boolean visible = ae.getChildText("visible").equals("true");
			String type = ae.getAttributeValue("type");
			if(type.equals("TimeStudied")){ 
				float seconds = Float.parseFloat(ae.getChildText("seconds"));
				return new TimeStudied(id,name,desc,course,icon,visible,seconds);
			} else if(type.equals("StudiedInPeriod")){
				Date startDate = readDate(ae, "start");
				Date endDate = readDate(ae, "end");
				return new StudiedInPeriod(id,name,desc,course,icon,visible,startDate,endDate);
			} else if(type.equals("RepeatingStudiedInPeriod")){
				Date startDate2 = readDate(ae,"start");
				Date endDate2 = readDate(ae, "end");
				int repeatingType = getRepeatingTime(ae.getChildText("repeating"));
				return new RepeatingStudiedInPeriod(id,name,desc,course,icon,visible,startDate2,endDate2,repeatingType);
			} else if(type.equals("FullCombined")){
				ArrayList<Achievement> cAchievementList = new ArrayList<Achievement>();
				for(Element c: ae.getChildren("subachievement")){
					try {
						cAchievementList.add(getByID(c.getText()));
					} catch (NoSuchAchievementException e1) {
						e1.printStackTrace();
					}
				}
				return new FullCombined(id,name,desc,course,icon,visible,cAchievementList);
			} else if(type.equals("PartialCombined")){
				ArrayList<Achievement> cAchievementList = new ArrayList<Achievement>();
				for(Element c: ae.getChildren("subachievement")){
					try{
						cAchievementList.add(getByID(c.getText()));
					} catch (NoSuchAchievementException e1){
						e1.printStackTrace();
					}
				}
				int amount = Integer.parseInt(ae.getChildText("amount"));
				return new PartialCombined(id,name,desc,course,icon,visible,cAchievementList,amount);
			} else if(type.equals("Omni")){
				int amount = Integer.parseInt(ae.getChildText("amount"));
				return new Omni(id,name,desc,course,icon,visible,amount);
			} else {
				return null;
			}
		}
	}
	
	private static NewAchievement newGetAchievementFromElement(Element ae){
		try{
			return getByID(ae.getChildText("id"));
		} catch(NoSuchAchievementException e){
			String id = ae.getChildText("id");
			String name = ae.getChildText("name");
			String desc = ae.getChildText("desc");
			Course course = CourseRegistry.getCourseByID(ae.getChildText("Course"));
			String icon = ae.getChildText("icon");
			boolean visible = ae.getChildText("visible").equals("true");
			NewAchievement achievement = new NewAchievement(id, name, desc, course, icon, visible);
			if(ae.getChildText("combo").equals("true")){
				ArrayList<Achievement> cAchievementList = new ArrayList<Achievement>();
				for(Element c: ae.getChildren("subachievement")){
					try{
						cAchievementList.add(getByID(c.getText()));
					} catch (NoSuchAchievementException e1){
						e1.printStackTrace();
					}
				}
			}
			if(ae.getChildText("needTime").equals("true"))
				achievement.addTimeRequirement(Long.valueOf(ae.getChildText("time")));
			if(ae.getChildText("needNumber").equals("true"))
				achievement.addNumberRequirement(Integer.valueOf(ae.getChildText("number")));
			if(ae.getChildText("needPeriod").equals("true")){
				Date start = readDate(ae, "start");
				Date end = readDate(ae, "end");
				achievement.addPeriodRequirement(start, end);
			}
			if(ae.getChildText("needExpiration").equals("true"))
				achievement.addExpirationRequirement(readDate(ae, "end"));
			if(ae.getChildText("needRepeating").equals("true"))
				achievement.addRepeatingRequirement(ae.getChildText("reccuring"));
			return achievement;
		}
	}
	
	private static Date readDate(Element ae, String prefix){
		Element top = ae.getChild(prefix);
		return CalendarToDateConverter.getDate(Integer.parseInt(top.getChildText("year")),
				Integer.parseInt(top.getChildText("month")),Integer.parseInt(top.getChildText("day")),
				Integer.parseInt(top.getChildText("hour")),Integer.parseInt(top.getChildText("minute")),
				Integer.parseInt(top.getChildText("second")));
	}
	
	private static int getRepeatingTime(String s){
		if(s.equals("daily")){
			return Calendar.DAY_OF_YEAR;
		} else if(s.equals("weekly")){
			return Calendar.WEEK_OF_YEAR;
		} else if(s.equals("monthly")){
			return Calendar.MONTH;
		} else if(s.equals("yearly")){
			return Calendar.YEAR;
		} else {
			return 0;
		}
	}

	private static ArrayList<TimeStudied> getTimeStudiedAchievements(){
		ArrayList<TimeStudied> timeStudiedList = new ArrayList<TimeStudied>();
		Iterator<Course> it = CourseRegistry.getAllCourses().iterator();
		while(it.hasNext()){
			Course course = it.next();
			TimeStudied ts1 = new TimeStudied("TIME_STUDIED_" + course.name() + "_1", course.getName() + " noob", "Studeer " + 2 * course.getCredit() + " minuten voor " + course.getName(), course, "noob.png", true,120 * course.getCredit());
			TimeStudied ts2 = new TimeStudied("TIME_STUDIED_" + course.name() + "_2", course.getName() + " novice", "Studeer " + 10 * course.getCredit() + " minuten voor " + course.getName(), course, "novice.png", true,600 * course.getCredit());
			TimeStudied ts3 = new TimeStudied("TIME_STUDIED_" + course.name() + "_3", course.getName() + " apprentice", "Studeer " + 1 * course.getCredit() + " uur voor " + course.getName(), course, "apprentice.png", true,3600 * course.getCredit());
			TimeStudied ts4 = new TimeStudied("TIME_STUDIED_" + course.name() + "_4", course.getName() + " expert", "Studeer " + 4 * course.getCredit() + " uur voor " + course.getName(), course, "expert.png", true,14400 * course.getCredit());
			TimeStudied ts5 = new TimeStudied("TIME_STUDIED_" + course.name() + "_5", course.getName() + " pro", "Studeer " + 10 * course.getCredit() + " uur voor " + course.getName(), course, "pro.png", true,36000 * course.getCredit());
			TimeStudied ts6 = new TimeStudied("TIME_STUDIED_" + course.name() + "_6", course.getName() + " zombie", "Studeer " + 35 * course.getCredit() + " uur voor " + course.getName(), course, "zombie.png", true,126000 * course.getCredit());
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
	
	public static Achievement getByID(String id) throws NoSuchAchievementException{
		Iterator<Achievement> it = achievementList.iterator();
		while(it.hasNext()){
			Achievement achievement = it.next();
			if(achievement.getId().equals(id)){
				return achievement;
			}
		}
		throw new NoSuchAchievementException();
	}

	public static void init(ServletContext servletContext) {
		started = true;
		try {
			fillAchievementList(servletContext);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean started(){
		return started;
	}
	
	public static ArrayList<Achievement> getChangedAchievements(Student student){
		ArrayList<Achievement> changedAchievements = new ArrayList<Achievement>();
		ArrayList<StudyMoment> newList = student.getStudyMoments();
		ArrayList<StudyMoment> oldList = new ArrayList<StudyMoment>();
		oldList.addAll(newList.subList(0, newList.size() - 1));
		for(Achievement achievement: achievementList){
			if(student.getCourseList().contains(achievement.getCourse())){
				if(achievement.getProgress(oldList)!=achievement.getProgress(newList)){
					changedAchievements.add(achievement);
				}
			}
		}
		return changedAchievements;
	}
}
