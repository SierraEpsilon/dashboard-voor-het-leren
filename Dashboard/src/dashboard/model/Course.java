package dashboard.model;

public class Course {

	private final String name;
	private final String[] diploma;
	private final int phase;
	private final int credit;
	
	/**
	 * initiates a course
	 * @param 	name
	 * 	the name of the course
	 * @post	
	 * 	the name equals the given name
	 * 	|	new.getName() = name
	 */
	public Course(String name, int phase, int credit,String[] diploma){
		this.name = name;
		this.phase = phase;
		this.credit = credit;
		this.diploma = diploma;
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
	 * 	returns the phase of the course
	 * 	|	phase
	 */
	public int getPhase() {
		return phase;
	}
	
	/**
	 * @return
	 * 	returns the possible diploma's 
	 * 	| diploma
	 */
	public String[] getDiploma() {
		return diploma;
	}
	
	/**
	 * @return
	 * 	the hours you should study for that particular course
	 * 	|	getCredit()*28
	 */
	public int getHoursNeeded(){
		return getCredit()*28;
	}
	
	/**
	 * @param diploma
	 * 	the diploma that has to be checked
	 * @return
	 * 	true if it is a possible diploma
	 * 	|	if(diploma.contains(diploma))
	 * 	|		return true
	 */
	public boolean isMatchingDiploma(String diploma){
		for(String thisDiploma: getDiploma())
			if(thisDiploma.equals(diploma))
				return true;
		return false;
	}
}
