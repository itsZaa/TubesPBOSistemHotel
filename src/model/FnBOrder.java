package model;

public class FnBOrder extends Order {
    private FnBMenu food;

    public FnBOrder(int quantity, FnBMenu food) {
        super(quantity, food.getPrice() * quantity);
        this.food = food;
    }

    public FnBMenu getFood() {
        return food;
    }

    public void setFood(FnBMenu food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "FnBOrder [food=" + food + "]";
    }
}
