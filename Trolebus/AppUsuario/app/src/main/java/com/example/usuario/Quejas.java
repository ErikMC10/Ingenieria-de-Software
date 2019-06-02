package com.example.usuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
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

public class Quejas extends FragmentActivity implements View.OnClickListener {
    private DatabaseReference mDatabase;
    TextView titulo;
    EditText numunidad;
    EditText quejasuge;
    Button enviar;
    String user;
    int ID=123;
    String numerounidad,quejaysugerencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quejas);
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        titulo= (TextView) findViewById(R.id.textView3);
        numunidad=(EditText) findViewById(R.id.numuni);
        quejasuge=(EditText)findViewById(R.id.quejatxt);

        enviar=findViewById(R.id.btnenviar);
        enviar.setOnClickListener(this);








    }



    @Override//55
    public void onClick(View v) {
        numerounidad=numunidad.getText().toString();
        quejaysugerencia=quejasuge.getText().toString();
        switch (v.getId()){
            case R.id.btnenviar:
                mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int w=1,ID=123,i=0;
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            w++;
                            for(DataSnapshot Numero:snapshot.child("Trolebus").getChildren()) {
                                if (Numero.getValue().toString().compareTo(numerounidad) == 0) {
                                    String user = snapshot.getKey();
                                    ID++;
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                    Date date = new Date();
                                    String fecha = dateFormat.format(date);
                                    mDatabase.child("Usuarios").child(user).child("Queja").child(fecha).child("Fecha").setValue(fecha);
                                    mDatabase.child("Usuarios").child(user).child("Queja").child(fecha).child("Numero").setValue(ID);
                                    mDatabase.child("Usuarios").child(user).child("Queja").child(fecha).child("Comentario").setValue(quejaysugerencia);
                                    Toast.makeText(Quejas.this, "Comentario enviado", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }break;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                break;
        }
    }//89
}
