package com.proyecto.support.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.support.Controller.UsuarioController;
import com.proyecto.support.R;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public String strmsg;

    Button btnLogin, btnRegistro;
    EditText txtUser, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Asociar Object XML -> Java
        btnLogin = findViewById(R.id.LogBtnLogin);
        btnRegistro = findViewById(R.id.LogBtnRegistro);
        txtUser = findViewById(R.id.LogEdtUserName);
        txtPass = findViewById(R.id.LogEdtUserPass);
        //Evento Click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GUI -> AsyncTask
                new Hilo().execute(txtUser.getText().toString(),
                        txtPass.getText().toString()
                );
            }
        });

        //EL REGISTRO EMPIESA AQUI
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regView = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(regView);

            }
        });
    }

    private class Hilo
            extends AsyncTask<String,String,JSONObject> {

        ProgressDialog dlg;


        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            dlg = new ProgressDialog(LoginActivity.this);
            dlg.setTitle("SUPPORT");
            dlg.setMessage("Validando Usuario...");
            dlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dlg.setCancelable(true);
            dlg.show();
        }
        @Override
        protected JSONObject doInBackground(String... p) {
            JSONObject res = new JSONObject();
            /*
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
            try {
                JSONObject env = new JSONObject();
                env.accumulate("usu_correo", p[0]);//
                env.accumulate("usu_password", p[1]);//


                UsuarioController ctl = new UsuarioController();
                res = ctl.consumo("http://192.168.137.1:8080/ServicesSupport/Login",
                        "POST",
                        env);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return res;
        }
        @Override
        protected void onPostExecute(JSONObject s) {
            //super.onPostExecute(s);
            //boolean bstatus = false;
            if(dlg.isShowing()){dlg.dismiss();}

            try {

                boolean bstatus = s.getBoolean("status");
                 strmsg = s.getString("data");
                Toast.makeText(getApplicationContext(),
                        strmsg, Toast.LENGTH_LONG).show();

                if(bstatus == true){ //si Login OK
                    Intent x = new Intent(getApplicationContext(),MainActivity.class);
                    x.putExtra("Data",strmsg);
                    startActivity(x);
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }
}
