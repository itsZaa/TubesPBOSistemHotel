package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrintTransactionController {
    private DatabaseHandler conn;

    public PrintTransactionController() {
        this.conn = new DatabaseHandler();
    }

    public double getAllTransactionsTotalPrice(String month, int year) {
        double totalRoom = getRoomOrdersTotalPrice(getMonthNumber(month), year);
        double totalFnb = getFnBTransactionsTotalPrice(getMonthNumber(month), year);
        double totalLaundry = getLaundryTransactionsTotalPrice(getMonthNumber(month), year);

        return totalRoom + totalFnb + totalLaundry;
    }

    private double getRoomOrdersTotalPrice(int month, int year) {
        double totalPrice = 0;
        try {
            conn.connect();
            String query = "SELECT price FROM room_order " +
                    "WHERE MONTH(date) = " + month + " AND YEAR(date) = " + year;
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                totalPrice += rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return (totalPrice);
    }

    private double getFnBTransactionsTotalPrice(int month, int year) {
        double totalPrice = 0;
        try {
            conn.connect();
            String query = "SELECT total_price FROM fnb_transaction "
                    + "WHERE MONTH(transaction_date) = " + month + " AND YEAR(transaction_date) = " + year;
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                totalPrice += rs.getDouble("total_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return (totalPrice);
    }

    private double getLaundryTransactionsTotalPrice(int month, int year) {
        double totalPrice = 0;
        try {
            conn.connect();
            String query = "SELECT total_price FROM laundry_transaction "
                    + "WHERE MONTH(date_order) = " + month + " AND YEAR(date_order) = " + year;

            // SELECT total_price FROM laundry_transaction WHERE MONTH(date_order) = 7 AND
            // YEAR(date_order) = 2023;
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                totalPrice += rs.getDouble("total_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return (totalPrice);
    }

    private int getMonthNumber(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return -1;
        }
    }
}