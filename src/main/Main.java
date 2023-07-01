package main;

import controller.*;
import model.*;
import view.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private DatabaseController dbController;
    private Scanner sc;

    public Main() {
        this.dbController = new DatabaseController();
        this.sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.check();
    }

    public void check(){
        //CEK GET LIST USER
        ArrayList<User> listUser = new ArrayList<User>();
        listUser = dbController.getAllUsers();

        System.out.println(listUser.toString());


        //CEK GET SPECIFIC USER
        String username = "nico.jonathann";
        User newUser = new User();
        newUser = dbController.getUser(username);

        System.out.println(newUser.toString());

        //CEK INSERT NEW USER
        System.out.println("Username: ");
        String usernameInput = sc.nextLine();

        System.out.println("Full name: ");
        String fullnameInput = sc.nextLine();

        System.out.println("Email: ");
        String emailInput =  sc.nextLine();

        System.out.println("Passowrd: ");
        String passwordInput = sc.nextLine();

        System.out.println("Address: ");
        String addressInput = sc.nextLine();

        System.out.println("Enter the gender (MALE, FEMALE): ");
        String genderInput = sc.nextLine();
        GenderType gender = GenderType.valueOf(genderInput.toUpperCase());

        System.out.println("Phone: ");
        String phoneInput = sc.nextLine();

        System.out.println("Enter the type (customer, staff): ");
        String typeInput = sc.nextLine();
        UserType type = UserType.valueOf(typeInput.toUpperCase());

        User userInput = new User(usernameInput, fullnameInput, passwordInput, gender, phoneInput, emailInput, type);

        dbController.insertNewUser(userInput);
    }
}