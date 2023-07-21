package controller;

import java.sql.SQLException;

import controller.DatabaseController;
import model.Customer;
import model.SingletonProfile;
import model.User;

public class ProfileController {
    public void updateUser(User newUser, String oldUsername){
        new DatabaseController().updateUser(newUser, oldUsername);
    }

}

