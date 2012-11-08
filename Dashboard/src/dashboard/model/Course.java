package dashboard.model;

public class Course {

	private final String name;
	
	/**
	 * initiates a course
	 * @param 	name
	 * 	the name of the course
	 * @post	
	 * 	the name equals the given name
	 * 	|	new.getName() = name
	 */
	public Course(String name){
		this.name = name;
	}
	
	/**
	 * @return
	 * 	|	name
	 */
	public String getName() {
		return name;
	}
}
