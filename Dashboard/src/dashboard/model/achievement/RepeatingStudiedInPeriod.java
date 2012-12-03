package dashboard.model.achievement;

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
	long repeatingTime;
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param desc
	 * @param course
	 * @param icon
	 * @param startDate
	 * @param endDate
	 * @param repeatingTime the number of milliseconds between the start of 2 periods
	 */
	public RepeatingStudiedInPeriod(String id, String name, String desc, Course course, String icon, Date startDate, Date endDate, long repeatingTime) {
		super(id, name, desc, course, icon);
		this.startDate = startDate;
		this.endDate = endDate;
		this.repeatingTime = repeatingTime;
	}

	@Override
	public float getProgress(Student student) {
		for(StudyMoment studyMoment: student.getStudyMoments()){
			int n = (int)((studyMoment.getStart().getTime() - endDate.getTime())/repeatingTime);
			Date startFrame = new Date(startDate.getTime() + n * repeatingTime);
			if(studyMoment.getEnd().after(startFrame)){
				return 1;
			}
		}
		return 0;
	}

}
