public class Management extends Fulltime {

    private int managementCode; // can be 1, 2, or 3
    public static final double MANAGER_BONUS = 5000;
    public static final double DEP_HEAD_BONUS = 9500;
    public static final double DIRECTOR_BONUS = 12000;

    // Constructor
    // managementCode is either 1 (manager), 2 (dep head), or 3 (director)
    public Management(Profile profile, double salary, int managementCode){

        super(profile, salary);
        this.managementCode = managementCode;

    }

    @Override
    public String toString() {

        String managerType;
        if(this.managementCode == 1){
            managerType = "Manager";
        }
        else if(this.managementCode == 2){
            managerType = "DepartmentHead";
        }
        else{
            managerType = "Director";
        }

        return super.toString() + "::" + managerType + " Compensation " + String.format("$%,.2f", this.getPayment());
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }
        if (!(obj instanceof Management)) {
            return false;
        }

        Management management = (Management) obj;

        return super.equals(management);
    }

    @Override
    public void calculatePayment(){

        if(this.managementCode == 1){
            this.setPayment(this.getPayment() + MANAGER_BONUS);
        }
        else if(this.managementCode == 2){
            this.setPayment(this.getPayment() + DEP_HEAD_BONUS);
        }
        else if(this.managementCode == 3){
            this.setPayment(this.getPayment() + DIRECTOR_BONUS);
        }

    }

}

