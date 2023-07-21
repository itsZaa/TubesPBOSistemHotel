package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.GenderType;
import model.Staff;
import model.StaffType;
import model.User;
import model.UserType;

public class UpdateStaffController {
    private DatabaseHandler conn;

    public UpdateStaffController() {
        this.conn = new DatabaseHandler();
    }

    // SELECT * FROM STAFF
    public ArrayList<Staff> getAllStaffs() {
        ArrayList<Staff> staffs = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM staff";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setUsername(rs.getString("username"));
                staff.setNIK(rs.getString("NIK"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setAddress(rs.getString("address"));

                String type = rs.getString("type");
                staff.setStaffType(StaffType.valueOf(type.toUpperCase()));

                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return staffs;
    }

    // SELECT STAFF BY USERNAME
    public Staff getStaffByUsername(String username) {
        Staff staff = null;
        try {
            conn.connect();
            String query = "SELECT * FROM staff WHERE username = '" + username + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                staff = new Staff();
                staff.setStaffId(rs.getInt("staff_id"));
                staff.setUsername(rs.getString("username"));
                staff.setNIK(rs.getString("NIK"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setAddress(rs.getString("address"));

                String type = rs.getString("type");
                staff.setStaffType(StaffType.valueOf(type.toUpperCase()));

                User user = getUserStaff(username);
                staff.setFullname(user.getFullname());
                staff.setPassword(user.getPassword());
                staff.setGender(user.getGender());
                staff.setEmail(user.getEmail());
                staff.setType(UserType.STAFF);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return staff;
    }

    // INSERT STAFF
    public boolean insertNewStaff(Staff staff) {
        try {
            conn.connect();
            
            boolean isUserInserted = new DatabaseController().insertNewUser((User) staff);
            if (isUserInserted) {
                String query = "INSERT INTO staff VALUES(?,?,?,?,?)";
                PreparedStatement stmt = conn.con.prepareStatement(query);
                stmt.setInt(1, 0);
                stmt.setString(2, staff.getUsername());
                stmt.setString(3, staff.getNIK());
                stmt.setDouble(4, staff.getSalary());
                stmt.setString(5, staff.getStaffType().name());

                stmt.executeUpdate();
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    // DELETE STAFF
    public boolean deleteStaff(String username) {
        try {
            conn.connect();
            String query = "DELETE FROM staff WHERE username='" + username + "'";
            Statement stmt = conn.con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);

            if (rowsAffected > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    // UPDATE STAFF
    public boolean updateStaff(Staff staff, String oldUsername) {
        try {
            conn.connect();
            String query = "UPDATE users SET username='" + staff.getUsername() + "', "
                    + "full_name='" + staff.getFullname() + "', "
                    + "email='" + staff.getEmail() + "', "
                    + "password='" + staff.getPassword() + "', "
                    + "address='" + staff.getAddress() + "', "
                    + "phone_number='" + staff.getPhoneNumber() + "', "
                    + "type='" + staff.getType().name() + "' "
                    + "WHERE username='" + oldUsername + "'";
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);

            query = "UPDATE staff SET NIK='" + staff.getNIK() + "', "
                    + "salary='" + staff.getSalary() + "', "
                    + "type='" + staff.getStaffType().name() + "' "
                    + "WHERE username='" + oldUsername + "'";
            stmt = conn.con.createStatement();
            stmt.executeUpdate(query);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    private User getUserStaff(String username) {
        User user = null;
        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE username = '" + username + "' AND type = 'staff'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                user = new User();
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
            conn.disconnect();
        }
        return user;
    }
}