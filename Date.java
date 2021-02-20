import java.util.StringTokenizer;
import java.util.Calendar;

public class Date implements Comparable<Date>{

    private int year;
    private int month;
    private int day;
    public static final int MIN_YEAR = 1900;
    public static final int JAN = 1;
    public static final int FEB = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUG = 8;
    public static final int SEPT = 9;
    public static final int OCT = 10;
    public static final int NOV = 11;
    public static final int DEC = 12;
    public static final int DAY_28 = 28;
    public static final int DAY_29 = 29;
    public static final int DAY_30 = 30;
    public static final int DAY_31 = 31;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;

    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }

    /**
     * Parameterized constructor that initializes a Date object using
     * the month, day, and year of the date passed in
     * @param date - specified date in format "mm/dd/yyyy"
     */
    public Date(String date) {

        StringTokenizer st = new StringTokenizer(date, "/");

        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
        year = Integer.parseInt(st.nextToken());
    }

    /**
     * Non-parameterized constructor that initializes a Date object using the
     * current month, day, and year as obtained from the Calendar class
     */
    public Date() {

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
    }
    /**
     * Checks whether a Date is valid.
     *
     * A valid Date must not be earlier than the year 1900, nor past the current date.
     *
     * A valid Date must have a valid month and a valid date number for the given month.
     *
     * The date 2/29 must be valid if the given year is a leap year (assuming the date is not beyond the current date),
     * and invalid if the given year is NOT a leap year.
     *
     * @return boolean - true if Date is valid, false if Date is invalid
     */
    public boolean isValid() {

        boolean isValid = false;
        Date currentDate = new Date();

        if (this.year < MIN_YEAR || this.year > currentDate.year) {

            // invalid year
            isValid = false;
        }
        else{

            // valid year
            if (this.month < JAN || this.month > DEC){

                // invalid month
                isValid = false;
            }
            else if ((this.month == JAN || this.month == MARCH || this.month == MAY || this.month == JULY || this.month == AUG || this.month == OCT || this.month == DEC) && (this.day > DAY_31 || this.day < 1)){

                // invalid day
                isValid = false;
            }
            else if ((this.month == APRIL || this.month == JUNE || this.month == SEPT || this.month == NOV) && (this.day > DAY_30 || this.day < 0)){

                // invalid day
                isValid = false;
            }
            else if (this.month == FEB){

                // february; determine whether it's a leap year

                boolean isLeapYear = false;

                if(this.year % QUADRENNIAL == 0){

                    if(this.year % CENTENNIAL == 0){

                        if(this.year % QUARTERCENTENNIAL == 0){
                            // is a leap year
                            isLeapYear = true;
                        }
                        else{
                            // not a leap year
                            isLeapYear = false;
                        }
                    }
                    else{
                        // is a leap year
                        isLeapYear = true;
                    }
                }
                else{
                    // not a leap year
                    isLeapYear = false;
                }

                // check if day is valid

                if (isLeapYear && this.day > DAY_29 || this.day < 1){

                    // invalid
                    isValid = false;
                    //System.out.println("Invalid Date!");
                }
                else if(!isLeapYear && this.day > DAY_28 || this.day < 1){

                    // invalid
                    isValid = false;
                    //System.out.println("Invalid Date!");
                }
                else{

                    // is valid
                    isValid = true;
                }
            }
            else{

                isValid = true;
            }

            // check if date is from current year
            if (this.year == currentDate.year){

                if (this.month > currentDate.month){

                    // invalid
                    isValid = false;
                }
                else if(this.month == currentDate.month){

                    if(this.day <= currentDate.day){

                        // valid
                        isValid = true;
                    }
                    else{

                        // invalid
                        isValid = false;
                    }
                }
            }
        }

        return isValid;
    }

    /**
     * Overrides the toString() method
     * @return date in the format of "mm/dd/yyy"
     */
    @Override
    public String toString(){
        return month + "/" + day + "/" + year;
    }

    /**
     * Overrides the compareTo() method
     * @param date - the date you want to compare "this" date object to
     * @return int; 0 if "this" is equal to "date", -1 if this is less than "date", and 1 if "this" is greater than "date"
     */
    @Override
    public int compareTo(Date date){

        if(this.year == date.year){

            if(this.month == date.month){

                if(this.day == date.day){
                    return 0;
                }
                else if(this.day < date.day){

                    return -1;
                }
                else{
                    return 1;
                }
            }
            else if(this.month < date.month){
                return -1;
            }
            else{
                return 1;
            }
        }
        else if(this.year < date.year){
            return -1;
        }
        else{
            return 1;
        }

    }

}
