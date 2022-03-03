package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.inc.vr.corp.app.mobilelearning_krisisenergi.adapter.MateriAdapter;
import com.inc.vr.corp.app.mobilelearning_krisisenergi.model.MateriModel;

import java.util.ArrayList;
import java.util.List;

public class MateriListActivity extends AppCompatActivity {
    private List<MateriModel> materiDataList;
    private ArrayList<String> islamijudulList;
    private ArrayList<String> islamigambarList;
    private ArrayList<String> islamipenerbitList;
    private ArrayList<String> islamiwaktuList;
    private ArrayList<String> islamiurlList;
    private ArrayList<String> islamikategoriList;
    private ArrayList<String> islamiDesList;
    private ArrayList<Integer> islamifavList;
    RecyclerView rc_cari;
    MateriAdapter mDataAdapter;
    CardView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        rc_cari = findViewById(R.id.rc_matlis);
        home = findViewById(R.id.btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MateriListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        islamijudulList= new ArrayList<>();
        islamigambarList= new ArrayList<String>();
        islamipenerbitList = new ArrayList<>();
        islamiwaktuList = new ArrayList<>();
        islamiurlList = new ArrayList<>();
        islamikategoriList = new ArrayList<>();
        islamiDesList = new ArrayList<>();
        islamifavList = new ArrayList<Integer>();
        materiDataList= new ArrayList<MateriModel>();
        mDataAdapter = new MateriAdapter( MateriListActivity.this, islamijudulList,
                islamikategoriList,
                islamigambarList, islamiurlList,islamipenerbitList
                ,islamiwaktuList,islamiDesList, islamifavList);
        islamijudulList.add("Pengertian Energi");
        islamijudulList.add("Bentuk Energi");
        islamijudulList.add("Energi Kinetik, Potensial dan Mekanik");
        islamijudulList.add("Perubahan Bentuk Energi");
        islamijudulList.add("Efisiensi Energi ");
        islamijudulList.add("Apa Itu Sumber Energi");
        islamijudulList.add("Sumber Energi Konvensional dan Dampak yang Ditimbulkannya");
        islamijudulList.add("Krisis Energi");
        islamijudulList.add("Upaya Penanggulangan Krisis Energi");
        islamijudulList.add("Sumber Energi Terbarukan");
        islamijudulList.add("Referensi");
        islamigambarList.add("pengertian01");
        islamigambarList.add("bentuk02");
        islamigambarList.add("energikpm03");
        islamigambarList.add("perubahan04");
        islamigambarList.add("efisiensi05");
        islamigambarList.add("sumber06");
        islamigambarList.add("krisis07");
        islamigambarList.add("dampak08");
        islamigambarList.add("upaya09");
        islamigambarList.add("sumber10");
        islamigambarList.add("daftar");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rc_cari.setLayoutManager(mLayoutManager);
        rc_cari.setAdapter(mDataAdapter);
    }
}