package dashboard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Serialized;

import dashboard.error.AlreadyEndedException;
import dashboard.error.AlreadyRequestedException;
import dashboard.error.CourseAlreadyTakenException;
import dashboard.error.InvalidAmountException;
import dashboard.error.InvalidEmailException;
import dashboard.error.InvalidEndDateException;
import dashboard.error.InvalidPasswordException;
import dashboard.error.InvalidFirstNameException;
import dashboard.error.InvalidLastNameException;
import dashboard.error.InvalidStudyMomentException;
import dashboard.error.InvalidUserNameException;
import dashboard.error.NameAlreadyInUseException;
import dashboard.error.NoSuchCourseException;
import dashboard.error.NotFriendException;
import dashboard.error.NotStudyingException;
import dashboard.util.OwnOfy;

public class Student implements Comparable<Student>,Cloneable,Serializable {

	private static final long serialVersionUID = -6268846212512642033L;
	@Id private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String mail;
	private String password;
	private boolean isCloned;
	@Serialized private StudyMoment currentStudyMoment;
	@Serialized private ArrayList<String> friendList;
	@Serialized private ArrayList<String> friendRequests;
	@Serialized private ArrayList<StudyMoment> studyMoments;
	@Serialized private ArrayList<Location> starredLocations;
	@Serialized private ArrayList<CourseContract> courses;
	public Student(){
	}

	/**
	 * initiates a student
	 * @param	firstName
	 * 			the first name you want your student to have
	 * @param 	lastName
	 * 			the last name you want your student to have
	 * @param	userName
	 * 			the user name you want your student to have
	 * @param	mail
	 * 			the mail-adress you want your student to have
	 * @param	password
	 *			the password you want your student to have
	 * @throws 	InvalidUserNameException 
	 *		|	(!isValidUserName(userName))
	 * @throws 	InvalidEmailException 
	 *		|	(!isValidUserMail(mail))
	 * @throws 	InvalidPasswordException 
	 *		|	(!isValidPassword(password))
	 * @throws 	InvalidFirstNameException
	 * 		|	(!isValidFirstName(firstName))
	 * @throws 	InvalidLastNameException
	 * 		|	(!isValidLastName(lastName))
	 * @effect	the first name of the student will be set to the designated first name
	 * 		|	setFirstName(name)
	 * @effect	the last name of the student will be set to the designated last name
	 * 		|	setLastName(name)
	 * @effect  the password of the student will be set to the designated password
	 * 		|	setPassword(password)
	 * @effect	the arraylists of the student will be created
	 * 		|	convertEmptyArrayLists()
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the username has been changed to the designated username
	 * 		|	new.getUserName() = userName
	 * @post	the mail has been changed to the designated mail
	 * 		|	new.getMail() = mail
	 */
	public Student(String firstName, String lastName, String userName, String mail, String password)
			throws InvalidUserNameException, InvalidEmailException, InvalidPasswordException, InvalidFirstNameException, InvalidLastNameException{
		if(!isValidUserName(userName))
			throw new InvalidUserNameException();
		if(!isValidMail(mail))
			throw new InvalidEmailException();
		if(!isValidPassword(password))
			throw new InvalidPasswordException();
		if(!isValidName(firstName))
			throw new InvalidFirstNameException();
		if(!isValidName(lastName))
			throw new InvalidLastNameException();
		this.userName = userName;
		this.mail = mail;
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
		convertEmptyArrayLists();
		refresh();
	}
	
	/**
	 * returns the user name of the student
	 * @return	the user name of the student
	 * 		|	userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * returns the mail of the student
	 * @return	the mail address of the student
	 * 		|	mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * returns the first name of the student
	 * @return	the first name of the student
	 * 		|	firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * returns the last name of the student
	 * @return	the last name of the student
	 * 		|	lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * returns the password of the student
	 * @return	the password of the student
	 * 		|	password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * returns the current study moment of the student
	 * @return	the current study moment 
	 * 		|	currentStudyMoment
	 */
	public StudyMoment getCurrentStudyMoment() {
		return currentStudyMoment;
	}

