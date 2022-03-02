package com.inc.vr.corp.app.mobilelearning_krisisenergi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class LaunchActivity extends AppCompatActivity {
    Intent intent;
    SharedPreferences myshared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        myshared = getSharedPreferences("Krisis-Energi", MODE_PRIVATE);
        String status = myshared.getString("status","logout");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(status.equals("login")){
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(intent);
                }else{
                    intent = new Intent(getApplicationContext(), LoginActiivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        },2000);
    }
    public void tokkken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TOKEN", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = token;
                        Log.d("TOKEN", msg);
                        Toast.makeText(LaunchActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}