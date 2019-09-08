package com.converter.cambio.app_petshop.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.converter.cambio.app_petshop.R;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private Button btnCadastrar;
    private EditText edEmail;
    private EditText edNome;
    private EditText edSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        inicializaCampos();

        btnCadastrarClick();
    }

    private void btnCadastrarClick() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(CadastroUsuarioActivity.this, "Cadastrado com Sucesso!", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
            }
        });
    }

    private void inicializaCampos() {
        btnCadastrar = findViewById(R.id.cad_btn_cadastrar);
        edEmail = findViewById(R.id.cad_usu_ed_email);
        edNome = findViewById(R.id.cad_usu_ed_nome);
        edSenha = findViewById(R.id.cad_usu_ed_senha);
    }
}
