package com.chepizhko.weather.presenter;

import com.chepizhko.weather.model.RepoInterface;
import com.chepizhko.weather.model.Repository;
import com.chepizhko.weather.view.WeatherInterfase;

public class WeatherPresenter implements WeatherPresenterInterface {

    private WeatherInterfase mWeatherInterfase;
    private RepoInterface mRepoInterface;


    public WeatherPresenter(WeatherInterfase mapsInt) {
        mWeatherInterfase = mapsInt;
        mRepoInterface = new Repository();
    }

    @Override
    public void onClickToRequest(double lat, double lon) {
        mRepoInterface.setRequest(lat, lon);

    }

    @Override
    public void onDestroy() {
        mWeatherInterfase = null;
    }

}
