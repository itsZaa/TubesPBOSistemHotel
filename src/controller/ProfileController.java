package controller;

import model.User;

public class ProfileController {
    public void updateUser(User newUser, String oldUsername) {
        new DatabaseController().updateUser(newUser, oldUsername);
    }

}