	/**
	 * returns the moment of the student that matches the given string
	 * @param 	momentString
	 * 			the string that is used to find the moment
	 * @return	if the moment that matches the string exists, it returns this moment
	 * 		|	if(getStudyMoments().contains(moment:getStart().toString().equals(momentString)))
	 * 		|		return moment
	 * @return 	if this moment does not exist return null
	 * 		|	if(!getStudyMoments().contains(moment:getStart().toString().equals(momentString)))
	 * 		|		return null
	 */
	public StudyMoment getMoment(String momentString){
		for(StudyMoment moment : getStudyMoments())
			if(moment.getStart().toString().equals(momentString))
				return moment;
		return null;
	}
	
	/**
	 * returns the list of studymoments of the student
	 * @return	the studymoments of this student
	 * 		|	studyMoments
	 */
	public ArrayList<StudyMoment> getStudyMoments() {
		return studyMoments;
	}

	/**
	 * returns the list of course contracts of the student
	 * @return	the course contracts of the student
	 * 		|	courses
	 */
	public ArrayList<CourseContract> getCourses() {
		return courses;
	}

	/**
	 * Returns a list of the courses the student is taking.
	 * @return	the courses the student is enrolled in
	 * 		|	courseList = new ArrayList<Course>()
	 * 		|	for(CourseContract courseContract: courses)
	 *		|		courseList.add(courseContract.getCourse())
	 *		|	return courseList
	 */
	public ArrayList<Course> getCourseList(){
		ArrayList<Course> courseList = new ArrayList<Course>();
		for(CourseContract courseContract: courses){
			courseList.add(courseContract.getCourse());
		}
		return courseList;
	}

	/**
	 * returns the list of friends of the student
	 * @return	the friend list of the student
	 *  	| 	friendList
	 */
	public ArrayList<String> getFriendList(){
		return friendList;
	}

	/**
	 * returns the list of locations of the student
	 * @return	the starred locations of the student
	 * 		|	starredLocations
	 */
	public ArrayList<Location> getStarredLocations(){
		return starredLocations;
	}

	/**
	 * returns the list of friend requests of the student
	 * @return	the requested friends of the student
	 *  	|	friendRequests
	 */
	public ArrayList<String> getFriendRequests() {
		return friendRequests;
	}

	/**
	 * returns the total time the student has studied
	 * @return	a sum of the time of all moments
	 * 		|	for(StudyMoment s: studyMoments)
	 * 		|		time += s.getTime()
	 * 		|	return time
	 */
	public long getTotalTimeStudied(){
		long time = 0;
		for(StudyMoment s: studyMoments){
			time += s.getTime();
		}
		return time;
	}

	/**
	 * returns the number of requests the student have
	 * @return	the number of requests
	 * 		|	getFriendRequests().size()
	 */
	public int getRequestNumbers(){
		return getFriendRequests().size();
	}

	/**
	 * sets the first name of the student to a designated name
	 * @param 	firstName
	 * 			the new first name of the student
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the first name was changed to the designated name
	 * 		|	new.getFirstName() = firstName
	 */
	public void setFirstName(String firstName) throws InvalidFirstNameException {
		if(!isValidName(firstName))
			throw new InvalidFirstNameException();
		this.firstName = firstName;
		refresh();
	}

	/**
	 * sets the last name of the student to a designated name
	 * @param 	lastName
	 * 			the new last name of the student
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the last name was changed to the designated name
	 * 		|	new.getLastName() = lastName
	 */
	public void setLastName(String lastName) throws InvalidLastNameException {
		if(!isValidName(lastName))
			throw new InvalidLastNameException();
		this.lastName = lastName;
		refresh();
	}

	/**
	 * sets the password of the student to a designated password
	 * @param 	password
	 * 			the new password of the student
	 * @throws 	InvalidPasswordException 
	 * 		|	(!isValidPassword(password))
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the password was changed to the designated password
	 * 		|	new.getPassword() = password
	 */
	public void setPassword(String password) throws InvalidPasswordException {
		if(!isValidPassword(password))
			throw new InvalidPasswordException();
		this.password = password;
		refresh();
	}

	/**
	 * sets the current study moment of the student to a designated study moment
	 * @param 	currentStudyMoment
	 * 			the studymoment you want to save as current studymoment
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the current studymoment was changed	to the designated study moment
	 * 		| 	new.getCurrentStudyMoment() = studyMoment
	 */
	public void setCurrentStudyMoment(StudyMoment currentStudyMoment) {
		this.currentStudyMoment = currentStudyMoment;
		refresh();
	}

