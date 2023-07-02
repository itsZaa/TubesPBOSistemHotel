package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Customer;
import model.User;
import model.GenderType;
import model.UserType;

//class ini digunakan untuk nampung fungsi berisi query-query ke DB
public class DatabaseController {
    static DatabaseHandler conn = new DatabaseHandler();
    
    // SELECT ALL from table users
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM users WHERE type='customer'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));

                String gender = rs.getString("gender");

                user.setGender(GenderType.valueOf(gender.toUpperCase()));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                
                String type = rs.getString("type");
                user.setType(UserType.valueOf(type.toUpperCase()));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }

    // SELECT WHERE
    public User getUser(String username) {
        conn.connect();
        String query = "SELECT * FROM users WHERE username='" + username + "'";
        User user = new User();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAddress(rs.getString("address"));

                String gender = rs.getString("gender");

                user.setGender(GenderType.valueOf(gender.toUpperCase()));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                
                String type = rs.getString("type");
                user.setType(UserType.valueOf(type.toUpperCase()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user);
    }

    // INSERT
    public boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getFullname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getGender().name());
            stmt.setString(7, user.getPhoneNumber());
            stmt.setString(8, user.getType().name());

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // UPDATE
    public boolean updateUser(User user, String oldUsername) {
        conn.connect();
        String query = "UPDATE users SET Username='" + user.getUsername() + "', "
                + "full_name='" + user.getFullname() + "', "
                + "Email='" + user.getEmail() + "', "
                + "Password='" + user.getPassword() + "', "
                + "Address='" + user.getAddress() + "', "
                + "phone_number='" + user.getPhoneNumber() + "', "
                + "Type='" + user.getType().name()+ "' "
                + "WHERE Username='" + oldUsername + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // DELETE
    public static boolean deleteUser(String username) {
        conn.connect();

        String query = "DELETE FROM users WHERE Username='" + username + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // //GET TRANSAKSI
    // public boolean insertRoomTransaction(String username, Transaction transaction) {
    //     conn.connect();
    //     String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)";
    //     try {
    //         PreparedStatement stmt = conn.con.prepareStatement(query);
    //         stmt.setString(1, user.getUsername());
    //         stmt.setString(2, user.getFullname());
    //         stmt.setString(3, user.getEmail());
    //         stmt.setString(4, user.getPassword());
    //         stmt.setString(5, user.getAddress());
    //         stmt.setString(6, user.getGender().name());
    //         stmt.setString(7, user.getPhoneNumber());
    //         stmt.setString(8, user.getType().name());

    //         stmt.executeUpdate();
    //         return (true);
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return (false);
    //     }
    // }
}