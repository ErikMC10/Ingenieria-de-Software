package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText TextEmail, TextPassword;
    Button btnLogin;
    private ProgressDialog progressDialog;
    //Declaramos un objeto firebaseAuth
    private FirebaseAuth mAuth;
    private String user;

    private DatabaseReference mDatabase;// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //inicializamos el objeto firebaseAuth


        Papeleta papeleta = new Papeleta();


        mAuth = FirebaseAuth.getInstance();
        btnLogin=(Button)findViewById(R.id.botonLogin);
        TextEmail = (EditText) findViewById(R.id.TxtEmail);
        TextPassword = (EditText) findViewById(R.id.TxtPassword);
        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextEmail.getText().toString().compareTo("")==0)
                    Toast.makeText(Login.this, "Correo no ingresado", Toast.LENGTH_SHORT).show();
                else if(TextEmail.getText().toString().compareTo("")==0)
                    Toast.makeText(Login.this, "Contraseña no ingresada", Toast.LENGTH_SHORT).show();
                else
                    verificarUsuario();



            }
        });


    }

    private void verificarUsuario(){
//Obtenemos el email y la contraseña desde las cajas de texto
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();
        progressDialog.setMessage("Realizando validacion");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            //Toast.makeText(Login.this,"Bienvenido "+ TextEmail.getText(),Toast.LENGTH_LONG).show();

                            Intent intentReg=new Intent(Login.this,Main2.class);
                            intentReg.putExtra("correoo",TextEmail.getText().toString().trim());
                            Login.this.startActivity(intentReg);
                        }else{
                            Toast.makeText(Login.this,"No Esta Registrado:  "+ TextEmail.getText(),Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();
                    }
                });


    }


}
