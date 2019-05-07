package com.example.amrgamal.moviesapp2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AmrGamal on 18/02/2019.
 */

@SuppressWarnings("ALL")
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.recyclerVH> {
    private  ArrayList<ReviewsInfo> ReviewsList =new ArrayList<>();

    public ReviewsAdapter(ArrayList<ReviewsInfo> name, Context context) {
        this.ReviewsList = name;
        Context context1 = context;

    }

    @NonNull
    @Override
    public ReviewsAdapter.recyclerVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reviews_item, viewGroup, false);
        return new ReviewsAdapter.recyclerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.recyclerVH holder, int position) {
        TextView author = holder.itemView.findViewById(R.id.author);
        author.setText(ReviewsList.get(position).getAuthor());
        TextView content = holder.itemView.findViewById(R.id.content);
        content.setText(ReviewsList.get(position).getContent());
    }


    @Override
    public int getItemCount() {
        return ReviewsList.size();
    }

    class recyclerVH extends RecyclerView.ViewHolder {
        public recyclerVH(@NonNull View itemView) {
            super(itemView);

        }
    }
}