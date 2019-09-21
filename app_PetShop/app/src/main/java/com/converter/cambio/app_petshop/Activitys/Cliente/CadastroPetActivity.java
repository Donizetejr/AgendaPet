package com.converter.cambio.app_petshop.Activitys.Cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.converter.cambio.app_petshop.Controller.FireBaseConexao;
import com.converter.cambio.app_petshop.Controller.FireBaseQuery;
import com.converter.cambio.app_petshop.Controller.ValidaCampos;
import com.converter.cambio.app_petshop.Model.PetModel;
import com.converter.cambio.app_petshop.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CadastroPetActivity extends AppCompatActivity {

    private MaterialButton btnCadastrar;
    private EditText edtNome, edtRaca, edtIdade;
    private Spinner spnSexo, spnPorte;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FireBaseQuery fireBaseQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);
        inicializarCampos();
        inicializarFirebase();
        btnCadastrarClick();

        configuraNavBar();
    }

    private void btnCadastrarClick() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetModel pet = validaCampos();

                if(pet.getPet_id() == null){
                    alertDialog("ATENÇÃO", "Preencha todos os campos");
                    return;
                }

                cadastrarPet(pet);

                alertDialog("Pet Adicionado!", "Pet cadastrado com Sucesso!");
                limparCampos();
            }
        });
    }

    private void limparCampos() {
        edtRaca.setText("");
        edtIdade.setText("");
        edtNome.setText("");
        spnPorte.setSelection(0);
        spnSexo.setSelection(0);
    }

    private void cadastrarPet(PetModel pet) {
        new FireBaseQuery().InsertObjectDb( pet, "Pet", pet.getPet_id(), databaseReference);
    }

    private PetModel validaCampos() {
        PetModel pet = new PetModel();

        ValidaCampos v = new ValidaCampos();

        String strMensagemNome = v.vString(edtNome.getText().toString().trim());
        String strMensagemIdade = v.vInt(edtIdade.getText().toString().trim());
        String strMensagemRaca = v.vString(edtRaca.getText().toString().trim());
        String strMensagemSexo = v.vStringSpn(spnSexo.getSelectedItem().toString().trim());
        String strMensagemPorte = v.vStringSpn(spnPorte.getSelectedItem().toString().trim());
        int contMsg = 0;


        if(!strMensagemNome.equals("ok")){
            edtNome.setError(strMensagemNome);
            contMsg += 1;
        }
        if(!strMensagemIdade.equals("ok")){
            edtIdade.setError(strMensagemIdade);
            contMsg += 1;
        }
        if(!strMensagemRaca.equals("ok")){
            edtRaca.setError(strMensagemRaca);
            contMsg += 1;
        }
        if(!strMensagemSexo.equals("ok")){
            alertDialog("ATENÇÃO!", strMensagemSexo);
            contMsg += 1;
        }
        if(!strMensagemPorte.equals("ok")){
            alertDialog("ATENÇÃO", strMensagemPorte);
            contMsg += 1;
        }

        if(contMsg > 0){
            return new PetModel();
        }

        pet.setPet_id(String.valueOf(UUID.randomUUID()));
        pet.setPet_nome(edtNome.getText().toString().trim());
        pet.setPet_idade(edtIdade.getText().toString().trim());
        pet.setPet_raca(edtRaca.getText().toString().trim());
        pet.setPet_porte(spnPorte.getSelectedItem().toString().trim());
        pet.setPet_sexo(spnSexo.getSelectedItem().toString().trim());

        return pet;
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(CadastroPetActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void inicializarCampos() {
        btnCadastrar = findViewById(R.id.cad_pet_btn_cadastrar);
        edtNome = findViewById(R.id.cad_pet_edt_nome);
        edtIdade = findViewById(R.id.cad_pet_edt_idade);
        edtRaca = findViewById(R.id.cad_pet_edt_raca);
        spnPorte = findViewById(R.id.cad_pet_spn_porte);
        spnSexo = findViewById(R.id.cad_pet_spn_sexo);

        List<String> lstSexo = new ArrayList<>();

        lstSexo.add("Selecione o sexo do pet");
        lstSexo.add("Macho");
        lstSexo.add("Fêmea");

        ArrayAdapter sexo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lstSexo);
        spnSexo.setAdapter(sexo);

        List<String> lstPorte = new ArrayList<>();

        lstPorte.add("Selecione o porte do pet");
        lstPorte.add("Filhote");
        lstPorte.add("Pequeno");
        lstPorte.add("Médio");
        lstPorte.add("Grande");
        lstPorte.add("Muito Grande");

        ArrayAdapter porte = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, lstPorte);
        spnPorte.setAdapter(porte);
    }

    private void configuraNavBar() {
        setTitle("Cadastrar Pet");
        ActionBar actionBar = getSupportActionBar(); //instancia objt da BAR
        actionBar.setDisplayHomeAsUpEnabled(true); //exibe o icone
        actionBar.setHomeButtonEnabled(true); //habilita click
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CadastroPetActivity.this, PaginaPrincipalActivity.class);
                startActivity(intent);
                finish();
                break;
            default:break;
        }
        return true;
    }

    private void  alertDialog(String strTitle, String strMsg){
        new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
            .setTitle(strTitle)
            .setMessage(strMsg)
            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }
}
