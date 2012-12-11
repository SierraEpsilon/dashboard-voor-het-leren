package dashboard.model.achievement;

import java.util.ArrayList;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.util.Statistics;

public class TimeStudied extends Achievement {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -4658676420666138891L;
	float seconds;
	
	public TimeStudied(String id, String name, String desc, Course course, String icon, boolean visible, float seconds){
		super(id, name, desc, course, icon, visible);
		this.seconds = seconds;
	}
	
	public float getProgress(Student student) {
		return getProgress(student.getStudyMoments());
	}
	
	public float getProgress(ArrayList<StudyMoment> studyMoments) {
		float progress = 0;
		if(getCourse() == null){
			progress = Statistics.getTotalTime(studyMoments)/seconds;
		} else {
			progress = Statistics.getTime(getCourse(), studyMoments)/seconds;
		}
		if(progress>1){
			progress = 1;
		}
		return progress;
	}
}
