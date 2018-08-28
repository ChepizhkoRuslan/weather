package com.chepizhko.weather.model;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.chepizhko.weather.App;
import com.chepizhko.weather.SaveDataTask;
import com.chepizhko.weather.data.AppDatabase;
import com.chepizhko.weather.data.Places;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository implements RepoInterface {
    private static String KEY = "7b6099f185f3650cfd03a8c368053d4e";
    private String TAG = "myLogs";
    private List<Place> lstPlace = new ArrayList<>();
    private List<Place> lstOnePlace = new ArrayList<>();



    /*
     * Метод делает запрос, получает response, создаёт список объектов и отправляет в базу;
     * */
    @Override
    public void setRequest(double lat, double lon) {
        Log.d(TAG, "RESPONSE 234================== " + lat);

        // get resp
        Call<ResponseBody> resp = App.getService().getWeather(lat, lon, KEY, "xml");
        resp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

//                Log.d(TAG, "RESPONSE 234================== " + response);

                if (response.isSuccessful()) {

                    try {
                        String respon = response.body().string();

                        new SaveDataTask(new Place(respon)).execute();
                        //new ParseTask(respon).execute();

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


    private class ParseTask extends AsyncTask<Void, Void, List<Place>> {
        private String response;
        private List<Place>mLstPlaces;


        // конструктор, который получает response и сохраняет ее в переменной
        ParseTask(String query) {
            response = query;
        }

        public ParseTask(List<Place> lstPlace) {
            mLstPlaces = lstPlace;
        }

        // метод doInBackground(…) для парсинга данных с сайта
        @Override
        protected List<Place> doInBackground(Void... params) {

//            Document document = Jsoup.parse(response);
//            Element city = document.selectFirst("current>city");
//            long id = Long.parseLong(city.attr("id"));
//            String name = city.attr("name");

            for(int i = 0; i < mLstPlaces.size(); i++){
                String resp =  mLstPlaces.get(i).getPlace();
                Document document = Jsoup.parse(resp);
                Element city = document.selectFirst("current>city");
                String name = city.attr("name");
                Log.e(TAG, "===========!!!!!!!!!!!!!!!!!!!!!!!!!!! ======== " + name);
                if(!name.equals("")) {
                    lstOnePlace.add(new Place(name));
                }
            }

//
//            Log.e(TAG, "===========!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + id + " ======== " + name);

            return null;
        }
    }

    public class LoadDataTask extends AsyncTask<Void, Void, List<Place>> {

        // метод doInBackground(…) для запроса в базу
        @Override
        protected List<Place> doInBackground(Void... params) {
            // получение базы
            AppDatabase db = App.getInstance().getDatabase();

            List<Places> lPlaces = db.placesDao().getAll();
            for (int j = 0; j < lPlaces.size(); j++) {
                lstPlace.add(new Place(lPlaces.get(j).weather));
//                Log.e(TAG, "===========!!!!!!!!!!!Response BD =======" + lPlaces.get(j).weather);
            }
            new ParseTask(lstPlace).execute();
            return null;
        }
    }

    @Override
    public List<Place> getListPlace() {
        return lstOnePlace;
    }

    @Override
    public void requestDB() {
        new LoadDataTask().execute();
    }

}
