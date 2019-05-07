package com.example.amrgamal.moviesapp2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrgamal on 15/12/2018.
 */
@SuppressWarnings("ALL")
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.recyclerVH>
{
    private final String poster_url="http://image.tmdb.org/t/p/w780";
    private final List<MoviesInfo> moviesList;
    private final Context context;
    private final itemclick itemclick;
    public MoviesAdapter(ArrayList<MoviesInfo> name, Context context,itemclick itemclick) {
        this.moviesList = name;
        this.context = context;
        this.itemclick=itemclick;
    }
    @NonNull
    @Override
    public MoviesAdapter.recyclerVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false);
        return new MoviesAdapter.recyclerVH(view);        }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.recyclerVH holder,final int position) {
        ImageView image=holder.itemView.findViewById(R.id.image) ;
        Picasso.with(context).load(poster_url+moviesList.get(position).getPoster()).into(image);
        Log.v(MoviesAdapter.class.getName(), poster_url+moviesList.get(position).getPoster());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public interface  itemclick{
        void onItemClicked(int position);
        }

    class recyclerVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public recyclerVH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemclick.onItemClicked(getAdapterPosition());
        }
    }
}
