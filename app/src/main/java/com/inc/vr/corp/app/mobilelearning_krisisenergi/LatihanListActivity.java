package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LatihanListActivity extends AppCompatActivity {

    CardView pg, essay, home, afektif, behavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        pg = findViewById(R.id.btn_pg);
        afektif = findViewById(R.id.btn_afektif);
        behavior = findViewById(R.id.btn_behavior);
        home = findViewById(R.id.btn_home);
        pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LatihanListActivity.this, TesKognitifActivity.class);
                intent.putExtra("jenis","pg");
                startActivity(intent);
            }
        });
        afektif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LatihanListActivity.this, TesActivity.class);
                intent.putExtra("jenis","afektif");
                startActivity(intent);
            }
        });
        behavior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LatihanListActivity.this, TesActivity.class);
                intent.putExtra("jenis","behavior");
                startActivity(intent);
            }
        });
        /*tes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LatihanListActivity.this, EssayActivity.class);
                startActivity(intent);
            }
        });*/
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LatihanListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}