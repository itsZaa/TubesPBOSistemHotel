package model;

import java.util.ArrayList;

public class Favorite {
    private int favoriteId;
    private ArrayList<RoomType> roomTypeList;

    public Favorite() {
    }

    public Favorite(int favoriteId, ArrayList<RoomType> roomTypeList) {
        this.favoriteId = favoriteId;
        this.roomTypeList = roomTypeList;
    }

    public int getFavoriteId() {
        return this.favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public ArrayList<RoomType> getRoomTypeList() {
        return this.roomTypeList;
    }

    public void setRoomTypeList(ArrayList<RoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }


    @Override
    public String toString() {
        return "{" +
            " favoriteId='" + getFavoriteId() + "'" +
            ", roomTypeList='" + getRoomTypeList() + "'" +
            "}";
    }
}