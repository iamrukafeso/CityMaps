package com.rukayat_oyefeso.citymaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





         if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
             Fragment cityFrgament = new cityFragment();
             getSupportFragmentManager().beginTransaction().replace(R.id.fragment,cityFrgament).commit();
        }
         else{
             Fragment cityFrgament = new cityFragment();
             Fragment googleMapFragment = new GoogleMapFragment();
             getSupportFragmentManager().beginTransaction().replace(R.id.fragment,cityFrgament).commit();
             getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,googleMapFragment).commit();


         }

    }
}
