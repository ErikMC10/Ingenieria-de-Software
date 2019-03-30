package com.example.trolebus;

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

public class MainActivity extends AppCompatActivity {
    TextView tv_registrar;
    EditText TextEmail, TextPassword;
    Button btnLogin;
    private ProgressDialog progressDialog;
    //Declaramos un objeto firebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicializamos el objeto firebaseAuth
        mAuth = FirebaseAuth.getInstance();
        btnLogin=(Button)findViewById(R.id.botonLogin);
        TextEmail = (EditText) findViewById(R.id.TxtEmail);
        TextPassword = (EditText) findViewById(R.id.TxtPassword);
        progressDialog = new ProgressDialog(this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                Toast.makeText(MainActivity.this,"Bienvenido "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                Intent intentReg=new Intent(MainActivity.this,Registro.class);
                MainActivity.this.startActivity(intentReg);
            }else{
                Toast.makeText(MainActivity.this,"No Esta Registrado:  "+ TextEmail.getText(),Toast.LENGTH_LONG).show();

            }
            progressDialog.dismiss();
        }
    });


}
}
