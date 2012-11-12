package dashboard.model;

public class Course {

	private final String name;
	private final int credit;
	
	/**
	 * initiates a course
	 * @param 	name
	 * 	the name of the course
	 * @param 	credit
	 * 	the credit of the course
	 * @post	
	 * 	the name equals the given name
	 * 	|	new.getName() = name
	 * @post	
	 * 	the credit equals the given credit
	 * 	|	new.getCredit() = credit
	 */
	public Course(String name, int phase, int credit,String[] diploma){
		this.name = name;
		this.credit = credit;
	}
	
	/**
	 * @return
	 * 	returns the name of the course
	 * 	|	name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return
	 * 	returns the credit of the course
	 * 	| credit
	 */
	public int getCredit() {
		return credit;
	}
	
	/**
	 * @return
	 * 	the hours you should study for that particular course
	 * 	|	getCredit()*28
	 */
	public int getHoursNeeded(){
		return getCredit()*28;
	}
}
