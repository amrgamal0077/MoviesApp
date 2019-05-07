package com.example.amrgamal.moviesapp2;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by amrgamal on 15/12/2018.
 */
@SuppressWarnings("ALL")
@Entity (tableName = "favourite_movies")

class MoviesInfo {
    @PrimaryKey
    private int id;
    private String original_title;
    private String poster;
    private String overview;
    private String release_date;
    private String voteAverage;
    private ArrayList<TrailerInfo> trailerInfos;
    private ArrayList<ReviewsInfo>reviewsInfos;
    public  MoviesInfo(ArrayList<TrailerInfo>trailerInfos,
                       ArrayList<ReviewsInfo>reviewsInfos,
                       String original_title,
                       String poster,
                       String overview,
                       String release_date,
                       String voteAverage,
                       int id)
    {
        this.trailerInfos=trailerInfos;
        this.reviewsInfos=reviewsInfos;
        this.original_title=original_title;
        this.overview=overview;
        this.poster=poster;
        this.release_date=release_date;
        this.voteAverage=voteAverage;
        this.id=id;
    }
    @Ignore
    public  MoviesInfo(String original_title,String poster,String overview,String release_date,String voteAverage,int id)
    {
        this.original_title=original_title;
        this.overview=overview;
        this.poster=poster;
        this.release_date=release_date;
        this.voteAverage=voteAverage;
        this.id=id;
    }

    public int getId() {
        return id;
    }

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setId(int id) {
//        this.id = id;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

    public String getOriginal_title() {
        return original_title;
    }

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setOriginal_title(String original_title) {
//        this.original_title = original_title;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

    public String getPoster() {
        return poster;
    }

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setPoster(String poster) {
//        this.poster = poster;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

    public String getVoteAverage() {
        return voteAverage;
    }

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setVoteAverage(String voteAverage) {
//        this.voteAverage = voteAverage;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

    public String getOverview() {
        return overview;
    }

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setOverview(String overview) {
//        this.overview = overview;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

    public String getRelease_date() {
        return release_date;
    }

    public ArrayList<ReviewsInfo> getReviewsInfos() {
        return reviewsInfos;
    }

    public ArrayList<TrailerInfo> getTrailerInfos() {
        return trailerInfos;
    }

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setReviewsInfos(ArrayList<ReviewsInfo> reviewsInfos) {
//        this.reviewsInfos = reviewsInfos;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setTrailerInfos(ArrayList<TrailerInfo> trailerInfos) {
//        this.trailerInfos = trailerInfos;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)

// --Commented out by Inspection START (19/02/2019 02:57 ص):
//    public void setRelease_date(String release_date) {
//        this.release_date = release_date;
//    }
// --Commented out by Inspection STOP (19/02/2019 02:57 ص)
}
