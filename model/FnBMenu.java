package model;

public class FnBMenu {
    private String menuName;
    private String menuType;
    private double price;

    public FnBMenu() {
    }

    public FnBMenu(String menuName, String menuType, double price) {
        this.menuName = menuName;
        this.menuType = menuType;
        this.price = price;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return this.menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                " menuName='" + getMenuName() + "'" +
                ", menuType='" + getMenuType() + "'" +
                ", price='" + getPrice() + "'" +
                "}";
    }
}