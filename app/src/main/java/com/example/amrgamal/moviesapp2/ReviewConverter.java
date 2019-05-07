package com.example.amrgamal.moviesapp2;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by AmrGamal on 17/02/2019.
 */

@SuppressWarnings("ALL")
class ReviewConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String movieToString(ArrayList<TrailerInfo> arrayList){
        return gson.toJson(arrayList);
 }
    @TypeConverter
    public static ArrayList<ReviewsInfo> stringToReview(String movie){
        Type listType = new TypeToken<ArrayList<ReviewsInfo>>() {}.getType();
        return gson.fromJson(movie , listType);
    }

    @TypeConverter
    public static ArrayList<TrailerInfo> stringToMovie(String movie){
       Type listType = new TypeToken<ArrayList<TrailerInfo>>() {}.getType();
        return gson.fromJson(movie , listType);
    }
    @TypeConverter
    public static String reviewToString(ArrayList<ReviewsInfo> arrayList){
       return gson.toJson(arrayList);
    }


}

