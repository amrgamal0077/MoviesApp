package com.example.amrgamal.moviesapp2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by CrazyNet on 30/01/2019.
 */

@SuppressWarnings("ALL")
class MovieViewModel extends AndroidViewModel {

    private final LiveData<List<MoviesInfo>> favourate;

    public MovieViewModel(Application application) {
        super(application);
        FavouriteDatabase database = FavouriteDatabase.getInstance(this.getApplication());
        favourate =database.moviesDao().loadAllFavouriteMovies();
    }

    public LiveData<List<MoviesInfo>> getFavourate() {
        return favourate;
    }

}
