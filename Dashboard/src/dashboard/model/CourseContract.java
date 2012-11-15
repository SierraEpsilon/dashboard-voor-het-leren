package dashboard.model;

import java.io.Serializable;

import javax.persistence.Id;

public class CourseContract{

	private Course course;
	
	public CourseContract(Course course){
		this.course = course;
	}
	
	public CourseContract(){
		
	}
	
	/**
	 * @return
	 * 	the course of the student
	 * 	|	course
	 */
	public Course getCourse() {
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
