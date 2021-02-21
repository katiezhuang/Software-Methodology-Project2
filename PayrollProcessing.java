import java.util.StringTokenizer;
import java.util.Scanner;

public class PayrollProcessing {
    String commandToken;
    String nameToken;
    String departmentToken;
    String dateToken;
    String payOrHoursToken;
    String codeToken;

    public PayrollProcessing() {

    }

    public enum tokenType {
        type1,
        type2,
        type3,
        type4,
        type5,
        type6,
        type7orMore
    }

    private void findCommand(tokenType type, Company company) {
        switch (type) {
            case type1:
                //it's one of the prints PA, PD, PH, or C
                if (commandToken.length() > 2) {
                    System.out.println("Invalid command!");
                } else if (commandToken.length() == 1) {
                    if (commandToken.charAt(0) == 'C') {
                        calculateEarnings(company);
                    } else {
                        System.out.println("Invalid command!");
                    }
                } else {
                    if (commandToken.charAt(0) != 'P') {
                        System.out.println("Invalid command!");
                    } else {
                        if (commandToken.charAt(1) == 'A') {
                            printEarnings(company);
                        } else if (commandToken.charAt(1) == 'D') {
                            printDepartment(company);
                        } else if (commandToken.charAt(1) == 'H') {
                            printdateHired(company);
                        } else {
                            System.out.println("Invalid command!");
                        }
                    }
                }
                break;
            case type2:
            case type3:
            case type7orMore:
                // too many tokens, display invalid
                System.out.println("Invalid command!");
                break;
            case type4:
                // R command
                if (commandToken.equals("R")) {
                    removeEmployee(company);
                } else {
                    System.out.println("Invalid command!");
                }
                break;
            case type5:
                // either AP, AF, or S
                if (commandToken.length() > 2) {
                    System.out.println("Invalid command!");
                } else if (commandToken.length() == 1) {
                    if (commandToken.charAt(0) == 'S') {
                        setHoursforParttime(company);
                    } else {
                        System.out.println("Invalid command!");
                    }
                } else {
                    if (commandToken.charAt(0) != 'A') {
                        System.out.println("Invalid command!");
                    } else {
                        if (commandToken.charAt(1) == 'P') {
                            addEmployee(company, 0);
                        } else if (commandToken.charAt(1) == 'F') {
                            addEmployee(company, 1);
                        } else {
                            System.out.println("Invalid command!");
                        }
                    }
                }
                break;
            case type6:
                // has to be AM

        }
    }

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

    private void printEarnings(Company company) {
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        } else {
            System.out.println("--Printing earning statements for all employees--");
            company.print();
        }
    }

    private void printDepartment(Company company) {
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        } else {
            System.out.println("--Printing earning statements by department--");
            company.printByDepartment();
        }
    }

    private void printdateHired(Company company) {
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        } else {
            System.out.println("--Printing earning statements by date hired--");
            company.printByDate();
        }
    }

    private void addEmployee(Company company, int PorF) {

        if(!departmentToken.equals("CS") && !departmentToken.equals("ECE") && !departmentToken.equals("IT")){

            System.out.println("invalid department code");
            return;
        }

        if(Double.parseDouble(payOrHoursToken) < 0){

            System.out.println("invalid salary/hourly rate");
            return;

        }
        Date dateHired = new Date(dateToken);

        // do I need this?
        if (!dateHired.isValid()){

            System.out.println("invalid date");
            return;
        }

        Employee employee;
        Profile profile = new Profile(nameToken, departmentToken, dateHired);

        if (PorF == 0) {
            // add a parttime - might have to be changed based on Katie's code for parttime
            employee = new Parttime(profile, Double.parseDouble(payOrHoursToken));

        }
        else {
            // add a fulltime

            if (codeToken == null) {

                employee = new Fulltime(profile, Double.parseDouble(payOrHoursToken));

            } else if (codeToken.equals("1") || codeToken.equals("2") || codeToken.equals("3")) {

                employee = new Management(profile, Double.parseDouble(payOrHoursToken), Integer.parseInt(codeToken));
            }
            else{

                System.out.println("invalid management code");
                return;

            }
            // I wrote:
            // Employee fulltime = new Fulltime(profile, Double.parseDouble(payOrHoursToken));
            // so that everything in the company is just an Employee, but each is an instance of fulltime or parttime or management
            // I think this is the right way to do it?? but gotta confirm
        }

        boolean addSuccessful = company.add(employee);

        if(addSuccessful){
            System.out.println("Employee added.");
        }
        else{
            System.out.println("Employee is already in the list.");
        }
}

    private void removeEmployee(Company company) {

        Date date = new Date(dateToken);
        Profile profile = new Profile(nameToken, departmentToken, date);
        Employee employee = new Employee(profile);
        boolean removeSuccessful = company.remove(employee);
        if(removeSuccessful){
            System.out.println("Employee removed.");
        }
        else{
            System.out.println("Employee doesn't exist.");
        }

    }

    private void calculateEarnings(Company company) {
//        Book found = getBookfromNum(secondToken, lib);
//        if (found == null) {
//            System.out.println("Book#" + secondToken + " is not available.");
//        }
//        else {
//            boolean checkOutSuccess = lib.checkOut(found);
//            if (checkOutSuccess == false) {
//                System.out.println("Book#" + secondToken + " is not available.");
//            }
//            else {
//                System.out.println("You’ve checked out Book#" + secondToken + ". Enjoy!");
//            }
//        }
    }

    private void setHoursforParttime(Company company) {
//        Book found = getBookfromNum(secondToken, lib);
//        if (found == null) {
//            System.out.println("Unable to return Book#" + secondToken + ".");
//        }
//        else {
//            boolean returnSuccess = lib.returns(found);
//            if (returnSuccess == false) {
//                System.out.println("Unable to return Book#" + secondToken + ".");
//            }
//            else {
//                System.out.println("Book#" + secondToken + " return has completed. Thanks!");
//            }
//        }
    }

}
