package com.proyecto.support.View;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.proyecto.support.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.jar.JarOutputStream;

public class InfoActivity extends AppCompatActivity {

    TextView txtAlerta, txtNombre, txtApellido, txtNumero;
    Button btnAlertaMensaje;

    String datosUsuario;

    LoginActivity dataUser = new LoginActivity();


    private PendingIntent pendingIntent;
    private  final static String CHANNEL_ID ="NOTIFICACION";
    private  final static int NOTIFICACION_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);



        btnAlertaMensaje = findViewById(R.id.btnAlertaMensaje);
        txtAlerta = findViewById(R.id.txtNomAlerta);
        txtNombre = findViewById(R.id.txtNomUsu);
        txtApellido = findViewById(R.id.txtApellUsu);
        txtNumero = findViewById(R.id.txtNumUsu);

        //----------------IMPORTAR LOS DATOS--------------------//

        String dataListUsu =getIntent().getStringExtra("DataMain");
        String nombreData = null;
        String apellidoData = null;
        String numeroData = null;
        String[] datafinal = null;
        JSONArray data;
        try {
            data = new JSONArray(dataListUsu);
             JSONObject  dataJson =data.getJSONObject(0);
            nombreData = dataJson.getString("usu_nombres");
            apellidoData = dataJson.getString("usu_apellidos");
            numeroData = dataJson.getString("usu_telefono");

            datafinal = new String[dataJson.length()];



        } catch (JSONException e) {
            e.printStackTrace();
        }
        String reciAlerta = getIntent().getStringExtra("Titulo");
        txtAlerta.setText(reciAlerta);
        txtNombre.setText(nombreData);
        txtApellido.setText(apellidoData);
        txtNumero.setText(numeroData);

        //------------------------------------------------------//


        btnAlertaMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent abrirMapa = new Intent(InfoActivity.this,MapsActivity.class);
                startActivity(abrirMapa);
            }
        });


            /*
                createNotification();

            private void createNotification() {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),     CHANNEL_ID);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("SUPPORT");
                builder.setContentText("Tu notificación fue enviado con exito, se esta trabajando en su solución ");
                builder.setColor(Color.RED);
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setLights(Color.MAGENTA, 1000,1000);
                builder.setVibrate(new long []{1000,1000,1000,1000});
                builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.notify(NOTIFICACION_ID,builder.build());
            }
             */

    }

}
