package dashboard.model;
import java.util.*;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import dashboard.error.AlreadyEndedException;
import dashboard.error.InvalidEmailException;
import dashboard.error.InvalidPasswordException;
import dashboard.error.InvalidUserNameException;

public class StudyMoment {

	
	
	private final Date start;
	private Date end;
	private final Course course;
	private int amount;
	private String kind;
	
	public StudyMoment(Date start, Course course){
		this.start = start;
		this.course = course;
	}
	
	public StudyMoment(Date start, Date end, Course course, int amount, String kind)
			throws InvalidEndDateException, InvalidAmountException{
		this.start = start;
		this.course = course;
		if(isValidEnd(end)){
			setEnd(end);
		}else 
			throw new InvalidEndDateException();
		if(isValidAmount(amount)){
			setAmount(amount);
		}else 
			throw new InvalidAmountException();
		setKind(kind);
		
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
	 *  | date of end
	 */
	public void setEnd(Date end){
		this.end = end;
	}
	
	/**	 
	 * @param amount
	 * the amount
	 *  | amount
	 */
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	/**	 
	 * @param kind
	 * the kind of studymoment
	 *  | kind
	 */
	public void setKind(String kind){
		this.kind = kind;
	}
	
	/**
	 * @param end
	 * @param amount
	 * @param kind
	 * @throws AlreadyEndedException 
	 * 	if the Moment has already been ended
	 * 	|	!isEnded()
	 * @post
	 * 	|	new.getEnd() = end
	 * @post
	 * 	|	new.getAmount() = amount
	 * @post
	 * 	|	new.getkind() = kind
	 */
	public void endMoment(Date end, int amount, String kind) 
			throws AlreadyEndedException{
		if(!isEnded())
			throw new AlreadyEndedException();
		this.end = end;
		this.amount = amount;
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
	 * 
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
	 * 
	 * @param amount
	 * the given amount
	 * @return
	 * true if amount is positive
	 */
	private boolean isValidAmount(int amount){
		return(amount > 0);
	}
}
	