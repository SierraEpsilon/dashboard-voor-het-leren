package dashboard.registry;

import java.util.*;
import dashboard.model.Course;
import dashboard.model.Student;

public class CourseRegistry {

	private static HashMap<String,HashSet<Course>> branches = new HashMap<String,HashSet<Course>>();
	
	
	private CourseRegistry(){
		branches.put("BaBi1", new HashSet<Course>());
		branches.put("BaBi2", new HashSet<Course>());
		branches.put("CwMa2", new HashSet<Course>());
		branches.put("CwMa3", new HashSet<Course>());
		branches.put("CwMi2", new HashSet<Course>());
		branches.put("CwMi3", new HashSet<Course>());
		branches.put("EtMa2", new HashSet<Course>());
		branches.put("EtMa3", new HashSet<Course>());
		branches.put("EtMi2", new HashSet<Course>());
		branches.put("EtMi3", new HashSet<Course>());
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
