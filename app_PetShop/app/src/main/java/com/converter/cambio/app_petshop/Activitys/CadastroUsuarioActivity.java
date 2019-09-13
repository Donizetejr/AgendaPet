package com.converter.cambio.app_petshop.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.converter.cambio.app_petshop.FireBaseConexao;
import com.converter.cambio.app_petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private Button btnCadastrar;
    private EditText edtEmail, edtNome, edtSenha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        inicializaComponentes();

        eventoClicks();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = FireBaseConexao.getFirebaseAuth();
    }

    private void eventoClicks() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strEmail = edtEmail.getText().toString().trim();
                String strSenha = edtSenha.getText().toString().trim();

                criarUser(strEmail, strSenha);

                Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
            }
        });
    }

    private void criarUser(String strEmail, String strSenha){
        auth.createUserWithEmailAndPassword(strEmail, strSenha)
                .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // task retorna o status da autenticação
                        if(task.isSuccessful()){
                            alertToast("Usuário cadastrado com sucesso!");

                            Intent intent = new Intent(CadastroUsuarioActivity.this, PerfilActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            alertToast("Erro ao cadastrar. Tente novamente.");
                        }
                    }
                });
    }

    private void  alertToast(String strMsg){
        Toast toast = Toast.makeText(CadastroUsuarioActivity.this, strMsg, Toast.LENGTH_LONG);
        toast.show();
    }

    private void inicializaComponentes() {
        btnCadastrar = findViewById(R.id.cad_btn_cadastrar);
        edtEmail = findViewById(R.id.cad_usu_ed_email);
        edtNome = findViewById(R.id.cad_usu_ed_nome);
        edtSenha = findViewById(R.id.cad_usu_ed_senha);
    }
}
