/**
 * This class holds the information for a Management employee.
 * Every Management object has Fulltime superclass fields
 * and can either be a manager (1), a department head (2), or a director (3).
 * The Management class is a subclass of Fulltime class.
 *
 * @author Ria Anand, Katie Zhuang
 */
public class Management extends Fulltime {

    private int managementCode; // can be 1, 2, or 3
    public static final double MANAGER_BONUS = 5000;
    public static final double DEP_HEAD_BONUS = 9500;
    public static final double DIRECTOR_BONUS = 12000;
    public static final double MANAGER_CODE = 1;
    public static final double DEP_HEAD_CODE = 2;
    public static final double DIRECTOR_CODE = 3;

    /**
     * Constructor to initialize a Management employee object.
     * @param profile - assigns the given profile to the newly created Management object.
     * @param salary - assigns the given salary to the newly created Management object.
     * @param managementCode - assigns the given management code to the newly created Management object.
     * @param paymentPerPeriod - assigns the given payment per period to the newly created Management object.
     */
    public Management(Profile profile, double salary, int managementCode, double paymentPerPeriod){
        super(profile, salary, paymentPerPeriod);
        this.managementCode = managementCode;
    }

    /**
     * Overrides the toString method for a Management object
     * @return String in the format:
     * "Payment $[payment]::FULL TIME::Annual Salary [salary]::[managerType] Compensation $[managerTypeBonus]"
     */
    @Override
    public String toString() {

        String managerType;
        double managerCompensation;
        if(this.managementCode == MANAGER_CODE){
            managerType = "Manager";
            managerCompensation = MANAGER_BONUS/Company.NUM_PAY_PERIODS;
        }
        else if(this.managementCode == DEP_HEAD_CODE){
            managerType = "DepartmentHead";
            managerCompensation = DEP_HEAD_BONUS/Company.NUM_PAY_PERIODS;
        }
        else{
            managerType = "Director";
            managerCompensation = DIRECTOR_BONUS/Company.NUM_PAY_PERIODS;
        }

        return super.toString() + "::" + managerType + " Compensation " + String.format("$%,.2f", managerCompensation);
    }

    /**
     * Equals method to check equivalence of object if object is an instance of Management.
     * Override method.
     * @param obj - Object to compare
     * @return boolean - true if matching profile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }

        return super.equals(obj);
    }

    /**
     * Method to calculate the payments of one payment period for a Management object, including their respective bonus.
     * Override method.
     */
    @Override
    public void calculatePayment(){

        super.calculatePayment();

        if(this.managementCode == MANAGER_CODE){
            this.setPayment(this.getPayment() + MANAGER_BONUS/Company.NUM_PAY_PERIODS);
        }
        else if(this.managementCode == DEP_HEAD_CODE){
            this.setPayment(this.getPayment() + DEP_HEAD_BONUS/Company.NUM_PAY_PERIODS);
        }
        else if(this.managementCode == DIRECTOR_CODE){
            this.setPayment(this.getPayment() + DIRECTOR_BONUS/Company.NUM_PAY_PERIODS);
        }
    }
}
