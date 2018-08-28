package com.chepizhko.weather;

import android.app.IntentService;
import android.content.Intent;

import com.chepizhko.weather.model.Place;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class MyService extends IntentService {
    List<Place>mPlaces;

    public MyService(List<Place>places) {
        super("MyService");
        mPlaces = places;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        EventBus.getDefault().post(mPlaces);
    }
}
