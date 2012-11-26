package dashboard.model.achievement;

import java.io.Serializable;

import dashboard.model.Course;
import dashboard.model.Student;

public abstract class Achievement implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4773912395822659711L;
	String id;
	String name;
	String desc;
	Course course;
	String icon;
	
	protected Achievement(String id, String name, String desc, Course course, String icon){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.course = course;
		this.icon = icon;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public Course getCourse() {
		return course;
	}
	
	public String getIcon(){
		return icon;
	}
	
	/**
	 * returns the progress (between 0 and 1) for the achievement
	 * @return
	 */
	abstract public float getProgress(Student student);
}
