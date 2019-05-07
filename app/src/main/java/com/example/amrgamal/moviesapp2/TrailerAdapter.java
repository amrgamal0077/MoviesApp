package com.example.amrgamal.moviesapp2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmrGamal on 17/02/2019.
 */

@SuppressWarnings("ALL")
public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.recyclerVH> {
private final List<TrailerInfo> TrailerList;
    private final itemclick itemclick;
public TrailerAdapter(ArrayList<TrailerInfo> name, Context context, itemclick itemclick) {
        this.TrailerList = name;
    Context context1 = context;
        this.itemclick=itemclick;
        }
@NonNull
@Override
public TrailerAdapter.recyclerVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_item, viewGroup, false);
        return new TrailerAdapter.recyclerVH(view);        }

            @Override
            public void onBindViewHolder(@NonNull recyclerVH holder, int position) {
                TextView TrailerName=holder.itemView.findViewById(R.id.Trailer_name);
                TrailerName.setText(TrailerList.get(position).getName());
            }


@Override
public int getItemCount() {
        return TrailerList.size();
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


