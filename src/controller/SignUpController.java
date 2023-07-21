package controller;

import java.sql.*;

public class SignUpController {
    public String SignUp(String userName, String fullName, String email, String password, String alamat, String gender,
            String noTelp) {
        String query;
        password = Hasher.password(password);
        try {
            DatabaseHandler conn = new DatabaseHandler();
            conn.connect();
            PreparedStatement stmt;
            // for insert customer data
            query = "INSERT INTO users(username, full_name, email, password, address, gender, phone_number, type) VALUES(?,?,?,?,?,?,?,?)";
            stmt = conn.con.prepareStatement(query);
            stmt.setString(1, userName);
            stmt.setString(2, fullName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, alamat);
            stmt.setString(6, gender);
            stmt.setString(7, noTelp);
            stmt.setString(8, "customer");
            stmt.executeUpdate();
            return "Berhasil melakukan registrasi";

        } catch (SQLException e) {
            if (e.getMessage().contains("'username'")) {
                return "Username sudah digunakan";
            } else if (e.getMessage().contains("'phone_number'")) {
                return "Telepon sudah digunakan";
            } else {
                e.printStackTrace();
                return "Error fatal ini mah!";
            }
        }
    }
}
