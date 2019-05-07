package com.example.amrgamal.moviesapp2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by AmrGamal on 17/02/2019.
 */

@SuppressWarnings("ALL")
@Dao
public interface MoviesDao {
    @Query("SELECT * FROM favourite_movies")
    LiveData <List<MoviesInfo>> loadAllFavouriteMovies();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie (MoviesInfo moviesInfo);
// --Commented out by Inspection START (19/02/2019 02:57 ص):
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie (MoviesInfo  moviesInfo);
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)
// --Commented out by Inspection START (19/02/2019 02:57 ص):
        @Delete
       void deleteMovie (MoviesInfo moviesInfo);
    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    LiveData<MoviesInfo> loadTaskById(String id);
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

}
