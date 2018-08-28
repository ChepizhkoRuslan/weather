package com.chepizhko.weather.presenter;

public interface MapsPresenterInterface {
    void onDestroy();
    void onClickToRequest(double lat, double lon);
}
