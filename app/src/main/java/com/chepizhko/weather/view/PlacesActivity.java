package com.chepizhko.weather.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chepizhko.weather.R;
import com.chepizhko.weather.adapter.PlacesAdapter;
import com.chepizhko.weather.model.Place;
import com.chepizhko.weather.presenter.PlacePresenter;

import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity implements PlacesInterface{
    private List<Place>lstmPlace = new ArrayList<>();
    private PlacesAdapter mAdapter;
    private PlacePresenter mPlacePresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.places_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PlacesAdapter(this,lstmPlace);
        mRecyclerView.setAdapter(mAdapter);
        mPlacePresenter = new PlacePresenter(this);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPlacePresenter.onLoadTask();
//            }
//        },500);
        mPlacePresenter.onLoadTask();
    }

    @Override
    public void setItem(List<Place> item) {
        lstmPlace = item;
        mAdapter.setData(item);
    }

    public void clickToMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
