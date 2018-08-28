package com.chepizhko.weather.presenter;

import com.chepizhko.weather.model.RepoInterface;
import com.chepizhko.weather.model.Repository;
import com.chepizhko.weather.view.MapsInterfase;

public class MapsPresenter implements MapsPresenterInterface {

    private MapsInterfase mMapsInterfase;
    private RepoInterface mRepoInterface;


    public MapsPresenter(MapsInterfase mapsInt) {
        mMapsInterfase = mapsInt;
        mRepoInterface = new Repository();
    }

    @Override
    public void onClickToRequest(double lat, double lon) {
        mRepoInterface.setRequest(lat, lon);

    }

    @Override
    public void onDestroy() {
        mMapsInterfase = null;
    }

}
