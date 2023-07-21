package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.HotelFacility;

public class UpdateFacilityController {
    private DatabaseHandler conn;

    public UpdateFacilityController() {
        this.conn = new DatabaseHandler();
    }

    // SELECT * FROM HOTEL_FACILITY
    public ArrayList<HotelFacility> getAllFacilities() {
        ArrayList<HotelFacility> facilites = new ArrayList<>();
        try {
            conn.connect();
            String query = "SELECT * FROM hotel_facility";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HotelFacility facility = new HotelFacility();
                facility.setHotelFacilityId(rs.getInt("hotel_facility_id"));
                facility.setFacilityName(rs.getString("facility"));

                facilites.add(facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return facilites;
    }

    // SELECT FACILITY BY ID
    public HotelFacility getFacilityByName(String name) {
        HotelFacility facility = null;
        try {
            conn.connect();
            String query = "SELECT * FROM hotel_facility WHERE facility ='" + name + "'";
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                facility = new HotelFacility();
                facility.setHotelFacilityId(rs.getInt("hotel_facility_id"));
                facility.setFacilityName(rs.getString("facility"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return facility;
    }

    // INSERT FACILITY
    public boolean insertNewFacility(HotelFacility facility) {
        try {
            conn.connect();
            String query = "INSERT INTO hotel_facility(facility) VALUES(?)";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, facility.getFacilityName());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn.disconnect();
        }
    }

    // DELETE FACILITY
    public boolean deleteFacility(String name) {
        try {
            conn.connect();
            String query = "DELETE FROM hotel_facility WHERE facility='" + name + "'";
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