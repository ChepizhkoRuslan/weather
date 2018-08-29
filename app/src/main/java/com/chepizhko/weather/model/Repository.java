package com.chepizhko.weather.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.chepizhko.weather.App;
import com.chepizhko.weather.SaveDataTask;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository implements RepoInterface {
    private static String KEY = "7b6099f185f3650cfd03a8c368053d4e";
    private String TAG = "myLogs";
    /*
     * Метод делает запрос, получает response, создаёт список объектов и отправляет в базу;
     * */
    @Override
    public void setRequest(double lat, double lon) {
        // get resp
        Call<ResponseBody> resp = App.getService().getWeather(lat, lon, KEY, "xml");
        resp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    try {
                        String respon = response.body().string();
                        new SaveDataTask(respon).execute();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
