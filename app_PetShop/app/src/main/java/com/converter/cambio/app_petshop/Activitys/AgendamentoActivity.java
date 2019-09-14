package com.converter.cambio.app_petshop.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_agendamento, container, false);

        inicializaCampos();
        eventosClick();

        return view;
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
