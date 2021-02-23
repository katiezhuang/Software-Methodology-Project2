public class Employee {
    private Profile profile;
    private double paymentPerPeriod;

    public Employee(Profile profile) {
        this.profile = profile;
    }

    public void calculatePayment() { }

    public Profile getProfile() {
        return this.profile;
    }

    public double getPayment() {
        return this.paymentPerPeriod;
    }

    public void setPayment(double paymentPerPeriod) {
        this.paymentPerPeriod = paymentPerPeriod;
    }
    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }

        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) obj;
        return (employee.getProfile().equals(this.getProfile()));
    }

    @Override
    public String toString() {
        return this.getProfile().toString()
                + "Payment $"
                + this.paymentPerPeriod
                + "::";
    }
}
