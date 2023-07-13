package controller;
import controller.DatabaseHandler;
import model.Customer;
import model.SingletonCustomer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SingletonUserController {
 SingletonUserController customers = SingletonUser.getInstance();

    public void addCustomerToDB() {
        customers.reset();
        DatabaseHandler c = new DatabaseHandler();
        try {
            c.connect();
            String query = "SELECT * FROM users";
            PreparedStatement stmt = c.con.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                String username = result.getString("username");
                String fullName = result.getString("full_name");
                String email = result.getString("email");
                String address = result.getString("address");
                String gender = result.getString("gender");
                String phone_number = result.getString("phone_number");
                Customer customers = new Customer(username, fullName, email, address, gender, phone_number);
                customers.addCustomerToDB(customers);
            }
            
            c.disconnect();
        } catch (Exception e) {
        }
    }
}
