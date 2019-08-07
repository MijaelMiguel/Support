package com.proyecto.support.View;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import com.proyecto.support.R;

public class MainActivity extends AppCompatActivity {
    ArrayList<HeladoModel> lst=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // lista de datos

        // ArrayList<>
        // HeladoModel miobj = new H+eladoModel();
        lst.add(new HeladoModel("LADRON",R.drawable.ladron));
        lst.add(new HeladoModel("INCENDIO",  R.drawable.incendio));
        lst.add(new HeladoModel("PARO CARDIACO", R.drawable.corazon));
        lst.add(new HeladoModel("ACCIDENTE DE TRANSITO", R.drawable.choque));
        lst.add(new HeladoModel("TERREMOTO", R.drawable.terremoto));
        lst.add(new HeladoModel("EXTRAVIADO", R.drawable.perdido));
        lst.add(new HeladoModel("PARO CARDIACO", R.drawable.corazon));



        //10 crear adapter
        SupportAdapter ad= new SupportAdapter(lst);
        //setear el recyclerview
        RecyclerView rv =findViewById(R.id.lista);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager lym= new LinearLayoutManager(this);
        rv.setLayoutManager(lym);

        rv.setAdapter(ad);

        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Click CardView",Toast.LENGTH_LONG).show();
                Intent windowInfo = new Intent(MainActivity.this,InfoActivity.class);
                startActivity(windowInfo);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
