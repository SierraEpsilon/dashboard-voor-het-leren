package dashboard.registry;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

import dashboard.error.EmailInUseException;
import dashboard.error.InvalidEmailException;
import dashboard.error.InvalidPasswordException;
import dashboard.error.InvalidUserNameException;
import dashboard.error.UserNameInUseException;
import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.model.Course;
import dashboard.util.OwnOfy;


public class StudentRegistry {

	//private static List<Student> users = new ArrayList<Student>();
		
	/*
	 * Temporary method to add fake user
	 */
	static{
		loadFromDatastore();
		//addFakeUser();		
	}
	
	public static void addFakeUser(){
		try {
			addUser("voornaam","achternaam","testuser","user@email.com","password");
		} catch (EmailInUseException e) {
			e.printStackTrace();
		} catch (UserNameInUseException e) {
			e.printStackTrace();
		} catch (InvalidUserNameException e) {
			e.printStackTrace();
		} catch (InvalidEmailException e) {
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private static void loadFromDatastore(){
		//users = OwnOfy.ofy().query(Student.class).list();
	}
	
	/**
	 * @return	the users of the userregistry
	 * 	|	users
	 */
	public static List<Student> getUsers() {
		return OwnOfy.ofy().query(Student.class).list();
	}
	
	/**
	 * @param 	username
	 * 	the username of the user you need
	 * @return
	 *	the user with the same name
	 *	|	if(user.contains(user.getUsername().equals(username)))
	 *	|		return user
	 * @return
	 * 	null if no such user exist
	 * 	|	if(!user.contains(user.getUsername().equals(username)))
	 *	|		return null
	 */
	public static Student getUserByUserName(String username){
		/*for(Student user: getUsers())
			if(user.getUserName().equals(username))
				return user;
		*/
		Student student = OwnOfy.ofy().query(Student.class).filter("userName",username).get();
		if(student != null)
			student.convertEmptyArrayLists();
		return student;
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
	public static Student getUserByMail(String mail){
		/*for(Student user: getUsers()){
			List<Student> userslist = getUsers();
			if(user.getMail().equals(mail))
				return user;
		}*/
		Student student = OwnOfy.ofy().query(Student.class).filter("mail",mail).get();
		if(student != null)
			student.convertEmptyArrayLists();
		return student;
	}
	
	/**
	 * 
	 * @param course
	 * the course which you want to know if other students are studying 
	 * @return 
	 * arraylist with all the students that are studying the course right now
	 * | 
	 */
	public static List<Student> getActiveUsersbyCourse(Course course){
		/*List<Student> studyMates = new ArrayList();
		for(Student user: getActiveUsers()){
			if(user.getCurrentStudyMoment().getCourse().equals(course)){
				studyMates.add(user);
			}
		}*/
		Query<Student> querry = OwnOfy.ofy().query(Student.class).filter("currentStudyMoment.course.name = ", course.getName());
		return querry.list();
	}
	
	/**
	 * 
	 * @return 
	 * arraylist with all the students that are studying the course right now
	 * 
	 */
	
	public static List<Student> getActiveUsers(){
		List<Student> activeStudents = new ArrayList();
		List<Student> allStudents = getUsers();
		for(Student user: allStudents){
			if(user.getCurrentStudyMoment() != null)
			activeStudents.add(user);
		}
		return activeStudents;
	}
	
	
	/**
	 * @param 	mail
	 * 	the mail you are looking availability for
	 * @return
	 * 	true if the mail dosen't exist
	 * 	|	(getUserByMail(mail) != null)
	 */
	public static boolean isMailExisting(String mail){
		return (getUserByMail(mail) != null);
	}
	
	/**
	 * @param 	userName
	 * 	the userName you are looking availability for
	 * @return
	 * 	true if the userName dosen't exist
	 * 	|	(getUserByUserName(userName) != null)
	 */
	public static boolean isUserNameExisting(String userName){
		return (getUserByUserName(userName) != null);
	}
	
	/**
	 * @param 	userName
	 * 	the username of the user
	 * @param 	password
	 * 	the password of the user
	 * @return
	 * 	true if user exists and password is correct, false otherwise
	 */
	public static boolean isValidlogIn(String userName, String password){
		if(getUserByUserName(userName) != null){
			return	(StudentRegistry.getUserByUserName(userName).isCorrectPassword(password));
		} else if(getUserByMail(userName) != null){
			return (StudentRegistry.getUserByMail(userName).isCorrectPassword(password));
		} else {
			return false;
		}
	}
	
	/**
	 * @param 	user
	 * 	the user you want to add
	 * @throws 	EmailInUseException 
	 * 	|	(isMailExisting(user.getMail()))
	 * @throws UserNameInUseException
	 * 	|	 (isUserNameExisting(user.getUserName()))
	 * @throws InvalidPasswordException 
	 * @throws InvalidEmailException 
	 * @throws InvalidUserNameException 
	 * @post
	 * 	the user was added to users
	 * 	|	new.getUsers().contains(user)
	 */
	public static void addUser(String firstName, String lastName, String username, String mail, String password) throws EmailInUseException, UserNameInUseException, InvalidUserNameException, InvalidEmailException, InvalidPasswordException{
		if(isMailExisting(mail)) {
			throw new EmailInUseException();
		} else if(isUserNameExisting(username)){
			throw new UserNameInUseException();
		} else{
			Student student = new Student(firstName, lastName, username, mail, password);
			getUsers().add(student);
		}
	}
	
	public static void sendFriendRequest(Student requestor, String friendlyName){
		if(requestor.getFriendRequests().contains(friendlyName))
			createFriends(requestor, friendlyName);
		else{
			Student friendlyUser = getUserByUserName(friendlyName);
			friendlyUser.requestedAsFriend(requestor.getUserName());
		}	
	}
	
	public static void createFriends(Student acceptor,String requestor){
		if(acceptor.getFriendRequests().contains("requestor")){
			Student reqUser = getUserByUserName(requestor);
			acceptor.addFriend(requestor);
			acceptor.removeRequest(requestor);
			reqUser.addFriend(acceptor.getUserName());
		}
	}
}
