package dashboard.model.achievement;

import dashboard.model.Course;
import dashboard.model.Student;

public interface Achievement {
	
	public Course getCourse();
	
	/**
	 * returns the progress (between 0 and 1) for the achievement
	 * @return
	 */
	public float getProgress(Student student);
}
