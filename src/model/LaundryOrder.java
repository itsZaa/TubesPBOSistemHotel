package model;

public class LaundryOrder extends Order {
    private Laundry laundry;

    public LaundryOrder(int quantity, Laundry laundry) {
        super(quantity, laundry.getPrice() * quantity);
        this.laundry = laundry;
    }

    public Laundry getLaundry() {
        return laundry;
    }

    public void setLaundry(Laundry laundry) {
        this.laundry = laundry;
    }

    @Override
    public String toString() {
        return "LaundryOrder [laundry=" + laundry + "]";
    }

}