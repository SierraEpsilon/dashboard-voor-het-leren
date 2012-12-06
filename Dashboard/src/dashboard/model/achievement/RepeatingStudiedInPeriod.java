package dashboard.model.achievement;

import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;

public class RepeatingStudiedInPeriod extends Achievement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3019165986990844499L;
	Date startDate;
	Date endDate;
	int repeatingType;
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param desc
	 * @param course
	 * @param icon
	 * @param visible
	 * @param startDate
	 * @param endDate
	 * @param repeatingType the Calendar type between 2 intervals, f.e. DAY_OF_YEAR
	 */
	public RepeatingStudiedInPeriod(String id, String name, String desc, Course course, String icon, boolean visible, Date startDate, Date endDate, int repeatingType) {
		super(id, name, desc, course, icon, visible);
		this.startDate = startDate;
		this.endDate = endDate;
		this.repeatingType = repeatingType;
	}

	@Override
	public float getProgress(Student student) {
		return getProgress(student.getStudyMoments());
	}

	@Override
	public float getProgress(ArrayList<StudyMoment> studyMoments) {
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		for(StudyMoment studyMoment: studyMoments){
			Calendar newEnd = Calendar.getInstance();
			newEnd.setTime(studyMoment.getStart());
			newEnd.set(MILLISECOND, endCalendar.get(MILLISECOND));
			newEnd.set(SECOND, endCalendar.get(SECOND));
			newEnd.set(MINUTE, endCalendar.get(MINUTE));
			newEnd.set(HOUR_OF_DAY, endCalendar.get(HOUR_OF_DAY));
			if(repeatingType == WEEK_OF_YEAR){
				newEnd.set(DAY_OF_WEEK, endCalendar.get(DAY_OF_WEEK));
			} else if(repeatingType == MONTH){
				newEnd.set(DAY_OF_MONTH, endCalendar.get(DAY_OF_MONTH));
			} else if(repeatingType == YEAR){
				newEnd.set(MONTH, endCalendar.get(MONTH));
				newEnd.set(DAY_OF_MONTH, endCalendar.get(DAY_OF_MONTH));
			}
			if((new Date(newEnd.getTimeInMillis())).before(studyMoment.getStart())){
				newEnd.add(repeatingType, 1);
			}
			if(new Date(newEnd.getTimeInMillis() - endDate.getTime() + startDate.getTime()).before(studyMoment.getEnd())){
				return 1;
			}
		}
		return 0;
	}
}
