package com.proyecto.support.View;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;


import com.proyecto.support.R;

public class PresentationActivity extends AppCompatActivity {

    ImageView presImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_presentation);

        presImage = findViewById(R.id.imagePresentation);

        presImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginView = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginView);
            }
        });
    }
}
