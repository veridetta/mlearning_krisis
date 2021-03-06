package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import android.content.Intent;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PetaKonsepActivity extends AppCompatActivity {
    CardView home;
    ImageView petaKonsep;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta_konsep);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetaKonsepActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}