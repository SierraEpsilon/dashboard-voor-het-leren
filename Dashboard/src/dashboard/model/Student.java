package dashboard.model;

import java.io.Serializable;

import java.util.*;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.*;

import dashboard.error.AlreadyEndedException;
import dashboard.error.InvalidAmountException;
import dashboard.error.InvalidEmailException;
import dashboard.error.InvalidEndDateException;
import dashboard.error.InvalidPasswordException;
import dashboard.error.InvalidStudyMomentException;
import dashboard.error.InvalidUserNameException;
import dashboard.error.NoSuchCourseException;
import dashboard.error.NotStudyingException;
import dashboard.registry.CourseRegistry;
import dashboard.util.OwnOfy;

public class Student implements Comparable<Student>,Cloneable,Serializable {

	private static final long serialVersionUID = -6268846212512642033L;
	@Id private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String mail;
	private String password;
	@Serialized private StudyMoment currentStudyMoment;
	@Serialized private ArrayList<StudyMoment> studyMoments;
	@Serialized private ArrayList<CourseContract> courses;
	
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
	public Student(String firstName, String lastName, String userName, String mail, String password)
			throws InvalidUserNameException, InvalidEmailException, InvalidPasswordException{
		if(!isValidUserName(userName))
			throw new InvalidUserNameException();
		if(!isValidMail(mail))
			throw new InvalidEmailException();
		if(!isValidPassword(password))
			throw new InvalidPasswordException();
		this.userName = userName;
		this.mail = mail;
		this.password = password;
		setFirstName(firstName);
		setLastName(lastName);
		studyMoments = new ArrayList<StudyMoment>();
		courses = new ArrayList<CourseContract>();
		OwnOfy.ofy().put(this);
	}
	
	public Student(){
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
	public ArrayList<CourseContract> getCourses() {
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
		OwnOfy.ofy().put(this);
	}
	
	/**
	 * @param lastName
	 * the new last name of the user
	 * @post	the last name was changed
	 * 	|	new.getLastName() = lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
		OwnOfy.ofy().put(this);
	}
	
	/**
	 * @param password
	 * 	the new password of the user
	 * @post	the password was changed
	 * 	|	new.getPassword() = password
	 */
	public void setPassword(String password) {
		this.password = password;
		OwnOfy.ofy().put(this);
	}
	
	/**
	 * @param currentStudyMoment
	 * 	the studymoment you want to save as current studymoment
	 * @post	the current studymoment was changed	
	 * 	| 	new.getCurrentStudyMoment() = studyMoment
	 */
	public void setCurrentStudyMoment(StudyMoment currentStudyMoment) {
		this.currentStudyMoment = currentStudyMoment;
		OwnOfy.ofy().put(this);
	}
	
	/**
	 * @param courses
	 * @post
	 * 	the courses have been changed
	 * 	|	new.getCourses() = courses
	 */
	public void setCourses(ArrayList<CourseContract> courses) {
		this.courses = courses;
		OwnOfy.ofy().put(this);
	}
	
	/**
	 * @param moment
	 * 	the moment you want to add
	 * @throws InvalidStudyMomentException 
	 * @post
	 * 	the moment was added to the student's studymoments
	 * 	|	new.studyMoments.contains(moment)
	 */
	public void addStudyMoment(StudyMoment moment) throws InvalidStudyMomentException {
		if(!IsValidStudyMoment(moment))
			throw new InvalidStudyMomentException();
		getStudyMoments().add(moment);
		OwnOfy.ofy().put(this);
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
		OwnOfy.ofy().put(this);
	}
	
	/**
	 * @param	courseName
	 * 	the name of the course you want to remove
	 * @throws NoSuchCourseException 
	 * |	!getCourses().contains(course)
	 */
	public void removeCourse(String courseName) throws NoSuchCourseException {
		for(CourseContract course : getCourses())
			if(course.getCourse().getName().equals(courseName)){
				getCourses().remove(course);
				return;
			}
		throw(new NoSuchCourseException());
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
	
	private boolean IsValidStudyMoment(StudyMoment moment) {
		boolean isValidMoment = true;
		for(int i = 0; i < studyMoments.size(); i++){
			StudyMoment momentToCheck = studyMoments.get(i);
			Date a = momentToCheck.getStart();
			Date b = momentToCheck.getEnd();
			Date c = moment.getStart();
			Date d = moment.getEnd();
			if(c.after(a) && c.before(b))
				isValidMoment = false;			
			if(d.after(a) && d.before(b))
					isValidMoment = false;
			if(a.after(c) && a.before(d))
				isValidMoment = false;
		}
			return isValidMoment;
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
	 * @throws InvalidStudyMomentException 
	 * @effect
	 * 	|	StudyMoment moment = getCurrentStudyMoment()
	 * 	|	addStuddyMoment(moment)
	 * @effect
	 * 	|	StudyMoment moment = getCurrentStudyMoment()
	 * 	|	setCurrentStudyMoment(null)
	 */
	public void endStudying(Date endDate, int amount, String kind) 
			throws AlreadyEndedException, InvalidAmountException,InvalidEndDateException, InvalidStudyMomentException{
		StudyMoment moment = getCurrentStudyMoment();
		moment.endMoment(endDate, amount, kind);
		addStudyMoment(moment);
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
	
	/**
	 * Returns a list of the courses the student is taking, NOT THE COURSECONTRACTS!!!!
	 * @return
	 */
	public ArrayList<Course> getCourseList(){
		ArrayList<Course> courseList = new ArrayList<Course>();
		for(CourseContract courseContract: courses){
			courseList.add(courseContract.getCourse());
		}
		return courseList;
	}

}
