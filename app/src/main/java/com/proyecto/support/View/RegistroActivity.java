package com.proyecto.support.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.proyecto.support.R;

public class RegistroActivity extends AppCompatActivity {

    TextView RegUsuNombres, RegUsuApellidos, RegUsuCorreo, RegUsuPassword, RegUsuTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        RegUsuNombres = findViewById(R.id.EtNombre);
        RegUsuApellidos = findViewById(R.id.EtApellidos);
        RegUsuCorreo = findViewById(R.id.EtCorreo);
        RegUsuPassword = findViewById(R.id.EtPassword);
        //RegUsuNombres = findViewById(R.id.EtNombre);
    }
}
