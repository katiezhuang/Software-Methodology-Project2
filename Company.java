/**
 *  This is a container class that holds a list of Employee objects in an array-based implementation.
 *  Methods within the Company class perform operations on the bag data structure.
 *
 *  @author Ria Anand, Katie Zhuang
 */
public class Company {
    private Employee[] emplist;
    private int numEmployee;
    public static final int NOT_FOUND = -1;
    public static final int GROWTH_FACTOR = 4;
    public static final int CS_DEPARTMENT = 1;
    public static final int ECE_DEPARTMENT = 2;
    public static final int IT_DEPARTMENT = 3;

    /**
     * Constructor to create an empty bag.
     */
    public Company() {
        emplist = new Employee[GROWTH_FACTOR];
        numEmployee = 0;
    }

    /**
     * Helper method to access private field numEmployee.
     * @return the number of employees currently within the bag
     */
    public int getNumEmployee() {
        return this.numEmployee;
    }
    
    /**
     * Helper method to find an employee within emplist that has a matching profile.
     * @param employee - Employee object to find in this emplist
     * @return int - index of employee if found, NOT_FOUND otherwise
     */
    private int find(Employee employee) {
        employee = new Employee(employee.getProfile());
        int index = 0;
        while(index < numEmployee){
            if(employee.equals(emplist[index])){
                // employee found
                return index;
            }
            index++;
        }
        // employee was not found
        return NOT_FOUND;
    }

    /**
     * Helper method to grow the capacity of the emplist array by 4.
     */
    private void grow() {
        Employee[] biggerCompany = new Employee[numEmployee + GROWTH_FACTOR];

        for(int i = 0; i < numEmployee; i++){
            biggerCompany[i] = emplist[i];
        }
        emplist = biggerCompany;
    }
    
    /**
     * Adds an Employee to this Company.
     * First checks to see if profile already present in emplist. If so, returns false
     * @param employee - Employee object to add to this Library
     * @return boolean - true if employee successfully added, false otherwise
     */
    public boolean add(Employee employee) { //check the profile before adding

        if (numEmployee == emplist.length) {
            this.grow();
        }
        int index = find(employee);
        if (index == NOT_FOUND) {
            emplist[numEmployee] = employee;
            numEmployee++;
            return true;
        }
        else {
            return false;
        }

    }
    
    /**
     * Removes an Employee from emplist.
     * @param employee - Employee object to remove from the bag
     * @return boolean - true if employee is successfully removed, false if employee doesn't exist in this Company
     */
    public boolean remove(Employee employee) { //maintain the original sequence
        int index = find(employee);
        // make sure find is accounting for employees that don't exist
        if (index == NOT_FOUND) {
            return false;
        }
        else {
            emplist[index] = null;
            numEmployee--;
            int counter = index;
            while (counter + 1 < emplist.length) {
                if (emplist[counter + 1] != null) {
                    emplist[counter] = emplist[counter + 1];
                    counter++;
                } else {
                    emplist[counter] = null;
                    break;
                }

            }
            emplist[counter] = null;
            return true;
        }

    }
    
    /**
     * Sets the hours worked of an Employee from emplist.
     * @param employee - Employee object to set hours to
     * @return boolean - true if employee is successfully set, false if employee doesn't exist or is not a part-time employee
     */
    public boolean setHours(Employee employee) { //set working hours for a part time
        int index = find(employee);
        if (index == NOT_FOUND) {
            return false;
        }
        else {
            if (emplist[index] instanceof Parttime && employee instanceof Parttime) {
                Parttime pt = (Parttime) employee;
                int workHours = pt.getWorkingHours();
                Parttime parttime = (Parttime) emplist[index];
                parttime.setWorkingHours(workHours);
                return true;
            }
            return false;
        }
    }
    
    /**
     * Processes payments of all employees by calling calculatePayment() methods.
     */
    public void processPayments() { //process payments for all employees
        for (int i = 0; i < numEmployee; i++) {
            emplist[i].calculatePayment();
        }
    }
    
    /**
     * Prints the list of employees in the bag.
     */
    public void print() { //print earning statements for all employees
        int counter = 0;
        while(counter < emplist.length) {
            if (emplist[counter] != null) {
                System.out.println(emplist[counter].toString());
                counter++;
            }
            else {
                break;
            }

        }
    }
    /**
     * Helper method to determine whether the first date comes before the second date
     * @param date1 - first date
     * @param date2 - second date
     * @return boolean - true if date1 comes before date2, false if date2 comes before date1
     */
    private boolean before(Date date1, Date date2) {
        if (date1.getYear() < date2.getYear()) {
            return true;
        }
        else if (date1.getYear() > date2.getYear()) {
            return false;
        }
        else {
            if (date1.getMonth() < date2.getMonth()) {
                return true;
            }
            else if (date1.getMonth() > date2.getMonth()) {
                return false;
            }
            else {
                if (date1.getDay() < date2.getDay()) {
                    return true;
                }
                else if (date1.getDay() > date2.getDay()) {
                    return false;
                }
                else {
                    return true;
                }
            }
        }
    }
    
    /**
     * Used by sort() function to get the corresponding department codes of each department.
     * @param employee - Employee object to retrieve department code
     * @return int - departmentCode number
     */
    private int departmentCode(Employee employee) {
        int codeRetrieve = 0;
        if (employee.getProfile().getDepartment().equals("CS")) {
            codeRetrieve = CS_DEPARTMENT;
        }
        else if (employee.getProfile().getDepartment().equals("ECE")) {
            codeRetrieve = ECE_DEPARTMENT;
        }
        else {
            codeRetrieve = IT_DEPARTMENT;
        }
        return codeRetrieve;
    }

    /**
     * Helper method to sort an array using selection sort.
     * @param arr - array to be sorted
     * @param num - specifies how array will be sorted; if num is 0, sort by dateHired and else, sort by department
     */
    private void sort(Employee[] arr, int num) {
        for (int i = 0; i < numEmployee - 1; i++) {
            int min = i;
            for (int j = i + 1; j < numEmployee; j++) {
                if (num == 0) {
                    if(before(arr[j].getProfile().getDateHired(), arr[min].getProfile().getDateHired())) {
                        min = j;
                    }
                }
                else {
                    if (departmentCode(arr[j]) < departmentCode(arr[min])) {
                        min = j;
                    }
                }
            }
            Employee temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Prints the list of employees by dateHired (ascending).
     */
    public void printByDate() { //print earning statements by date hired
        sort(emplist, 0);
        this.print();
    }
    
     /**
     * Prints the list of employees by department in the following order (CS, ECE, IT).
     */
    public void printByDepartment() { //print earning statements by department
        sort(emplist, 1);
        this.print();
    }
}
 
