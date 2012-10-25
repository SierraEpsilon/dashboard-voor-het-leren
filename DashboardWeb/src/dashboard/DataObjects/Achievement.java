package dashboard.DataObjects;

import dashboard.Interfaces.IAchievement;

public class Achievement implements IAchievement,Cloneable {

	private final String title;
	
	/**
	 * set up an achievement object
	 * @param 	tittle
	 * 			the title you want your achievement to have
	 * @post	the title has been changed
	 * 		|	setTittle(title)
	 */
	public Achievement(String title){
		this.title = title;
	}
	
	/**
	 * @return	the title of the user
	 * 		|	this.title	
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * clones the achievement
	 * @return	...
	 * 		|	new Achievement(getTitle())
	 */
	public Achievement clone(){
		return new Achievement(getTitle());
	}
}
