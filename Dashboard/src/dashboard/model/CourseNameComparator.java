package dashboard.model;

import java.util.Comparator;


/**
 * Custom comparator made to sort courses alphabetically
 *
 */
public class CourseNameComparator implements Comparator<Course>{
	public int compare(Course o1, Course o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
