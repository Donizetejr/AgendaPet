package com.converter.cambio.app_petshop.Activitys.Cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.converter.cambio.app_petshop.Activitys.ResetSenhaActivity;
import com.converter.cambio.app_petshop.Controller.FireBaseConexao;
import com.converter.cambio.app_petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginClienteActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha;
    private TextView txtEsqueceuSenha;
    MaterialButton btnCadastrar, btnLogin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaComponentes();
        eventosClick();
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth = FireBaseConexao.getFirebaseAuth();
    }

    private void eventosClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = txtEmail.getText().toString().trim();
                String strSenha = txtSenha.getText().toString().trim();

                if (validaInputUsuario(strEmail, strSenha)) return;

                loginFirebase(strEmail, strSenha);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginClienteActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });

        txtEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginClienteActivity.this, ResetSenhaActivity.class);
                startActivity(i);
            }
        });
    }

    private boolean validaInputUsuario(String strEmail, String strSenha) {
        if(strEmail.trim().equals("") || strSenha.equals("")){
            if (strEmail.equals("") && !strSenha.equals("")){
                alertDialog("ATENÇÃO!","O E-mail deve ser preenchido!");
                return true;
            }
            else if(strSenha.equals("") && !strEmail.equals("")){
                alertDialog("ATENÇÃO!","A Senha deve ser preenchida!");
                return true;
            }
                alertDialog("ATENÇÃO!","Todos os campos devem ser preenchidos!");
            return true;
        }
        return false;
    }

    private void loginFirebase(String strEmail, String strSenha) {
        auth.signInWithEmailAndPassword(strEmail, strSenha)
                .addOnCompleteListener(LoginClienteActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(LoginClienteActivity.this, PaginaPrincipalActivity.class);
                            startActivity(i);
                        }else{
                            altertToast("E-mail ou senha inválidos!");
                        }
                    }
                });
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

    private void altertToast(String strMsgm){
        Toast.makeText(LoginClienteActivity.this, strMsgm, Toast.LENGTH_LONG).show();
    }

    private void inicializaComponentes() {
        txtEmail = findViewById(R.id.login_txt_email);
        txtSenha = findViewById(R.id.login_txt_senha);

        btnCadastrar = findViewById(R.id.login_btn_cadastrar);
        btnLogin = findViewById(R.id.login_btn_login);
        txtEsqueceuSenha = findViewById(R.id.txt_esqueceu_senha);

    }
}