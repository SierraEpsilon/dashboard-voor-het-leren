package dashboard.model;

public class User implements Comparable<User>,Cloneable {

	private String name;
	private final String userName;
	private final String mail;
	
	/**
	 * initiates a user
	 * @param 	name
	 * the name you want your student to have
	 * @param	userName
	 * the username you want your user to have
	 * @param	mail
	 * the mail adress you want your user to have
	 * @post
	 * new.getName() = name
	 * @post
	 * new.getUserName() = userName
	 * @post
	 * new.getMail() = mail
	 */
	public User(String name, String userName, String mail){
		this.name = name;
		this.userName = userName;
		this.mail	= mail;
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
	 * @return
	 * the mail address of the user
	 * 	|	mail
	 */
	public String getMail() {
		return mail;
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
		User clonedUser = new User(getName(),getUserName(),getMail());
		return clonedUser;
	}

}
