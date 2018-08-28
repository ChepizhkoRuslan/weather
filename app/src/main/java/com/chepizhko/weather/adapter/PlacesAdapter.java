package com.chepizhko.weather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chepizhko.weather.R;
import com.chepizhko.weather.model.Place;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.CatViewHolder> {

    private Context mContext;
    private List<Place> items;

    public class CatViewHolder extends RecyclerView.ViewHolder {

        private TextView place;


        CatViewHolder(View itemView) {
            super(itemView);
            place = (TextView)itemView.findViewById(R.id.txt_places);
        }

        private void bindGalleryItem(Place imageItem) {
            place.setText(imageItem.getPlace());
        }
    }

    public PlacesAdapter(Context context, List<Place> items){
        this.mContext = context;
        this.items = items;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        CatViewHolder pvh = new CatViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CatViewHolder prestaViewHolder, @SuppressLint("RecyclerView") int i) {
        final Place txtPlace = items.get(i);
        prestaViewHolder.bindGalleryItem(txtPlace);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<Place> items){
        this.items = items;
        notifyDataSetChanged();
    }
}


