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
import com.converter.cambio.app_petshop.Controller.ValidaCampos;
import com.converter.cambio.app_petshop.Model.ClienteModel;
import com.converter.cambio.app_petshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CadastroClienteActivity extends AppCompatActivity {
    private MaterialButton btnCadastrar;
    private EditText edtEmail, edtNome, edtSenha, edtCpf, edtTelefone, edtEndereco, estPetId;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        inicializaComponentes();
        configuraNavBar();
        inicializarFirebase();
        eventoClicks();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(CadastroClienteActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void cadastrarCliente(){
        ClienteModel cliente = validaInputUsuarioConvertToClienteModel();

        databaseReference.child("cliente").child(String.valueOf(cliente.getCli_id())).setValue(cliente);
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

                ClienteModel clienteModel = validaInputUsuarioConvertToClienteModel();

                //Alterar mensagem e fazer validação
                if(clienteModel.getCli_cpf().equals("")){
                    alertDialog("ATENCÃO", "Falha ao cadastrar usuário.");
                }

                String strEmail = edtEmail.getText().toString().trim();
                String strSenha = edtSenha.getText().toString().trim();

                if(!strEmail.trim().equals("") && !strSenha.trim().equals("")) {
                    cadastrarUsuario(strEmail, strSenha);
                }else{
                    alertDialog("ATENCÃO", "Os todos os campos devem ser preenchidos.");
                }
                Intent intent = new Intent(CadastroClienteActivity.this, LoginClienteActivity.class);
            }
        });
    }

    private ClienteModel validaInputUsuarioConvertToClienteModel() {

        ClienteModel clienteModel = new ClienteModel();
        ValidaCampos v = new ValidaCampos();

        String strMensagemNome = v.vString(edtNome.toString());
        String strMensagemEndereco = v.vStringEndereco(edtNome.toString());
        String strMensagemTelefone = v.vStringTelefone(edtNome.toString());
        String strMensagemCpf = v.vStringCpf(edtNome.toString());
        String strMensagemSenha = v.vStringSenha(edtNome.toString());
        String strMensagemEmail = v.vStringEmail(edtNome.toString());

        if(!strMensagemNome.equals("ok")){
            alertDialog("ATENÇÃO!", strMensagemNome);
            return null;
        }
        if(!strMensagemCpf.equals("ok")){
            alertDialog("ATENÇÃO!", strMensagemCpf);
            return null;
        }
        if(!strMensagemEndereco.equals("ok")){
            alertDialog("ATENÇÃO!", strMensagemEndereco);
            return null;
        }
        if(!strMensagemTelefone.equals("ok")){
            alertDialog("ATENÇÃO!", strMensagemTelefone);
            return null;
        }
        if(!strMensagemSenha.equals("ok")){
            alertDialog("ATENÇÃO!",strMensagemSenha);
            return null;
        }
        if(!strMensagemEmail.equals("ok")){
            alertDialog("ATENÇÃO!",strMensagemEmail);
            return null;
        }

        clienteModel.setCli_id(Integer.valueOf(UUID.randomUUID().toString()));
        clienteModel.setCli_nome(edtNome.getText().toString().trim());
        clienteModel.setCli_telefone(edtTelefone.getText().toString().trim());
        clienteModel.setCli_endereco(edtEndereco.getText().toString().trim());
        clienteModel.setCli_cpf(edtCpf.getText().toString().trim());
        clienteModel.setCli_email(edtEmail.getText().toString().trim());
        clienteModel.setCli_senha(edtSenha.getText().toString().trim());

        return clienteModel;
    }

    private void cadastrarUsuario(String strEmail, String strSenha){
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
            }
        );
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
        edtCpf = findViewById(R.id.cad_usu_ed_cpf);
        edtEndereco = findViewById(R.id.cad_usu_ed_endereco);
        edtTelefone = findViewById(R.id.cad_usu_ed_telefone);
    }
}
