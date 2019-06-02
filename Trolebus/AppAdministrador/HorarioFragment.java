package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HorarioFragment extends Fragment {
    private String correo;
    TextView horario;
    ListView lista;
    ArrayList<String> Horario = new ArrayList<>();
    String [] Dias = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
    private DatabaseReference mDatabase;// ...


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_horario, container, false);


        if(getArguments() != null)
            correo= getArguments().getString("keybundle");
        else
            correo = "no entiendoooo";

        horario = (TextView)view.findViewById(R.id.Horario);

        //mail.setText(correo);
        //Toast.makeText(getContext(), "el correo es:  "+correo, Toast.LENGTH_LONG).show();

        lista = (ListView) view.findViewById(R.id.lista);

        MostrarDatos(correo);
        return view;



    }

    public void MostrarDatos(final String correo)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.child("Correo").getValue().toString().compareTo(correo)==0)
                    {
                        //Horario = snapshot.child("Horario").getValue().toString();
                        for (DataSnapshot horario : snapshot.child("Horario").getChildren()) {
                            Horario.add(Dias[i]+horario.getValue().toString());
                            i++;
                        }

                    }

                }
                i=0;
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,Horario);
                lista.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
