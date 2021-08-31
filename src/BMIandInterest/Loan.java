package BMIandInterest;

import java.io.Serializable;

public class Loan implements Serializable {
    private double loan;
    private double interest;
    private double period;

    public Loan(double loan, double interest, double years) {
        this.loan = loan;
        this.interest = interest;
        this.period = years;
    }

    public double getLoan() {
        return loan;
    }

    public double getInterest() {
        return interest;
    }

    public double getPeriod() {
        return period;
    }
}
