package com.converter.cambio.app_petshop.Activitys.Cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.converter.cambio.app_petshop.Controller.FireBaseConexao;
import com.converter.cambio.app_petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroClienteActivity extends AppCompatActivity {
    private MaterialButton btnCadastrar;
    private EditText edtEmail, edtNome, edtSenha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        inicializaComponentes();
        configuraNavBar();
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

                if(!strEmail.trim().equals("") && !strSenha.trim().equals("")) {
                    criarUser(strEmail, strSenha);
                }else{
                    alertDialog("ATENCÃO", "Os todos os campos devem ser preenchidos.");
                }
                Intent intent = new Intent(CadastroClienteActivity.this, LoginClienteActivity.class);
            }
        });
    }

    private void criarUser(String strEmail, String strSenha){
        auth.createUserWithEmailAndPassword(strEmail, strSenha)
                .addOnCompleteListener(CadastroClienteActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // task retorna o status da autenticação
                        if(task.isSuccessful()){
                            alertToast("Usuário cadastrado com sucesso!");

                            Intent intent = new Intent(CadastroClienteActivity.this, PerfilActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            alertToast("Erro ao cadastrar. Tente novamente.");
                        }
                    }
                });
    }

    private void  alertToast(String strMsg){
        Toast toast = Toast.makeText(CadastroClienteActivity.this, strMsg, Toast.LENGTH_LONG);
        toast.show();
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

    private void configuraNavBar() {
        setTitle("Cadastro de Usuário");
        ActionBar actionBar = getSupportActionBar(); //instancia objt da BAR
        actionBar.setDisplayHomeAsUpEnabled(true); //exibe o icone
        actionBar.setHomeButtonEnabled(true); //habilita click
    }

    //Para inserir a ação e selecionar para qual página voltar...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CadastroClienteActivity.this, LoginClienteActivity.class);
                startActivity(intent);
                finish();
                break;
            default:break;
        }
        return true;
    }

    private void inicializaComponentes() {
        btnCadastrar = findViewById(R.id.cad_btn_cadastrar);
        edtEmail = findViewById(R.id.cad_usu_ed_email);
        edtNome = findViewById(R.id.cad_usu_ed_nome);
        edtSenha = findViewById(R.id.cad_usu_ed_senha);
    }
}
