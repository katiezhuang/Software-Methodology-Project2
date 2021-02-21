public class Profile {

    private String name; // employee's name in the form "lastname,firstname"
    private String department; // department code: CS, ECE, IT
    private Date dateHired;

    public Profile(String name, String department, Date dateHired){

        this.name = name;
        this.department = department;
        this.dateHired = dateHired;

    }

    public String getName(){
        return name;
    }
    public String getDepartment(){
        return department;
    }
    public Date getDateHired(){
        return dateHired;
    }

    @Override
    public String toString(){

        return name + "::" + department + "::" + dateHired.toString();
    }

    @Override
    public boolean equals(Object obj) {
        // compare name, department, and dateHired
        if(this == obj){
            return true;
        }
        Profile profile = (Profile) obj;
        if(profile.name.equals(this.name) && profile.department.equals(this.department) && profile.dateHired.equals(this.dateHired)) {
            return true;
        }
        else{
            return false;
        }
    }

}
