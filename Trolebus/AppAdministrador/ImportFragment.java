package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ImportFragment extends Fragment {

   private String correo;
    TextView mail,nombre, nss, matricula, numero;
    private DatabaseReference mDatabase;// ...

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        /*if(getArguments() != null)
            correo= getArguments().getString("keybundle");*/


        //mail = (TextView) getView().findViewById(R.id.emaill);

        //mail.setText("hola");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_import, container, false);


        if(getArguments() != null)
            correo= getArguments().getString("keybundle");
        else
            correo = "no entiendoooo";

        mail = (TextView) view.findViewById(R.id.emaill);
        nombre = (TextView) view.findViewById(R.id.Nombre);
        nss = (TextView)view.findViewById(R.id.Nss);
        matricula = (TextView)view.findViewById(R.id.Matricula);
        numero = (TextView)view.findViewById(R.id.Numero);

        //mail.setText(correo);
        //Toast.makeText(getContext(), "el correo es:  "+correo, Toast.LENGTH_LONG).show();

        MostrarDatos(correo);
        return view;



    }


    public void MostrarDatos(final String correo)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String NSS="",Nombre="",Unidad="", Correo="", Matricula="", Numero="";

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.child("Correo").getValue().toString().compareTo(correo)==0)
                    {
                        NSS += snapshot.child("NSS").getValue().toString() + "";
                        Nombre += snapshot.child("Nombre").getValue().toString()+"";
                        Correo += snapshot.child("Correo").getValue().toString();
                        Matricula += snapshot.child("Trolebus").child("Matricula").getValue().toString();
                        Numero += snapshot.child("Trolebus").child("Numero").getValue().toString();
                        break;
                    }

                }
                //correos.setText(correo);
                //mail.setText("NSS: " + NSS + "\nNombre: " + Nombre + "Unidad: " + Unidad+ "Correo: "+Correo);
                nombre.setText("Nombre: "+ Nombre);
                nss.setText("Seguro Social: " + NSS);
                mail.setText("Correo: "+correo);
                matricula.setText("Matricula: "+Matricula);
                numero.setText("Unidad: "+Numero);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
