import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self;//Edit Variable Names SeLf to self by CSS
	private static java.util.Calendar  calendar;//Fix Variable Name instead CaLeNdAr
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();
	}
	
	public static Calendar Instance() {//Set Method Name INSTANCE to INSTANCE
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void IncrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void SetDate(Date date) {//Fix Method Name SET_DATE to SetDate
		try {
			calendar.setTime(date);
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date DueDate(int loanPeriod) {//Edit Mathod Name As CamelBack by CSS
		Date Now = Date();
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		Date DueDate = calendar.getTime();
		calendar.setTime(Now);
		return DueDate;
	}
	
	public synchronized long GetDaysDifference(Date targetDate) {//Edit Mathod Name As CamelBack by CSS
		
		long giffMillis = Date().getTime() - targetDate.getTime();
	    long diffDays = TimeUnit.DAYS.convert(giffMillis, TimeUnit.MILLISECONDS);
	    return diffDays;
	}

}
