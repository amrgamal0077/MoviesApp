package com.example.amrgamal.moviesapp2;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by AmrGamal on 18/02/2019.
 */

@SuppressWarnings("ALL")
class ReivewsLoader extends AsyncTaskLoader<ArrayList<ReviewsInfo>> {
private final String url;
    public ArrayList<ReviewsInfo> reviewsInfos = new ArrayList<>();
    public ReivewsLoader(Context context,String url) {
        super(context);
        this.url=url;
    }

    @Override
    public ArrayList<ReviewsInfo> loadInBackground() {
        if(url==null)
            return null;
        reviewsInfos=ReviewsUtils.fetchdata(url);
        Log.d("values",String.valueOf(reviewsInfos.size()) );
        return ReviewsUtils.fetchdata(url);

    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
