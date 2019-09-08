package com.converter.cambio.app_petshop.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.converter.cambio.app_petshop.R;

public class AgendamentoActivity extends AppCompatActivity {
    private Button btnSolicitar;
    private Button btnLimpar;
    private EditText edNomePet;
    private EditText edRacaPet;
    private EditText edNomeUsuario;
    private EditText edTelefone;
    private TextView txtCusto;
    private TextView txtNomeEmpresa;
    private Spinner spnPortePet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);
        inicializaCampos();

        btnSolicitaAgendamentoClick();
        btnLimparCamposClick();
    }

    private void btnLimparCamposClick() {
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edRacaPet.setText("");
                edNomeUsuario.setText("");
                edTelefone.setText("");
                edNomePet.setText("");
            }
        });
    }

    private void btnSolicitaAgendamentoClick() {
        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendamentoActivity.this, MenuLateralActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
