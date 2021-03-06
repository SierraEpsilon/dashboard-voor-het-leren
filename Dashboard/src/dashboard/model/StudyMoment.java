package dashboard.model;
import java.io.Serializable;
import java.util.Date;

import dashboard.error.AlreadyEndedException;
import dashboard.error.InvalidAmountException;
import dashboard.error.InvalidEndDateException;

public class StudyMoment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -13565681668567850L;
	private Date start;
	private Date end;
	private Course course;
	private int amount;
	private String kind;
	private Location location;
	
	/**
	 * @param start
	 * 	the start of the studymoment
	 * @param course
	 * 	the course of the studymoment
	 * @post	the start was initialized
	 * 	|	new.getStart() = start
	 * @post	the course was initialized
	 * 	| new.getCourse() = course
	 */
	public StudyMoment(Date start, Course course){
		this.start = start;
		this.course = course;
	}
	
	/**
	 * @param start
	 * 	the start of the studymoment
	 * @param course
	 * 	the course of the studymoment
	 * @param
	 * the location of the studymoment
	 * @post	the start was initialized
	 * 	|	new.getStart() = start
	 * @post	the course was initialized
	 * 	| new.getCourse() = course
	 */
	public StudyMoment(Date start, Course course,Location location){
		this.start = start;
		this.course = course;
		this.location = location;
	}
	
	public StudyMoment(){
		
	}
	
	/**
	 * @param start
	 * 	the start of the studymoment
	 * @param course
	 * 	the course of the studymoment
	 * @param course
	 * 	the course of the studymoment
	 * @param amount
	 * 	the amount of the studymoment
	 * @param kind
	 * 	the kind of the studymoment
	 * @throws InvalidEndDateException
	 *  if endMoment fails to succeed because of a InvalidEndDateException
	 * @throws InvalidAmountException
	 * 	if endMoment fails to succeed because of a InvalidAmountException
	 * @effect	
	 * 	|	this(start,course)
	 * @effect
	 * 	|	endMoment(end, amount, kind)
	 */
	public StudyMoment(Date start, Date end, Course course, int amount, String kind)
			throws InvalidEndDateException, InvalidAmountException{
		this(start,course);
		try {
			endMoment(end, amount, kind);
		} catch (AlreadyEndedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 * the date of start
	 *  | date of start
	 */
	public Date getStart() {
		return start;
	}
	
	/**
	 * @return
	 * the date of end
	 *  | date of end
	 */
	public Date getEnd() {
		return end;
	}
	/**
	 * @return
	 * the location
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * @return
	 * the course
	 *  | course
	 */
	public Course getCourse() {
		return course;
	}
	
	/**
	 * @return
	 * the amount
	 * 	| amount
	 */
	public int getAmount() {
		return amount;
	}
	
	/** 
	 * @return
	 * the kind of studymoment
	 *  | kind
	 */
	public String getKind(){
		return kind;
	}
	
	/**	 
	 * @param end
	 * the date of end
	 * @post	the end has been changed
	 * 	|	new.getEnd() = end
	 */
	public void setEnd(Date end){
		this.end = end;
	}
	
	/**	 
	 * @param amount
	 * the amount
	 * @post	the amount has been changed
	 * 	|	new.getAmount() = amount 
	 */
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	/**	 
	 * @param location
	 * the location
	 * @post	the location has been changed
	 * 	|	new.getLocation() = amount 
	 */
	public void setLocation(Location location){
		this.location = location;
	}
	/**	 
	 * @param kind
	 * the kind of studymoment
	 * @post	the kind has been changed
	 * 	|	new.getKind() = kind
	 */
	public void setKind(String kind){
		this.kind = kind;
	}
	
	/**
	 * @return
	 * studymoment has ended
	 *  | end != null
	 */
	private boolean isEnded(){
		return(end != null);
	}

	/** 
	 * @param end
	 * the end date you want to enter
	 * @return
	 * true if date is valid
	 * | end.after(start)
	 */
	private boolean isValidEnd(Date end){
		return(end.after(start));
	}

	/**
	 * @param end
	 * @param amount
	 * @param kind
	 * @throws AlreadyEndedException 
	 * 	if the Moment has already been ended
	 * 	|	!isEnded()
	 * @throws InvalidEndDateException 
	 * 	| 	!isValidEnd(end)
	 * @throws InvalidAmountException 
	 * 	| 	!isValidAmount(amount)
	 * @effect
	 * 	|	setEnd(end)
	 * @effect
	 * 	|	setAmount(amount)
	 * @effect
	 * 	|	setKind(kind)
	 */
	public void endMoment(Date end, int amount, String kind) 
			throws AlreadyEndedException, InvalidEndDateException, InvalidAmountException{
		if(isEnded())
			throw new AlreadyEndedException();
		if(!isValidEnd(end))
			throw new InvalidEndDateException();
		if(!isValidAmount(amount))
			throw new InvalidAmountException();
		setEnd(end);
		setAmount(amount);
		setKind(kind);
	}
	
	/**
	 * 
	 * @param amount
	 * the given amount
	 * @return
	 * true if amount is positive
	 */
	private boolean isValidAmount(int amount){
		return(amount > 0);
	}
	
	/**
	 * @return
	 * returns time between beginning and end in seconds
	 * 	|	(getStart().getTime() - getEnd().getTime())/1000
	 */
	public long getTime(){
		return (getEnd().getTime() - getStart().getTime())/1000;
	}
}
	