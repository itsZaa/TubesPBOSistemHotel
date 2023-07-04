package controller;

import java.util.ArrayList;

import model.FnBMenu;

public class FnBController {
    public FnBController() {

    }

    public String getFnBMenu() {
        ArrayList<FnBMenu> menuList = new DatabaseController().getAllFnBMenu();

        StringBuilder sb = new StringBuilder();
        sb.append("Food & Beverages List :\n\n");

        int c = 1;
        for (FnBMenu menu : menuList) {
            sb.append(c).append(". ").append(menu.getMenuName()).append(" : ").append(menu.getPrice());
            c++;
        }

        return sb.toString();
    }
}