import java.util.Objects;

public class Profile {

    private String name; // employee's name in the form "lastname,firstname"
    private String department; // department code: CS, ECE, IT
    private Date dateHired;

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

        return name + "::" + department + "::" + dateHired.toString() + "::";
    }

    @Override
    public boolean equals(Object obj) {
        // compare name, department, and dateHired
        if(this == obj){
            return true;
        }
        Profile profile = (Profile) obj;
        if(profile.name == this.name && profile.department == this.department && profile.dateHired == this.dateHired) {
            return true;
        }
        else{
            return false;
        }
    }


}
