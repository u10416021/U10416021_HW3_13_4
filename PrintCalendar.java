//u10416021 張馨容
import java.util.Scanner;
import java.util.*;

public class PrintCalendar {
  /** Main method */
  public static void main(String[] args) {
  	//create an scanner
    Scanner input = new Scanner(System.in);
    //create a calendar
	Calendar calendar = new GregorianCalendar();
	//create a date
	Date date = new Date();
	//get instance of the calendar
    Calendar cal = Calendar.getInstance();
    //set the current time
    cal.setTime(date);
    
    //data field
    int myYear = cal.get(Calendar.YEAR);
    int myMonth = cal.get(Calendar.MONTH)+1;
	int year,month;
	int len = args.length;
	
	
	//user enter month first and then enter year,month is not empty
	if(len==2&&args[0].length()<=2&&args[1].length()>2){
		month = Integer.parseInt(args[0]);
		year = Integer.parseInt(args[1]);
	}
	//user enter year first and then enter month,and month is not empty
	else if (len==2&&args[0].length()>2&&args[1].length()<=2){
		year = Integer.parseInt(args[0]);
		month = Integer.parseInt(args[1]);
	}
	//user only enter month,year is not entered ,and month is not empty
	else if (len==1&&args[0].length()<=2){
		month = Integer.parseInt(args[0]);
		year = myYear;
	}
	//user only enter year ,year isn't entered
	else if (len==1&&args[0].length()>2){
		year = Integer.parseInt(args[0]);
		month = myMonth;
	}
	//user enter nothing
	else {
		year = myYear;
		month = myMonth;
	}
    // Print calendar for the month of the year
    printMonth(year, month);
  }

  /** Print the calendar for a month in a year */
  public static void printMonth(int year, int month) {
    // Print the headings of the calendar
    printMonthTitle(year, month);

    // Print the body of the calendar
    printMonthBody(year, month);
  }

  /** Print the month title, e.g., May, 1999 */
  public static void printMonthTitle(int year, int month) {
    System.out.println("         " + getMonthName(month)
      + " " + year);
    System.out.println("-----------------------------");
    System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
  }

  /** Get the English name for the month */
  public static String getMonthName(int month) {
    String monthName = "";
    switch (month) {
      case 1: monthName = "January"; break;
      case 2: monthName = "February"; break;
      case 3: monthName = "March"; break;
      case 4: monthName = "April"; break;
      case 5: monthName = "May"; break;
      case 6: monthName = "June"; break;
      case 7: monthName = "July"; break;
      case 8: monthName = "August"; break;
      case 9: monthName = "September"; break;
      case 10: monthName = "October"; break;
      case 11: monthName = "November"; break;
      case 12: monthName = "December";
    }

    return monthName;
  }

  /** Print month body */
  public static void printMonthBody(int year, int month) {
    // Get start day of the week for the first date in the month
    int startDay = getStartDay(year, month);

    // Get number of days in the month
    int numberOfDaysInMonth = getNumberOfDaysInMonth(year, month);

    // Pad space before the first day of the month
    int i = 0;
    for (i = 0; i < startDay; i++)
      System.out.print("    ");

    for (i = 1; i <= numberOfDaysInMonth; i++) {
      System.out.printf("%4d", i);

      if ((i + startDay) % 7 == 0)
        System.out.println();
    }

    System.out.println();
  }

  /** Get the start day of month/1/year */
  public static int getStartDay(int year, int month) {
    final int START_DAY_FOR_JAN_1_1800 = 3;
    // Get total number of days from 1/1/1800 to month/1/year
    int totalNumberOfDays = getTotalNumberOfDays(year, month);

    // Return the start day for month/1/year
    return (totalNumberOfDays + START_DAY_FOR_JAN_1_1800) % 7;
  }

  /** Get the total number of days since January 1, 1800 */
  public static int getTotalNumberOfDays(int year, int month) {
    int total = 0;

    // Get the total days from 1800 to 1/1/year
    for (int i = 1800; i < year; i++)
      if (isLeapYear(i))
        total = total + 366;
      else
        total = total + 365;

    // Add days from Jan to the month prior to the calendar month
    for (int i = 1; i < month; i++)
      total = total + getNumberOfDaysInMonth(year, i);

    return total;
  }

  /** Get the number of days in a month */
  public static int getNumberOfDaysInMonth(int year, int month) {
    if (month == 1 || month == 3 || month == 5 || month == 7 ||
      month == 8 || month == 10 || month == 12)
      return 31;

    if (month == 4 || month == 6 || month == 9 || month == 11)
      return 30;

    if (month == 2) return isLeapYear(year) ? 29 : 28;

    return 0; // If month is incorrect
  }

  /** Determine if it is a leap year */
  public static boolean isLeapYear(int year) {
    return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
  }
}
