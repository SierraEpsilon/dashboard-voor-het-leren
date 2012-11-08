package dashboard.model;
import java.util.*;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

import dashboard.error.InvalidUserNameException;

public class StudyMoment {

	enum Kind {
		
		PAGE("page"),EXERCISE("exercise");
		
		private final String kind;
		
		private Kind(String kind){
			this.kind = kind;
		}
		
		/**
		 * @return
		 * 	the kind
		 * 	|	kind
		 */
		public String getKind() {
			return kind;
		}
	}
	
	private final Date start;
	private Date end;
	private final Course course;
	private int amount;
	private Kind kind;
	//opvragen of hij bezig is
	
	public StudyMoment(Date start, Course course){
		setStart(start);
		this.course = course;
		
	}
	public StudyMoment(Date start, Date end, Course course, int amount, String kind){
		
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
	public Kind getKind(){
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
	public void setKind(Kind kind){
		this.kind = kind;
	}
	
	public void endMoment(Date end, int amount, Kind kind){
		if(endthis.end = end;
		this.amount = amount;
		this.kind = kind;
	}
	
	/**
	 * @return
	 * studymoment has ended
	 *  | studymoment ended
	 */
	private boolean isEnded(){
		return(end != null);
	}
	
	
	private boolean isValidEnd(Date end){
		
	}
   
}
	