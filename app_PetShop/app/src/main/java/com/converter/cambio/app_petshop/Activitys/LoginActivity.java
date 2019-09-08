package com.converter.cambio.app_petshop.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.converter.cambio.app_petshop.R;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtSenha;
    Button btnCadastrar;
    Button btnLogin;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializaCampos();
        validaInputUsuario();

        redirecionaParaCadastro();
    }

    private void validaInputUsuario() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable email = txtEmail.getText();
                Editable senha = txtSenha.getText();

                boolean emailValido = validaEmail(email);
                boolean senhaValida = validaSenha(senha);
                String strMensagem = validaLogin(email, senha, emailValido, senhaValida);
                validaLogin(email, senha, emailValido, senhaValida);

                Toast.makeText(LoginActivity.this, strMensagem, Toast.LENGTH_LONG).show();
            }
        });
    }

    private String validaLogin(Editable email, Editable senha, boolean emailValido, boolean senhaValida) {

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

    private boolean validaEmail(Editable email) {
        if(email.toString().contains("@") && email.toString().trim() != "" && email.toString().contains("."))
        {
            return true;
        }

        return false;
    }

    private boolean validaSenha(Editable senha) {
        if(senha.toString().trim() != "" && senha.toString().trim().length() >= 8)
        {
            return true;
        }
        return false;
    }

    private void redirecionaParaCadastro() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicializaCampos() {
        txtEmail = findViewById(R.id.login_txt_email);
        txtSenha = findViewById(R.id.login_txt_senha);
        btnCadastrar = findViewById(R.id.login_btn_cadastrar);
        btnLogin = findViewById(R.id.login_btn_login);
        btnCadastrar = findViewById(R.id.login_btn_cadastrar);
    }
}
