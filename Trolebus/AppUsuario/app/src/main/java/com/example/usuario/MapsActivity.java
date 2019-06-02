package com.example.usuario;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private ArrayList<Marker>tmpRealTimeMarkers=new ArrayList<>();
    private ArrayList<Marker>realTimeMarkers=new ArrayList<>();
    private Double Latitud, Longitud;
    private ArrayList<Double> array = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mDatabase =  FirebaseDatabase.getInstance().getReference();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(Marker marker:realTimeMarkers){
                    marker.remove();
                }
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){

                        for( DataSnapshot snap : snapshot.child("Ubicacion").getChildren()){
                        //MapsPojo mp = snap.getValue(MapsPojo.class);
                        //latitud = Double.parseDouble(snap.child("latitud").getValue().toString());//mp.getLatitud();
                        //longitud = Double.parseDouble(snap.child("longitud").getValue().toString()); //mp.getLongitud();
                        array.add(Double.parseDouble(snap.getValue().toString()));


                    }
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(array.get(0), array.get(1))).title("Camion 1");
                    tmpRealTimeMarkers.add(mMap.addMarker(markerOptions));

                  //  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(array.get(0), array.get(1)),13));
                    array.clear();


                }

                    realTimeMarkers.clear();
                    realTimeMarkers.addAll(tmpRealTimeMarkers);
               // Toast.makeText(MapsActivity.this, "lat:"+latitud+"  lon:"+longitud, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

    }
}
