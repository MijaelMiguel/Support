package com.proyecto.support.View;

import android.Manifest;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.support.Controller.UsuarioController;
import com.proyecto.support.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Array;
import java.util.List;
import java.util.Locale;
import java.util.jar.JarOutputStream;

public class InfoActivity extends AppCompatActivity {

    TextView txtAlerta, txtNombre, txtApellido, txtNumero;
    Button btnAlertaMensaje;

    public String strmsg;

    TextView mensaje1;
    TextView mensaje2;
    double latitud, longitud;

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


        //----------------------localizacion---------------------------//

        mensaje1 = (TextView) findViewById(R.id.mensaje_id);
        mensaje2 = (TextView) findViewById(R.id.mensaje_id2);

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
                abrirMapa.putExtra("longitud", longitud);
                abrirMapa.putExtra("latitud", latitud);

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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

    }


    //---------------------------------ENVIAR EL MENSAJE A LA BASE DE DATOS-------------------------------------//

    private class HiloRegistro extends AsyncTask<String, String, JSONObject>{

        ProgressDialog dlgRegistro;

        @Override
        protected void onPreExecute() {
            //Tarjeta de Carga de Registro con servicios
            dlgRegistro = new ProgressDialog(InfoActivity.this);
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

    //----------------------------------------------------------------------------------------------------------//


    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setInfoActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
        mensaje1.setText("Localización agregada");
        mensaje2.setText("");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    mensaje2.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        InfoActivity infoActivity;
        public InfoActivity getInfoActivity() {
            return infoActivity;
        }
        public void setInfoActivity(InfoActivity infoActivity) {
            this.infoActivity = infoActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            longitud = loc.getLongitude();
            latitud = loc.getLatitude();
            loc.getLatitude();
            loc.getLongitude();
            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            mensaje1.setText(Text);
            this.infoActivity.setLocation(loc);
        }
        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            mensaje1.setText("GPS Desactivado");
        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            mensaje1.setText("GPS Activado");
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }

}
