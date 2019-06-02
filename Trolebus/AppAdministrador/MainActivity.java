package com.example.myapplication;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private DatabaseReference mDatabase;// ...
    private Bundle bundle;
    String correo,user,alertid;
    TextView mail;
    String lat="nada", lon="nada";
    private FusedLocationProviderClient mFusedLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle datos = getIntent().getExtras();
        correo =datos.getString("correoo");
        user = datos.getString("userr");





        //Toast.makeText(this, correo, Toast.LENGTH_LONG).show();

        /*mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
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
        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    lat = String.valueOf(location.getLatitude());
                    lon = String.valueOf(location.getLongitude());
                    Intent sendmap = new Intent(MainActivity.this, Ubicacion.class);
                    sendmap.putExtra("Mail",correo);
                    sendmap.putExtra("Latitud",lat);
                    sendmap.putExtra("Longitud",lon);
                    startService(sendmap);
                }
                else
                {
                    lat = "no sirve";
                    lon = "no sirve";
                }
            }
        });*/

        /*Intent sendmap = new Intent(this, Ubicacion.class);
        sendmap.putExtra("Mail",correo);
        //sendmap.putExtra("Latitud",lat);
        //sendmap.putExtra("Longitud",lon);
        startService(sendmap);*/



        ImportFragment importFragment = new ImportFragment();
        bundle = new Bundle();
        bundle.putString("keybundle",correo);
        importFragment.setArguments(bundle);
        FragmentManager fragment = getSupportFragmentManager();
        fragment.beginTransaction().add(R.id.contenedor, importFragment).commit();






        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragment = getSupportFragmentManager();
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        ImportFragment importFragment = new ImportFragment();
                        bundle = new Bundle();
                        bundle.putString("keybundle",correo);
                        importFragment.setArguments(bundle);
                        fragment.beginTransaction().replace(R.id.contenedor, importFragment).commit();
                        //Toast.makeText(MainActivity.this, "Lat:"+lat+"  Lon"+lon, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.horario:
                        //Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();break;
                        HorarioFragment horarioFragment = new HorarioFragment();
                        bundle = new Bundle();
                        bundle.putString("keybundle",correo);
                        horarioFragment.setArguments(bundle);
                        fragment.beginTransaction().replace(R.id.contenedor, horarioFragment).commit();
                        break;
                    case R.id.boletaje:
                        BoletajeFragment boletajeFragment = new BoletajeFragment();
                        bundle = new Bundle();
                        bundle.putString("keybundle",correo);
                        boletajeFragment.setArguments(bundle);
                        fragment.beginTransaction().replace(R.id.contenedor, boletajeFragment).commit();
                        break;
                    case R.id.Alerta:
                        AlertaFragment alertaFragment = new AlertaFragment();
                        bundle = new Bundle();
                        bundle.putString("keybundle",correo);
                        bundle.putString("userr",user);
                        alertaFragment.setArguments(bundle);
                        fragment.beginTransaction().replace(R.id.contenedor, alertaFragment).commit();
                        break;
                    default:
                        return true;
                }

                return true;

            }
        });





    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }





}
