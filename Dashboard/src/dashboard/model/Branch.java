package dashboard.model;

import java.util.*;

public enum Branch {
	
	BABI1(Arrays.asList(	Course.H01A8A,Course.H01B0A,Course.H01B2A,Course.H01B4A,
							Course.H01D0A,Course.H01Z2A,Course.H01B6B,Course.H01A4A,
							Course.H01A0B,Course.H01A2A,Course.H01B9A,Course.H01C2A,
							Course.H01C4B),"Bachelor Burgerlijk Ingenieur fase 1"),
	BABI2(Arrays.asList(	Course.H01C6A,Course.H01C8A,Course.H01D2A,Course.H01A6A,
							Course.H08W0A,Course.H01D8B,Course.H01D4B,Course.H01D7B)
							,"Bachelor Burgerlijk Ingenieur fase 2");
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
