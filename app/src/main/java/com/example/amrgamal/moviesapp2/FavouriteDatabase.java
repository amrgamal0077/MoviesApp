package com.example.amrgamal.moviesapp2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by AmrGamal on 17/02/2019.
 */

@SuppressWarnings("ALL")
@Database(entities = {MoviesInfo.class},version = 1,exportSchema = false)
@TypeConverters({ReviewConverter.class})
public abstract class FavouriteDatabase extends RoomDatabase{
    private static final Object Lock= new Object();
    private static final String DatabaseName="Favourite";
    private static FavouriteDatabase favouriteDatabase;

    public static FavouriteDatabase getInstance(Context context)
    {
        if (favouriteDatabase==null)
        {
            synchronized (Lock)
            {
                favouriteDatabase= Room.databaseBuilder(context.getApplicationContext(),
                        FavouriteDatabase.class,
                        FavouriteDatabase.DatabaseName)
                        .build();
            }
        }
        return favouriteDatabase;
    }
    public abstract MoviesDao moviesDao();
}
