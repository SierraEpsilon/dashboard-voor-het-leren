package dashboard.registry;

import java.util.ArrayList;
import java.util.HashMap;

import dashboard.error.NoSuchCourseException;
import dashboard.model.Branch;
import dashboard.model.Course;

public class CourseRegistry {

	private static HashMap<String,Branch> branches;
	private static ArrayList<Course> courses;
	
	
	static{
		branches = new HashMap<String,Branch>();
		courses = new ArrayList<Course>();
		addBranches();
		addCourses();
	}

	/**
	 * add all branches to branches
	 */
	private static void addBranches() {
		branches.put(Branch.BABI1.getName(),Branch.BABI1);
		branches.put(Branch.BABI2.getName(),Branch.BABI2);

	}
	
	private static void addCourses(){
		courses.addAll(Branch.BABI1.getCourses());
		courses.addAll(Branch.BABI2.getCourses());
	}
	
	/**
	 * @return	the branches of the registry
	 * 	|	branches
	 */
	public static HashMap<String, Branch> getBranches() {
		return branches;
	}
	
	/**
	 * @param branch
	 * 	the branch you want to get
	 * @return a set with all courses of that branch
	 * 	|	getBranches().get(branch)
	 */
	public static ArrayList<Course> getBranch(String branch){
		return getBranches().get(branch).getCourses();
	}
	
	public static Course getCourse(String coursename) throws NoSuchCourseException{
		for(Course course: courses){
			if(course.getName().equals(coursename)){
				return course;
			}
		}
		throw new NoSuchCourseException();
	}
	
	public static ArrayList<Course> getAllCourses(){
		return courses;
	}
}
