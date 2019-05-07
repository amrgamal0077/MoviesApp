package com.example.amrgamal.moviesapp2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Created by AmrGamal on 22/03/2019.
 */

public class Getmovie extends ViewModelProvider.NewInstanceFactory {
    private Application mdatabase;
    private String id ;

    public Getmovie(Application database ,String  taskId) {
        mdatabase = database;
        id = taskId ;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GetmovieViewModel(mdatabase,id);
    }
}
