public class Parttime extends Employee {
    private int workingHours;
    private double hourlyRate;
    public static final int MAX_HOURS = 80;

    public Parttime(Profile profile, int payment, int workingHours, int hourlyRate) {
        super(profile, payment);
        this.workingHours = workingHours;
        this.hourlyRate = hourlyRate;
    }

    public int getWorkingHours() {
        return this.workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Parttime) {
            Parttime parttime = (Parttime) obj;
            return super.equals(parttime)
                    && (this.workingHours == parttime.workingHours)
                    && (this.hourlyRate == parttime.hourlyRate);
        }

    }

    @Override
    public String toString() {
        return super.toString()
                + "PART TIME"
                + "::"
                + "Hourly Rate $"
                + this.hourlyRate
                + "::"
                + "Hours worked this period: "
                + this.workingHours;
    }
    @Override
    public void calculatePayment() {
        if (this.workingHours <= MAX_HOURS) {
            this.setPayment(this.workingHours * this.hourlyRate);
        }
        else {
            int extraHours = this.workingHours - MAX_HOURS;
            this.setPayment((MAX_HOURS * this.hourlyRate) + (extraHours * 1.5 * this.hourlyRate));
        }
    }
}
