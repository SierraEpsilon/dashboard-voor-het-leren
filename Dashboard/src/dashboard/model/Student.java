package dashboard.model;

import java.io.Serializable;

import java.util.*;

import dashboard.error.AlreadyEndedException;
import dashboard.error.InvalidAmountException;
import dashboard.error.InvalidEmailException;
import dashboard.error.InvalidEndDateException;
import dashboard.error.InvalidPasswordException;
import dashboard.error.InvalidUserNameException;
import dashboard.error.NotStudyingException;
import dashboard.registry.CourseRegistry;

public class Student implements Comparable<Student>,Cloneable,Serializable {

	private String firstName;
	private String lastName;
	private final String userName;
	private final String mail;
	private String password;
	private StudyMoment currentStudyMoment;
	private ArrayList<StudyMoment> studyMoments;
	private HashSet<CourseContract> courses;
	
	/**
	 * initiates a user
	 * @param 	firstName
	 * the first name you want your student to have
	 * @param 	lastName
	 * the last name you want your student to have
	 * @param	userName
	 * the username you want your user to have
	 * @param	mail
	 * the mail adress you want your user to have
	 * @param	passWord
	 * the password you want your user to have
	 * @throws InvalidUserNameException 
	 *	|	(!isValidUserName(userName))
	 * @throws InvalidEmailException 
	 *	|	(!isValidUserMail(mail))
	 * @throws InvalidPasswordException 
	 *	|	(!isValidPassword(password))
	 * @effect
	 * setName(name);
	 * @effect
	 * setPassword(passWord);
	 * @post
	 * new.getUserName() = userName
	 * @post
	 * new.getMail() = mail
	 */
	public Student(String firstName, String lastName, String userName, String mail, String passWord)
			throws InvalidUserNameException, InvalidEmailException, InvalidPasswordException{
		if(isValidUserName(userName))
			this.userName = userName;
		else
			throw new InvalidUserNameException();
		if(isValidMail(mail))
			this.mail	= mail;
		else
			throw new InvalidEmailException();
		if(isValidPassword(passWord))
			setPassword(passWord);
		else
			throw new InvalidPasswordException();
		setFirstName(firstName);
		setLastName(lastName);
		studyMoments = new ArrayList<StudyMoment>();
		courses = new HashSet<CourseContract>();
		createFakeInfo();
	}
	
	//moet weggehaald worden LATER
	private void createFakeInfo(){
		ArrayList<CourseEnum> testCourses = CourseRegistry.getBranch("BaBi1");
		for(CourseEnum course: testCourses)
			addCourse(new CourseContract(course));
	}
	
	/**
	 * @return	
	 *	the first name of the student
	 * 	|	first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @return	
	 *	the last name of the student
	 * 	|	last name
	 */
	public String getLastName() {
		return lastName;
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
	 * @return
	 * 	the current study moment 
	 * 	|	currentStudyMoment
	 */
	public StudyMoment getCurrentStudyMoment() {
		return currentStudyMoment;
	}
	
	/**
	 * @return
	 * 	the studymoments of this student
	 * 	|	studyMoments
	 */
	public ArrayList<StudyMoment> getStudyMoments() {
		return studyMoments;
	}
	
	/**
	 * @return
	 * 	the courses of the student
	 * 	|	courses
	 */
	public HashSet<CourseContract> getCourses() {
		return courses;
	}
	
	/**
	 * @param firstName
	 * the new first name of the user
	 * @post	the first name was changed
	 * 	|	new.getFirstName() = firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @param lastName
	 * the new last name of the user
	 * @post	the last name was changed
	 * 	|	new.getLastName() = lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @param password
	 * 	the new password of the user
	 * @post	the password was changed
	 * 	|	new.getPassword() = password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @param currentStudyMoment
	 * 	the studymoment you want to save as current studymoment
	 * @post	the current studymoment was changed	
	 * 	| 	new.getCurrentStudyMoment() = studyMoment
	 */
	public void setCurrentStudyMoment(StudyMoment currentStudyMoment) {
		this.currentStudyMoment = currentStudyMoment;
	}
	
	/**
	 * @param moment
	 * 	the moment you want to add
	 * @post
	 * 	the moment was added to the student's studymoments
	 * 	|	new.studyMoments.contains(moment)
	 */
	public void addStuddyMoment(StudyMoment moment) {
		getStudyMoments().add(moment);
	}
	
	/**
	  * @param course
	 * 	the course you want to add
	 * @post
	 * 	the courseContract was added to the student's courses
	 * 	|	new.courses.contains(course)
	 */
	public void addCourse(CourseContract course){
		getCourses().add(course);
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
	 * @param 	mail
	 * 	the mail address that has to be checked
	 * @return
	 * 	true, if it is a valid mail address
	 * 	|	(mail.contains("@"))
	 */
	private boolean isValidMail(String mail) {
		return	(mail.contains("@"));
	}
	
	/**
	 * checks the validity of the password
	 * @param	password
	 * 	the password that has to be checked
	 * @return
	 * 	true, if the password is valid
	 * 	|	(password.length() > 5) && (password.length() < 25)
	 */
	private boolean isValidPassword(String password){
		return 	(password.length() > 5) && (password.length() < 25);
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
	 * @throws NotStudyingException 
	 * 	|	getCurrentStudyMoment() == null
	 * @effect
	 * 	|	setCurrentStudyMoment(null)
	 */
	public void cancelCurrentStudyMoment() throws NotStudyingException{
		if(getCurrentStudyMoment() == null)
			throw new NotStudyingException();
		setCurrentStudyMoment(null);
	}
	
	/**
	 * @param amount
	 * 	the amount he studied
	 * @param kind
	 * 	what kind of studying he did
	 * @throws AlreadyEndedException
	 * @throws InvalidAmountException
	 * @effect
	 * 	|	StudyMoment moment = getCurrentStudyMoment()
	 * 	|	addStuddyMoment(moment)
	 * @effect
	 * 	|	StudyMoment moment = getCurrentStudyMoment()
	 * 	|	setCurrentStudyMoment(null)
	 */
	public void endStudying(int amount, String kind) 
			throws AlreadyEndedException, InvalidAmountException{
		StudyMoment moment = getCurrentStudyMoment();
		try {
			moment.endMoment(new Date(), amount, kind);
		} catch (InvalidEndDateException e) {
			e.printStackTrace();
		}
		addStuddyMoment(moment);
		setCurrentStudyMoment(null);
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
	public int compareTo(Student other) {
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
		Student clonedUser = null;
		try {
			clonedUser = new Student(getFirstName(),getLastName(),getUserName(),getMail(),getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clonedUser;
	}

}
