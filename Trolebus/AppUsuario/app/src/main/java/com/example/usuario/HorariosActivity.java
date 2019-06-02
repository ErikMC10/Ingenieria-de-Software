package com.example.usuario;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class HorariosActivity extends FragmentActivity {
    private DatabaseReference mDatabase;
    //textView2
    TextView hor;
    ListView lista;
    ArrayList<String> Horario = new ArrayList<>();
    String [] Dias = {"Unidad","Lunes","Martes","Miercoles","Jueves","Viernes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        hor= (TextView) findViewById(R.id.textView2);
        lista=findViewById(R.id.listhor);
        mDatabase =  FirebaseDatabase.getInstance().getReference();
      //  final String[] Horario = new String[1];

        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=1;
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    i=1;
                    Horario.add(Dias[0] + snapshot.child("Trolebus").child("Numero").getValue().toString() );
                    for (DataSnapshot horario : snapshot.child("Horario").getChildren()) {
                        Horario.add(Dias[i]+horario.getValue().toString());
                        i++;
                    }

                }
                i=1;
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(HorariosActivity.this, android.R.layout.simple_list_item_1,Horario);
                lista.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }

}
