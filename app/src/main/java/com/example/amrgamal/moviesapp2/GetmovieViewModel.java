package com.example.amrgamal.moviesapp2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

/**
 * Created by AmrGamal on 22/03/2019.
 */

public class GetmovieViewModel extends AndroidViewModel {
    private LiveData<MoviesInfo> taskEntry ;

    // TODO (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId



    public GetmovieViewModel(Application context , String mTaskId ) {
        super(context);
       FavouriteDatabase database=  FavouriteDatabase.getInstance(context.getApplicationContext());
        taskEntry = database.moviesDao().loadTaskById(mTaskId);
    }

    public LiveData<MoviesInfo> getTaskEntry() {
        return taskEntry;
    }
}

