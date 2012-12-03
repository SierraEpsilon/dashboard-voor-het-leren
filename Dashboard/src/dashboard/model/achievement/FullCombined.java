package dashboard.model.achievement;

import java.util.ArrayList;

import dashboard.model.Course;
import dashboard.model.Student;

public class FullCombined extends Combined {

	public FullCombined(String id, String name, String desc, Course course, String icon, ArrayList<Achievement> achievementList) {
		super(id, name, desc, course, icon, achievementList);
	}

	@Override
	public float getProgress(Student student) {
		return super.getCombinedProgress(student, achievementList);
	}
}