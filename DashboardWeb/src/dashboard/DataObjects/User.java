package dashboard.DataObjects;

import java.util.ArrayList;
import dashboard.Interfaces.IUser;

public class User implements IUser,Cloneable{

	private final String userName;
	private String password;
	private ArrayList<Vak> vakken;
	
	/**
	 * initiates a User object
	 * @param 	userName
	 * 			the username of the User
	 * @param 	password
	 * 			the password of the user
	 * @post	password becomes the password of the user
	 * 		|	setPassword(password)
	 * @post	userName becomes the username of the user
	 * 		|	setUserName(userName)
	 */
	public User(String userName,String password){
		this.userName = userName;
		this.password = password;
		vakken = new ArrayList<Vak>();
	}
	
	/**
	 * @return	...
	 * 		|	this.password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @return	...
	 * 		|	this.userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @return	...
	 * 		|	vakken
	 */
	public ArrayList<Vak> getVakken() {
		return vakken;
	}
	
	/**
	 * changes the password of the user
	 * @param 	password
	 * 			password it should change to
	 * @post	the password has been changed
	 * 		|	this.password = password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * clones the user
	 * @return	...
	 * 		|	new User(getUserName(), getPassword())
	 */
	public User clone(){
		return new User(getUserName(), getPassword());
	}
	
	/**
	 * @param 	vak
	 * 			the branch you want to add
	 * @post	...
	 * 		|	getVakken().contains(vak)
	 */
	public void addVak(Vak vak){
		getVakken().add(vak);
	}
}