	/**
	 * sets the list of study moments of the student to a designated list
	 * @param 	studyMoments
	 * 			the list you want to save as your list of study moments
	 * @post	the list of studymoments was changed to the designated list
	 * 		| 	new.getStudyMoments() = studyMoments
	 */
	public void setStudyMoments(ArrayList<StudyMoment> studyMoments) {
		this.studyMoments = studyMoments;
	}

	/**
	 * sets the list of friends of the student to a designated list
	 * @param 	friendList
	 * 			the list you want to save as your list of friends
	 * @post	the list of friends was changed to the designated list
	 * 		| 	new.getFriendList() = friendList
	 */
	public void setFriendList(ArrayList<String> friendList) {
		this.friendList = friendList;
	}

	/**
	 * sets the list of requests of the student to a designated list
	 * @param 	friendRequests
	 * 			the list you want to save as your list of requests
	 * @post	the list of requests was changed to the designated list
	 * 		| 	new.getFriendRequests() = friendRequests
	 */
	public void setFriendRequests(ArrayList<String> friendRequests) {
		this.friendRequests = friendRequests;
	}

	/**
	 * sets the list of locations of the student to a designated list
	 * @param 	starredLocations
	 * 			the list you want to save as your list of locations
	 * @post	the list of locations was changed to the designated list
	 * 		| 	new.getStarredLocations() = starredLocations
	 */
	public void setStarredLocations(ArrayList<Location> starredLocations) {
		this.starredLocations = starredLocations;
	}

	/**
	 * sets the list of course contracts of the student to a designated list
	 * @param 	courses
	 * 			the list you want to save as your list of course contracts
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the list of course contracts was changed to the designated list
	 * 		| 	new.getCourses() = courses
	 */
	public void setCourses(ArrayList<CourseContract> courses) {
		this.courses = courses;
		refresh();
	}

	/**
	 * adds a study moment to the list of study moments of the student
	 * @param 	moment
	 * 			the moment you want to add
	 * @throws 	InvalidStudyMomentException 
	 * 		|	(!IsValidStudyMoment(moment))
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	if the list of study moments is empty it will simply add the moment
	 * 		|	if(getStudyMoments().isEmpty())
	 * 		|		getStudyMoments().add(moment)
	 * @post	if the list is not empty, the moment will be put after the last moment that comes
	 * 			before the moment
	 * 		|	for(i = getStudyMoments().size()-1 -> 0)
	 * 		|		if(moment.getStart().after(getStudyMoments().get(i).getStart()))
	 * 		|			getStudyMoments().add(i + 1, moment)
	 * @post	if the list does not contain such moment, the moment will be put on the first place
	 * 		|	if(!moments.contains(aMoment:moment.getStart().after(aMoment.getStart())))
	 * 		|		getStudyMoments().add(0, moment)
	 */
	public void addStudyMoment(StudyMoment moment) throws InvalidStudyMomentException {
		if(!IsValidStudyMoment(moment))
			throw new InvalidStudyMomentException();
		if(getStudyMoments().isEmpty()){
			getStudyMoments().add(moment);
			refresh();
			return;
		}
		for(int i = getStudyMoments().size() - 1; i!=-1; i--)
			if(moment.getStart().after(getStudyMoments().get(i).getStart())){
				getStudyMoments().add(i + 1, moment);
				refresh();
				return;
			}
		getStudyMoments().add(0, moment);
		refresh();
		return;
	}

	/**
	 * adds a course to the list of courses of the student
	 * @param 	course
	 * 			the course you want to add to the list
	 * @throws 	CourseAlreadyTakenException 
	 * 			getCourses().contains(studentCourse: studentCourse.getCourse().getName().equals(course.getCourse().getName()))
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the courseContract was added to the student's courses
	 * 		|	new.courses.contains(course)
	 */
	public void addCourse(CourseContract course) throws CourseAlreadyTakenException{
		for(CourseContract userCourse : getCourses()){
			if(userCourse.getCourse().getName().equals(course.getCourse().getName())){
				throw(new CourseAlreadyTakenException());
			}
		}
		getCourses().add(course);
		refresh();
	}

