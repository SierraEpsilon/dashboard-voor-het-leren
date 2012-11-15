package dashboard.registry;

import java.util.*;

import dashboard.model.Branch;
import dashboard.model.Course;
import dashboard.model.CourseEnum;
import dashboard.model.Student;

public class CourseRegistry {

	private static HashMap<String,Branch> branches = new HashMap<String,Branch>();
	
	
	private CourseRegistry(){
		addBranches();
	}

	/**
	 * add all branches to branches
	 */
	private void addBranches() {
		getBranches().put(Branch.BABI1.getName(),Branch.BABI1);
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
	public static ArrayList<CourseEnum> getBranch(String branch){
		return getBranches().get(branch).getCourses();
	}
}
