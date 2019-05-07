package com.example.amrgamal.moviesapp2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  MoviesAdapter.itemclick {
   private static final String apiKey="?api_key=8d5f626a55a50b11c79d72345b5eb7bc";
   private static final String Url="http://api.themoviedb.org/3/movie/";
   private String query="popular";
    private RecyclerView recyclerView;
   private ArrayList<MoviesInfo> moviesList;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        if (savedInstanceState!=null)
        {
            if (savedInstanceState.get("query").equals("popular"))
            {
                new MoviesTask().execute(Url+query+apiKey);
            }
            else
            {
                query="top_rated";
                new MoviesTask().execute(Url+query+apiKey);
            }
        }
        else {
            new MoviesTask().execute(Url+query+apiKey);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.popular:
                query="popular";

                new MoviesTask().execute(Url+query+apiKey);return true;
            case R.id.Top_rated:
                query="top_rated";
                new MoviesTask().execute(Url+query+apiKey);
                return true;
            case R.id.favourite:
                startActivity(new Intent(this,FavouriteActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("query",query);
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(MainActivity.this ,Details.class);
        intent.putExtra("title",moviesList.get(position).getOriginal_title());
        intent.putExtra("poster",moviesList.get(position).getPoster());
        intent.putExtra("date",moviesList.get(position).getRelease_date());
        intent.putExtra("overview",moviesList.get(position).getOverview());
        intent.putExtra("voteAverage",moviesList.get(position).getVoteAverage() );
        intent.putExtra("id",String.valueOf(moviesList.get(position).getId())) ;
        intent.putExtra("check","main");
        startActivity(intent);
   }
   @SuppressLint("StaticFieldLeak")
   private class  MoviesTask extends AsyncTask<String,Void,ArrayList<MoviesInfo>>{

       @Override
       protected ArrayList<MoviesInfo> doInBackground(String... strings) {

           return MoviesUtils.fetchdata(strings[0]);
       }

       @Override
       protected void onPostExecute(ArrayList<MoviesInfo> moviesInfos) {
           moviesList=moviesInfos;
           MoviesAdapter moviesAdapter = new MoviesAdapter(moviesInfos, MainActivity.this, MainActivity.this);
           recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
           recyclerView.setAdapter(moviesAdapter);
       }
   }
}
