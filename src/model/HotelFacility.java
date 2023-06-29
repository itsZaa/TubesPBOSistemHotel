package model;

public class HotelFacility {
    private int hotelFacilityId;
    private String facilityName;

    public HotelFacility() {
    }

    public HotelFacility(int hotelFacilityId, String facilityName) {
        this.hotelFacilityId = hotelFacilityId;
        this.facilityName = facilityName;
    }

    public int getHotelFacilityId() {
        return this.hotelFacilityId;
    }

    public void setHotelFacilityId(int hotelFacilityId) {
        this.hotelFacilityId = hotelFacilityId;
    }

    public String getFacilityName() {
        return this.facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public HotelFacility hotelFacilityId(int hotelFacilityId) {
        setHotelFacilityId(hotelFacilityId);
        return this;
    }

    public HotelFacility facilityName(String facilityName) {
        setFacilityName(facilityName);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " hotelFacilityId='" + getHotelFacilityId() + "'" +
                ", facilityName='" + getFacilityName() + "'" +
                "}";
    }
}
