package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.sufficientlysecure.htmltextview.HtmlTextView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TesKognitifActivity extends AppCompatActivity {
    LinearLayout ly_soal;
    CardView selesai;
    Integer nomorsoal=1, nomorlain=0, jumlah_soal_pg, jumlah_soal_essay, jumlah_soal;
    //String[] jawab,kunci;
    ArrayList<String> jawab,kunci;
    ArrayList<EditText> editTextList;
    Intent intent;
    SharedPreferences myshared;
    TextView nama_tes;
    String jenis, filenya, nama, jk, sekolah, kelas, hp, email, nama_tesnya;
    boolean connected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_kognitif);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        intent = getIntent();
        jenis = intent.getStringExtra("jenis");
        myshared = getSharedPreferences("Krisis-Energi", MODE_PRIVATE);
        nama = myshared.getString("nama","default nama");
        jk = myshared.getString("jk","default jk");
        sekolah = myshared.getString("sekolah","default sekolah");
        kelas = myshared.getString("kelas","default kelas");
        hp = myshared.getString("nohp","default nohp");
        email = myshared.getString("email","default email");
        nama_tes = findViewById(R.id.nama_tes);
        if (jenis.equals("pg")){
            filenya = "tes.xml";
            jumlah_soal_pg=13;
            jumlah_soal_essay=7;
            jumlah_soal = 20;
            nama_tesnya = "Tes Kemampuan Kognitif";
            nama_tes.setText("Tes Kemampuan Kognitif");
        }
        if (jenis.equals("afektif")){
            filenya = "afektif.xml";
            jumlah_soal_pg=13;
            jumlah_soal_essay=0;
            jumlah_soal = 13;
            nama_tesnya = "Tes Kemampuan Afektif";
            nama_tes.setText("Tes Kemampuan Afektif");
        }
        if (jenis.equals("behavior")){
            filenya = "behavior.xml";
            jumlah_soal_pg=8;
            jumlah_soal_essay=0;
            jumlah_soal = 8;
            nama_tesnya = "Tes Kemampuan Behavior";
            nama_tes.setText("Tes Kemampuan Behavior");
        }
        editTextList = new ArrayList<EditText>();
        ly_soal = findViewById(R.id.soal_ly);
        selesai = findViewById(R.id.selesai);
        jawab = new ArrayList<String>();
        kunci =new ArrayList<String>();
        for(int x=0;x<jumlah_soal;x++){
            jawab.add(null);
            kunci.add(null);
        }
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nozz=0;
                for(int px=jumlah_soal_pg;px<jumlah_soal;px++){
                    Log.d("nomor", "onClick: "+px);
                    Log.d("jumlah", "onClick: " +editTextList.get(nozz).getText().toString());
                    jawab.set(px, editTextList.get(nozz).getText().toString());
                    nozz++;
                }
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Konfirmasi Selesai")
                        .setMessage("Pastikan internet telah terhubung")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                    //we are connected to a network
                                    connected = true;
                                } else{
                                    connected = false;
                                }
                                if(connected){
                                    Toast.makeText(TesKognitifActivity.this, "Terhubung", Toast.LENGTH_SHORT).show();
                                    Integer benar = 0, nomor=1;
                                    String isi = "";
                                    String ess="";
                                    for(int o=0;o<jumlah_soal_pg;o++){
                                        if(jawab.get(o) !=null){
                                            if(jawab.get(o).equals(kunci.get(o))){
                                                benar++;
                                            }
                                            isi = isi+("\n"+nomor+". "+jawab.get(o).toUpperCase());
                                        }else{
                                            isi = isi+("\n"+nomor+". tidak dijawab");
                                        }
                                        nomor++;
                                    };
                                    for(int y=jumlah_soal_pg;y<jumlah_soal;y++){
                                        if(jawab.get(y) !=null){
                                            ess = ess+("\n"+nomor+". "+jawab.get(y));
                                        }else{
                                            ess = ess+("\n"+nomor+". tidak dijawab");
                                        }
                                        nomor++;
                                    };
                                    Log.d("JAWABAN", "onClick: "+jawab.toString());
                                    String skor = String.valueOf((benar/3)*10);
                                    isi = isi+ ess+" \nkode "+md5(String.valueOf(skor));
                                    sendEmail("Jawaban "+ nama_tesnya,"Nama Lengkap : "+nama+"\n Jenis Kelamin : "+jk+" \n" +
                                            "Sekolah :"+sekolah+" \n Kelas : "+kelas+"\n No HP : "+hp+"\n Email :"+email+"\n"+"------------- \n"+
                                            "Jawaban \n -------------"+isi);

                                }else{
                                    Toast.makeText(TesKognitifActivity.this, "Tidak ada koneksi internet, jawaban kamu belum terkirim", Toast.LENGTH_SHORT).show();
                                }
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        try {
            InputStream is = getAssets().open(filenya);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element = doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("soal");
            NodeList eList = doc.getElementsByTagName("essay");
            //Log.d("TOTAL", "onCreate: "+nList.getLength());
            for (int xz=0;xz<nList.getLength();xz++){
                NodeList isix = nList.item(xz).getChildNodes();
                Log.d("total soal kecil", "onCreate: "+isix.getLength());
                for(int a=0;a<isix.getLength();a++) {
                    Node xjenis = isix.item(a);
                    if (xjenis.getNodeName().equals("kunci")) {
                        kunci.set(xz, isix.item(a).getTextContent());
                    }
                    if(xjenis.getNodeName().equals("isi")){
                        TextView textView = new TextView(this);
                        textView.setText("Soal Nomor "+nomorsoal);
                        textView.setId(nomorsoal);
                        LinearLayout.LayoutParams ly = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        ly.setMargins(10,3,2,3);
                        textView.setMinLines(1);
                        textView.setLayoutParams(ly);
                        //tv.setTextSize(16);
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                getResources().getDimension(R.dimen._14sdp));
                        textView.setPadding(4, 3, 0, 3);
                        textView.setIncludeFontPadding (false);
                        ly_soal.addView(textView);
                        getRinci(ly_soal,xjenis.getChildNodes());
                        Log.d("Nomor Soal", "onCreate: "+nomorsoal);
                    }
                }
                LayoutInflater inflater = (LayoutInflater)      this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View childLayout = inflater.inflate(R.layout.pg_d, ly_soal,false);
                ly_soal.addView(childLayout);
                final CardView xa, b, c,d;
                final LinearLayout  a_isi, b_isi, c_isi, d_isi;
                a_isi = childLayout.findViewById(R.id.opsi_a_isi);
                b_isi = childLayout.findViewById(R.id.opsi_b_isi);
                c_isi = childLayout.findViewById(R.id.opsi_c_isi);
                d_isi = childLayout.findViewById(R.id.opsi_d_isi);
                //e_isi = childLayout.findViewById(R.id.opsi_e_isi);
                xa = childLayout.findViewById(R.id.opsi_a);
                xa.setTag(nomorlain);
                b = childLayout.findViewById(R.id.opsi_b);
                b.setTag(nomorlain);
                c = childLayout.findViewById(R.id.opsi_c);
                c.setTag(nomorlain);
                d = childLayout.findViewById(R.id.opsi_d);
                d.setTag(nomorlain);
               // e = childLayout.findViewById(R.id.opsi_e);
                //e.setTag(nomorlain);

                list("a",isix, "opsi",this,childLayout,a_isi,xa);
                list("b",isix, "opsi",this,childLayout,b_isi,b);
                list("c",isix, "opsi",this,childLayout,c_isi,c);
                list("d",isix, "opsi",this,childLayout,d_isi,d);
               // list("e",isix, "opsi",this,childLayout,e_isi,e);
                xa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Log.d("APAKA", "onCreate: "+xa.getTag());
                        jawab.set(Integer.parseInt(v.getTag().toString()), "a");
                        xa.setCardBackgroundColor(getResources().getColor(R.color.white));
                        b.setCardBackgroundColor(getResources().getColor(R.color.white));
                        c.setCardBackgroundColor(getResources().getColor(R.color.white));
                        d.setCardBackgroundColor(getResources().getColor(R.color.white));
                       // e.setCardBackgroundColor(getResources().getColor(R.color.white));
                        xa.setCardBackgroundColor(getResources().getColor(R.color.yellow_400));
                    }
                });
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jawab.set(Integer.parseInt(v.getTag().toString()), "b");
                        xa.setCardBackgroundColor(getResources().getColor(R.color.white));
                        b.setCardBackgroundColor(getResources().getColor(R.color.white));
                        c.setCardBackgroundColor(getResources().getColor(R.color.white));
                        d.setCardBackgroundColor(getResources().getColor(R.color.white));
                        //e.setCardBackgroundColor(getResources().getColor(R.color.white));
                        b.setCardBackgroundColor(getResources().getColor(R.color.yellow_400));

                    }
                });

                c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jawab.set(Integer.parseInt(v.getTag().toString()), "c");
                        xa.setCardBackgroundColor(getResources().getColor(R.color.white));
                        b.setCardBackgroundColor(getResources().getColor(R.color.white));
                        c.setCardBackgroundColor(getResources().getColor(R.color.white));
                        d.setCardBackgroundColor(getResources().getColor(R.color.white));
                        // e.setCardBackgroundColor(getResources().getColor(R.color.white));
                        c.setCardBackgroundColor(getResources().getColor(R.color.yellow_400));
                    }
                });
                d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jawab.set(Integer.parseInt(v.getTag().toString()), "d");
                        xa.setCardBackgroundColor(getResources().getColor(R.color.white));
                        b.setCardBackgroundColor(getResources().getColor(R.color.white));
                        c.setCardBackgroundColor(getResources().getColor(R.color.white));
                        d.setCardBackgroundColor(getResources().getColor(R.color.white));
                       // e.setCardBackgroundColor(getResources().getColor(R.color.white));
                        d.setCardBackgroundColor(getResources().getColor(R.color.yellow_400));
                    }
                });
               /* e.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jawab.set(Integer.parseInt(v.getTag().toString()), "e");
                        xa.setCardBackgroundColor(getResources().getColor(R.color.white));
                        b.setCardBackgroundColor(getResources().getColor(R.color.white));
                        c.setCardBackgroundColor(getResources().getColor(R.color.white));
                        d.setCardBackgroundColor(getResources().getColor(R.color.white));
                        e.setCardBackgroundColor(getResources().getColor(R.color.white));
                        e.setCardBackgroundColor(getResources().getColor(R.color.yellow_400));
                    }
                }); */
                nomorlain++;
                nomorsoal++;
            }
            for (int yz=0;yz<eList.getLength();yz++){
                NodeList isix = eList.item(yz).getChildNodes();
                Log.d("total soal kecil", "onCreate: "+isix.getLength());
                for(int a=0;a<isix.getLength();a++) {
                    Node xjenis = isix.item(a);
                    if(xjenis.getNodeName().equals("isi")){
                        TextView textView = new TextView(this);
                        textView.setText("Soal Nomor "+nomorsoal);
                        textView.setId(nomorsoal);
                        LinearLayout.LayoutParams ly = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        ly.setMargins(10,3,2,3);
                        textView.setMinLines(1);
                        textView.setLayoutParams(ly);
                        //tv.setTextSize(16);
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                        textView.setTextColor(getResources().getColor(R.color.black));
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                                getResources().getDimension(R.dimen._14sdp));
                        textView.setPadding(4, 3, 0, 3);
                        textView.setIncludeFontPadding (false);
                        ly_soal.addView(textView);
                        getRinci(ly_soal,xjenis.getChildNodes());
                        Log.d("Nomor Soal", "onCreate: "+nomorsoal);
                    }

                }
                final EditText editText = new EditText(this);
                editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText.setMinLines(4);
                editText.setHint("Your Answer");
                ly_soal.addView(editText);
                editTextList.add(editText);
                Log.d("jumlah", "onCreate: " + editTextList.size());
                nomorlain++;
                nomorsoal++;
            }
        }catch (Exception e){e.printStackTrace();}
    }
    public void list(String tipe, NodeList nodeList, String parent, Context context, View childLayout, LinearLayout isi, CardView opsi){
        for(int a=0;a<nodeList.getLength();a++){
            Node jenis = nodeList.item(a);
            if(parent.equals("opsi")){
                if(jenis.getNodeName().equals(tipe)){
                    //opsi.setTag(tipe);
                    getRinci(isi,jenis.getChildNodes());

                }
            }
        }
    }
    public void getRinci(LinearLayout linearLayout, NodeList list){
        for(int u=0;u<list.getLength();u++){
            Node rinci = list.item(u);
            if(rinci.getNodeName().equals("teks")){
                TextView textView = new TextView(this);
                textView.setText(rinci.getTextContent());
                textView.setId(u);
                LinearLayout.LayoutParams ly = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                ly.setMargins(10,3,2,3);
                textView.setMinLines(1);
                textView.setLayoutParams(ly);
                //tv.setTextSize(16);
                textView.setTextColor(getResources().getColor(R.color.black));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen._13sdp));
                textView.setPadding(4, 3, 0, 3);
                textView.setIncludeFontPadding (false);
                linearLayout.addView(textView);
            }
            if(rinci.getNodeName().equals("gambar")){
                // add ImageView
                Display display = getWindowManager().getDefaultDisplay();
                ImageView iv = new ImageView(this);
                int resourceId = getResources().getIdentifier (rinci.getTextContent(), "drawable", "com.inc.vr.corp.app.mobilelearning_krisisenergi");
                iv.setImageResource(resourceId);
                iv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                //iv.setLayoutParams(parms);
                iv.setAdjustViewBounds(true);
                // float in = getResources().getDimension(R.dimen._90sdp);
                linearLayout.addView(iv);
            }
            if(rinci.getNodeName().equals("html_format")){
                //TextView tv = new TextView(this);
                HtmlTextView tv = new HtmlTextView(this);
                tv.setId(u);
                String hh = rinci.getTextContent().replace("kk2020","<");
                tv.setHtml(hh);
                //Spanned formattedHtml = HtmlFormatter.formatHtml(new HtmlFormatterBuilder().setHtml(hh).setImageGetter(new HtmlResImageGetter(tv.getContext())));
                Log.d("HTML", hh);
                //tv.setText(formattedHtml);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                params.setMargins(7,3,3,3);
                tv.setLayoutParams(params);
                //tv.setTextSize(16);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen._13sdp));
                linearLayout.addView(tv);
            }
        }
    }
    protected void sendEmail(String subject, String isi) {
        Log.i("Send email", "");
        String[] TO = {"alfisandion@upi.edu"};
        String[] CC = {"inc.vr.corp@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, isi);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(TesKognitifActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public String md5(String isi) {
        String plaintext = isi;
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.reset();
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return  hashtext;
    }
}