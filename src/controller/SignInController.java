package controller;

import controller.DatabaseHandler;
import model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignInController {

    public String SignInController(String userName, String pass) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        String tempPass = pass;
        pass = Hasher.password(pass);
        try {
            java.sql.Statement stat = conn.con.createStatement();
            ResultSet result = stat.executeQuery("select * from users where username='" + userName + "'");
            if (result.next()) {
                if (pass.equals(result.getString("password"))) {
                    GenderType genderFinal;
                    UserType typeFinal;
                    StaffType staffType = null;
                    if (result.getString("type").equals("customer")){
                        typeFinal = UserType.CUSTOMER;
                    }else{
                        typeFinal = UserType.STAFF;
                    }
                    String user_name = result.getString("username");
                    String full_name = result.getString("full_name");
                    String email = result.getString("email");
                    String address = result.getString("address");
                    String gender = result.getString("gender");
                    if (gender.equals("male")){
                        genderFinal = GenderType.MALE;
                    }else{
                        genderFinal = GenderType.FEMALE;
                    }
                    String phone_number = result.getString("phone_number");
                    ResultSet resultStaff = stat.executeQuery("select * from staff where username='" + userName + "'");
                    if (result.getString("type").equals("staff")){
                        if (resultStaff.getString("type").equals("receptionist")){
                            staffType = StaffType.RECEPTIONIST;
                        }else if(resultStaff.getString("type").equals("staff_fnb")){
                            staffType = StaffType.STAFF_FNB;
                        }else if(resultStaff.getString("type").equals("staff_laundry")){
                            staffType = StaffType.STAFF_LAUNDRY;
                        }else if(resultStaff.getString("type").equals("manager")){
                            staffType = StaffType.MANAGER;
                        }
                    }
                    if (typeFinal.equals(UserType.CUSTOMER)){
                        User user = new User(user_name, full_name, tempPass, genderFinal, phone_number, email, typeFinal);
                        SingletonProfile.getInstance().setUser(user);
                        return "customer";
                    }else{
                        Staff staff = new Staff(resultStaff.getInt("staff_id"),resultStaff.getString("NIK"),resultStaff.getDouble("salary"),staffType,user_name,full_name,tempPass,genderFinal,phone_number,email,typeFinal);
                        SingletonProfile.getInstance().setUser(staff);
                        if(staffType.equals(StaffType.MANAGER)){
                            return "manager";
                        }else if(staffType.equals(StaffType.STAFF_FNB)){
                            return "staff_fnb";
                        }else if(staffType.equals(StaffType.STAFF_LAUNDRY)){
                            return "staff_laundry";
                        }else if(staffType.equals(StaffType.RECEPTIONIST)){
                            return "receptionist";
                        }
                    }
                } else {
                    return "Password Salah!";
                }
            } else {
                return "User tidak ditemukan!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}