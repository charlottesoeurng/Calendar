
import java.util.Scanner;


public class CalendarFun {

    // TODO: add javadoc comments in all required places
    
    public static final int[] monthDayCounts = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public static final String[] monthNames = {"","January", "February", "March", "April", "May", "June", "July", "August","September","October","November","December"};
    public static final String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    
    
    public static void main(String[] args) {
    	
    	// test();
    	
    	int[] getdate = new int[3];
    	Scanner keyboard = new Scanner(System.in);
    	
    	System.out.print("Enter a month: ");
    	getdate[0] = keyboard.nextInt();
    	
    	System.out.print("Enter a date: ");
    	getdate[1] = keyboard.nextInt();
    	
    	System.out.print("Enter a year (1900 or later): ");
    	getdate[2] = keyboard.nextInt();
    	
    	getDayOfWeek(getdate);
    	
    	if (getDayOfWeek(getdate) == 7) {
    		System.out.println(dateToString(getdate) + " is a " + dayNames[0]); // if sunday
    	}
    	
    	else if (getDayOfWeek(getdate) != 7) {
    		System.out.println(dateToString(getdate) + " is a " + dayNames[getDayOfWeek(getdate)]);
    	}
    	
    	getThanksgivingDate(getdate[2]); // get year
    	System.out.println("Thanksgiving that year is " + dateToString(getThanksgivingDate(getdate[2])));
    	
    	System.out.println("Here is the calendar for " + monthNames[getdate[0]] + " that year:");
    	printMonth(getdate[0], getdate[2]);
    }
  
