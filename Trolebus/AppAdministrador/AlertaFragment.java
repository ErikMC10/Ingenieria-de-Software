package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.method.BaseKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class AlertaFragment extends Fragment {

    Button btn;
    String correo,user,aux;
    int i=1 ;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_alerta, container, false);


        if(getArguments() != null) {
            correo = getArguments().getString("keybundle");
            user = getArguments().getString("userr");
        }
        else
            correo = "no entiendoooo";


        btn = (Button)view.findViewById(R.id.Send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MandarAlerta(correo, user);
            }
        });

        return view;



    }

    public void MandarAlerta(final String correo, final String user)
    {

        mDatabase = FirebaseDatabase.getInstance().getReference();




        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        mDatabase.child("Usuarios").child(user).child("Alerta").child(fecha).child("Fecha").setValue(fecha);
        mDatabase.child("Usuarios").child(user).child("Alerta").child(fecha).child("ID").setValue(i);
        mDatabase.child("Usuarios").child(user).child("Alerta").child(fecha).child("Ubicacion").child("Latitud").setValue(BackgroundService.Latitud);
        mDatabase.child("Usuarios").child(user).child("Alerta").child(fecha).child("Ubicacion").child("Longitud").setValue(BackgroundService.Longitud);
        Toast.makeText(getContext(), "Alerta enviada", Toast.LENGTH_LONG).show();

        try{
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            callIntent.setData(Uri.parse("tel:" + "5525762964"));
            getActivity().startActivity(callIntent);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }





}
