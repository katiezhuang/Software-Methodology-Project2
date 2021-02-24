/**
 * This class processes command lines from the console, in order to edit the company
 * Accepted commands include:
 * A[P/F] [name] [department] [dateHired] [hourlyRate/salary]--> to add a part-time or full time employee to the company
 * AM [name] [department] [dateHired] [salary] [managementRole]--> to add a part-time or full time employee to the company
 * R [name] [department] [dateHired] --> to remove an employee from the company
 * C --> to process payments of all employees
 * S [name] [department] [dateHired] [hoursWorked]--> to set the number of hours worked for a part-time employee
 * PA --> to output the list of employees to the console with the current sequence
 * PD --> to output the list of employees by department in the order (CS, ECE, IT)
 * PH --> to output the list of employees by the date hired in ascending order
 * Q --> to quit the program
 *
 * @author Ria Anand, Katie Zhuang
 */
import java.util.StringTokenizer;
import java.util.Scanner;

public class PayrollProcessing {
    String commandToken;
    String nameToken;
    String departmentToken;
    String dateToken;
    String payOrHoursToken;
    String codeToken;
    public static final int TOKEN_LENGTH = 2;
    public static final int PARTTIME = 0;
    public static final int FULLTIME = 1;

    /**
     *Constructor that initializes a Payroll Processing object
     */
    public PayrollProcessing() {

    }

    /**
     * Enum that contains fields for different possible types of tokens from the command line.
     * The types correspond to the number of tokens read from each line.
     */
    public enum tokenType {
        type1,
        type2,
        type3,
        type4,
        type5,
        type6,
        type7orMore
    }

    /**
     * Determines which command has been entered through the command line and calls the corresponding method.
     * @param type - the type of token entered
     * @param company - Company object containing Employee array
     */
    private void findCommand(tokenType type, Company company) {
        switch (type) {
            case type1:
                //it's one of the prints PA, PD, PH, or C
                if (commandToken.length() > TOKEN_LENGTH) {
                    System.out.println("Command '" + commandToken + "' not supported!");
                } else if (commandToken.length() == 1) {
                    if (commandToken.charAt(0) == 'C') {
                        calculateEarnings(company);
                    } else {
                        System.out.println("Command '" + commandToken + "' not supported!");
                    }
                } else {
                    if (commandToken.charAt(0) != 'P') {
                        System.out.println("Command '" + commandToken + "' not supported!");
                    } else {
                        if (commandToken.charAt(1) == 'A') {
                            printEarnings(company);
                        } else if (commandToken.charAt(1) == 'D') {
                            printDepartment(company);
                        } else if (commandToken.charAt(1) == 'H') {
                            printdateHired(company);
                        } else {
                            System.out.println("Command '" + commandToken + "' not supported!");
                        }
                    }
                }
                break;
            case type2:
            case type3:
            case type7orMore:
                // too many tokens, display invalid
                System.out.println("Command '" + commandToken + "' not supported!");
                break;
            case type4:
                // R command
                if (commandToken.equals("R")) {
                    removeEmployee(company);
                } else {
                    System.out.println("Command '" + commandToken + "' not supported!");
                }
                break;
            case type5:
                // either AP, AF, or S
                if (commandToken.length() > TOKEN_LENGTH) {
                    System.out.println("Command '" + commandToken + "' not supported!");
                } else if (commandToken.length() == 1) {
                    if (commandToken.charAt(0) == 'S') {
                        setHoursforParttime(company);
                    } else {
                        System.out.println("Command '" + commandToken + "' not supported!");
                    }
                } else {
                    if (commandToken.charAt(0) != 'A') {
                        System.out.println("Command '" + commandToken + "' not supported!");
                    } else {
                        if (commandToken.charAt(1) == 'P') {
                            addEmployee(company, 0);
                        } else if (commandToken.charAt(1) == 'F') {
                            addEmployee(company, 1);
                        } else {
                            System.out.println("Command '" + commandToken + "' not supported!");
                        }
                    }
                }
                break;
            case type6:
                // has to be AM
                if (commandToken.equals("AM")) {
                    addEmployee(company, 1);
                }
                else {
                    System.out.println("Command '" + commandToken + "' not supported!");
                }
        }
    }

