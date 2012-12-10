package dashboard.model.achievement;

import java.util.*;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.util.Statistics;

public class Omni extends Achievement {
	
	final int amount;
	
	public Omni(String id, String name, String desc, Course course,
			String icon, boolean visible, int amount) {
		super(id, name, desc, course, icon, visible);
		this.amount = amount;

	}

	public int getAmount() {
		return amount;
	}
	
	private static final long serialVersionUID = 8159895836826470444L;

	@Override
	public float getProgress(Student student) {
		return getProgress(student.getStudyMoments());
	}

	@Override
	public float getProgress(ArrayList<StudyMoment> studyMoments) {
		for(StudyMoment moment : studyMoments){
			HashSet<Course> courses = new HashSet<Course>();
			Date afterDate = new Date(moment.getStart().getTime() + (24*60*60*1000));
			for(StudyMoment moment2: studyMoments){
				if(moment2.getStart().before(afterDate) && moment2.getStart().after(moment.getStart())){
					if(!courses.contains(moment2.getCourse())){
						courses.add(moment2.getCourse());
						if(courses.size() == getAmount())
							return 1;
					}
				}
			}
		}
		return 0;
	}

}
