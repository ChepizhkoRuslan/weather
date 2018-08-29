package com.chepizhko.weather.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chepizhko.weather.R;
import com.chepizhko.weather.adapter.PlacesAdapter;
import com.chepizhko.weather.model.Place;
import com.chepizhko.weather.presenter.WeatherPresenter;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity implements WeatherInterfase{
    private String TAG = "myLogs";
    private static List<Place> lstmPlace = new ArrayList<>();
    private List<Place>lstmOnePlace = new ArrayList<>();
    private PlacesAdapter mAdapter;
    private static String place;
    private WeatherPresenter mWeatherPresenter;
    private static double lat, lon;

    public static Intent newIntent(Context packageContext, List<Place> lst) {
        lat = Double.parseDouble((lst.get(0).getLat()));
        lon = Double.parseDouble(lst.get(0).getLon());
        lstmPlace = lst;
        place = lst.get(0).getPlace();
        Intent intent = new Intent(packageContext, WeatherActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.weather_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PlacesAdapter(this,lstmOnePlace, "onePlace");
        mRecyclerView.setAdapter(mAdapter);

        for(int i = 0; i<lstmPlace.size(); i++){
            if(lstmPlace.get(i).getPlace().equals(place)){
                lstmOnePlace.add(lstmPlace.get(i));
            }
        }
        mWeatherPresenter = new WeatherPresenter(this);


    }
    @Override
    public void clickWeather(View view) {
        mWeatherPresenter.onClickToRequest(lat,lon);
//        SystemClock.sleep(500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherPresenter.onDestroy();
    }
}
