package dashboard.registry;

import java.util.HashSet;
import dashboard.model.User;

public class UserRegistry {

	private HashSet<User> users;
	
	/**
	 * @post
	 * 	the users set has been made
	 * 	|	new.users = new HashMap<User>()
	 */
	public UserRegistry(){
		users = new HashSet<User>();
	}
	
	/**
	 * @return	the users of the userregistry
	 * 	|	users
	 */
	public HashSet<User> getUsers() {
		return users;
	}
	
	/**
	 * @param 	name
	 * 	the name of the user you need
	 * @return
	 *	the user with the same name
	 *	|	if(user.contains(user.getName().equals(name)))
	 *	|		return user
	 * @return
	 * 	null if no such user exist
	 * 	|	if(!user.contains(user.getName().equals(name)))
	 *	|		return null
	 */
	public User getUserByName(String name){
		for(User user: getUsers())
			if(user.getName().equals(name))
				return user;
		return null;
	}
	
	/**
	 * @param 	mail
	 * 	the mail address of the user you need
	 * @return
	 *	the user with the same mail address
	 *	|	if(user.contains(user.getMail().equals(mail)))
	 *	|		return user
	 * @return
	 * 	null if no such user exist
	 * 	|	if(!user.contains(user.getMail().equals(mail)))
	 *	|		return null
	 */
	public User getUserByMail(String mail){
		for(User user: getUsers())
			if(user.getMail().equals(mail))
				return user;
		return null;
	}
	
}
