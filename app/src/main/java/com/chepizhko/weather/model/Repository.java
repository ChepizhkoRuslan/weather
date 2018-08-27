package com.chepizhko.weather.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.chepizhko.weather.App;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {

    private static String TAG = "myLogs";
    private static String KEY = "7b6099f185f3650cfd03a8c368053d4e";


    /*
     * Метод делает запрос, получает response, создаёт список объектов и отправляет в базу;
     * */
    public static void setRequest(int lat, int lon) {
        // get resp
        Call<ResponseBody>resp = App.getService().getWeather(lat, lon, KEY);
        resp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                    Log.d(TAG, "RESPONSE 234================== " + response);

                if (response.isSuccessful()) {

                    try {
                        String items = response.body().string();
                        Log.d(TAG, "RESPONSE 234================== " + items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                    new ParseTask(items).execute();

                } else {
                    Log.d(TAG, "RESPONSE FILED ");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "RESPONSE ERROR ");
            }
        });

    }

}
