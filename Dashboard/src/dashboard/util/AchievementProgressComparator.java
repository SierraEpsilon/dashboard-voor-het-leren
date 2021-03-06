package dashboard.util;

import java.util.ArrayList;
import java.util.Comparator;

import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.model.achievement.Achievement;

public class AchievementProgressComparator implements Comparator<Achievement> {
	
	Student student;
	
	public AchievementProgressComparator(Student student){
		this.student = student;
	}
	
	public int compare(Achievement o1, Achievement o2) {
		float prog1 = o1.getProgress(student);
		float prog2 = o2.getProgress(student);
		if(prog1<prog2){
			return 1;
		} else if(prog1>prog2){
			return -1;
		} else {
			return 0;
		}
	}
}
