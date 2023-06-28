package model;

public class RedeemMerchandise {
    private int redeemId;
    private User user;
    private Merchandise merchandise;

    public RedeemMerchandise() {
    }

    public RedeemMerchandise(int redeemId, User user, Merchandise merchandise) {
        this.redeemId = redeemId;
        this.user = user;
        this.merchandise = merchandise;
    }

    public int getRedeemId() {
        return this.redeemId;
    }

    public void setRedeemId(int redeemId) {
        this.redeemId = redeemId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Merchandise getMerchandise() {
        return this.merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    @Override
    public String toString() {
        return "{" +
                " redeemId='" + getRedeemId() + "'" +
                ", user='" + getUser() + "'" +
                ", merchandise='" + getMerchandise() + "'" +
                "}";
    }
}