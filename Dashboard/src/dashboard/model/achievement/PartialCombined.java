package dashboard.model.achievement;

import java.util.ArrayList;

import dashboard.model.Course;
import dashboard.model.Student;

public class PartialCombined extends Combined {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2727336172027210942L;
	private int amountRequired;

	public PartialCombined(String id, String name, String desc, Course course, String icon, ArrayList<Achievement> achievementList, int amountRequired) {
		super(id, name, desc, course, icon, achievementList);
		this.amountRequired = amountRequired;
	}

	@Override
	public float getProgress(Student student) {
		return super.getCombinedProgress(student, super.getSortedAchievementList(student).subList(0, amountRequired - 1));
	}
}
