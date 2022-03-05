package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    CardView petunjuk, kompetensi, peta, materi, latihan, tentang,exit,kalkulator;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        String msg = token;
                        Log.d("TAG", msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        //KUMPULAN CARD
        petunjuk = findViewById(R.id.petunjuk);
        kompetensi = findViewById(R.id.kompetensi);
        peta= findViewById(R.id.peta_konsep);
        materi = findViewById(R.id.gel_bunyi);
        kalkulator = findViewById(R.id.kalkulator);
        latihan = findViewById(R.id.latihan_soal);
        tentang = findViewById(R.id.tentang);
        exit = findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(startMain);
            }
        });
        // CARD KLIK
        petunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("petunjuk");
            }
        });
        kompetensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("kompetensi");
            }
        });
        peta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("peta");
            }
        });
        materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("materi");
            }
        });
        kalkulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("kalkulator");
            }
        });
        latihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("latihan");
            }
        });
        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klik("tentang");
            }
        });
    }

    public void klik(String kondisi){
        Class activity = PetunjukActivity.class;
        switch (kondisi){
            case "petunjuk":
                activity = PetunjukActivity.class;
                break;
            case "kompetensi":
                activity = KompetensiActivity.class;
                break;
            case "peta":
                activity = PetaKonsepActivity.class;
                break;
            case "materi":
                activity = MateriListActivity.class;
                break;
            case "kalkulator":
                activity = KalkulatorActivity.class;
                break;
            case "latihan":
                activity = LatihanListActivity.class;
                break;
            case "tentang":
                activity = TentangActivity.class;
                break;
        }
        Intent intent = new Intent(getApplicationContext(),activity);
        startActivity(intent);
    }
    private static long back_pressed;

    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        else Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
}