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

import com.proyecto.support.R;

public class InfoActivity extends AppCompatActivity {
    Button btNoti;
    private PendingIntent pendingIntent;
    private  final static String CHANNEL_ID ="NOTIFICACION";
    private  final static int NOTIFICACION_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btNoti = findViewById(R.id.btnMapa);
        btNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirMapa = new Intent(InfoActivity.this,MapsActivity.class);
                startActivity(abrirMapa);
            }
        });



        /*btNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification();

            }
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
        });*/

    }

}
