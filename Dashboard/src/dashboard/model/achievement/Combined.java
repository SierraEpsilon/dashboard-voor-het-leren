package dashboard.model.achievement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.util.AchievementProgressComparator;

abstract class Combined extends Achievement {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2654412861135898693L;
	ArrayList<Achievement> achievementList;
	
	public Combined(String id, String name, String desc, Course course, String icon, boolean visible, ArrayList<Achievement> achievementList) {
		super(id, name, desc, course, icon, visible);
		this.achievementList = achievementList;
	}
	
	public float getCombinedProgress(Student student, List<Achievement> achievementList){
		float progress = 0;
		for(Achievement achievement: achievementList){
			if(student.getCourseList().contains(achievement.getCourse())){
				progress += achievement.getProgress(student)/achievementList.size();
			}
		}
		return progress;
	}
	
	public ArrayList<Achievement> getAchievementList(){
		return achievementList;
	}
	
	public ArrayList<Achievement> getSortedAchievementList(Student student){
		ArrayList<Achievement> sortedAchievementList = new ArrayList<Achievement>(achievementList);
		Collections.sort(sortedAchievementList, new AchievementProgressComparator(student));
		return sortedAchievementList;
	}
}
