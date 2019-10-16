package com.mezan.multiplemarker;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    ArrayList<Map> userInfo = new ArrayList<>();
    Marker[] markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Add Data

        prepareData();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void prepareData() {
        HashMap<String, String> mUser= new HashMap<>();
        mUser.put("UID","xyz1");
        mUser.put("lat","22.9");
        mUser.put("lon","65.5");
        userInfo.add(mUser);

        mUser= new HashMap<>();
        mUser.put("UID","xyz2");
        mUser.put("lat","28.1");
        mUser.put("lon","75.5");
        userInfo.add(mUser);

        mUser= new HashMap<>();
        mUser.put("UID","xyz3");
        mUser.put("lat","25.1");
        mUser.put("lon","95.5");
        userInfo.add(mUser);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(21.5, 88.5);



       Marker userMarker =  mMap.addMarker(new MarkerOptions().position(sydney).title("User Me"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        userMarker.setTag("Your UID");

        markers = new Marker[userInfo.size()];
        for(int i=0;i<userInfo.size();i++){
            HashMap<String,String> markerInfo;
            markerInfo = (HashMap<String, String>) userInfo.get(i);
            Double lat = Double.valueOf(markerInfo.get("lat"));
            Double lon = Double.valueOf(markerInfo.get("lon"));
            LatLng mLatLng = new LatLng(lat,lon);
            String uid = markerInfo.get("UID");
           // drawMarker(googleMap,mLatLng,uid);
            markers[i] = mMap.addMarker(new MarkerOptions()
                    .position(mLatLng)
                    .title(uid));
            markers[i].setTag(uid);
        }
        mMap.setOnMarkerClickListener(this);

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        Toast.makeText(getApplicationContext(), marker.getTag().toString(),Toast.LENGTH_LONG).show();
       // Log.d("Marker Select", String.valueOf(marker.getTag()));

        return false;
    }
}