    // TODO: implement methods listed in instructions.  dateToString is completed for you.
    
    
    /**
     * Check if a year is a leap year
     * @param leapyear
     * @return isLeapYear
     */
    public static boolean isLeapYear(int leapyear) {
    	if (leapyear % 4 == 0 && leapyear % 100 != 0) {
    		return true;
    	}
    	else if (leapyear % 100 == 0 && leapyear % 400 == 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Check if a year is a leap year
     * @param validdate
     * @return isValidDate
     */
    public static boolean isValidDate(int[] validdate) {
    	int month = validdate[0];
    	int date = validdate[1];
    	int year = validdate[2];
    	
    	if (month == 2 && date > 28 && isLeapYear(year) == false) {
    		return false;
    	}
    	if (month == 2 && date > 29 && isLeapYear(year) == true) {
    		return false;
    	}
    	else if ((month == 4 || month == 6 || month == 9 || month == 11) && date > 30) {
    		return false;
    	}
    	else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && date > 31) {
    		return false;
    	}
    	else if (month < 1 || month > 12) {
    		return false;
    	}
    	
        return true;
    }
    
    /**
     * Take a date and generatate the next date
     * @param datee
     * @return getNextDate
     */
    public static int[] getNextDate(int[] datee) {
    	int month = datee[0];
    	int date = datee[1];
    	int year = datee[2];
    	
    	int[] nextdate = new int[3];
		nextdate[0] = month;
		nextdate[1] = date + 1;
		nextdate[2] = year;
		
		if (isValidDate(nextdate) == false) {
			if (month == 12 && date == 31) {
				nextdate[0] = 1;
				nextdate[1] = 1;
				nextdate[2] = year + 1;
			}
			else {
				nextdate[0] = month + 1;
				nextdate[1] = 1;
				nextdate[2] = year;
			}
		}	
		
    	return nextdate;
    }
    
    /**
     * Compare two dates; return -1 if date 1 comes before date 2, 0 if the dates are the same, or 1 if date 1 comes after date 2
     * @param date1
     * @param date2
     * @return CompareDates
     */
    public static int compareDates(int[] date1, int[] date2) {
    	if (date1[2] < date2[2]) {
    		return -1;
    	}
    	else if (date1[2] == date2[2]) {
    		if (date1[0] < date2[0]) {
    			return -1;
    		}
    		else if (date1[0] == date2[0]) {
    			if (date1[1] < date2[1]) {
    				return -1;
    			}
    			else if (date1[1] == date2[1]) {  	
    				return 0;
    			}
    		}
    	}
    	return 1;
    }
    
    /**
     * Convert date to a string
     * @param date
     * @return dateToString
     */
    public static String dateToString(int[] date)
    {
        return ""+date[0]+"/"+date[1]+"/"+date[2];
    }
    
    /**
     * Take a date and say what day of the week it is
     * @param date
     * @return getDayOfWeek
     */
    public static int getDayOfWeek(int[] date)
    {
    	int day = 1; // monday
    	int[] startdate = new int[3]; // initialize starting date
    	startdate[0] = 1;
    	startdate[1] = 1;
    	startdate[2] = 1900;
    	int[] nextdate = new int[3];
    	
    	if (compareDates(date, startdate) == 0)
    	{
    		return 1;
    	}
    	else
    	{
    		day ++;
    		nextdate = getNextDate(startdate);
    		while (true)
    		{
    			if (compareDates(nextdate, date) == 0)
    			{
    				return day; // return statement
    			}
    			day ++;
    			if (day == 8)
    			{
    				day = 1;
    			}
    			nextdate = getNextDate(nextdate);
    		}
    	}
    }
    
    /**
     * Get Thanksgiving date for the input year
     * @param year
     * @return getThanksgivingDate
     */
    public static int[] getThanksgivingDate(int year) {
    	int[] thanksgiving = new int[3];
    	thanksgiving[0] = 11;
    	thanksgiving[2] = year;
    	
    	int[] start = new int[3];
    	start[0] = 11;
    	start[1] = 1;
    	start[2] = year;
    	
    	if (getDayOfWeek(start) >= 4) {
    		if (getDayOfWeek(start) == 4) {
    			thanksgiving[1] = 22;
    		}
    		else {
    			thanksgiving[1] = 8 - getDayOfWeek(start) + 25;
    		}
    	}
    	else if (getDayOfWeek(start) < 4) {
    		thanksgiving[1] = 8 - getDayOfWeek(start) + 18;
    	}
    	
    	return thanksgiving;
    }
    
	/**
	 * Print calendar for the input month
	 * @param month
	 * @param year
	 */
	public static void printMonth(int month, int year) {
		int[] date = new int[3];
		int numcount; // count the number of dates in a line
		date[0] = month;
		date[1] = 1;
		date[2] = year;
		
		getDayOfWeek(date); // get day of the first day for the month
		
		System.out.println("--------------------");
		System.out.println(monthNames[month] + " " + year);
		System.out.println("Su Mo Tu We Th Fr Sa");
		
		if (getDayOfWeek(date) == 7) {
			numcount = 0;
		}
		else if (getDayOfWeek(date) == 1) {
			numcount = 1;
			System.out.print("   ");
		}
		else if (getDayOfWeek(date) == 2) {
			numcount = 2;
			System.out.print("      ");
		}
		else if (getDayOfWeek(date) == 3) {
			numcount = 3;
			System.out.print("         ");
		}
		else if (getDayOfWeek(date) == 4) {
			numcount = 4;
			System.out.print("            ");
		}
		else if (getDayOfWeek(date) == 5) {
			numcount = 5;
			System.out.print("               ");
		}
		else {
			numcount = 6;
			System.out.print("                  ");
		}
		
		for (int i = 1; i < monthDayCounts[month] + 1; i ++) {
			if (numcount == 7) {
				System.out.println(""); // new line
				numcount = 0;
			}
			if (i < 10) {
				System.out.print(i + "  ");
			}
			else if (i >= 10) {
				System.out.print(i + " ");
			}
			numcount = numcount + 1;
		}
			
		System.out.print('\n');
		System.out.println("--------------------");
	}


	/**    
	// TODO: as you complete methods above, uncomment the tests below
    public static void test()
    {    
        System.out.println("Testing isLeapYear...");
        System.out.println("isLeapYear(2020) should be true, yours gives "+isLeapYear(2020));
        System.out.println("isLeapYear(1901) should be false, yours gives "+isLeapYear(1901));
        System.out.println("isLeapYear(1800) should be false, yours gives "+isLeapYear(1800));
        System.out.println("isLeapYear(2000) should be true, yours gives "+isLeapYear(2000));
        
        System.out.println("Testing isValidDate...");
        System.out.println("30 day months....");
        System.out.println("isValidDate([9,30,1990]) should be true, yours gives "+isValidDate(new int[] {9,30,1990}));
        System.out.println("isValidDate([9,31,1990]) should be false, yours gives "+isValidDate(new int[] {9,31,1990}));
        System.out.println("isValidDate([11,30,1950]) should be true, yours gives "+isValidDate(new int[] {11,30,1950}));
        System.out.println("isValidDate([11,31,1950]) should be false, yours gives "+isValidDate(new int[] {11,31,1950}));
        System.out.println("isValidDate([4,30,1900]) should be true, yours gives "+isValidDate(new int[] {4,30,1900}));
        System.out.println("isValidDate([4,31,1900]) should be false, yours gives "+isValidDate(new int[] {4,31,1900}));
        System.out.println("isValidDate([6,30,1800]) should be true, yours gives "+isValidDate(new int[] {6,30,1800}));
        System.out.println("isValidDate([6,31,1800]) should be false, yours gives "+isValidDate(new int[] {6,31,1800}));
        System.out.println("February....");
        System.out.println("isValidDate([2,28,2000]) should be true, yours gives "+isValidDate(new int[] {2,28,2000}));
        System.out.println("isValidDate([2,29,2000]) should be true, yours gives "+isValidDate(new int[] {2,29,2000}));
        System.out.println("isValidDate([2,30,2000]) should be false, yours gives "+isValidDate(new int[] {2,30,2000}));
        System.out.println("isValidDate([2,28,2001]) should be true, yours gives "+isValidDate(new int[] {2,28,2001}));
        System.out.println("isValidDate([2,29,2001]) should be false, yours gives "+isValidDate(new int[] {2,29,2001}));
        System.out.println("isValidDate([2,30,2001]) should be false, yours gives "+isValidDate(new int[] {2,30,2001}));
        System.out.println("isValidDate([2,28,1900]) should be true, yours gives "+isValidDate(new int[] {2,28,1900}));
        System.out.println("isValidDate([2,29,1900]) should be false, yours gives "+isValidDate(new int[] {2,29,1900}));
        System.out.println("isValidDate([2,30,1900]) should be false, yours gives "+isValidDate(new int[] {2,30,1900}));
        System.out.println("October - 31 day month");
        System.out.println("isValidDate([10,30,2020]) should be true, yours gives "+isValidDate(new int[] {10,30,2020}));
        System.out.println("isValidDate([10,31,2020]) should be true, yours gives "+isValidDate(new int[] {10,31,2020}));
        System.out.println("Very invalid dates...");
        System.out.println("isValidDate([14,20,2005]) should be false, yours gives "+isValidDate(new int[] {14,20,2005}));
        System.out.println("isValidDate([5,40,2010]) should be false, yours gives "+isValidDate(new int[] {5,40,2010}));
        System.out.println("isValidDate([0,15,2000]) should be false, yours gives "+isValidDate(new int[] {0,15,2000}));
        System.out.println("isValidDate([2,31,2004]) should be false, yours gives "+isValidDate(new int[] {2,31,2004}));
        
        System.out.println("Testing compareDates...");
        System.out.println("compareDates([12,5,2010], [12,5,2010]) should give 0, yours gives "
                +compareDates(new int[] {12,5,2010},new int[] {12,5,2010}));
        System.out.println("compareDates([10,5,2005], [10,5,2010]) should give -1, yours gives "
                +compareDates(new int[] {10,5,2005},new int[] {10,5,2010}));
        System.out.println("compareDates([6,5,2005], [6,5,1910]) should give 1, yours gives "
                +compareDates(new int[] {6,5,2005},new int[] {6,5,1910}));
        System.out.println("compareDates([6,20,2004], [7,5,2004]) should give -1, yours gives "
                +compareDates(new int[] {6,20,2004},new int[] {7,5,2004}));
        System.out.println("compareDates([6,20,2004], [6,5,2004]) should give 1, yours gives "
                +compareDates(new int[] {6,20,2004},new int[] {6,5,2004}));
        
                
        System.out.println("Testing getNextDate...");
        System.out.println("getNextDate([10,14,2004]) should be 10/15/2004, yours gives "
                +dateToString(getNextDate(new int[] {10,14,2004})));
        System.out.println("getNextDate([10,31,2004]) should be 11/1/2004, yours gives "
                +dateToString(getNextDate(new int[] {10,31,2004})));
        System.out.println("getNextDate([12,31,2004]) should be 1/1/2005, yours gives "
                +dateToString(getNextDate(new int[] {12,31,2004})));
        System.out.println("getNextDate([2,28,2004]) should be 2/29/2004, yours gives "
                +dateToString(getNextDate(new int[] {2,28,2004})));
        System.out.println("getNextDate([2,29,2004]) should be 3/1/2004, yours gives "
                +dateToString(getNextDate(new int[] {2,29,2004})));
        
        System.out.println("Testing getDayOfWeek...");
        System.out.println("getDayOfWeek([1,1,2024]) should give 1, yours gives "+getDayOfWeek(new int[] {1,1,2024}));
        System.out.println("getDayOfWeek([12,31,2024]) should give 2, yours gives "+getDayOfWeek(new int[] {12,31,2024}));
        System.out.println("getDayOfWeek([10,15,1910]) should give 6, yours gives "+getDayOfWeek(new int[] {10,15,1910}));
        System.out.println("getDayOfWeek([8,25,1950]) should give 5, yours gives "+getDayOfWeek(new int[] {8,25,1950}));
    
        System.out.println("Testing getThanksgivingDate...");
        System.out.println("getThanksgivingDate(2024) should be 11/28/2024, yours gives "+ dateToString(getThanksgivingDate(2024)));
        System.out.println("getThanksgivingDate(2014) should be 11/27/2014, yours gives "+ dateToString(getThanksgivingDate(2014)));
        System.out.println("getThanksgivingDate(2023) should be 11/23/2023, yours gives "+ dateToString(getThanksgivingDate(2023)));
    }
    */
    
}