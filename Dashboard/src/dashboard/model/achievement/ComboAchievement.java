package dashboard.model.achievement;

import java.util.ArrayList;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;

public class ComboAchievement extends Achievement {

	private static final long serialVersionUID = 2654412861135898693L;
	private ArrayList<Achievement> achievementList;
	private int needed;

	public ComboAchievement(String id, String name, String desc, Course course,
			String icon, boolean visible,
			ArrayList<Achievement> achievementList, int needed) {
		super(id, name, desc, course, icon, visible);
		this.achievementList = achievementList;
		this.needed = needed;
	}

	public int getNeeded() {
		return needed;
	}

	public ArrayList<Achievement> getAchievementList() {
		return achievementList;
	}

	public float getProgress(Student student) {
		float progress = 0;
		int got = 0;
		for (Achievement achievement : getAchievementList()) {
			float achievementProgress = achievement.getProgress(student);
			if (achievementProgress == 1) {
				got++;
				if (got == getNeeded())
					return 1;
			}
			progress += achievementProgress;
		}
		float x = needed;
		return progress / x;
	}
}
