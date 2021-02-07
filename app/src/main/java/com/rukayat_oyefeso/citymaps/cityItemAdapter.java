package com.rukayat_oyefeso.citymaps;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cityItemAdapter extends RecyclerView.Adapter<cityItemAdapter.ItemsViewHolder> {

    private Context context;
    private List<CityItems> list;

    public cityItemAdapter(Context context, List<CityItems> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.city_item_layout, parent, false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        final CityItems cityItems = list.get(position);
        holder.cityName.setText(cityItems.getCityName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cityName;
        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final CityItems cityItems = list.get(getLayoutPosition());
            Bundle bundle = new Bundle();
            bundle.putString("cityName", cityItems.getCityName());
            bundle.putString("cityLatitude", String.valueOf(cityItems.getLatitude()));
            bundle.putString("cityLongitude", String.valueOf(cityItems.getLongitude()));

            AppCompatActivity appCompatActivity = (AppCompatActivity)itemView.getContext();

            GoogleMapFragment googleMapFragment = new GoogleMapFragment();
            googleMapFragment.setArguments(bundle);



            if (appCompatActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment,googleMapFragment).commit();
            }
            else{
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,googleMapFragment).commit();


            }
        }
    }
}
