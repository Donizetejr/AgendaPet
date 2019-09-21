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
import com.converter.cambio.app_petshop.Controller.FireBaseQuery;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CadastroClienteActivity extends AppCompatActivity {
    private MaterialButton btnCadastrar;
    private EditText edtEmail, edtNome, edtSenha, edtCpf, edtTelefone, edtEndereco;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FireBaseQuery fireBaseQuery;

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

    @Override
    protected void onStart() {
        super.onStart();
        auth = FireBaseConexao.getFirebaseAuth();
    }

    private void eventoClicks() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClienteModel clienteModel = validaCampos();

                if(clienteModel.getCli_id() == null){
                    alertDialog("ATENCÃO", "Preencha todos os campos.");
                    return;
                }
                else{
                    //Fazer busca no banco para ver se usuario ja é cadastrado
                }
                    cadastrarUsuario(clienteModel);
            }
        });
    }

    private ClienteModel validaCampos() {

        ClienteModel c = new ClienteModel();
        ValidaCampos v = new ValidaCampos();

        String strMensagemNome = v.vString(edtNome.getText().toString());
        String strMensagemEndereco = v.vStringEndereco(edtEndereco.getText().toString());
        String strMensagemTelefone = v.vStringTelefone(edtTelefone.getText().toString());
        String strMensagemCpf = v.vStringCpf(edtCpf.getText().toString());
        String strMensagemSenha = v.vStringSenha(edtSenha.getText().toString());
        String strMensagemEmail = v.vStringEmail(edtEmail.getText().toString());

        int contMsg = 0;

        if(!strMensagemNome.equals("ok")){
            edtNome.setError(strMensagemNome);
            contMsg += 1;
        }
        if(!strMensagemCpf.equals("ok")){
            edtCpf.setError(strMensagemNome);
            contMsg += 1;
        }
        if(!strMensagemEndereco.equals("ok")){
            edtEndereco.setError(strMensagemNome);
            contMsg += 1;
        }
        if(!strMensagemTelefone.equals("ok")){
            edtTelefone.setError(strMensagemNome);
            contMsg += 1;
        }
        if(!strMensagemSenha.equals("ok")){
            edtSenha.setError(strMensagemNome);
            contMsg += 1;
        }
        if(!strMensagemEmail.equals("ok")){
            edtEmail.setError(strMensagemNome);
            contMsg += 1;
        }

        if(contMsg > 0){
            return new ClienteModel();
        }

        c.setCli_id(UUID.randomUUID().toString());
        c.setCli_nome(edtNome.getText().toString().trim());
        c.setCli_telefone(edtTelefone.getText().toString().trim());
        c.setCli_endereco(edtEndereco.getText().toString().trim());
        c.setCli_cpf(edtCpf.getText().toString().trim());
        c.setCli_email(edtEmail.getText().toString().trim());
        c.setCli_senha(edtSenha.getText().toString().trim());

        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        String dataFormatada = formataData.format(data);

        c.setCli_data_ultima_alteracao_senha(dataFormatada);

        return c;
    }

    private void cadastrarUsuario(ClienteModel clienteModel){
        fireBaseQuery.InsertObjectDb(clienteModel, "Cliente", clienteModel.getCli_id(), databaseReference);

        if(databaseReference.getDatabase() != null){
            cadastrarLoginUsuario(clienteModel);
        }
    }

    private void cadastrarLoginUsuario(final ClienteModel clienteModel){
        auth.createUserWithEmailAndPassword(clienteModel.getCli_email(), clienteModel.getCli_senha())
            .addOnCompleteListener(CadastroClienteActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                // task retorna o status da autenticação
                if(task.isSuccessful()){
                    limparCampos();
                    alertToast("Usuário cadastrado com sucesso!");
                }else{
                    alertToast("Erro ao cadastrar. Tente novamente.");
                }
                }
            }
        );
    }

    private void limparCampos() {
        edtEmail.setText("");
        edtNome.setText("");
        edtSenha.setText("");
        edtCpf.setText("");
        edtTelefone.setText("");
        edtEndereco.setText("");
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
        fireBaseQuery  = new FireBaseQuery();
    }
}
