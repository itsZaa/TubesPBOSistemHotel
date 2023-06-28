package model;

public class Merchandise {
    private int merchId;
    private String name;
    private int pointRequired;
    private int stock;

    public Merchandise() {
    }

    public Merchandise(int merchId, String name, int pointRequired, int stock) {
        this.merchId = merchId;
        this.name = name;
        this.pointRequired = pointRequired;
        this.stock = stock;
    }

    public int getMerchId() {
        return this.merchId;
    }

    public void setMerchId(int merchId) {
        this.merchId = merchId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPointRequired() {
        return this.pointRequired;
    }

    public void setPointRequired(int pointRequired) {
        this.pointRequired = pointRequired;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "{" +
                " merchId='" + getMerchId() + "'" +
                ", name='" + getName() + "'" +
                ", pointRequired='" + getPointRequired() + "'" +
                ", stock='" + getStock() + "'" +
                "}";
    }
}