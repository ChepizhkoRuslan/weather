package com.chepizhko.weather.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Places.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlacesDao placesDao();
}
