package dashboard.model.achievement;

import dashboard.model.Course;
import dashboard.model.Student;
import dashboard.util.Statistics;

public enum TimeStudied implements Achievement {
	TOTAL_1("Studying noob", "5 min gestudeerd in totaal.", null, 300),
	TOTAL_2("Studying apprentice", "10 min gestudeerd in totaal.", null, 600),
	TOTAL_3("Studying pro", "15 min gestudeerd in totaal.", null, 900),
	ANALYSE1_1("Analyse noob", "5 min gestudeerd voor Analyse 1", Course.H01A0B, 300),
	ANALYSE1_2("Analyse apprentice", "10 min gestudeerd voor Analyse 1.", Course.H01A0B, 600),
	ANALYSE1_3("Analyse pro", "15 min gestudeerd voor Analyse 1.", Course.H01A0B, 900);
	
	String name;
	String desc;
	Course course;
	float seconds;
	
	private TimeStudied(String name, String desc, Course course, float seconds){
		this.name = name;
		this.desc = desc;
		this.course = course;
		this.seconds = seconds;
	}
	
	@Override
	public float getProgress(Student student) {
		float progress = 0;
		if(course == null){
			progress = Statistics.getTotalTime(student.getStudyMoments())/seconds;
		} else {
			progress = Statistics.getTime(course, student.getStudyMoments())/seconds;
		}
		if(progress>1){
			progress = 1;
		}
		return progress;
	}

	@Override
	public Course getCourse() {
		return course;
	}

}
