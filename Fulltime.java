public class Fulltime extends Employee{

    double salary;

    public Fulltime(double salary){
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

        Fulltime fulltime = (Fulltime) obj;

        return super.equals(obj) && (this.salary == fulltime.salary);

    }

    @Override
    public void calculatePayment(){

        setMoneyEarned(salary/26);

    }
}
