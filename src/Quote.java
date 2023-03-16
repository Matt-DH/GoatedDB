//Quote parent class

public abstract class Quote {

    // VARIABLES
    public int id = 0;
    public double base = 0.00;
    public double total = 0.00;

    // CONSTRUCTORS
    public Quote() {
        this.base = 0;
        this.total = 0;
    }

    public Quote(double base, double total) {
        this.base = base;
        this.total = total;
    }

    // SETTERS
    public void setBase(double base) {
        this.base = base;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // GETTERS
    public double getBase() {
        return base;
    }

    public double getTotal() {
        return total;
    }

}