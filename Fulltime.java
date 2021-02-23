/**
 * This class holds the information for a Fulltime employee.
 * Every Fulltime object, in addition to the Employee superclass fields,
 * has a salary (represented by a double)
 * The Fulltime class is a subclass of Employee class.
 *
 * @author Ria Anand, Katie Zhuang
 */
public class Fulltime extends Employee{

    private double salary;
    /**
     * Constructor to initialize a Fulltime employee object.
     * @param profile - assigns the given profile to the newly created Fulltime object.
     * @param salary - assigns the given salary to the newly created Fulltime object.
     */
    public Fulltime(Profile profile, double salary){
        super(profile);
        this.salary = salary;
    }
    
    /**
     * Overrides the toString method for a Fulltime object
     * @return String in the format: "Payment $[payment]::FULL TIME::Annual Salary [salary]"
     */
    @Override
    public String toString() {
        return super.toString() + "::FULL TIME::Annual Salary " + String.format("$%,.2f", salary);
    }
    
    /**
     * Equals method to check equivalence of object if object is an instance of Fulltime.
     * Override method.
     * @param obj - Object to compare
     * @return boolean - true if matching profile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if(this == obj){
           return true;
        }
        if (!(obj instanceof Fulltime)) {
            return false;
        }
        Fulltime fulltime = (Fulltime) obj;

        return super.equals(fulltime);

    }
    /**
     * Method to calculate the payments of one payment period for a Fulltime object.
     * Override method.
     */
    @Override
    public void calculatePayment(){

        setPayment(salary/26);

    }

}
