/**
 * This class holds the information for a Profile.
 * Every Profile object has a name (represented by a String), department (represented by a String),
 * and a dateHired (represented by a Date object).
 * Profile is a field of Employee class.
 *
 * @author Ria Anand, Katie Zhuang
 */
public class Profile {

    private String name; // employee's name in the form "lastname,firstname"
    private String department; // department code: CS, ECE, IT
    private Date dateHired;

    /**
     * Constructor to initialize a Profile object.
     * @param name - assigns the given name to the newly created Profile object.
     * @param department - assigns the department to the newly created Profile object.
     * @param dateHired - assigns the dateHired to the newly created Profile object.
     */
    public Profile(String name, String department, Date dateHired){

        this.name = name;
        this.department = department;
        this.dateHired = dateHired;

    }

    /**
     * Getter method to access private field department.
     * @return String - the department of the Employee
     */
    public String getDepartment(){
        return department;
    }

    /**
     * Getter method to access private field dateHired.
     * @return Date - the hire date of the Employee
     */
    public Date getDateHired(){
        return dateHired;
    }

    /**
     * Overrides the toString method for a Profile object
     * @return String in the format: "[name]::[department]::[dateHired]"
     */

    @Override
    public String toString(){

        return name + "::" + department + "::" + dateHired.toString();
    }

    /**
     * Equals method to check equivalence of object if object is an instance of Profile.
     * Override method.
     * @param obj - Object to compare
     * @return boolean - true if matching profile, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // compare name, department, and dateHired
        if(this == obj){
            return true;
        }
        Profile profile = (Profile) obj;
        if(profile.name.equals(this.name) && profile.department.equals(this.department) && (profile.dateHired.compareTo(this.dateHired) == 0)) {
            return true;
        }
        else{
            return false;
        }
    }


}
