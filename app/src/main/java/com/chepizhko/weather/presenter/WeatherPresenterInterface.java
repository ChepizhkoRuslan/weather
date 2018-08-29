package com.chepizhko.weather.presenter;

public interface WeatherPresenterInterface {
    void onDestroy();
    void onClickToRequest(double lat, double lon);
}
