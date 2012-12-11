package dashboard.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.util.AchievementProgressComparator;

public class NewCombined extends NewAchievement {
	
	private static final long serialVersionUID = 2654412861135898693L;
	private ArrayList<NewAchievement> achievementList;
	private int needed;
	
	public NewCombined(String id, String name, String desc, Course course,
			String icon, boolean visible,ArrayList<NewAchievement> achievementList, 
			int needed) {
		super(id, name, desc, course, icon, visible);
		this.achievementList = achievementList;
		this.needed = needed;
	}
	
	public int getNeeded() {
		return needed;
	}
	
	public ArrayList<NewAchievement> getAchievementList(){
		return achievementList;
	}

	public float getProgress(Student student){
		return getProgress(student.getStudyMoments());
	}
	
	public float getProgress(ArrayList<StudyMoment> studyMoments){
		float progress = 0;
		int got = 0;
		for(NewAchievement achievement: getAchievementList()){
			float achievementProgress = achievement.getProgress(studyMoments);
			if(achievementProgress == 1){
				got++;
				if(got == getNeeded())
					return 1;
			}
			progress += achievementProgress;
		}
		return progress/needed;
	}
}
