package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.baoyachi.stepview.VerticalStepView;

public class PetunjukActivity extends AppCompatActivity {

    VerticalStepView verticalStepView;
    CardView home;
    TextView petunjuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        petunjuk = findViewById(R.id.petunjuk);
        petunjuk.setText(Html.fromHtml(String.format(this.getString(R.string.petunjuk))));
        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetunjukActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}