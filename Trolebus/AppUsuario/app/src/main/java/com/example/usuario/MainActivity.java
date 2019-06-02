package com.example.usuario;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FusedLocationProviderClient mFuseLocationClient;
    private Button mbtnmap,mbtnhor,mbtnqueja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFuseLocationClient = LocationServices.getFusedLocationProviderClient(this);

        subirLatLongFirebase();
        /**/
        mbtnqueja=findViewById(R.id.button);
        mbtnqueja.setOnClickListener(this);
        /**/

        /**/
        mbtnmap=findViewById(R.id.btnmap);
        mbtnmap.setOnClickListener(this);
        /**/

        /**/
        mbtnhor=findViewById(R.id.btnhorario);
        mbtnhor.setOnClickListener(this);
        /**/


    }

    private void subirLatLongFirebase() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFuseLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Log.e("Latitud:",+location.getLatitude()+"Longitud:"+location.getLongitude());
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnmap: Intent intent=new Intent(MainActivity.this,MapsActivity.class);
            startActivity(intent);
            break;
            case R.id.btnhorario: Intent intent1=new Intent(MainActivity.this,HorariosActivity.class);
            startActivity(intent1);
            break;
            case R.id.button: Intent intent2=new Intent(MainActivity.this,Quejas.class);
            startActivity(intent2);
            break;
        }
    }


}

