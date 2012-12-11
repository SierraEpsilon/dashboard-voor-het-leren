package dashboard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;
import dashboard.util.Statistics;

public class NewAchievement implements Serializable {
	
	enum Repeat implements Serializable{
		
		DAILY("daily"),
		WEEKLY("weekly"),
		MONTHLY("monthley"),
		YEARLY("yearly");
		
		private String reccuring;
		private Calendar calendar = Calendar.getInstance();
		
		private Repeat(String reccuring){
			this.reccuring = reccuring;
		}
		
		private Date changeDate(Date date, Date momentDate){
			calendar.setTime(date);
			Calendar momentCalendar = Calendar.getInstance();
			momentCalendar.setTime(momentDate);
			calendar.set(Calendar.YEAR, momentCalendar.YEAR);
			if(reccuring.equals("yearly"))
				return calendar.getTime();
			calendar.set(Calendar.MONTH, momentCalendar.MONTH);
			if(reccuring.equals("monthly"))
				return calendar.getTime();
			calendar.set(Calendar.WEEK_OF_YEAR, momentCalendar.WEEK_OF_YEAR);
			if(reccuring.equals("weekly"))
				return calendar.getTime();
			calendar.set(Calendar.DAY_OF_YEAR, momentCalendar.DAY_OF_YEAR);
			if(reccuring.equals("daily"))
				return calendar.getTime();
			else
				return null;
		}
	}	
	private static final long serialVersionUID = -4773912395822659711L;
	//info
	private String id;
	private String name;
	private String desc;
	private Course course;
	private String icon;
	private boolean visible;
	//parameters
	private boolean needTime;
	private long time;
	private boolean needNumber;
	private int number;
	private boolean needPeriod;
	//if you chose needExpiration it only needs an end date
	private boolean needExpiration;
	private Date startDate;
	private Date endDate;
	private boolean needRepeating;
	private Repeat repeat;
	
	
	public NewAchievement(String id, String name, String desc, Course course, String icon, boolean visible){
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.course = course;
		this.icon = icon;
		this.visible = visible;
	}
	
	public void addTimeRequirement(long time){
		needTime = true;
		this.time = time;
	}
	
	public void addNumberRequirement(int number){
		needNumber = true;
		this.number = number;
	}
	
	
	public void addExpirationRequirement(Date endDate){
		needExpiration = true;
		this.endDate = endDate;
	}
	
	public void addPeriodRequirement(Date startDate,Date endDate){
		needPeriod = true;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public void addRepeatingRequirement(String sortRepeat){
		needRepeating = true;
		if(sortRepeat.equals("daily"))
			repeat = Repeat.DAILY;
		else if(sortRepeat.equals("weekly"))
			repeat = Repeat.WEEKLY;
		else if(sortRepeat.equals("monthly"))
			repeat = Repeat.MONTHLY;
		else if(sortRepeat.equals("yearly"))
			repeat = Repeat.YEARLY;
		else
			needRepeating = false;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public Course getCourse() {
		return course;
	}
	
	public String getIcon(){
		return icon;
	}

	public long getTime() {
		return time;
	}
	
	public int getNumber() {
		return number;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public float checkTimeProgress(long seconds){
		float progress = 0;
		progress = seconds/getTime();
		if(progress > 1)
			progress = 1;
		return progress;
	}
	
	public float checkNumberProgress(int number){
		float progress = 0;
		progress = number/getNumber();
		if(progress > 1)
			progress = 1;
		return progress;
	}

	private float checkProgress(ArrayList<StudyMoment> moments) {
		float progress = 0;
		int parameters = 0;
		if(needTime){
			long momentsTime = Statistics.getTotalTime(moments);
			progress = checkTimeProgress(momentsTime);
			parameters++;
		}
		if(needNumber){
			int number = moments.size();
			checkNumberProgress(number);
			parameters++;
		}
		progress = progress/parameters;
		return progress;
	}

	public float getProgress(Student student){
		return getProgress(student.getStudyMoments());
	}
	
	public float getProgress(ArrayList<StudyMoment> studyMoments){
		ArrayList<StudyMoment> moments = studyMoments;
		if(getCourse() != null)
			moments = Statistics.filterMomentsByCourse(moments, getCourse());
		if(needExpiration)
			moments = Statistics.getMomentsUntil(moments, getEndDate());
		if(needRepeating){
			for(StudyMoment moment : moments){
				Date start = repeat.changeDate(getStartDate(), moment.getStart());
				Date end = repeat.changeDate(getEndDate(), moment.getStart());
				ArrayList<StudyMoment>localMoments = Statistics.getMomentsPeriod(moments, start, end);
				if(checkProgress(localMoments) == 1)
					return 1;
			}
			Date now = new Date();
			Date start = repeat.changeDate(getStartDate(),now);
			Date end = repeat.changeDate(getEndDate(), now);
			moments = Statistics.getMomentsPeriod(moments, start, end);
			return checkProgress(moments);
		}
		if(needPeriod)
			moments = Statistics.getMomentsPeriod(moments, getStartDate(), getEndDate());
		return checkProgress(moments);
	}
}
