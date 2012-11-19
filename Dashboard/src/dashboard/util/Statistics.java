package dashboard.util;

import java.util.ArrayList;

import dashboard.model.StudyMoment;

public class Statistics {

	/**
	 * @param course
	 * the course you ant to get the time from
	 * @return
	 * 	the total time if course was "all"
	 * 	|	getTotalTime()
	 * @return
	 * 	the total time studied for that course
	 * 	|	for(StudyMoment moment : moments))
	 *	|		if(moment.getCourse().getName().equals(course))
	 *	|			time += moment.getTime()
	 */
	public long getTime(String course, ArrayList<StudyMoment> moments){
		long time = 0;
		if(course.equals("all"))
			time = getTotalTime();
		for(StudyMoment moment : moments)
			if(moment.getCourse().getName().equals(course))
				time += moment.getTime();
	}

	/**
	 * @return
	 * 	returns the total time the student has studied
	 * 	|	for(StudyMoment moment : getStudyMoments())
	 *	|	time += moment.getTime()
	 */
	private long getTotalTime() {
		long time = 0;
		for(StudyMoment moment : getStudyMoments())
			time += moment.getTime();
		return time;
	}
	
}
