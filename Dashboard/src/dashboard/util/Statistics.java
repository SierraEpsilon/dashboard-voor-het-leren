package dashboard.util;

import java.util.ArrayList;

import java.util.HashMap;

import dashboard.model.*;

public class Statistics {

	/**
	 * @param course
	 * the course you ant to get the time from
	 * @param moments
	 * 	the moments you want to use to get the time from
	 * @return
	 * 	the total time if course was "all"
	 * 	|	getTotalTime()
	 * @return
	 * 	the total time studied for that course
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
	 * 	returns the total time the student has studied
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
	 * 	the moments of a student
	 * @param courses
	 * 	the courses of a student
	 * @return
	 * 	a hashmap filled with the courses and the percentage of time
	 *  put into those courses based on total time
	 */
	public static HashMap<String, Double> getPercents(ArrayList<StudyMoment> moments,ArrayList<CourseContract> courses){
		long total = getTotalTime(moments);
		HashMap<String, Double> result = new HashMap<String, Double>();
		for(CourseContract course: courses){
			long part = getTime(course.getCourse(), moments);
			double percent = part/total;
			result.put(course.getCourse().getName(), percent);
		}
		return result;
	}
}
