package com.example.moxpoc.quest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SpllashActivity extends AppCompatActivity {

    private final int SPLASH_LENGTH = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SpllashActivity.this, MenuActivity.class);
                SpllashActivity.this.startActivity(intent);
                SpllashActivity.this.finish();
            }
        }, SPLASH_LENGTH);

    }
}
