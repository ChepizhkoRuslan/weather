package com.chepizhko.weather.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PlacesDao {
    // позволяет получить полный список погоды
    @Query("SELECT * FROM places")
    List<Places> getAll();

    @Insert
    void insert(Places places);

    @Update
    void update(Places places);

    @Delete
    void delete(Places places);
}
