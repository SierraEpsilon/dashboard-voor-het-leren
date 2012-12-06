package dashboard.model.achievement;

import java.util.ArrayList;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;

public class FullCombined extends Combined {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3912736283266787008L;

	public FullCombined(String id, String name, String desc, Course course, String icon,boolean visible, ArrayList<Achievement> achievementList) {
		super(id, name, desc, course, icon, visible, achievementList);
	}

	@Override
	public float getProgress(Student student) {
		return super.getCombinedProgress(student, achievementList);
	}

	@Override
	public float getProgress(ArrayList<StudyMoment> studyMoments) {
		return super.getCombinedProgress(studyMoments, achievementList);
	}
}
