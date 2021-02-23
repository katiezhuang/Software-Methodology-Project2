public class Parttime extends Employee{

    private int workingHours;
    private double hourlyRate;
    public static final int MAX_HOURS = 80;

    public Parttime(Profile profile, double hourlyRate){
        super(profile);
        this.hourlyRate = hourlyRate;
    }

    public int getWorkingHours() {
        return this.workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    @Override
    public String toString(){

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
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (!(obj instanceof Parttime)) {
            return false;
        }
        Parttime parttime = (Parttime) obj;

        return super.equals(parttime);
    }

    @Override
    public void calculatePayment(){
        if (this.workingHours <= MAX_HOURS) {
            this.setPayment(this.workingHours * this.hourlyRate);
        }
        else {
            int extraHours = this.workingHours - MAX_HOURS;
            this.setPayment((MAX_HOURS * this.hourlyRate) + (extraHours * 1.5 * this.hourlyRate));
        }

    }

}
