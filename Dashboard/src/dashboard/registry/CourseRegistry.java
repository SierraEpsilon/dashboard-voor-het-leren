package dashboard.registry;

import java.util.*;

import dashboard.model.Course;
import dashboard.model.Student;

public class CourseRegistry {

	private static HashMap<String,HashSet<Course>> branches = new HashMap<String,HashSet<Course>>();
	
	
	private CourseRegistry(){
		addBranches();
		addCourses();
	}

	/**
	 * add all courses
	 */
	private void addCourses() {
		Course tempCourse = new Course("Algemene en technische scheikunde", 7);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Toegepaste mechanica 1", 5);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Algemene natuurkunde", 7);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Thermodynamica", 3);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Inleiding tot de materiaalkunde", 3);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Elektrische netwerken", 3);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Methodiek van de informatica", 6);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Toegepaste algebra", 5);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Analyse 1", 6);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Analyse 2", 5);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("P&o 1", 4);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("P&o 2", 3);
		branches.get("BaBi1").add(tempCourse);
		tempCourse = new Course("Wijsbegeerte", 3);
		branches.get("BaBi1").add(tempCourse);
		
		tempCourse = new Course("Organische scheikunde", 3);
		branches.get("BaBi2").add(tempCourse);
		tempCourse = new Course("Toegepaste mechanica 2", 5);
		branches.get("BaBi2").add(tempCourse);
		tempCourse = new Course("Informatieoverdracht en -verwerking", 5);
		branches.get("BaBi2").add(tempCourse);
		tempCourse = new Course("Kansrekenen en statistiek", 3);
		branches.get("BaBi2").add(tempCourse);
		tempCourse = new Course("Analyse 3", 3);
		branches.get("BaBi2").add(tempCourse);
		tempCourse = new Course("Numerieke wiskunde", 4);
		branches.get("BaBi2").add(tempCourse);
		tempCourse = new Course("P&o 3", 4);
		branches.get("BaBi2").add(tempCourse);
		tempCourse = new Course("Economie", 3);
		branches.get("BaBi2").add(tempCourse);
	}

	/**
	 * add all branches to branches
	 */
	private void addBranches() {
		branches.put("BaBi1", new HashSet<Course>());
		branches.put("BaBi2", new HashSet<Course>());
		branches.put("BaBkMa2", new HashSet<Course>());
		branches.put("BaBkMa3", new HashSet<Course>());
		branches.put("BaCtMa2", new HashSet<Course>());
		branches.put("BaCtMa3", new HashSet<Course>());
		branches.put("BaCwMa2", new HashSet<Course>());
		branches.put("BaCwMa3", new HashSet<Course>());
		branches.put("BaEtMa2", new HashSet<Course>());
		branches.put("BaEtMa3", new HashSet<Course>());
		branches.put("BaGtMa2", new HashSet<Course>());
		branches.put("BaGtMa3", new HashSet<Course>());
		branches.put("BaMkMa2", new HashSet<Course>());
		branches.put("BaMkMa3", new HashSet<Course>());
		branches.put("BaWkMa2", new HashSet<Course>());
		branches.put("BaWkMa3", new HashSet<Course>());
		branches.put("BaBkMi2", new HashSet<Course>());
		branches.put("BaBkMi3", new HashSet<Course>());
		branches.put("BaCtMi2", new HashSet<Course>());
		branches.put("BaCtMi3", new HashSet<Course>());
		branches.put("BaCwMi2", new HashSet<Course>());
		branches.put("BaCwMi3", new HashSet<Course>());
		branches.put("BaEtMi2", new HashSet<Course>());
		branches.put("BaEtMi3", new HashSet<Course>());
		branches.put("BaGtMi2", new HashSet<Course>());
		branches.put("BaGtMi3", new HashSet<Course>());
		branches.put("BaMkMi2", new HashSet<Course>());
		branches.put("BaMkMi3", new HashSet<Course>());
		branches.put("BaWkMi2", new HashSet<Course>());
		branches.put("BaWkMi3", new HashSet<Course>());
		branches.put("BaLsMi2", new HashSet<Course>());
		branches.put("BaLsMi3", new HashSet<Course>());
		branches.put("BaBbMi2", new HashSet<Course>());
		branches.put("BaBbMi3", new HashSet<Course>());
	}
	
	/**
	 * @return	the branches of the registry
	 * 	|	branches
	 */
	public static HashMap<String, HashSet<Course>> getBranches() {
		return branches;
	}
	
	/**
	 * @param branch
	 * 	the branch you want to get
	 * @return a set with all courses of that branch
	 * 	|	getBranches().get(branch)
	 */
	public static HashSet<Course> getBranch(String branch){
		return getBranches().get(branch);
	}
}
