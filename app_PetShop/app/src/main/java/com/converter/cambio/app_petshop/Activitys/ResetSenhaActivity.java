package com.converter.cambio.app_petshop.Activitys;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.converter.cambio.app_petshop.Controller.FireBaseConexao;
import com.converter.cambio.app_petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenhaActivity extends AppCompatActivity {

    private EditText edtEmail;
    private MaterialButton btnResetSenha;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);

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
                        String strEmailDigitado = edtEmail.getText().toString().trim();
                        if(task.isSuccessful()){
                            alertDialog("ENVIADO!","Foi enviado um link de redefinição de senha para "+
                                    strEmailDigitado);
                        }else{
                            alertDialog("ATENÇÃO!","O e-mail "+strEmailDigitado+" não está cadastrado no aplicativo.");
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

    private void  alertDialog(String strTitle, String strMsg){
        new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                .setTitle(strTitle)
                .setMessage(strMsg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    } }).show();
    }

    private void inicalizaComponentes() {
        edtEmail = findViewById(R.id.reset_txt_email);
        btnResetSenha = findViewById(R.id.reset_btn_enviar_email);
    }
}
