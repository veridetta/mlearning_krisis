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
        islamijudulList.add("Pengertian Energi");//a1
        islamijudulList.add("Bentuk Energi");//b2
        islamijudulList.add("Energi Kinetik, Potensial dan Mekanik");//c3
        islamijudulList.add("Perubahan Bentuk Energi");//d4
        islamijudulList.add("Efisiensi Energi ");//e5
        islamijudulList.add("Apa Itu Sumber Energi");//f6
        islamijudulList.add("Energi Batu Bara");//g7
        islamijudulList.add("Energi Minyak Bumi");//h8
        islamijudulList.add("Energi Gas Alam");//i9
        islamijudulList.add("Energi Nuklir");//j10
        islamijudulList.add("Dampak Krisis Energi");//k11
        islamijudulList.add("Krisis Energi");//l12
        islamijudulList.add("Upaya Penanggulangan Krisis Energi");//m13
        islamijudulList.add("Sumber Energi Terbarukan");//n14
        islamijudulList.add("Referensi");//o
        islamigambarList.add("m_01_pengertian");
        islamigambarList.add("m_02_bentuk");
        islamigambarList.add("m_03_kpm");
        islamigambarList.add("m_04_perubahan");
        islamigambarList.add("m_05_efisiensi");
        islamigambarList.add("m_06_apaitu");
        islamigambarList.add("m_07_batubara");
        islamigambarList.add("m_08_minyak");
        islamigambarList.add("m_09_gas");
        islamigambarList.add("m_10_nuklir");
        islamigambarList.add("m_11_dampak");
        islamigambarList.add("m_12_krisis");
        islamigambarList.add("m_13_upaya");
        islamigambarList.add("m_14_terbarukan");
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
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        islamiurlList.add("com.inc.vr.corp.app.mobilelearning_krisisenergi.materi.MateriContent");
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        rc_cari.setLayoutManager(mLayoutManager);
        rc_cari.setAdapter(mDataAdapter);
    }
}