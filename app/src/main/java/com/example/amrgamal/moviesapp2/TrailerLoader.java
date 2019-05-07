package com.example.amrgamal.moviesapp2;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by AmrGamal on 16/02/2019.
 */

@SuppressWarnings("ALL")
class TrailerLoader extends AsyncTaskLoader<ArrayList<TrailerInfo>> {

    private final String url;
    public TrailerLoader(String Url, Context context)
    {
        super(context);
        url=Url;

    }
    @Override
    public ArrayList<TrailerInfo> loadInBackground() {
        if(url==null)
            return null;
        return TrailerUtils.fetchdata(url);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
