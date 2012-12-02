package dashboard.util;

import java.util.ArrayList;
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
		Calendar.getInstance().setTime(new java.util.Date());
		Calendar.getInstance();
		Calendar.getInstance();
		Calendar.getInstance().setWeekDate(Calendar.YEAR, Calendar.WEEK_OF_YEAR, 1);
		java.util.Date lastWeek = Calendar.getInstance().getTime();
		ArrayList<StudyMoment> weekMoments = new ArrayList<StudyMoment>();
		for(StudyMoment moment : moments)
			if(moment.getStart().after(lastWeek))
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
		java.util.Date lastMonth = new java.util.Date();
		lastMonth = new java.util.Date(lastMonth.getTime() - 604800000);
		ArrayList<StudyMoment> weekMoments = new ArrayList<StudyMoment>();
		for(StudyMoment moment : moments)
			if(moment.getStart().after(lastMonth))
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
	 * @return
	 * 	a hashmap filled with days and the corresponding relative amount studied
	 */
	public static HashMap<String,Long> getTimeByDay(ArrayList<StudyMoment> moments){
		return null;
	}
	
	/**
	 * @param moments
	 * 	the moments of a student
	 * @return
	 * 	a hashmap filled with months and the corresponding relative amount studied
	 */
	public static HashMap<String,Long> getTimeByMonth(ArrayList<StudyMoment> moments){
		return null;
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
	public static HashMap<String,Long> filterMomentsByCourse(ArrayList<StudyMoment> moments,Course course){
		return null;
	}
	
	
}
