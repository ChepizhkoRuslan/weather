package com.chepizhko.weather;

import android.app.IntentService;
import android.content.Intent;

import com.chepizhko.weather.data.AppDatabase;
import com.chepizhko.weather.data.Places;
import com.chepizhko.weather.model.Place;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;


public class MyService extends IntentService {
    private String TAG = "myLogs";
    private List<String> strWeath = new ArrayList<>();
    private List<Place> lstPlace = new ArrayList<>();

    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // получение базы.
        AppDatabase db = App.getInstance().getDatabase();
        List<Places> lPlaces = db.placesDao().getAll();

        for (int j = 0; j < lPlaces.size(); j++) {
            strWeath.add(lPlaces.get(j).weather);
        }
        // получение списка Place из ответа.
        for(int i = 0; i < strWeath.size(); i++){
            String resp =  strWeath.get(i);
            Document document = Jsoup.parse(resp);
            try {
                Element city = document.selectFirst("current>city");
                String name = city.attr("name");
                Element coordLat = document.select("coord[lat]").first();
                String lat = coordLat.attr("lat");
                Element coord = document.select("coord[lon]").first();
                String lon = coord.attr("lon");

                lstPlace.add(new Place(name,resp,lat,lon));

            }catch (NullPointerException ex){
                ex.fillInStackTrace();
            }
        }

        EventBus.getDefault().post(lstPlace);
    }
}
