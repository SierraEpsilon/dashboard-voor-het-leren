package dashboard.model.achievement;

import dashboard.model.Course;
import dashboard.model.Student;

public abstract class Achievement {
	
	String id;
	String name;
	String desc;
	Course course;
	
	protected Achievement(String id, String name, String desc, Course course){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.course = course;
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
	
	/**
	 * returns the progress (between 0 and 1) for the achievement
	 * @return
	 */
	abstract public float getProgress(Student student);
}