package com.chepizhko.weather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chepizhko.weather.R;
import com.chepizhko.weather.model.Place;
import com.chepizhko.weather.view.WeatherActivity;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.CatViewHolder> {

    private Context mContext;
    private List<Place> items;
    private String flagPlace;


    public class CatViewHolder extends RecyclerView.ViewHolder {

        private TextView place;
        private RelativeLayout mRelativeLayout;


        CatViewHolder(View itemView) {
            super(itemView);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rel_lay_card);
            place = (TextView) itemView.findViewById(R.id.txt_places);
        }

        private void bindGalleryItem(Place imageItem) {
            if(flagPlace.equals("listPlace")) {
                place.setText(imageItem.getPlace());
            }
            if(flagPlace.equals("onePlace")) {
                place.setText(imageItem.getWeather());
            }
        }
    }

    public PlacesAdapter(Context context, List<Place> items, String flag){
        this.mContext = context;
        this.items = items;
        flagPlace = flag;

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
    public void onBindViewHolder(CatViewHolder placeViewHolder, @SuppressLint("RecyclerView") final int i) {
        final Place txtPlace = items.get(i);
        placeViewHolder.bindGalleryItem(txtPlace);
        if(flagPlace.equals("listPlace")) {
            placeViewHolder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Выбрано " + txtPlace.getPlace(), Toast.LENGTH_SHORT).show();
                    Intent intent = WeatherActivity.newIntent(mContext, items);
                    mContext.startActivity(intent);
                }
            });
        }
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


