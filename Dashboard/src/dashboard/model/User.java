package dashboard.model;

public class User implements Comparable<User>,Cloneable {

	private String name;
	private final String userName;
	
	/**
	 * initiates a user
	 * @param 	name
	 * the name you want your student to have
	 */
	public User(String name, String userName){
		this.name = name;
		this.userName = userName;
	}
	
	/**
	 * @return	
	 * the name of the student
	 * 	|	name
	 */
	public String getName() {
		return name;
	}
	

	/**
	 * @return	
	 * the username of the student
	 * 	|	userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * compares user with other user
	 * @return
	 * 0 if the usernames of both users match
	 * 	|	if(other.getUserName().equals(getUserName()))
	 *	|		return 0
	 * @return
	 * -1 if the usernames of both users match
	 * 	|	if(!other.getUserName().equals(getUserName()))
	 *	|		return -1
	 */
	public int compareTo(User other) {
		if(other.getUserName().equals(getUserName()))
			return 0;
		else
			return -1;
	}
	
	/**
	 * clones the user
	 * @return
	 * a User with the same information as this user
	 * 	|	clonedUser = new User(getName(),getUserName())
	 */
	protected Object clone() throws CloneNotSupportedException {
		User clonedUser = new User(getName(),getUserName());
		return clonedUser;
	}

}
