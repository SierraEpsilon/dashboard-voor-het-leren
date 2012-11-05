package dashboard.registry;

import java.util.HashSet;

import dashboard.error.EmailInUseException;
import dashboard.error.UserNameInUseException;
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
	public User getUserByUserName(String name){
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
	
	/**
	 * @param 	mail
	 * 	the mail you are looking availability for
	 * @return
	 * 	true if the mail dosen't exist
	 * 	|	(getUserByMail(mail) != null)
	 */
	private boolean isMailExisting(String mail){
		return (getUserByMail(mail) != null);
	}
	
	/**
	 * @param 	userName
	 * 	the userName you are looking availability for
	 * @return
	 * 	true if the userName dosen't exist
	 * 	|	(getUserByUserName(userName) != null)
	 */
	private boolean isUserNameExisting(String userName){
		return (getUserByUserName(userName) != null);
	}
	
	/**
	 * @param 	user
	 * 	the user you want to add
	 * @throws 	EmailInUseException 
	 * 	|	(isMailExisting(user.getMail()))
	 * @throws UserNameInUseException
	 * 	|	 (isUserNameExisting(user.getUserName()))
	 * @post
	 * 	the user was added to users
	 * 	|	new.getUsers().contains(user)
	 */
	public void addUser(User user) 
			throws EmailInUseException, UserNameInUseException{
		if(isMailExisting(user.getMail()))
			throw new EmailInUseException();
		else if(isUserNameExisting(user.getUserName()))
			throw new UserNameInUseException();
		else
			getUsers().add(user);
	}
	
}
