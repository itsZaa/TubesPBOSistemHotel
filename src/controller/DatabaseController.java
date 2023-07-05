package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Customer;
import model.FnBMenu;
import model.User;
import model.GenderType;
import model.RoomType;
import model.UserType;
import model.Laundry;
import model.LaundryTransaction;
import model.PaymentMethod;
import model.RoomTransaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//class ini digunakan untuk nampung fungsi berisi query-query ke DB
public class DatabaseController {
    static DatabaseHandler conn = new DatabaseHandler();

    // SELECT ALL from table users
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE type='customer'";
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
        User user = new User();
        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE username='" + username + "'";
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
        try {
            conn.connect();
            String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)";
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
        try {
            conn.connect();
            String query = "UPDATE users SET Username='" + user.getUsername() + "', "
                    + "full_name='" + user.getFullname() + "', "
                    + "Email='" + user.getEmail() + "', "
                    + "Password='" + user.getPassword() + "', "
                    + "Address='" + user.getAddress() + "', "
                    + "phone_number='" + user.getPhoneNumber() + "', "
                    + "Type='" + user.getType().name() + "' "
                    + "WHERE Username='" + oldUsername + "'";
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
        try {
            conn.connect();
            String query = "DELETE FROM users WHERE Username='" + username + "'";
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // SELECT ALL from fnb_menu
    public ArrayList<FnBMenu> getAllFnBMenu() {
        ArrayList<FnBMenu> menuList = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM fnb_menu";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                FnBMenu fnbMenu = new FnBMenu();
                fnbMenu.setMenuName(rs.getString("menu_name"));
                fnbMenu.setMenuType(rs.getString("menu_type"));
                fnbMenu.setPrice(rs.getDouble("price"));

                menuList.add(fnbMenu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (menuList);
    }

    // SELECT ALL from laundry menu
    public ArrayList<Laundry> getAllLaundry() {
        ArrayList<Laundry> menuList = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM laundry";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Laundry laundry = new Laundry();
                laundry.setLaundryName(rs.getString("laundry_name"));
                laundry.setPrice(rs.getDouble("price"));

                menuList.add(laundry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (menuList);
    }

    public PaymentMethod getPaymentMethod(String name) {
        PaymentMethod paymentMethod = new PaymentMethod();
        try {
            conn.connect();
            String query = "SELECT * FROM payment_method WHERE name='" + name + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                paymentMethod.setPaymentMethodId(rs.getInt("payment_method_id"));
                paymentMethod.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (paymentMethod);
    }

    // GET ROOM TRANSACTION
    public RoomTransaction getRoomTransaction(String username) {
        RoomTransaction transaction = new RoomTransaction();
        try {
            conn.connect();
            String query = "SELECT * FROM room_transaction WHERE username='" + username + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                transaction.setRoomTransactionId(rs.getInt("room_transaction_id"));
                transaction.setDateBooked(rs.getTimestamp("date_booked").toLocalDateTime().toLocalDate());
                transaction.setDateCheckIn(rs.getTimestamp("date_check_in").toLocalDateTime().toLocalDate());
                transaction.setDateCheckOut(rs.getTimestamp("date_check_out").toLocalDateTime().toLocalDate());

                String paymentMethodName = rs.getString("payment_method").toUpperCase();
                PaymentMethod paymentMethod = getPaymentMethod(paymentMethodName);

                transaction.setPaymentMethod(paymentMethod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (transaction);
    }

    // GET LAUNDRY
    public Laundry getLaundry(String laundryName) {
        Laundry laundry = new Laundry();
        try {
            conn.connect();
            String query = "SELECT * FROM laundry WHERE laundry_name='" + laundryName + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                laundry.setLaundryName(rs.getString("laundry_name"));
                laundry.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (laundry);
    }

    // CREATE LAUNDRY TRANSACTION
    public boolean insertLaundryTransaction(LaundryTransaction transaction) {
        try {
            conn.connect();
            String query = "INSERT INTO laundry_transaction VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, transaction.getTransactionId());
            stmt.setString(2, transaction.getUser().getUsername());
            stmt.setInt(3, transaction.getRoomNumber());
            stmt.setString(4, transaction.getOrderStatus().name());
            stmt.setDouble(5, transaction.getTotalPrice());
            stmt.setString(6, null);

            LocalDate dateOrder = transaction.getDateOrder();
            LocalDateTime currentDateTime = LocalDateTime.of(dateOrder, LocalTime.now());
            Timestamp timestamp = Timestamp.valueOf(currentDateTime);

            stmt.setTimestamp(7, timestamp);
            
            stmt.setTimestamp(8, null);
            stmt.setString(9, transaction.getLaundry().getLaundryName());

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // public boolean insertRoomTransaction(String username, Transaction
    // transaction) {
    // conn.connect();
    // String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)";
    // try {
    // PreparedStatement stmt = conn.con.prepareStatement(query);
    // stmt.setString(1, user.getUsername());
    // stmt.setString(2, user.getFullname());
    // stmt.setString(3, user.getEmail());
    // stmt.setString(4, user.getPassword());
    // stmt.setString(5, user.getAddress());
    // stmt.setString(6, user.getGender().name());
    // stmt.setString(7, user.getPhoneNumber());
    // stmt.setString(8, user.getType().name());

    // stmt.executeUpdate();
    // return (true);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // return (false);
    // }
    // }

    public ArrayList<RoomType> getRoom() {
        ArrayList<RoomType> roomList = new ArrayList<>();

        String query = "SELECT room_type_id, type_name, price, number_of_room FROM Room_Type";

        conn.connect();
        try {

            Statement stmt = conn.con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                int roomTypeId = resultSet.getInt("room_type_id");
                String typeName = resultSet.getString("type_name");
                double price = resultSet.getDouble("price");
                int room = resultSet.getInt("number_of_room");

                RoomType roomType = new RoomType(roomTypeId, typeName, price, room);
                roomList.add(roomType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.disconnect();

        return roomList;
    }
}