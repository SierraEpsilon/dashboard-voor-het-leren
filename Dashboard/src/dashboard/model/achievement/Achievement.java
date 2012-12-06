package dashboard.model.achievement;

import java.io.Serializable;
import java.util.ArrayList;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;

public abstract class Achievement implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4773912395822659711L;
	protected String id;
	protected String name;
	protected String desc;
	protected Course course;
	protected String icon;
	protected boolean visible;
	
	protected Achievement(String id, String name, String desc, Course course, String icon, boolean visible){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.course = course;
		this.icon = icon;
		this.visible = visible;
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
	abstract public float getProgress(ArrayList<StudyMoment> studyMoments);
}
