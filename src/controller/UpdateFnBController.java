package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.FnBMenu;

public class UpdateFnBController {
    private DatabaseHandler conn;

    public UpdateFnBController() {
        this.conn = new DatabaseHandler();
    }

    // SELECT * FROM FNB_MENU
    public ArrayList<FnBMenu> getAllFnBMenus() {
        ArrayList<FnBMenu> menus = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM fnb_menu";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                FnBMenu menu = new FnBMenu();
                menu.setMenuName(rs.getString("menu_name"));
                menu.setMenuType(rs.getString("menu_type"));
                menu.setPrice(rs.getDouble("price"));

                menus.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return menus;
    }

    // SELECT MENU BY NAME
    public FnBMenu getMenuByName(String name) {
        FnBMenu menu = null;
        try {
            conn.connect();
            String query = "SELECT * FROM fnb_menu WHERE menu_name ='" + name + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                menu = new FnBMenu();
                menu.setMenuName(rs.getString("menu_name"));
                menu.setMenuType(rs.getString("menu_type"));
                menu.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return menu;
    }

    // INSERT MENU
    public boolean insertNewFnBMenu(FnBMenu menu) {
        try {
            conn.connect();
            String query = "INSERT INTO fnb_menu(menu_name, menu_type, price) VALUES(?,?,?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, menu.getMenuName());
            stmt.setString(2, menu.getMenuType());
            stmt.setDouble(3, menu.getPrice());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    // DELETE MENU
    public boolean deleteFnBMenu(String name) {
        try {
            conn.connect();
            String query = "DELETE FROM fnb_menu WHERE menu_name='" + name + "'";
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
}