	/**
	 * adds a starred location to the student's list of locations
	 * @param 	location
	 * 			the starred location you want to add
	 * @throws	NameAlreadyInUseException
	 * 		|	getStarredLocations().contains(existing: location.getAlias().equals(existing.getAlias()))
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the location was added to the student's starred locations
	 * 		|	new.getStarredLocations().contains(location)
	 */
	public void addStarredLocation(Location location) throws NameAlreadyInUseException{
		String name = location.getAlias();
		for(Location existing : getStarredLocations()){
			if(name.equals(existing.getAlias())){
	 		 throw(new NameAlreadyInUseException());}
		}
		getStarredLocations().add(location);
		refresh();
	}

	/**
	 * adds a friend's user name to the student's list of friend user names
	 * @param 	userName
	 * 			the user name to add to friend list
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the name was added to the student's list of friend names
	 * 		|	new.getFriendList().contains(userName) 
	 */
	public void addFriend(String userName) {
		getFriendList().add(userName);
		refresh();
	}

	/**
	 * @param	courseName
	 * 			the name of the course you want to remove
	 * @throws 	NoSuchCourseException 
	 * 		|	!getCourses().contains(course)
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the course with matching course name was removed from the list of courses
	 * 		|	!new.getCourses().contains(course: course.getName().equals(courseName))
	 */
	public void removeCourse(String courseName) throws NoSuchCourseException {
		for(CourseContract course : getCourses()){
			if(course.getCourse().getName().equals(courseName)){
				getCourses().remove(course);
				refresh();
				return;
			}
		}
		throw(new NoSuchCourseException());
	}

	/**
	 * removes a moment matching the given String of the student's list of moments
	 * @param 	momentString
	 * 			the String that is used to check the moment
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the moment was removed, if it existed
	 * 		|	!new.getStudyMoments().contains(getMoment(momentString))
	 */
	public void removeMoment(String momentString){
		getStudyMoments().remove(getMoment(momentString));
		refresh();
	}
	
	/**
	 * removes a friend's user name from your list of friends
	 * @param	userName
	 * 			the name you want to remove from you list of friend names
	 * @throws	NotFriendException
	 * 		|	(!isAFriend(userName))
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the friend's user name was removed from the list
	 * 		|	!new.getFriendList.contains(userName)
	 * 
	 */
	public void removeFriend(String userName) throws NotFriendException{
		if(!isAFriend(userName))
			throw new NotFriendException();
		friendList.remove(userName);
		refresh();
	}

	/**
	 * adds the user name of the person that requests you as friend in the list of requests
	 * @param 	username
	 * 			the user name of the requesting person
	 * @throws	AlreadyRequestedException
	 * 		|	(getFriendRequests().contains(userName))
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the requesting student's user name was added to your list of requests
	 * 		|	getFriendRequests().contains(userName)
	 */
	public void requestedAsFriend(String userName) 
			throws AlreadyRequestedException{
		if(getFriendRequests().contains(userName))
			throw new AlreadyRequestedException();
		getFriendRequests().add(userName);
		refresh();
	}

	/**
	 * removes the user name of a person that requested you as friend in the list of requests
	 * @param 	username
	 * 			the user name of the person that has to be removed
	 * @effect	the student will be put in the google appstore
	 * 		|	refresh()
	 * @post	the requesting student's user name was removed from your list of requests
	 * 		|	!getFriendRequests().contains(userName)
	 */
	public void removeRequest(String username){
		if(getFriendRequests().contains(username))
			getFriendRequests().remove(username);
		refresh();
	}

	/**
	 * ends the studying of a student
	 * @param 	amount
	 * 			the amount the student studied
	 * @param 	kind
	 * 			what kind of studying he did
	 * @param	endDate
	 * 			the date on which the student stopped studying
	 * @throws 	AlreadyEndedException
	 * @throws 	InvalidAmountException
	 * @throws 	InvalidStudyMomentException 
	 * @effect	ends the study moment
	 * 		|	StudyMoment moment = getCurrentStudyMoment()
	 * 		|	moment.endMoment(endDate, amount, kind)
	 * @effect	adds the study moment to the student's list of study moments
	 * 		|	addStuddyMoment(moment)
	 * @effect	sets the current study moment of the student to null
	 * 		|	setCurrentStudyMoment(null)
	 */
	public void endStudying(Date endDate, int amount, String kind) 
			throws AlreadyEndedException, InvalidAmountException,InvalidEndDateException, InvalidStudyMomentException{
		StudyMoment moment = getCurrentStudyMoment();
		moment.endMoment(endDate, amount, kind);
		addStudyMoment(moment);
		setCurrentStudyMoment(null);
	}

