package com.chepizhko.weather.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.chepizhko.weather.R;
import com.chepizhko.weather.presenter.MapsPresenter;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsInterfase {
    private String TAG = "myLogs";
    private GoogleMap mMap;
    private double lat = 50.0, lon = 36.0;
    private MapsPresenter mMapsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMapsPresenter = new MapsPresenter(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng charkiv = new LatLng(50, 36);
        mMap.addMarker(new MarkerOptions().position(charkiv).title("Marker"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(charkiv)
                .zoom(5)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(cameraUpdate);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                lat = latLng.latitude;
                lon = latLng.longitude;
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            }
        });
    }

    @Override
    public void clickOk(View view) {
        mMapsPresenter.onClickToRequest(lat,lon);
        Log.d(TAG, "RESPONSE MAPS ============ " + lat+ "      "+lon);
        Intent intent = new Intent(this,PlacesActivity.class);
        SystemClock.sleep(500);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void clickCancel(View view) {
        Intent intent = new Intent(this,PlacesActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapsPresenter.onDestroy();
    }

}
