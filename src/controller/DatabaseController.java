package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import model.Order;
import model.Customer;
import model.FnBMenu;
import model.FnBTransaction;
import model.User;
import model.OrderStatus;
import model.GenderType;
import model.RoomType;
import model.UserType;
import model.Laundry;
import model.LaundryTransaction;
import model.PaymentMethod;
import model.RoomOrder;
import model.RoomTransaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JOptionPane;

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
        } finally {
            conn.disconnect(); // Close the database connection
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
        } finally {
            conn.disconnect(); // Close the database connection
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
        } finally {
            conn.disconnect(); // Close the database connection
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
        } finally {
            conn.disconnect(); // Close the database connection
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
        } finally {
            conn.disconnect(); // Close the database connection
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
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (menuList);
    }


    //get fnb transaction list
    public ArrayList<FnBTransaction> getFnBTransactionList(User user){
        ArrayList<FnBTransaction> transactions = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM fnb_transaction WHERE username = '" + user.getUsername() + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                FnBTransaction transaction = new FnBTransaction();

                transaction.setUser(getUser(user.getUsername()));
                transaction.setTransactionId(rs.getString("fnb_transaction_id"));
                transaction.setUser(user);
                transaction.setRoomNumber(rs.getInt("room_number"));
                transaction.setStatus(OrderStatus.valueOf(rs.getString("status")));
                transaction.setTotalPrice(rs.getDouble("total_price"));

                String paymentMethodName = rs.getString("payment_method").toUpperCase();
                PaymentMethod paymentMethod = getPaymentMethod(paymentMethodName);

                transaction.setPaymentMethod(paymentMethod);

                transaction.setTransactionDate(rs.getTimestamp("transaction_date").toLocalDateTime().toLocalDate());

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (transactions);
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
        } finally {
            conn.disconnect(); // Close the database connection
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
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (paymentMethod);
    }
  
    public RoomTransaction getRoomTransaction(String username) {
        RoomTransaction transaction = new RoomTransaction();
        try {
            conn.connect();
            String query = "SELECT * FROM room_transaction WHERE username='" + username + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                transaction.setTransactionId(rs.getString("room_transaction_id"));
                transaction.setDateBooked(new Date(rs.getTimestamp("time_booked").getTime()));
                transaction.setTimeStampCheckIn(new Date(rs.getTimestamp("time_check_in").getTime()));
                transaction.setTimeStampCheckOut(new Date(rs.getTimestamp("time_check_out").getTime()));
                transaction.setDateCheckIn(rs.getDate("date_check_in").toLocalDate());
                transaction.setDuration(rs.getInt("stay_duration"));

                String paymentMethodName = rs.getString("payment_method").toUpperCase();
                PaymentMethod paymentMethod = getPaymentMethod(paymentMethodName);

                transaction.setPaymentMethod(paymentMethod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); 
        }
        return (transaction);
    }

    //get room transaction list
    public ArrayList<RoomTransaction> getRoomTransactionList(User user){
        ArrayList<RoomTransaction> transactions = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM room_transaction WHERE username = '" + user.getUsername() + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                RoomTransaction transaction = new RoomTransaction();

                transaction.setUser(getUser(user.getUsername()));
                transaction.setTransactionId(rs.getString("room_transaction_id"));
                transaction.setDateBooked(new Date(rs.getTimestamp("time_booked").getTime()));
                transaction.setTimeStampCheckIn(new Date(rs.getTimestamp("time_check_in").getTime()));
                transaction.setTimeStampCheckOut(new Date(rs.getTimestamp("time_check_out").getTime()));
                transaction.setDateCheckIn(rs.getDate("date_check_in").toLocalDate());
                transaction.setDuration(rs.getInt("stay_duration"));

                String paymentMethodName = rs.getString("payment_method").toUpperCase();
                PaymentMethod paymentMethod = getPaymentMethod(paymentMethodName);

                transaction.setPaymentMethod(paymentMethod);

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (transactions);
    }



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
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (laundry);
    }

    public boolean insertRoomTransaction(RoomTransaction transaction) {
        try {
            conn.connect();
            String query = "INSERT INTO room_transaction VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, transaction.getTransactionId());
            stmt.setString(2, transaction.getUser().getUsername());
            LocalDate checkInDate = transaction.getDateCheckIn();
            java.sql.Date sqlDate = java.sql.Date.valueOf(checkInDate);
            stmt.setDate(3, sqlDate);
            stmt.setInt(4, transaction.getDuration());
            stmt.setString(5, "booked");
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(7, null);
            stmt.setTimestamp(8, null);
            stmt.setString(9, transaction.getPaymentMethod().getName());
    
            stmt.executeUpdate();
            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return false;
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }
    

    public boolean insertRoomOrder(String id, RoomOrder order, LocalDate date) {
        try {
            conn.connect();
            String query = "INSERT INTO room_order (room_transaction_id, room_number, room_type, quantity, price, date) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setNull(2, Types.INTEGER);
            stmt.setString(3, order.getRoomType().getTypeName());
            stmt.setInt(4, order.getQuantity());
            stmt.setDouble(5, order.getOrderPrice());
            stmt.setDate(6, java.sql.Date.valueOf(date));
            stmt.executeUpdate();
            conn.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            conn.disconnect();
            return false;
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }
    
    public int checkRoomAvailable(LocalDate date, RoomType roomType) {
        int availableQuantity = roomType.getNumberOfRoom();
        try {
            conn.connect();
            String query = "SELECT SUM(quantity) AS total_quantity FROM room_order WHERE date = ? AND room_type = ?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setDate(1, java.sql.Date.valueOf(date));
            stmt.setString(2, roomType.getTypeName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int totalQuantity = rs.getInt("total_quantity");
                availableQuantity -= totalQuantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return availableQuantity;
    }
    


    // public ArrayList <RoomTransaction> getRoomTransactionbyDate(LocalDate date) {
    //     ArrayList <RoomTransaction> transactionList = new ArrayList<>();
    //     try {
    //         conn.connect();
    //         String query = "SELECT * FROM room_transaction WHERE date_check_in = date";
    //         Statement stmt = conn.con.createStatement();
    //         ResultSet rs = stmt.executeQuery(query);
    //         while (rs.next()) {
                
    //             paymentMethod.setPaymentMethodId(rs.getInt("payment_method_id"));
    //             paymentMethod.setName(rs.getString("name"));

    //             paymentMethods.add(paymentMethod);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     } finally {
    //         conn.disconnect(); // Close the database connection
    //     }
    //     return transactionList;
    // }

    // CREATE LAUNDRY TRANSACTION
    public boolean insertLaundryTransaction(LaundryTransaction transaction) {
        try {
            conn.connect();
            String query = "INSERT INTO laundry_transaction VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, transaction.getTransactionId());
            stmt.setString(2, transaction.getUser().getUsername());
            stmt.setInt(3, transaction.getRoomNumber());
            stmt.setString(4, transaction.getOrderStatus().name());
            stmt.setDouble(5, transaction.getTotalPrice());
            stmt.setString(6, transaction.getPaymentMethod().getName());

            LocalDate dateOrder = transaction.getDateOrder();
            LocalDateTime currentDateTime = LocalDateTime.of(dateOrder, LocalTime.now());
            Timestamp timestamp = Timestamp.valueOf(currentDateTime);

            stmt.setTimestamp(7, timestamp);

            stmt.setTimestamp(8, null);
            stmt.setString(9, transaction.getLaundry().getLaundryName());

            if(transaction.getLaundry().getLaundryName().equals("Express")){
                
                LocalDateTime estimationDone = currentDateTime.plusHours(10);
                
                Timestamp timestampEstimationDone = Timestamp.valueOf(estimationDone);

                stmt.setTimestamp(10, timestampEstimationDone);

            }else if(transaction.getLaundry().getLaundryName().equals("Standard")){
                LocalDateTime estimationDone = currentDateTime.plusHours(24);
                
                Timestamp timestampEstimationDone = Timestamp.valueOf(estimationDone);

                stmt.setTimestamp(10, timestampEstimationDone);
            }

            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    //update laundry transaction
    public boolean updateLaundryTransaction(LaundryTransaction laundryTransaction) {
        try {
            conn.connect();

            LocalDateTime currentDateTime = LocalDateTime.now();

            String query = "UPDATE laundry_transaction SET status='" + OrderStatus.DELIVERED + "', date_delivered='" + Timestamp.valueOf(currentDateTime) + "' WHERE laundry_transaction_id = '" + laundryTransaction.getTransactionId() + "';";


            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    //GET UNPROCESSED LAUNDRY TRANSACTION
    public ArrayList<LaundryTransaction> getUnprocessedLaundryTransactions(){
        ArrayList<LaundryTransaction> transactions = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM laundry_transaction WHERE status = 'waiting' ORDER BY estimation_done";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LaundryTransaction transaction = new LaundryTransaction();
                transaction.setTransactionId(rs.getString("laundry_transaction_id"));
                transaction.setUser(getUser(rs.getString("username")));
                transaction.setRoomNumber(rs.getInt("room_number"));

                String status = rs.getString("status");
                transaction.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));

                transaction.setTotalPrice(rs.getDouble("total_price"));

                transaction.setPaymentMethod(getPaymentMethod(rs.getString("payment_method")));

    
                transaction.setDateOrder(rs.getTimestamp("date_order").toLocalDateTime().toLocalDate());

                transaction.setDateDelivered(null);

                transaction.setLaundry(getLaundry(rs.getString("laundry_name")));

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (transactions);
    }


    //get laundry transaction list
    //GET UNPROCESSED LAUNDRY TRANSACTION
    public ArrayList<LaundryTransaction> getLaundryTransactionList(User user){
        ArrayList<LaundryTransaction> transactions = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM laundry_transaction WHERE username = '" + user.getUsername() + "' ORDER BY estimation_done";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LaundryTransaction transaction = new LaundryTransaction();
                transaction.setTransactionId(rs.getString("laundry_transaction_id"));
                transaction.setUser(getUser(rs.getString("username")));
                transaction.setRoomNumber(rs.getInt("room_number"));

                String status = rs.getString("status");
                transaction.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));

                transaction.setTotalPrice(rs.getDouble("total_price"));

                transaction.setPaymentMethod(getPaymentMethod(rs.getString("payment_method")));

    
                transaction.setDateOrder(rs.getTimestamp("date_order").toLocalDateTime().toLocalDate());

                transaction.setDateDelivered(null);

                transaction.setLaundry(getLaundry(rs.getString("laundry_name")));

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (transactions);
    }

    // GET Payment Method
    public ArrayList<PaymentMethod> getAllPaymentMethod() {
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM payment_method";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setPaymentMethodId(rs.getInt("payment_method_id"));
                paymentMethod.setName(rs.getString("name"));

                paymentMethods.add(paymentMethod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect(); // Close the database connection
        }
        return (paymentMethods);
    }

    // CREATE FNB TRANSACTION
    public boolean insertFnBTransaction(FnBTransaction transaction) {
        try {
            conn.connect();
            String query = "INSERT INTO fnb_transaction VALUES(?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, transaction.getTransactionId());
            stmt.setString(2, transaction.getUser().getUsername());
            stmt.setInt(3, transaction.getRoomNumber());
            stmt.setString(4, transaction.getStatus().name());
            stmt.setDouble(5, transaction.getTotalPrice());
            stmt.setString(6, transaction.getPaymentMethod().getName());

            LocalDate dateOrder = transaction.getTransactionDate();
            LocalDateTime currentDateTime = LocalDateTime.of(dateOrder, LocalTime.now());
            Timestamp timestamp = Timestamp.valueOf(currentDateTime);

            stmt.setTimestamp(7, timestamp);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect(); // Close the database connection
        }
    }

    // SELECT ROOM NUMBER LIST FROM ROOM TRANSACTION BY USERNAME
    public ArrayList<Integer> getRoomNumberOrdered(String username) {
        try {
            conn.connect();
            ArrayList<Integer> numbers = new ArrayList<>();
            String query = "SELECT ro.room_number "
                    + "FROM room_order ro "
                    + "JOIN room_transaction rt ON ro.room_transaction_id = rt.room_transaction_id "
                    + "WHERE rt.username = ?";

            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int roomNumber = resultSet.getInt("room_number");
                numbers.add(roomNumber);
            }

            return numbers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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