    /**
     * Runs the PayrollProcessing and actually accepts and tokenizes the input commands from the command line.
     */
    public void run() {
        System.out.println("Payroll Processing Starts.");
        Company company = new Company();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("Q")) {
                break;
            } else if (line.equals("")) {
                continue;
            }
            else if(line.charAt(0) == ' '){
                line = line.trim();
                continue;
            } else {
                commandToken = null;
                nameToken = null;
                departmentToken = null;
                dateToken = null;
                payOrHoursToken = null;
                codeToken = null;

                StringTokenizer tokenizer = new StringTokenizer(line);
                tokenType type = tokenType.type1;
                commandToken = tokenizer.nextToken();
                while (tokenizer.hasMoreTokens()) {
                    if (type == tokenType.type1) {
                        type = tokenType.type2;
                        nameToken = tokenizer.nextToken();
                    } else if (type == tokenType.type2) {
                        type = tokenType.type3;
                        departmentToken = tokenizer.nextToken();
                    } else if (type == tokenType.type3) {
                        type = tokenType.type4;
                        dateToken = tokenizer.nextToken();
                    } else if (type == tokenType.type4) {
                        type = tokenType.type5;
                        payOrHoursToken = tokenizer.nextToken();
                    } else if (type == tokenType.type5) {
                        type = tokenType.type6;
                        codeToken = tokenizer.nextToken();
                    } else {
                        type = tokenType.type7orMore;
                        break;
                    }
                }
                findCommand(type, company);
            }
        }
        System.out.println("Payroll Processing completed");
    }

    /**
     * Outputs the list of employees to the console with the current sequence.
     * @param company - Company object containing Employee array
     */
    private void printEarnings(Company company) {
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        } else {
            System.out.println("--Printing earning statements for all employees--");
            company.print();
        }
    }

    /**
     * Outputs the list of employees by department in the order (CS, ECE, IT).
     * @param company - Company object containing Employee array
     */
    private void printDepartment(Company company) {
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        } else {
            System.out.println("--Printing earning statements by department--");
            company.printByDepartment();
        }
    }

    /**
     * Outputs the list of employees by date hired in ascending order.
     * @param company - Company object containing Employee array
     */
    private void printdateHired(Company company) {
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        } else {
            System.out.println("--Printing earning statements by date hired--");
            company.printByDate();
        }
    }

    /**
     * Adds an Employee to the company.
     * @param company - Company object containing Employee array
     * @param PorF - either a part-time or full time employee
     */
    private void addEmployee(Company company, int PorF) {

        if(!departmentToken.equals("CS") && !departmentToken.equals("ECE") && !departmentToken.equals("IT")){

            System.out.println("'" + departmentToken + "' is not a valid department code.");
            return;
        }

        if(Double.parseDouble(payOrHoursToken) < 0){

            if(PorF == PARTTIME){
                System.out.println("Pay rate cannot be negative.");
            }
            else if(PorF == FULLTIME){
                System.out.println("Salary cannot be negative.");
            }
            return;

        }
        Date dateHired = new Date(dateToken);

        if (!dateHired.isValid()){

            System.out.println(dateToken + " is not a valid date!");
            return;
        }

        Employee employee;
        Profile profile = new Profile(nameToken, departmentToken, dateHired);

        if (PorF == PARTTIME) {
            // add a parttime
            employee = new Parttime(profile, Double.parseDouble(payOrHoursToken), 0, 0);

        }
        else {
            // add a fulltime

            if (codeToken == null) {

                employee = new Fulltime(profile, Double.parseDouble(payOrHoursToken), 0);

            } else if (codeToken.equals("1") || codeToken.equals("2") || codeToken.equals("3")) {

                employee = new Management(profile, Double.parseDouble(payOrHoursToken), Integer.parseInt(codeToken), 0);
            }
            else{

                System.out.println("Invalid management code.");
                return;
            }
        }

        boolean addSuccessful = company.add(employee);

        if(addSuccessful){
            System.out.println("Employee added.");
        }
        else{
            System.out.println("Employee is already in the list.");
        }
    }

    /**
     * Removes an Employee from the company.
     * @param company - Company object containing Employee array
     */
    private void removeEmployee(Company company) {

        if(company.getNumEmployee() == 0){
            System.out.println("Employee database is empty.");
            return;
        }
        Date date = new Date(dateToken);
        Profile profile = new Profile(nameToken, departmentToken, date);
        Employee employee = new Employee(profile, 0);
        boolean removeSuccessful = company.remove(employee);
        if(removeSuccessful){
            System.out.println("Employee removed.");
        }
        else{
            System.out.println("Employee does not exist.");
        }

    }

    /**
     * Calculates the earnings of every Employee in the company.
     * @param company - Company object containing Employee array
     */
    private void calculateEarnings(Company company) {

        if(company.getNumEmployee() == 0){
            System.out.println("Employee database is empty.");
            return;
        }

        company.processPayments();
        System.out.println("Calculation of employee payments is done.");
    }

    /**
     * Sets the hours worked for a parttime employee by calling the setHours method in Company class.
     * @param company - Company object containing Employee array
     */
    private void setHoursforParttime(Company company) {

        Date date = new Date(dateToken);
        if (date.isValid()) {

            try{
                Integer.parseInt(payOrHoursToken);
            } catch (NumberFormatException nfe){
                System.out.println("Working hours must be an integer.");
                return;
            }

            if(Integer.parseInt(payOrHoursToken) < 0){
                System.out.println("Working hours cannot be negative");
                return;
            }

            if(Integer.parseInt(payOrHoursToken) > Parttime.MAX_TOTAL_HOURS){
                System.out.println("Invalid Hours: over " + Parttime.MAX_TOTAL_HOURS + ".");
                return;
            }

            Profile profile = new Profile(nameToken, departmentToken, date);

            Parttime parttime = new Parttime(profile, 0, 0, Integer.parseInt(payOrHoursToken));
            if (company.setHours(parttime)) {
                System.out.println("Working hours set.");
                return;
            }
            else {
                System.out.println("Employee does not exist.");
            }
        }
        else {
            System.out.println(dateToken + " is not a valid date!");
        }
    }

}
