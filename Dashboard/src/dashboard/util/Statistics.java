package dashboard.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;

import dashboard.model.Course;
import dashboard.model.CourseContract;
import dashboard.model.Location;
import dashboard.model.StudyMoment;

public class Statistics {

	/**
	 * @param course
	 * the course you ant to get the time from
	 * @param moments
	 * 	the moments you want to use to get the time from
	 * @return
	 * 	the total time studied for that course in seconds
	 * 	|	for(StudyMoment moment : moments))
	 *	|		if(moment.getCourse().getName().equals(course))
	 *	|			time += moment.getTime()
	 */
	public static long getTime(Course course, ArrayList<StudyMoment> moments){
		long time = 0;
		for(StudyMoment moment : moments)
			if(moment.getCourse().equals(course))
				time += moment.getTime();
		return time;
	}

	/**
	 * @param moments
	 * 	the moments you want to use to get the time from
	 * @return
	 * 	returns the total time the student has studied in seconds
	 * 	|	for(StudyMoment moment : moments)
	 *	|	time += moment.getTime()
	 */
	public static long getTotalTime(ArrayList<StudyMoment> moments) {
		long time = 0;
		for(StudyMoment moment : moments)
			time += moment.getTime();
		return time;
	}
	
	/**
	 * @param moments
	 * 	the moments you want to use to get the time from
	 * @return
	 * 	an arrayList with the moments the student studied last week
	 */
	public static ArrayList<StudyMoment> getMomentsWeek(ArrayList<StudyMoment> moments) {
		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(Calendar.YEAR, Calendar.WEEK_OF_YEAR, 1);
		Date lastWeek = calendar.getTime();
		ArrayList<StudyMoment> weekMoments = new ArrayList<StudyMoment>();
		for(StudyMoment moment : moments)
			if(moment.getStart().getTime()-lastWeek.getTime() <= 10080000 &&
			moment.getStart().after(lastWeek))
				weekMoments.add(moment);
		return weekMoments;
	}
	
	/**
	 * @param moments
	 * 	the moments you want to use to get the time from
	 * @return
	 * 	an arrayList with the moments the student studied last week
	 */
	public static ArrayList<StudyMoment> getMomentsMonth(ArrayList<StudyMoment> moments) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.WEEK_OF_MONTH, 1);
		Date lastMonth = calendar.getTime();
		ArrayList<StudyMoment> weekMoments = new ArrayList<StudyMoment>();
		for(StudyMoment moment : moments)
			if(moment.getStart().getTime()-lastMonth.getTime() <= 302400000 &&
			moment.getStart().after(lastMonth))
				weekMoments.add(moment);
		return weekMoments;
	}
	
	/**
	 * @param moments
	 * 	the moments of a student
	 * @param courses
	 * 	the courses of a student
	 * @return
	 * 	a hashmap filled with the courses and the percentage of time
	 *  put into those courses based on total time
	 */
	public static HashMap<String,Long> getCourseTimes(ArrayList<StudyMoment> moments,ArrayList<CourseContract> courses){
		HashMap<String,Long> result = new HashMap<String,Long>();
		for(CourseContract course: courses){
			long part = getTime(course.getCourse(), moments);
			result.put(course.getCourse().getName(), part);
		}
		return result;
	}
	
	/**
	 * @param moments
	 * 	the moments of a student
	 * @param courses
	 * 	the courses of a student
	 * @return
	 * 	a hashmap filled with the courses and the percentage of time
	 *  put into those courses based on total time
	 */
	public static HashMap<String,Long> getCoursePercents(ArrayList<StudyMoment> moments,ArrayList<CourseContract> courses){
		HashMap<String,Long> result = new HashMap<String,Long>();
		long totTime = getTotalTime(moments);
		for(CourseContract course: courses){
			long part = getTime(course.getCourse(), moments);
			long resTime = (part/totTime)*100;
			result.put(course.getCourse().getName(), resTime);
		}
		return result;
	}
	
	/**
	 * @param moments
	 * 	the moments of a student
	 * @return
	 * 	a hashmap filled with days and the corresponding relative amount studied
	 */
	public static HashMap<String,Long> getTimeByDay(ArrayList<StudyMoment> moments){
		HashMap<String, Long> results = new HashMap<String, Long>();
		String dateString = "geen";
		for(StudyMoment moment : moments){
			String newDateString = moment.getStart().toString().substring(0, 10);
			long time = moment.getTime();
			if(dateString.equals(newDateString)){
				time += results.get(newDateString);
				results.remove(newDateString);
			}
			results.put(newDateString, time);		
		}
		return results;
	}
	
	/**
	 * @param moments
	 * 	the moments of a student
	 * @return
	 * 	a hashmap filled with days and the corresponding relative amount studied
	 */
	public static HashMap<String,Long> getTimeByDayInWeek(ArrayList<StudyMoment> moments){
		HashMap<String, Long> results = new HashMap<String, Long>();
		String dateString = "geen";
		for(StudyMoment moment : moments){
			String newDateString = moment.getStart().toString().substring(0,3);
			long time = moment.getTime();
			if(dateString.equals(newDateString)){
				time += results.get(newDateString);
				results.remove(newDateString);
			}
			results.put(newDateString, time);		
		}
		return results;
	}
	
	/**
	 * @param moments
	 * 	the moments of a student
	 * @return
	 * 	a hashmap filled with months and the corresponding relative amount studied
	 */
	public static HashMap<String,Long> getTimeByMonth(ArrayList<StudyMoment> moments){
		HashMap<String, Long> results = new HashMap<String, Long>();
		String dateString = "geen";
		for(StudyMoment moment : moments){
			String newDateString = moment.getStart().toString().substring(4, 7) + moment.getStart().toString().substring(24, 28);
			long time = moment.getTime();
			if(dateString.equals(newDateString)){
				time += results.get(newDateString);
				results.remove(newDateString);
			} else
				dateString = newDateString;			
			results.put(newDateString, time);		
		}
		return results;
	}
	
	/**
	 * @param moments
	 * 	the moments of a student
	 * @return
	 * 	a hashmap filled with locations and the corresponding relative amount studied
	 */
	public static HashMap<Location,Long> getTimeByLoc(ArrayList<StudyMoment> moments){
		return null;
	}
	
	/**
	 * @param moments
	 * 	the moments of a student
	 * @param course
	 * the course to search for
	 * @return
	 * 	an arraylist containing moments belonging to the course
	 */
	public static ArrayList<StudyMoment> filterMomentsByCourse(ArrayList<StudyMoment> moments,Course course){
		ArrayList<StudyMoment> results = new ArrayList<StudyMoment>();
		for(StudyMoment moment : moments){
			if(moment.getCourse().equals(course))
				results.add(moment);
		}
		return results;
	}
	
	
}
