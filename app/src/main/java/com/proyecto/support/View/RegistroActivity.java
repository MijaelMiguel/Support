package com.proyecto.support.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.support.Controller.UsuarioController;
import com.proyecto.support.R;

import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity {

    TextView RegUsuNombres, RegUsuApellidos, RegUsuCorreo, RegUsuPassword, RegUsuTelefono;
    Button RegUsuBtnReistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Declarando las casillas de Texto
        RegUsuNombres = findViewById(R.id.EtNombre);
        RegUsuApellidos = findViewById(R.id.EtApellidos);
        RegUsuCorreo = findViewById(R.id.EtCorreo);
        RegUsuPassword = findViewById(R.id.EtPassword);
        RegUsuTelefono = findViewById(R.id.EtTelefono);
        RegUsuBtnReistrar = findViewById(R.id.btnRegistrar);


        //Acciones de la ventana
        RegUsuBtnReistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HiloRegistro().execute(
                        RegUsuNombres.getText().toString(),
                        RegUsuApellidos.getText().toString(),
                        RegUsuCorreo.getText().toString(),
                        RegUsuPassword.getText().toString(),
                        RegUsuTelefono.getText().toString()
                );
            }
        });

    }

    //Enpieza el consumo de servicio

    private class HiloRegistro extends AsyncTask<String, String, JSONObject>{

        ProgressDialog dlgRegistro;

        @Override
        protected void onPreExecute() {
            //Tarjeta de Carga de Registro con servicios
            dlgRegistro = new ProgressDialog(RegistroActivity.this);
            dlgRegistro.setTitle("SUPPORT");
            dlgRegistro.setMessage("Registrando Usuario...");
            dlgRegistro.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dlgRegistro.setCancelable(true);
            dlgRegistro.show();
        }

        @Override
        protected void onPostExecute(JSONObject sRegistro) {
            //super.onPostExecute(jsonObject);
            if (dlgRegistro.isShowing()){dlgRegistro.dismiss();}

            try {

                if(sRegistro.getBoolean("status")==true) {
                    Toast.makeText(getApplicationContext(),sRegistro.getString("message"),Toast.LENGTH_LONG).show();
                    Intent registro = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(registro);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        @Override
        protected JSONObject doInBackground(String... datosRegistro) {
            UsuarioController ctlRegistro = new UsuarioController();
            JSONObject envRegistro = null;

            try {
                JSONObject envRegArray = new JSONObject();
                envRegArray.accumulate("usu_nombres", datosRegistro[0]);
                envRegArray.accumulate("usu_apellidos", datosRegistro[1]);
                envRegArray.accumulate("usu_correo", datosRegistro[2]);
                envRegArray.accumulate("usu_password", datosRegistro[3]);
                envRegArray.accumulate("usu_telefono", datosRegistro[4]);


                envRegistro = ctlRegistro.consumo("http://192.168.137.1:8080/ServicesSupport/Usuario",
                        "POST",
                        envRegArray);

            }catch (Exception ex){
                ex.printStackTrace();

            }

            return envRegistro;
        }
    }
}
