package com.chepizhko.weather.model;

public class Place {
    private String place;
    private String weather;
    private String lat;
    private String lon;

    public Place(String place, String weather, String lat, String lon) {
        this.place = place;
        this.weather = weather;
        this.lat = lat;
        this.lon = lon;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