	/**
	 * @throws 	NotStudyingException 
	 * 		|	getCurrentStudyMoment() == null
	 * @effect	sets the current study moment to null
	 * 		|	setCurrentStudyMoment(null)
	 */
	public void cancelCurrentStudyMoment() throws NotStudyingException{
		if(getCurrentStudyMoment() == null)
			throw new NotStudyingException();
		setCurrentStudyMoment(null);
	}

	/**
	 * matches a location with a starred location
	 * @return	the starred location if a match was found
	 * 		|	if(getStarredLocations().contains(aLocation: loc.distanceWorstCase(location)< radius))
	 * 		|		for(Location aLocation: getStarredLocations())
	 * 		|			dist = aLocation.distanceWorstCase(location)
	 * 		|			if(dist < distance)
	 * 		|				distance = dist
	 *		|				bestMatch = aLocation
	 * 		|		return bestMatch
	 * @return	null if no match was found
	 * 		| 	if(!getStarredLocations().contains(aLocation: loc.distanceWorstCase(location)< radius))
	 * 		|		return null
	 */
	public  Location matchStarredLocation(Location location,double radius) {
		Iterator<Location> it = starredLocations.iterator();
		Location bestMatch = null;
		double distance = radius;
		while(it.hasNext()){
			Location loc = it.next();
			double dist = loc.distanceWorstCase(location);
			if(dist<distance){
				distance = dist;
				bestMatch = loc;
			}	
		}
		return bestMatch;
		
	}

	/**
	 * creates new Arraylist if any of the student's list equals null
	 * @post	studymoments has been initialized
	 * 		|	if(studyMoments == null)
	 * 		|	new.getStudyMoments() = new ArrayList<StudyMoment>()
	 * @post	courses has been initialized
	 * 		|	if(courses == null)
	 * 		|	new.getCourses() = new ArrayList<CourseContract>()
	 * @post	friendList has been initialized
	 * 		|	if(friendList == null)
	 * 		|	new.getFriendList() = new ArrayList<String>()
	 * @post	friendRequests has been initialized
	 * 		|	if(friendRequests == null)
	 * 		|	new.getFriendRequests() = new ArrayList<String>()
	 * @post	starredLocations has been initialized
	 * 		|	if(starredLocations == null)
	 * 		|	new.getStarredLocations() = new ArrayList<Location>()
	 */
	public void convertEmptyArrayLists(){
		if(studyMoments == null)
			studyMoments = new ArrayList<StudyMoment>();
		if(courses == null)
			courses = new ArrayList<CourseContract>();
		if(friendList == null)
			friendList = new ArrayList<String>();
		if(friendRequests == null)
			friendRequests = new ArrayList<String>();
		if(starredLocations == null)
			starredLocations = new ArrayList<Location>();
	}

	/**
	 * compares student with other student
	 * @return	0 if the usernames of both users match
	 * 		|	if(other.getUserName().equals(getUserName()))
	 *		|		return 0
	 * @return	-1 if the usernames of both users match
	 * 		|	if(!other.getUserName().equals(getUserName()))
	 *		|		return -1
	 */
	public int compareTo(Student other) {
		if(other.getUserName().equals(getUserName()))
			return 0;
		else
			return -1;
	}
	
	/**
	 * clones the student
	 * @return	a cloned student
	 * 		|	clone = new Student()
	 * 		|	clone.setCloneInfo(getFirstName(),getLastName(),getUserName(),getMail(),getPassword())
	 * 		|	clone.setStudyMoments((ArrayList<StudyMoment>) getStudyMoments().clone())
	 *		|	clone.setStarredLocations((ArrayList<Location>) starredLocations.clone())
	 *		|	clone.setFriendRequests((ArrayList<String>) friendRequests.clone())
	 *		|	clone.setFriendList((ArrayList<String>) getFriendList().clone())
	 *		|	clone.setCourses((ArrayList<CourseContract>) getCourses().clone())
	 *		|	clone.setCurrentStudyMoment(getCurrentStudyMoment())
	 *		| 	return clone
	 */
	public Object clone() throws CloneNotSupportedException {
		Student clone = null;
		clone = new Student();
		clone.setCloneInfo(getFirstName(),getLastName(),getUserName(),getMail(),getPassword());
		clone.setStudyMoments((ArrayList<StudyMoment>) getStudyMoments().clone());
		clone.setStarredLocations((ArrayList<Location>) starredLocations.clone());
		clone.setFriendRequests((ArrayList<String>) friendRequests.clone());
		clone.setFriendList((ArrayList<String>) getFriendList().clone());
		clone.setCourses((ArrayList<CourseContract>) getCourses().clone());
		clone.setCurrentStudyMoment(getCurrentStudyMoment());	
		return clone;
	}
	
