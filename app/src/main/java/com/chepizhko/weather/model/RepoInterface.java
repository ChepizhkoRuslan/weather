package com.chepizhko.weather.model;

import java.util.List;

public interface RepoInterface {
    void setRequest(double lat, double lon);
    List<Place> getListPlace();
    void requestDB();
}
