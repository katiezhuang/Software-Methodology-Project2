/**
 * This class holds the information for a Parttime employee.
 * Every Parttime object, in addition to the Employee superclass fields,
 * has a workingHours (represented by a int) and a hourlyRate (represented by a double)
 * The Parttime class is a subclass of Employee class.
 *
 * @author Ria Anand, Katie Zhuang
 */
public class Parttime extends Employee{

    private int workingHours;
    private double hourlyRate;
    public static final int MAX_HOURS = 80;
    
    /**
     * Constructor to initialize a Parttime employee object.
     * @param profile - assigns the given profile to the newly created Parttime object.
     * @param hourlyRate - assigns the hourly rate to the newly created Parttime object.
     */
    public Parttime(Profile profile, double hourlyRate){
        super(profile);
        this.hourlyRate = hourlyRate;
    }
    /**
     * Getter method to access private field workingHours.
     * @return int - the number of hours worked
     */
    public int getWorkingHours() {
        return this.workingHours;
    }
    /**
     * Setter method to set private field workingHours.
     */
    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }
    /**
     * Overrides the toString method for a Parttime object
     * @return String in the format:
     * "Payment $[payment]::PART TIME::Hourly Rate $[hourlyRate]::Hours worked this period: [workingHours]"
     */
    @Override
    public String toString(){

        return super.toString()
                + "PART TIME"
                + "::"
                + "Hourly Rate $"
                + this.hourlyRate
                + "::"
                + "Hours worked this period: "
                + this.workingHours;
    }
    /**
     * Equals method to check equivalence of object if object is an instance of Parttime.
     * Override method.
     * @param obj - Object to compare
     * @return boolean - true if matching profile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (!(obj instanceof Parttime)) {
            return false;
        }
        Parttime parttime = (Parttime) obj;

        return super.equals(parttime);
    }
    /**
     * Method to calculate the payments of one 2-week period for a Parttime object.
     * Any extra hours past 80 hours worked will be paid 1.5 times the hourlyRate.
     * Override method.
     */
    @Override
    public void calculatePayment(){
        if (this.workingHours <= MAX_HOURS) {
            this.setPayment(this.workingHours * this.hourlyRate);
        }
        else {
            int extraHours = this.workingHours - MAX_HOURS;
            this.setPayment((MAX_HOURS * this.hourlyRate) + (extraHours * 1.5 * this.hourlyRate));
        }

    }

}
