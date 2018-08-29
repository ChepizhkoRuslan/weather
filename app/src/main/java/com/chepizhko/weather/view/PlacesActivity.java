package com.chepizhko.weather.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chepizhko.weather.MyService;
import com.chepizhko.weather.R;
import com.chepizhko.weather.adapter.PlacesAdapter;
import com.chepizhko.weather.model.Place;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity {
    private List<Place>lstmPlace = new ArrayList<>();
    private List<Place>lstmOnePlace = new ArrayList<>();
    private PlacesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.places_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PlacesAdapter(this,lstmOnePlace, "listPlace");
        mRecyclerView.setAdapter(mAdapter);

        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);

    }

    public void setItem(List<Place> item) {
        lstmPlace = item;
        mAdapter.setData(lstmPlace);
    }


    public void clickToMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        this.finish();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventFromService(List<Place>lstOnePlace){
        setItem(lstOnePlace);
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
