package com.chepizhko.weather.presenter;

import com.chepizhko.weather.model.Place;
import com.chepizhko.weather.model.RepoInterface;
import com.chepizhko.weather.model.Repository;
import com.chepizhko.weather.view.PlacesInterface;

import java.util.List;


public class PlacePresenter implements PlacePrasenterInterface {
    RepoInterface mRepoInterface;
    PlacesInterface mPlacesInterface;

    public PlacePresenter(PlacesInterface placesInterface) {
        mPlacesInterface = placesInterface;
        mRepoInterface = new Repository();
    }

    @Override
    public void onLoadTask() {
        mRepoInterface.requestDB();

        List<Place> places = mRepoInterface.getListPlace();
        mPlacesInterface.setItem(places);

    }

    @Override
    public void onRespTask() {

    }
}