	/**
	 * checks if a given password is valid
	 * @param 	password
	 * 			the password that has to be checked
	 * @return	true if the password matches this student's password
	 * 		|	(getPassword().equals(password))
	 */
	public boolean isCorrectPassword(String password){
		return (getPassword().equals(password));
	}

	/**
	 * checks if a given user name matches a student's friends
	 * @param 	userName
	 * 			the user name that has to be checked
	 * @return	true, if the user name matches a friend's user name
	 * 		|	friendList.contains(userName)
	 */
	public boolean isAFriend(String userName){
		return friendList.contains(userName);
	}

	/**
	 * checks the validity of the user name
	 * @param 	userName
	 * 			the user name that has to be checked
	 * @return	the validity of the given user name
	 * 		|	(userName.length() > 5) && (userName.length() < 25) &&
	 *		|	(userName.matches("^[a-zA-Z_0-9]+$"))
	 */
	private boolean isValidUserName(String userName){
		return 	(userName.length() > 5) && (userName.length() < 25) &&
				(userName.matches("^[a-zA-Z_0-9]+$"));
	}
	
	/**
	 * checks the validity of a name
	 * @param 	name
	 * 			the name that has to be checked
	 * @return	the validity of the given name
	 * 		|	(userName.length() > 1) && (userName.length() < 25) &&
	 *		|	(userName.matches("^[a-zA-Z]+$"))
	 */
	private boolean isValidName(String name){
		return 	(name.length() > 1) && (name.length() < 25) &&
				(name.matches("^[a-zA-Z]+$"));
	}

	/**
	 * checks the validity of the mail address
	 * @param 	mail
	 * 			the mail address that has to be checked
	 * @return	true, if it is a valid mail address
	 * 		|	(mail.contains("@"))
	 */
	private boolean isValidMail(String mail) {
		return	(mail.contains("@"));
	}

	/**
	 * checks the validity of the password
	 * @param	password
	 * 			the password that has to be checked
	 * @return	true, if the password is valid
	 * 		|	(password.length() > 5) && (password.length() < 25)
	 */
	private boolean isValidPassword(String password){
		return 	(password.length() > 5) && (password.length() < 25);
	}

	/**
	 * checks the validity of a study moment
	 * @param 	moment
	 * 			the moment that has to be checked
	 * @return	true, if the studymoment is valid
	 *  		|	getStudyMoments().contains(aMoment:
	 *  		|		!(moment.getStart().after(aMoment.getStart()) &&
	 *  		|		moment.getStart().before(aMoment.getEnd())) &&
	 *  		|		!(moment.getEnd().after(aMoment.getStart()) &&
	 *  		|		moment.getEnd().before(aMoment.getEnd())) &&
	 *  		|		!(aMoment.getStart().after(moment.getStart()) &&
	 *  		|		aMoment.getStart().before(aMoment.getEnd())))
	 */
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
	 * set info for a cloned student, so it will not be put in the appstore
	 * @param	firstName
	 * 			the first name you want your clone to have
	 * @param 	lastName
	 * 			the last name you want your clone to have
	 * @param	userName
	 * 			the username you want your clone to have
	 * @param	mail
	 * 			the mail-adress you want your clone to have
	 * @param	password
	 *			the password you want your clone to have
	 * @effect	the arraylists of the clone will be created
	 * 		|	convertEmptyArrayLists()
	 */
	private void setCloneInfo(String firstName, String lastName, String userName, String mail, String password){
		isCloned = true;
		this.userName = userName;
		this.mail = mail;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		convertEmptyArrayLists();
	}

	/**
	 * refresh the student, by putting him in the appstore
	 * @effect	the student was put in the appstore if it was not a clone
	 * 		|	if(!isCloned)
	 * 		|		OwnOfy.ofy().put(this)
	 */
	private void refresh(){
		if(!isCloned)
			OwnOfy.ofy().put(this);
	}
}
