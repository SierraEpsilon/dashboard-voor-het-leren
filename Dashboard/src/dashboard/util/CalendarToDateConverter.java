package dashboard.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarToDateConverter {
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, date, hourOfDay, minute, second);
		return new Date(calendar.getTimeInMillis());
	}
}
