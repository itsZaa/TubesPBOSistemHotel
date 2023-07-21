package controller;

import model.*;

public class SignInController {
    public User getUser(String username, String password) {
        return new DatabaseController().getUser(username, password);
    }
}