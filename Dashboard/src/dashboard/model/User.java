package dashboard.model;

import dashboard.error.InvalidEmailException;
import dashboard.error.InvalidUserNameException;

public class User implements Comparable<User>,Cloneable {

	private String name;
	private final String userName;
	private final String mail;
	private String password;
	
	/**
	 * initiates a user
	 * @param 	name
	 * the name you want your student to have
	 * @param	userName
	 * the username you want your user to have
	 * @param	mail
	 * the mail adress you want your user to have
	 * @throws InvalidUserNameException 
	 * 	|	(!isValidUserName(userName))
	 * @throws InvalidEmailException 
	 * 	|	(!isValidUserMail(mail))
	 * @effect
	 * setName(name);
	 * @effect
	 * setPassword(passWord);
	 * @post
	 * new.getUserName() = userName
	 * @post
	 * new.getMail() = mail
	 */
	public User(String name, String userName, String mail, String passWord)
			throws InvalidUserNameException, InvalidEmailException{
		if(isValidUserName(userName))
			this.userName = userName;
		else
			throw new InvalidUserNameException();
		if(isValidMail(mail))
			this.mail	= mail;
		else
			throw new InvalidEmailException();
		setName(name);
		setPassword(passWord);
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
	 * @return
	 * the password of the user
	 * 	|	password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param name
	 * the new name of the user
	 * @post	the name was changed
	 * 	|	new.name = name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param password
	 * 	the new password of the user
	 * @post	the password was changed
	 * 	|	new.password = password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * checks the validity of the username
	 * @param userName
	 * 	the username that has to be checked
	 * @return
	 * 	true, if it is a valid username
	 * 	|	(userName.length() > 5) && (userName.length() < 25) &&
	 *	|	(userName.matches("^[a-zA-Z_0-9]+$"))
	 */
	private boolean isValidUserName(String userName){
		return 	(userName.length() > 5) && (userName.length() < 25) &&
				(userName.matches("^[a-zA-Z_0-9]+$"));
	}
	
	/**
	 * checks the validity of the mail address
	 * @param mail
	 * 	the mail address that has to be checked
	 * @return
	 * 	true, if it is a valid mail address
	 * 	|	(mail.contains("@"))
	 */
	private boolean isValidMail(String mail) {
		return	(mail.contains("@"));
	}
	
	/**
	 * @param 	password
	 * 	the password that has to be checked
	 * @return
	 * 	true if the password matches this user's password
	 * 	|	(getPassword().equals(password))
	 */
	public boolean isCorrectPassword(String password){
		return (getPassword().equals(password));
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
		User clonedUser = null;
		try {
			clonedUser = new User(getName(),getUserName(),getMail(),getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clonedUser;
	}

}
