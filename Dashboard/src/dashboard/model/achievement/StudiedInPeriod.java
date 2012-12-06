package dashboard.model.achievement;

import java.util.ArrayList;
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

	public StudiedInPeriod(String id, String name, String desc, Course course, String icon, boolean visible, Date startDate, Date endDate) {
		super(id, name, desc, course, icon, visible);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public float getProgress(Student student) {
		return getProgress(student.getStudyMoments());
	}

	@Override
	public float getProgress(ArrayList<StudyMoment> studyMoments) {
		for(StudyMoment studyMoment: studyMoments){
			if(studyMoment.getStart().before(endDate) && studyMoment.getEnd().after(startDate)){
				return 1;
			}
		}
		return 0;
	}

}
