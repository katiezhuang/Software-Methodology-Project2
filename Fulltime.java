public class Fulltime extends Employee{

    private double salary;

    public Fulltime(Profile profile, double salary){
        super(profile);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + "::FULL TIME::Annual Salary " + String.format("$%,.2f", salary);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj){
           return true;
        }
        if (!(obj instanceof Fulltime)) {
            return false;
        }
        Fulltime fulltime = (Fulltime) obj;

        return super.equals(fulltime);

    }

    @Override
    public void calculatePayment(){

        setPayment(salary/26);

    }

}
