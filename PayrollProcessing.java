import java.util.StringTokenizer;
import java.util.Scanner;

public class PayrollProcessing {
    String firstToken;
    String secondToken;
    String thirdToken;
    String fourthToken;
    String fifthToken;
    String sixthToken;
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
    };

    private void findCommand(tokenType type, Company company) {
        switch(type) {
            case type1:
                //it's one of the prints PA, PD, PH, or C
                if (firstToken.length() > 2) {
                    System.out.println("Invalid command!");
                }
                else if (firstToken.length() == 1) {
                    if (firstToken.charAt(0) == 'C') {
                        calculateEarnings(company);
                    }
                    else {
                        System.out.println("Invalid command!");
                    }
                }
                else {
                    if (firstToken.charAt(0) != 'P') {
                        System.out.println("Invalid command!");
                    }
                    else {
                        if (firstToken.charAt(1) == 'A') {
                            printEarnings(company);
                        }
                        else if (firstToken.charAt(1) == 'D') {
                            printDepartment(company);
                        }
                        else if (firstToken.charAt(1) == 'H') {
                            printdateHired(company);
                        }
                        else {
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
                if (firstToken.equals("R")) {
                    removeEmployee(company);
                }
                else {
                    System.out.println("Invalid command!");
                }
                break;
            case type5:
                // either AP, AF, or S
                if (firstToken.length() > 2) {
                    System.out.println("Invalid command!");
                }
                else if (firstToken.length() == 1) {
                    if (firstToken.charAt(0) == 'S') {
                        setHoursforParttime(company);
                    }
                    else {
                        System.out.println("Invalid command!");
                    }
                }
                else {
                    if (firstToken.charAt(0) != 'A') {
                        System.out.println("Invalid command!");
                    }
                    else {
                        if (firstToken.charAt(1) == 'P') {
                            addEmployee(company, 0);
                        }
                        else if (firstToken.charAt(1) == 'F') {
                            addEmployee(company, 1);
                        }
                        else {
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
            String command = scanner.nextLine();
            if (command.equals("Q")) {
                break;
            }
            else if (command.equals("")) {
                continue;
            }
            else {
                StringTokenizer tokenizer = new StringTokenizer(command);
                tokenType type = tokenType.type1;
                firstToken = tokenizer.nextToken();
                while (tokenizer.hasMoreTokens()) {
                    if (type == tokenType.type1) {
                        type = tokenType.type2;
                        secondToken = tokenizer.nextToken();
                    }
                    else if (type == tokenType.type2) {
                        type = tokenType.type3;
                        thirdToken = tokenizer.nextToken();
                    }
                    else if (type == tokenType.type3) {
                        type = tokenType.type4;
                        fourthToken = tokenizer.nextToken();
                    }
                    else if (type == tokenType.type4) {
                        type = tokenType.type5;
                        fifthToken = tokenizer.nextToken();
                    }
                    else if (type == tokenType.type5) {
                        type = tokenType.type6;
                        sixthToken = tokenizer.nextToken();
                    }
                    else {
                        type = tokenType.type7orMore;
                        break;
                    }
                }
                findCommand(type, company);
            }
        }
        System.out.println("Payroll Processing completed");
    }
    private void printEarnings(Company company){
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        }
        else {
            System.out.println("--Printing earning statements for all employees--");
            company.print();
        }
    }
    private void printDepartment(Company company){
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        }
        else {
            System.out.println("--Printing earning statements by department--");
            company.printByDepartment();
        }
    }
    private void printdateHired(Company company){
        if (company.getNumEmployee() == 0) {
            System.out.println("Employee database is empty.");
        }
        else {
            System.out.println("--Printing earning statements by date hired--");
            company.printByDate();
        }
    }
    private void addEmployee(Company company, int PorF){
        if (PorF == 0) {
            // add a parttime
        }
        else {
            // add a fulltime
        }
//        Date date = new Date(thirdToken);
//        if (date.isValid()) {
//            //book has to not exist in library
//            Book newBook = new Book();
//            newBook.setName(secondToken);
//            //how do i do this again?
//            newBook.setNumber(currSerialNumber);
//            newBook.setCheckOutStatus(false);
//            newBook.setDatePublished(date);
//            lib.add(newBook);
//            currSerialNumber = (Integer.parseInt(currSerialNumber) + 1) + "";
//            System.out.println(secondToken + " added to the library.");
//        }
//        else {
//            System.out.println("Invalid Date!");
//        }
    }
//    private Book getBookfromNum(String secondToken, Library lib) {
//        Book[] array = lib.getBooks();
//        for (int i = 0; i < lib.getNumBooks(); i++) {
//            if (array[i].getNumber().equals(secondToken)) {
//                return array[i];
//            }
//        }
//        return null;
//    }
    private void removeEmployee(Company company){
        // given serial number, is Book in library?
//        Book found = getBookfromNum(secondToken, lib);
//        if (found == null) {
//            System.out.println("Unable to remove, the library does not have this book.");
//        }
//        else {
//            boolean removeSuccess = lib.remove(found);
//            if (removeSuccess == false) {
//                System.out.println("Unable to remove, the library does not have this book.");
//            }
//            else {
//                System.out.println("Book#" + secondToken + " removed.");
//            }
//        }
    }
    private void calculateEarnings(Company company){
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
    private void setHoursforParttime(Company company){
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
