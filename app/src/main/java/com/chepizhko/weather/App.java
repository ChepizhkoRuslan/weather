package com.chepizhko.weather;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.chepizhko.weather.api.APIService;
import com.chepizhko.weather.data.AppDatabase;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
* Класс используется для создания и получения объекта ретрофит и объекта базы данных.
* */
public class App extends Application {
    private static OkHttpClient client;
    private static APIService service;

    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        client = new OkHttpClient.Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(APIService.class);

        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();

    }

    public static OkHttpClient getClient() {
        return client;
    }

    public static APIService getService() {
        return service;
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
