package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.RoomType;

public class RoomTransactionController {
    private DatabaseHandler dbHandler;

    public RoomTransactionController() {
        dbHandler = new DatabaseHandler();
    }

    public ArrayList<RoomType> getRoom () {
        return null;
    }
}

