package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class BoletajeFragment extends Fragment {
    private String correo;
    TextView Fecha;
    EditText boletos;
    Button Send;
    String user,fecha,tickets;
    private DatabaseReference mDatabase;// ...


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_boletaje, container, false);


        if(getArguments() != null)
            correo= getArguments().getString("keybundle");
        else
            correo = "no entiendoooo";

        Fecha = (TextView) view.findViewById(R.id.Fecha);
        Send = (Button) view.findViewById(R.id.Send);
        boletos = (EditText) view.findViewById(R.id.Boletos);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        fecha = dateFormat.format(date);

        Fecha.setText("Fecha: " + fecha);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tickets = boletos.getText().toString();

                if (tickets.compareTo("") == 0)
                    Toast.makeText(getContext(), "boletaje vacio", Toast.LENGTH_LONG).show();

                else {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.child("Correo").getValue().toString().compareTo(correo) == 0) {
                                    user = snapshot.getKey();
                                    break;
                                }

                            }

                            mDatabase.child("Usuarios").child(user).child("Boletaje").child(fecha).setValue(tickets);
                            Toast.makeText(getContext(), "Boletaje subido", Toast.LENGTH_LONG).show();
                            boletos.setText("");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

        });
        return view;



    }


}
