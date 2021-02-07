package com.rukayat_oyefeso.citymaps;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class GoogleMapFragment extends Fragment {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    String city;
    double  latitude,longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_google_map, container, false);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMapView);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            city = bundle.getString("cityName");
            latitude = Double.parseDouble(bundle.getString("cityLatitude"));
            longitude = Double.parseDouble(bundle.getString("cityLongitude"));

        }
        else{
            city = "Cork";
            latitude =51.892171;
            longitude = -8.475068;

        }

        Log.i("CityName", String.valueOf(latitude));
        Log.i("CityName", String.valueOf(longitude));

        drawMarker();
        return v;

    }


    private void drawMarker() {
        //Initialize task location

                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //Initialize lat lng
                            googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                                @Override
                                public void onMapLoaded() {

                                    MarkerOptions options = new MarkerOptions().position(new LatLng(latitude,longitude))
                                            .title("Current Location");
                                    //Zoom maap
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 10));
                                    //Add marker on map
                                    googleMap.addMarker(options);

                                }
                            });





                        }
                    });
                }


}