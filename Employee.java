/**
 * This class holds the information for a single Employee.
 * Every Employee object has a profile (Profile object) and a field that holds their money earned (represented by a double).
 * The Employee class is the superclass of Parttime and Fulltime employees.
 *
 * @author Ria Anand, Katie Zhuang
 */
public class Employee {
    private Profile profile;
    private double paymentPerPeriod;
    
    /**
     * Constructor to initialize an Employee object.
     * @param profile - assigns the given profile to the newly created Employee object.
     */
    public Employee(Profile profile) {
        this.profile = profile;
    }
    /**
     * Method to calculate the payments for an Employee.
     * Overridden in the Parttime, Fulltime, and Management classes.
     */
    public void calculatePayment() { }
    /**
     * Getter method to access private field profile.
     * @return Profile - the profile of the Employee
     */
    public Profile getProfile() {
        return this.profile;
    }
    /**
     * Getter method to access private field payment.
     * @return double - the payment of the Employee
     */
    public double getPayment() {
        return this.paymentPerPeriod;
    }
    /**
     * Setter method to set private field payment.
     */
    public void setPayment(double paymentPerPeriod) {
        this.paymentPerPeriod = paymentPerPeriod;
    }
    
    /**
     * Equals method to check equivalence of object if object is an instance of Employee.
     * Override method.
     * @param obj - Object to compare
     * @return boolean - true if matching profile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }

        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) obj;
        return (employee.getProfile().equals(this.getProfile()));
    }
    
    /**
     * Overrides the toString method for a Employee object
     * @return String in the format: "Payment $[payment]::"
     */
    @Override
    public String toString() {
        return this.getProfile().toString()
                + "Payment $"
                + this.paymentPerPeriod
                + "::";
    }
}
