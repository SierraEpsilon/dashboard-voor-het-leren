package dashboard.model.achievement;

import java.util.Date;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.model.StudyMoment;

public class StudiedInPeriod extends Achievement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1484393458199276079L;
	private Date startDate;
	private Date endDate;

	public StudiedInPeriod(String id, String name, String desc, Course course, String icon, Date startDate, Date endDate) {
		super(id, name, desc, course, icon);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public float getProgress(Student student) {
		for(StudyMoment studyMoment: student.getStudyMoments()){
			if(!(startDate.before(studyMoment.getStart()) || endDate.after(studyMoment.getEnd()))){
				return 1;
			}
		}
		return 0;
	}

}
