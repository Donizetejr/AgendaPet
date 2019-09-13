package com.converter.cambio.app_petshop.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.converter.cambio.app_petshop.FireBaseConexao;
import com.converter.cambio.app_petshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilActivity extends AppCompatActivity {
    private EditText edtNome, edtEmail;
    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        inicializaComponentes();
        eventosClick();

    }

    private void eventosClick() {

    }

    private void inicializaComponentes() {
        edtEmail = (EditText) findViewById(R.id.per_usu_txt_email);
        edtNome = (EditText) findViewById(R.id.per_usu_txt_nome);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = FireBaseConexao.getFirebaseAuth();
        user = FireBaseConexao.getFirebaseUser();
        verificaUser();
    }

    private void verificaUser() {
        if(user == null){
            finish();
        }else{
            edtNome.setText("Digite um nome");
            edtEmail.setText(user.getEmail());
        }

    }
}
