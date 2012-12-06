package dashboard.model;

import java.io.Serializable;

public class CourseContract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5967842487754441840L;
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
	
	public long getNeededExp(int lvl){
		int time = 0;
		int nextTime = 100;
		for(int i = 1; i < lvl; i++){
			time += nextTime;
			if(nextTime < 500)
				nextTime = nextTime + 100/nextTime;
		}
		return time;
	}
	
	public long getTimeUntilNext(long time){
		int lvl = getLevel(time);
		long timeNext = getNeededExp(lvl + 1);
		return timeNext - time;
	}
	
	public long getTimeNeededNext(long time){
		int lvl = getLevel(time);
		long timeNext = getNeededExp(lvl + 1);
		long lvlTime = getNeededExp(lvl);
		return timeNext - lvlTime;
	}
	
	/**
	 * @param time
	 * 	the time you studied
	 * @return
	 * 	the level
	 */
	public int getLevel(long time){
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
	
	public long getPercent(long time){
		return (getTimeUntilNext(time)/getTimeNeededNext(time))*100;
	}
}
