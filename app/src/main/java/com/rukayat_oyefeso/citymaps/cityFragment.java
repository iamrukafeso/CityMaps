package com.rukayat_oyefeso.citymaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;


public class cityFragment extends Fragment {

    //Initialize variable
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    List<CityItems> items = new ArrayList<>();
    cityItemAdapter adapter;
    RecyclerView recyclerView;
    Button myCityBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       //
        //
        View view =  inflater.inflate(R.layout.fragment_city, container, false);

        client = LocationServices.getFusedLocationProviderClient(getContext());
        myCityBtn = view.findViewById(R.id.location_btn);

        myCityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //When permission granted  call method
            getCurrentLocation();
        }else {
            //When permission denied Request permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        }
        });
       getCityList();
       // items.add(new CityItems("Test",4455.5,344444.99));
        recyclerView = view.findViewById(R.id.city_items_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new cityItemAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment

        return  view;
    }

    private void getCityList(){
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.cities));
        while (scan.hasNextLine()){
            String names = scan.nextLine();
            String [] array = names.split(" ");
            String cityName = array[0];
            Double cityLatitude = Double.parseDouble(array[1]);

            Double cityLongitude = Double.parseDouble(array[2]);

            items.add(new CityItems(cityName, cityLatitude, cityLongitude));
        }
    }

    private void getCurrentLocation() {
        //Initialize task location
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if(location != null){
                    List<Address> addressList;
                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                    try {
                        addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                        Bundle bundle = new Bundle();
                        double cityLatitude = addressList.get(0).getLatitude();
                        double cityLongtitude = addressList.get(0).getLongitude();

                        if(cityLatitude != 0 && cityLongtitude != 0 ){

                            bundle.putString("cityLatitude", String.valueOf(cityLatitude));
                            bundle.putString("cityLongitude", String.valueOf(cityLongtitude));


                            GoogleMapFragment googleMapFragment = new GoogleMapFragment();
                            googleMapFragment.setArguments(bundle);

                            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment,googleMapFragment).commit();

                            }else{
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment2, googleMapFragment).commit();
                            }


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }


}