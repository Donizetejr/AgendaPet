package com.converter.cambio.app_petshop.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.converter.cambio.app_petshop.R;

public class MapServicosActivity extends AppCompatActivity {

    private Spinner spnServicos;
    private Button btnAgendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_servicos);

        btnAgendar = findViewById(R.id.map_btn_agendar);

        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapServicosActivity.this, AgendamentoActivity.class);
                startActivity(intent);
            }
        });
    }

}
