package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActiivity extends AppCompatActivity {

    CardView btn_simpan;
    TextView nama, jk, sekolah, kelas, email, nohp;
    SharedPreferences myshared;
    SharedPreferences.Editor editormyshared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actiivity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        nama = findViewById(R.id.nama);
        jk = findViewById(R.id.jk);
        sekolah = findViewById(R.id.sekolah);
        kelas = findViewById(R.id.kelas);
        email = findViewById(R.id.email);
        nohp = findViewById(R.id.hp);
        btn_simpan = findViewById(R.id.simpan);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().length()>0 & jk.getText().length()>0 & sekolah.getText().length()>0 &
                kelas.getText().length()>0 & email.getText().length()>0 & nohp.getText().length()>0){
                    editormyshared = getSharedPreferences("Krisis-Energi", MODE_PRIVATE).edit();
                    editormyshared.putString("nama", nama.getText().toString());
                    editormyshared.putString("jk", jk.getText().toString());
                    editormyshared.putString("sekolah", sekolah.getText().toString());
                    editormyshared.putString("kelas", kelas.getText().toString());
                    editormyshared.putString("email", email.getText().toString());
                    editormyshared.putString("nohp", nohp.getText().toString());
                    editormyshared.putString("status","login");
                    editormyshared.apply();
                    Intent intent = new Intent(LoginActiivity.this, MainActivity.class);
                    intent.putExtra("jenis","pg");
                    startActivity(intent);
                }else{
                    Toast.makeText(view.getContext(), "Masih ada yang belum diisi",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}