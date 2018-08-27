package com.chepizhko.weather.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Places {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String weather;
}
