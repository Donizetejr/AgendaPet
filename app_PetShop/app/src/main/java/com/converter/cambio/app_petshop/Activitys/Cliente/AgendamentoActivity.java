package com.converter.cambio.app_petshop.Activitys.Cliente;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.converter.cambio.app_petshop.R;

public class AgendamentoActivity extends AppCompatActivity {
    private MaterialButton btnSolicitar, btnLimpar;
    private EditText edNomePet, edRacaPet, edNomeUsuario, edTelefone;
    private TextView txtCusto;
    private TextView txtNomeEmpresa;
    private Spinner spnPortePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);
        inicializaCampos();
        configuraNavBar();
        eventosClick();
    }

    private void eventosClick() {
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edRacaPet.setText("");
                edNomeUsuario.setText("");
                edTelefone.setText("");
                edNomePet.setText("");
            }
        });

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendamentoActivity.this, PaginaPrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void configuraNavBar() {
        setTitle("Agendamento");
        ActionBar actionBar = getSupportActionBar(); //instancia objt da BAR
        actionBar.setDisplayHomeAsUpEnabled(true); //exibe o icone
        actionBar.setHomeButtonEnabled(true); //habilita click
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AgendamentoActivity.this, LocalizaPetSopActivity.class);
                startActivity(intent);
                finish();
                break;
            default:break;
        }
        return true;
    }

    private void inicializaCampos() {
        btnSolicitar = findViewById(R.id.age_btn_solicitar);
        btnLimpar = findViewById(R.id.age_btn_limpar);
        edNomePet = findViewById(R.id.age_nome_pet);
        spnPortePet = findViewById(R.id.age_porte_pet);
        edRacaPet = findViewById(R.id.age_raca_pet);
        edNomeUsuario = findViewById(R.id.age_txt_nome_usuario);
        edTelefone = findViewById(R.id.age_txt_telefone);
        txtCusto = findViewById(R.id.age_txt_custo);
        txtNomeEmpresa = findViewById(R.id.age_txt_nome_empresa);
    }
}
