package com.example.amrgamal.moviesapp2;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.TypeConverter;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Details extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<TrailerInfo>>,TrailerAdapter.itemclick {
    private static final String apiKey="?api_key=8d5f626a55a50b11c79d72345b5eb7bc";
    private static final String Url="http://api.themoviedb.org/3/movie/";
    private ArrayList<TrailerInfo> trailerInfos;
    private String movie_id;
    private FavouriteDatabase favouriteDatabase;
    private ReivewsLoader reivewsLoader;
    private ArrayList<ReviewsInfo> reviewsInfos=null;
    private ArrayList<ReviewsInfo> reviewsInfos_fov=new ArrayList<>();
    private ArrayList<TrailerInfo> trailerInfos_fov= new ArrayList<>();
    private String check;
    private String  Title;
    private String Date;
    private String Rate;
    private String OverView;
    private String Poster;
    private String trailerinfo_fev;
    private  String reviews_fev;
    private final Gson gson =new Gson();
    LoaderManager loaderManager;
    private int added;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //get data from intent
        final Intent intent  = getIntent();
        if (savedInstanceState ==null) {
            added=0;
            Title = intent.getStringExtra("title");
            Date = intent.getStringExtra("date");
            Rate = intent.getStringExtra("voteAverage");
            OverView = intent.getStringExtra("overview");
            Poster = intent.getStringExtra("poster");
            trailerinfo_fev = intent.getStringExtra("Trailer");
            reviews_fev = intent.getStringExtra("Reviews");
            trailerInfos_fov = ReviewConverter.stringToMovie(trailerinfo_fev);
            reviewsInfos_fov = ReviewConverter.stringToReview(reviews_fev);
            check = intent.getStringExtra("check");
            movie_id =intent.getStringExtra("id");
        }
        else
        {
            added=savedInstanceState.getInt("added");
            Title=savedInstanceState.getString("title");
            Date=savedInstanceState.getString("date");
            Rate=savedInstanceState.getString("rate");
            OverView=savedInstanceState.getString("overview");
            Poster=savedInstanceState.getString("poster");
            trailerinfo_fev=savedInstanceState.getString("trailerinfo");
            reviews_fev=savedInstanceState.getString("review");
            trailerInfos_fov=ReviewConverter.stringToMovie(trailerinfo_fev);
            reviewsInfos_fov=ReviewConverter.stringToReview(reviews_fev);
            movie_id=savedInstanceState.getString("id");
            check=savedInstanceState.getString("check");
            trailerInfos=ReviewConverter.stringToMovie(savedInstanceState.getString("trailer"));
            reviewsInfos=ReviewConverter.stringToReview(savedInstanceState.getString("reviews"));
        }//put data to ui
        setTitle(intent.getStringExtra("title"));
        TextView title =findViewById(R.id.Name);
        title.setText(intent.getStringExtra("title"));
        TextView date =findViewById(R.id.date);
        TextView rate =findViewById(R.id.rate);
        TextView overview =findViewById(R.id.overview);
        date.setText(intent.getStringExtra("date"));
        overview.setText(intent.getStringExtra("overview"));
        rate.setText(intent.getStringExtra("voteAverage")+rate.getText());
        ImageView image=findViewById(R.id.imageView);
        final ImageView star=findViewById(R.id.star);
        Getmovie getmovie = new Getmovie(this.getApplication(),movie_id);
        GetmovieViewModel viewModel = ViewModelProviders.of(this,getmovie).get(GetmovieViewModel.class);
        viewModel.getTaskEntry().observe(this, new Observer<MoviesInfo>() {
            @Override
            public void onChanged(@Nullable MoviesInfo moviesInfo) {
                if (moviesInfo !=null)
                {
                    added=1;
                    star.setImageResource(R.drawable.star);

                }
                else
                {
                    star.setImageResource(R.drawable.star2);

                    added=0;
                }
            }
        });
        final MoviesInfo moviesInfo = new MoviesInfo(trailerInfos, reviewsInfos, Title, Poster, OverView, Date, Rate, Integer.valueOf(movie_id));

        String poster_url = "http://image.tmdb.org/t/p/w780";
        Picasso.with(this).load(poster_url +intent.getStringExtra("poster")).into(image);


            loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
            loaderManager.initLoader(1, null, this);

        favouriteDatabase = FavouriteDatabase.getInstance(this);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (added==1 )
                {
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            favouriteDatabase.moviesDao().deleteMovie(moviesInfo);
                            added = 0;
                            star.setImageResource(R.drawable.star2);
                        }
                    });
                    Toast.makeText(Details.this, "The film is deleted from your favourite list", Toast.LENGTH_SHORT).show();
                    }

                else {
                if (check.equals("main")) {
                    final MoviesInfo moviesInfo = new MoviesInfo(trailerInfos, reviewsInfos, Title, Poster, OverView, Date, Rate, Integer.valueOf(movie_id));
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            favouriteDatabase.moviesDao().insertMovie(moviesInfo);
                            added = 1;
                            star.setImageResource(R.drawable.star);
                        }
                    });
                    Toast.makeText(Details.this, "The film is added in your favourite list", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(check.equals("main"))
        {
            loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
            loaderManager.initLoader(1, null, this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("poster", Poster);
        outState.putString("title", Title);
        outState.putString("trailerinfo", ReviewConverter.movieToString( trailerInfos_fov));
        outState.putString("review", ReviewConverter.reviewToString(reviewsInfos_fov));
        outState.putString("rate", Rate);
        outState.putString("overview", OverView);
        outState.putString("date", Date);
        outState.putString("id", movie_id);
        outState.putString("check", check);
        outState.putString( "trailer",ReviewConverter.movieToString(trailerInfos));
        outState.putString("reviews",ReviewConverter.reviewToString(reviewsInfos));
        outState.putInt("added",added);
    }


    @Override
    public Loader<ArrayList<TrailerInfo>> onCreateLoader(int id, Bundle args) {
        reivewsLoader = new ReivewsLoader(this,Url+movie_id+"/reviews"+apiKey);
        reivewsLoader.forceLoad();
        return   new TrailerLoader(Url+movie_id+"/videos"+apiKey,this);
    }


    @Override
    public void onLoadFinished(Loader<ArrayList<TrailerInfo>> loader, ArrayList<TrailerInfo> data) {
        trailerInfos=data;
// recyclerview
        RecyclerView recyclerView_review = findViewById(R.id.recycler_reviews);


        RecyclerView recyclerView = findViewById(R.id.recycler_trailer);
        ReviewsAdapter reviewsAdapter =new ReviewsAdapter(null,this);
        TrailerAdapter trailerAdapter= new TrailerAdapter(null,this,this) ;
        if (check.equals("main")) {
            Toast.makeText(this, "main", Toast.LENGTH_SHORT).show();
            reviewsInfos=reivewsLoader.reviewsInfos;
            reviewsAdapter = new ReviewsAdapter(reviewsInfos, Details.this);
            trailerAdapter = new TrailerAdapter(data, Details.this, this);
        }
        if (check.equals("favourite")){
            Toast.makeText(this, "favourite", Toast.LENGTH_SHORT).show();
            reviewsAdapter = new ReviewsAdapter(reviewsInfos_fov , Details.this);
            trailerAdapter = new TrailerAdapter(trailerInfos_fov, Details.this, this);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(trailerAdapter);
        recyclerView_review.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_review.setAdapter(reviewsAdapter);
        }


    @Override
    public void onLoaderReset(Loader<ArrayList<TrailerInfo>> loader) {

    }

    @Override
    public void onItemClicked(int position) {
        String Video_Id=trailerInfos.get(position).getKey();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://"+Video_Id)));

        }

    @Override
    protected void onStop() {
        if (check.equals("main")) {
            loaderManager.destroyLoader(0);
            loaderManager.destroyLoader(1);
        }super.onStop();
    }
}
