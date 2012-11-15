package dashboard.model;

public class CourseContract {
	
	private final CourseEnum course;
	
	public CourseContract(CourseEnum course){
		this.course = course;
	}
	
	/**
	 * @return
	 * 	the course of the student
	 * 	|	course
	 */
	public CourseEnum getCourse() {
		return course;
	}
	
	/**
	 * @param time
	 * 	the time yuo studied
	 * @return
	 * 	the level
	 */
	public int getLevel(int time){
		int nextLevel = 100;
		int level = 0;
		while(time >= 0){
			time -= nextLevel;
			level++;
			if(nextLevel < 500)
				nextLevel = nextLevel + 100/nextLevel;
		}
		return level;
	}
}
