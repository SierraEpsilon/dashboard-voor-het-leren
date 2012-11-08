package dashboard.registry;

import java.util.HashSet;
import dashboard.model.Course;
import dashboard.model.Student;

public class CourseRegistry {

	private static HashSet<Course> courses = new HashSet<Course>();
	
	private CourseRegistry(){
		courses.add(new Course("Analyse I"));
	}
	
	/**
	 * @return	the courses of the registry
	 * 	|	courses
	 */
	public static HashSet<Course> getCourses() {
		return courses;
	}
	
	/**
	 * @param name
	 * 	the name of the course
	 * @return	the course with the given name if it exists
	 * 	|	if(geCourses().contains(course.getName().equals(name)))
	 * 	|	return course
	 * @return	null, if there is no such course
	 * 	|	if(!geCourses().contains(course.getName().equals(name)))
	 * 	|	return null
	 */
	public static Course getCourse(String name){
		for(Course course : getCourses())
			if(course.getName().equals(name))
				return course;
		return null;
	}
}
