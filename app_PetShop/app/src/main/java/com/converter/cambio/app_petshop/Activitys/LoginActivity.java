package com.converter.cambio.app_petshop.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.converter.cambio.app_petshop.FireBaseConexao;
import com.converter.cambio.app_petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail, txtSenha;
    private TextView txtEsqueceuSenha;
    Button btnCadastrar, btnLogin;

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

    private String validaLogin(String email, String senha, boolean emailValido, boolean senhaValida) {

        String strMensagem = "";

        if(emailValido == true && senhaValida == true)
        {
            Intent intent = new Intent(LoginActivity.this, MenuLateralActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            if(senha.toString().trim().length() == 0 || email.toString().trim().length() == 0){
                strMensagem = "Todos os campos devem ser preenchidos";
                return strMensagem;
            }
            else if(senha.toString().trim().length() < 8 && senha.toString().trim().length() > 0) {
                strMensagem ="Senha - mínimo de 8 caracteres";
                return strMensagem;
            }
            if(!email.toString().contains("@") || !email.toString().contains("."))
            {
                strMensagem = "Digite um e-mail válido.";
                return strMensagem;
            }
            else{
                strMensagem ="Email ou Senha incorretos.";
                return strMensagem;
            }
        }
        return strMensagem;
    }

    private boolean validaEmail(String email) {
        if(email.toString().contains("@") && email.toString().trim() != "" && email.toString().contains("."))
        {
            return true;
        }

        return false;
    }

    private boolean validaSenha(String senha) {
        if(senha.toString().trim() != "" && senha.toString().trim().length() >= 8)
        {
            return true;
        }
        return false;
    }

    private void eventosClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = txtEmail.getText().toString().trim();
                String strSenha = txtSenha.getText().toString().trim();

                loginFirebase(strEmail, strSenha);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        txtEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ResetSenhaActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginFirebase(String strEmail, String strSenha) {
        auth.signInWithEmailAndPassword(strEmail, strSenha)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(LoginActivity.this, PerfilActivity.class);
                            startActivity(i);
                        }else{
                            altertToast("E-mail ou senha inválidos!");
                        }
                    }
                });
    }

    private void altertToast(String strMsgm){
        Toast.makeText(LoginActivity.this, strMsgm, Toast.LENGTH_LONG).show();
    }
    private void inicializaComponentes() {
        txtEmail = findViewById(R.id.login_txt_email);
        txtSenha = findViewById(R.id.login_txt_senha);
<<<<<<< Updated upstream
        btnCadastrar = (Button) findViewById(R.id.login_btn_cadastrar);
        btnLogin = (Button) findViewById(R.id.login_btn_login);
        txtEsqueceuSenha = findViewById(R.id.txt_esqueceu_senha);
=======
        btnCadastrar = findViewById(R.id.login_btn_cadastrar);
        btnLogin = findViewById(R.id.login_btn_login);
>>>>>>> Stashed changes
    }
}
