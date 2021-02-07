package com.rukayat_oyefeso.citymaps;

public class CityItems {
    String CityName;
    Double Longitude;
    Double Latitude;

    public CityItems(String cityName, Double latitude,  Double longitude) {
        CityName = cityName;
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getCityName() {
        return CityName;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public Double getLatitude() {
        return Latitude;
    }
}
