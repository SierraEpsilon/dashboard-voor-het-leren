package dashboard.model;

import java.util.*;

public enum Branch {
	
	BABI1(Arrays.asList(	Course.H01A8A,Course.H01B0A,Course.H01B2A,Course.H01B4A,
							Course.H01D0A,Course.H01Z2A,Course.H01B6B,Course.H01A4A,
							Course.H01A0B,Course.H01A2A,Course.H01B9A,Course.H01C2A,
							Course.H01C4B),"BaBi1");
	private ArrayList<Course> courses;
	private String name;
	
	private Branch(List<Course> courses, String name){
		this.courses = new ArrayList<Course>();
		this.courses.addAll(courses);
		this.name = name;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public String getName() {
		return name;
	}
}
