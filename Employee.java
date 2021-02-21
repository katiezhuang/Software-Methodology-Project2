public class Employee {
    private Profile profile;
    private double payment;

    public Employee(Profile profile, int payment) {
        this.payment = payment;
        this.profile = profile;
    }

    public void calculatePayment() { }

    public Profile getProfile() {
        return this.profile;
    }

    public double getPayment() {
        return this.payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            return (employee.getProfile().equals(this.getProfile()))
                    && (employee.getPayment() == this.getPayment());
        }
    }
    @Override
    public String toString() {
        return this.getProfile().toString()
                + "Payment $"
                + this.payment
                + "::";
    }

}
