package model;

import java.util.ArrayList;

public class Member extends Customer {
    private int memberId;
    private int point;

    public Member() {
    }

    public Member(ArrayList<Favorite> favList, ArrayList<Transaction> transactionList, int memberId, int point) {
        super(favList, transactionList);
        this.memberId = memberId;
        this.point = point;
    }

    public int getMemberId() {
        return this.memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


    @Override
    public String toString() {
        return "{" +
            " memberId='" + getMemberId() + "'" +
            ", point='" + getPoint() + "'" +
            "}";
    }
}