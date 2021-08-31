package BMIandInterest;

import java.io.Serializable;

public class BMIobject implements Serializable {
    private double weight;
    private double height;

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public BMIobject(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }
}
