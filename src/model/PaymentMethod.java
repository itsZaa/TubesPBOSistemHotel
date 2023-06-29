package model;

public class PaymentMethod {
    private int paymentMethodId;
    private String name;

    public PaymentMethod() {
    }

    public PaymentMethod(int paymentMethodId, String name) {
        this.paymentMethodId = paymentMethodId;
        this.name = name;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                " paymentMethodId='" + getPaymentMethodId() + "'" +
                ", name='" + getName() + "'" +
                "}";
    }
}