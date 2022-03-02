package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationBarView;

public class KalkulatorActivity extends AppCompatActivity {
    TextView  val_kanan, des_kiri_1, des_kiri_2, des_kanan_1, des_kanan_2
            ,val_kwh, val_batu_bara, val_gas, val_minyak, val_polusi;
    ImageView img_kiri, img_kanan;
    EditText val_awal,val_kiri;
    String v_awwal;
    Spinner sp_kiri, sp_kanan;
    ArrayAdapter<String> sp_ar_adapter_kiri, getSp_ar_adapter_kanan;
    String[] arr_sp, arr_des1, arr_des2, arr_gambar;
    double[] arr_kwh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        val_awal = findViewById(R.id.val_kiri);
        val_awal.setText("0");
        if(val_awal==null){
            v_awwal="0";
        }
        if(val_kiri==null){
            v_awwal="0";
        }
        sp_kiri = findViewById(R.id.kiri);
        sp_kanan = findViewById(R.id.kanan);
        val_kiri = findViewById(R.id.val_kiri);
        val_kanan = findViewById(R.id.val_kanan);
        des_kiri_1 = findViewById(R.id.des_kiri_1);
        des_kiri_2 = findViewById(R.id.des_kiri_2);
        des_kanan_1 = findViewById(R.id.des_kanan_1);
        des_kanan_2 = findViewById(R.id.des_kanan_2);
        val_kwh = findViewById(R.id.val_kwh);
        val_batu_bara = findViewById(R.id.val_batubara);
        val_gas = findViewById(R.id.val_gas);
        val_minyak = findViewById(R.id.val_minyak);
        val_polusi = findViewById(R.id.val_polusi);
        img_kiri = findViewById(R.id.img_kiri);
        img_kanan = findViewById(R.id.img_kanan);
        arr_sp = new String[]{"AC (1PK) ", "Kulkas 1 Pintu", "Kulkas 2 Pintu", "Setrika", "Rice cooker (memasak)", "Rice cooker (menghangatkan)",
        "Dispenser air panas", "Lampu pijar", "Lampu hemat energi", "Lampu LED", "Komputer desktop", "LED TV 32 inch",
        "LED TV 4K 50 inch", "Mobil", "Motor", "Mesin cuci", "Water heater elektrik", "Kompor gas (eq.)", "Kompor induksi",
        "Pompa air", "Vacum cleaner", "Hiar dryer"};
        arr_kwh = new double[]{1, 0.00163, 0.000278, 2.46, 0.099, 625, 584, 0.8462, 1.2358, 0.0875, 0.15, 0.54,
        3.84, 0.64, 0.16, 0.08, 0.64, 0.24, 0.58, 7.96, 1.95, 0.2, 2.61, 2.67,0.79, 0.58, 0.325, 0.15};
        arr_des1 = new String[]{"hari penggunaan","hari penggunaan","hari penggunaan","hari penggunaan","hari penggunaan",
                "hari penggunaan","hari penggunaan","hari penggunaan","hari penggunaan","hari penggunaan","hari penggunaan",
        "hari menonton TV menggunakan", "hari menonton TV menggunakan", "hari berkendara menggunakan","hari berkendara menggunakan",
        "hari mencuci menggunakan","hari penggunaan","hari memasak menggunakan","hari memasak menggunakan","hari penggunaan",
        "hari penggunaan","hari penggunaan"};
        arr_des2 = new String[]{"(selama 8 jam per hari)", "","","(selama 15 menit per hari)","(selama 30 menit per hari)",
        "(selama 12 jam per hari)","(non stop 24 jam)","60 watt, 800 lumen, selama 8 jam/hari","20 watt, 1000 lumen, selama 8 jam/hari",
        "10 watt, 1000 lumen, selama 8 jam/hari","8 jam per hari","(selama 4 jam per hari)","(selama 4 jam per hari)","(sejauh 20 km per hari)",
        "(sejauh 20 km per hari)","","","(selama 1 jam per hari)","(selama 1 jam per hari)","(untuk mengisi tandon air 1000 liter per hari)",
        "(selama 30 menit per hari)","(selama 10 menit per hari)"};
        arr_gambar = new String[]{"ac","kulkas_1","kulkas_2","setrika","rice_cooker_2","rice_cooker","dispen_air",
        "lampu_pijar","lampu_hemat","lampu_led","komputer","tv32","tv55","mobil","motor","mesin_cuci","water_heater",
        "kompor_gas","kompor_induksi","pompa_air","vacum","hair"};
        sp_ar_adapter_kiri = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        arr_sp);
        sp_ar_adapter_kiri.setDropDownViewResource(android.R.layout
                .simple_spinner_item);
        sp_kiri.setAdapter(sp_ar_adapter_kiri);
        getSp_ar_adapter_kanan = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        arr_sp);
        getSp_ar_adapter_kanan.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        sp_kanan.setAdapter(getSp_ar_adapter_kanan);
        val_awal.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                v_awwal = 0+String.valueOf(val_kiri.getText());
                if(val_awal==null || val_awal.equals("")){
                    v_awwal="0";
                }
                if(val_kiri==null || val_kiri.equals("")){
                    v_awwal="0";
                }
                Integer v_awal = Integer.valueOf(String.valueOf(v_awwal));
                double v_kanan = (v_awal * arr_kwh[sp_kiri.getSelectedItemPosition()])/arr_kwh[sp_kanan.getSelectedItemPosition()];
                des_kiri_1.setText(arr_des1[sp_kiri.getSelectedItemPosition()]);
                des_kiri_2.setText(arr_des2[sp_kiri.getSelectedItemPosition()]);
                val_kanan.setText(String.valueOf(v_kanan));
                val_kwh.setText(String.valueOf(arr_kwh[sp_kiri.getSelectedItemPosition()]));
                val_batu_bara.setText(String.valueOf( ((v_awal * arr_kwh[sp_kiri.getSelectedItemPosition()])/arr_kwh[3])));
                val_gas.setText(String.valueOf(((v_awal * arr_kwh[sp_kiri.getSelectedItemPosition()])/arr_kwh[4])));
                val_minyak.setText(String.valueOf(((v_awal * arr_kwh[sp_kiri.getSelectedItemPosition()])/arr_kwh[5])));
                val_polusi.setText(String.valueOf(((v_awal * 1.05)/12)));
                RequestOptions options = new RequestOptions();
                options.centerCrop();
                int res_kiri = getResources().getIdentifier (arr_gambar[sp_kiri.getSelectedItemPosition()], "drawable", "com.inc.vr.corp.app.mobilelearning_krisisenergi");
                Glide.with(img_kiri.getContext())
                        .load(res_kiri).apply(options)
                        .into(img_kiri);
                int res_kanan = getResources().getIdentifier (arr_gambar[sp_kanan.getSelectedItemPosition()], "drawable", "com.inc.vr.corp.app.mobilelearning_krisisenergi");
                img_kanan.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Glide.with(img_kanan.getContext())
                        .load(res_kanan).apply(options)
                        .into(img_kanan);
                img_kanan.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        });
        sp_kiri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int i, long id) {
                v_awwal = 0+String.valueOf(val_kiri.getText());
                if(val_awal==null || val_awal.equals("")){
                    v_awwal="0";
                }
                if(val_kiri==null || val_kiri.equals("")){
                    v_awwal="0";
                }
                Integer v_awal = Integer.valueOf(String.valueOf(v_awwal));
                double v_kanan = (v_awal * arr_kwh[i])/arr_kwh[sp_kanan.getSelectedItemPosition()];
                des_kiri_1.setText(arr_des1[i]);
                des_kiri_2.setText(arr_des2[i]);
                val_kanan.setText(String.valueOf(v_kanan));
                val_kwh.setText(String.valueOf(arr_kwh[i]));
                val_batu_bara.setText(String.valueOf( ((v_awal * arr_kwh[i])/arr_kwh[3])));
                val_gas.setText(String.valueOf(((v_awal * arr_kwh[i])/arr_kwh[4])));
                val_minyak.setText(String.valueOf(((v_awal * arr_kwh[i])/arr_kwh[5])));
                val_polusi.setText(String.valueOf(((v_awal * 1.05)/12)));
                RequestOptions options = new RequestOptions();
                options.centerCrop();
                int res_kiri = getResources().getIdentifier (arr_gambar[sp_kiri.getSelectedItemPosition()], "drawable", "com.inc.vr.corp.app.mobilelearning_krisisenergi");
                Glide.with(img_kiri.getContext())
                        .load(res_kiri).apply(options)
                        .into(img_kiri);
                int res_kanan = getResources().getIdentifier (arr_gambar[sp_kanan.getSelectedItemPosition()], "drawable", "com.inc.vr.corp.app.mobilelearning_krisisenergi");
                img_kanan.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Glide.with(img_kanan.getContext())
                        .load(res_kanan).apply(options)
                        .into(img_kanan);
                img_kanan.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        sp_kanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int i, long id) {
                v_awwal = 0+String.valueOf(val_kiri.getText());
                if(val_awal==null || val_awal.equals("")){
                    v_awwal="0";
                }
                if(val_kiri==null || val_kiri.equals("")){
                    v_awwal="0";
                }
                Integer v_awal = Integer.valueOf(String.valueOf(v_awwal));
                double v_kanan = (v_awal * arr_kwh[i])/arr_kwh[sp_kiri.getSelectedItemPosition()];
                des_kiri_1.setText(arr_des1[i]);
                des_kiri_2.setText(arr_des2[i]);
                val_kanan.setText(String.valueOf(v_kanan));
                val_kwh.setText(String.valueOf(arr_kwh[i]));
                val_batu_bara.setText(String.valueOf( ((v_awal * arr_kwh[i])/arr_kwh[3])));
                val_gas.setText(String.valueOf(((v_awal * arr_kwh[i])/arr_kwh[4])));
                val_minyak.setText(String.valueOf(((v_awal * arr_kwh[i])/arr_kwh[5])));
                val_polusi.setText(String.valueOf(((v_awal * 1.05)/12)));
                RequestOptions options = new RequestOptions();
                options.centerCrop();
                int res_kiri = getResources().getIdentifier (arr_gambar[sp_kiri.getSelectedItemPosition()], "drawable", "com.inc.vr.corp.app.mobilelearning_krisisenergi");
                Glide.with(img_kiri.getContext())
                        .load(res_kiri).apply(options)
                        .into(img_kiri);
                int res_kanan = getResources().getIdentifier (arr_gambar[sp_kanan.getSelectedItemPosition()], "drawable", "com.inc.vr.corp.app.mobilelearning_krisisenergi");
                img_kanan.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                Glide.with(img_kanan.getContext())
                        .load(res_kanan).apply(options)
                        .into(img_kanan);
                img_kanan.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}