package BMIandInterest;

import java.io.Serializable;

public class CalculatedLoan implements Serializable {
    private double monthlyPayment;
    private double totalPayment;

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public CalculatedLoan(double monthlyPayment, double totalPayment) {
        this.monthlyPayment = monthlyPayment;
        this.totalPayment = totalPayment;
    }
}
