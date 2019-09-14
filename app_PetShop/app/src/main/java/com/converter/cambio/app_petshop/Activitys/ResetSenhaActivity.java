package com.converter.cambio.app_petshop.Activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.converter.cambio.app_petshop.FireBaseConexao;
import com.converter.cambio.app_petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

class ResetSenhaActivity extends AppCompatActivity {
    private EditText edtEmail;
    private Button btnResetSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicalizaComponentes();
        eventosClick();
    }

    private void eventosClick() {
        btnResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = edtEmail.getText().toString().trim();
                resetSenha(strEmail);
            }
        });
    }

    private void resetSenha(String strEmail) {
        auth.sendPasswordResetEmail(strEmail)
            .addOnCompleteListener(ResetSenhaActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        alert("Um e-mail foi enviado para alterar sua senha.");
                    }else{
                        alert("E-mail não encontrado.");
                    }
                }
            });
    }

    private void alert(String s) {
        Toast toast = Toast.makeText(ResetSenhaActivity.this, s, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = FireBaseConexao.getFirebaseAuth();
    }

    private void inicalizaComponentes() {
        edtEmail = (EditText) findViewById(R.id.reset_txt_email);
        btnResetSenha = (Button) findViewById(R.id.reset_btn_enviar_email);
    }
}
