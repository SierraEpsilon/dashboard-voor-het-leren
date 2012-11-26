package dashboard.model.achievement;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.util.Statistics;

public class TimeStudied extends Achievement {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -4658676420666138891L;
	float seconds;
	
	public TimeStudied(String id, String name, String desc, Course course, float seconds, String icon){
		super(id, name, desc, course, icon);
		this.seconds = seconds;
	}
	
	public float getProgress(Student student) {
		float progress = 0;
		if(course == null){
			progress = Statistics.getTotalTime(student.getStudyMoments())/seconds;
		} else {
			progress = Statistics.getTime(course, student.getStudyMoments())/seconds;
		}
		if(progress>1){
			progress = 1;
		}
		return progress;
	}
}
