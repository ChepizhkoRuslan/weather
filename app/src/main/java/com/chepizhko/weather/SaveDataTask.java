package com.chepizhko.weather;

import android.os.AsyncTask;

import com.chepizhko.weather.data.AppDatabase;
import com.chepizhko.weather.data.Places;
import com.chepizhko.weather.data.PlacesDao;

/*
 * Класс загружает в базу данные.
 * */
public class SaveDataTask extends AsyncTask<Void, Void, Void> {

    private String mPlace;

public SaveDataTask(String places) {
    mPlace = places;
}

    // метод doInBackground(…) для для заполнения базы
    @Override
    protected Void doInBackground(Void... params) {
        // получение базы
        AppDatabase db = App.getInstance().getDatabase();
        // Из Database объекта получаем Dao
        PlacesDao placesDao = db.placesDao();

        Places places = new Places();

        places.weather = mPlace;

        placesDao.insert(places);

        return null;
    }
}
