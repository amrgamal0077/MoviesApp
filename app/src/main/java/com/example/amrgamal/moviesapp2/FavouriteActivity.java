package com.example.amrgamal.moviesapp2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity implements  MoviesAdapter.itemclick{
private final ArrayList<MoviesInfo> moviesinfos= new ArrayList<>();
private RecyclerView recyclerView;
private MoviesAdapter moviesAdapter;
    private final Gson gson = new Gson();
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        MovieViewModel viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getFavourate().observe(this, new Observer<List<MoviesInfo>>() {
            @Override
            public void onChanged(@Nullable List<MoviesInfo> movieFavourates) {
                assert movieFavourates != null;
                for (int i = 0; i < movieFavourates.size() ; i++)
                {
                    moviesinfos.add(movieFavourates.get(i));
                    recyclerView = findViewById(R.id.recycler);
                    moviesAdapter= new MoviesAdapter(moviesinfos,FavouriteActivity.this,FavouriteActivity.this);
                    recyclerView.setLayoutManager(new GridLayoutManager(FavouriteActivity.this,2));
                    recyclerView.setAdapter(moviesAdapter);
                }

            }
        });
}



    private String movieToString(List<TrailerInfo> arrayList){
        return gson.toJson(arrayList);
    }
    private String reviewToString(List<ReviewsInfo> arrayList){
        return gson.toJson(arrayList);
    }
    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(FavouriteActivity.this ,Details.class);
        intent.putExtra("title",moviesinfos.get(position).getOriginal_title());
        intent.putExtra("poster",moviesinfos.get(position).getPoster());
        intent.putExtra("date",moviesinfos.get(position).getRelease_date());
        intent.putExtra("overview",moviesinfos.get(position).getOverview());
        intent.putExtra("voteAverage",moviesinfos.get(position).getVoteAverage() );
        intent.putExtra("id",String.valueOf(moviesinfos.get(position).getId())) ;
        intent.putExtra("Trailer",movieToString(moviesinfos.get(position).getTrailerInfos()));
        intent.putExtra("Reviews",reviewToString(moviesinfos.get(position).getReviewsInfos()));
        intent.putExtra("check","favourite");

        startActivity(intent);


    }
}
