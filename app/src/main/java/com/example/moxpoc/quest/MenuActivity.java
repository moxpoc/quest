package com.example.moxpoc.quest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOError;
import java.io.IOException;

public class MenuActivity extends AppCompatActivity {

    public static final String ACTION = "android.intent.action.main_game";
    public static final String TOSETTINGS = "android.intent.action.settings";
    public static final String TOAUTHORS = "android.intent.action.authors";
    public static final String TOCONTENTS = "android.intent.action.contents1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button newGame = findViewById(R.id.newGame);
        Button continueGame = findViewById(R.id.continueGame);
        Button settings = findViewById(R.id.settings);
        Button authors = findViewById(R.id.authors);
        Button contents = findViewById(R.id.contents);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent = new Intent(ACTION);
                startActivity(intent);
            }
        });

        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SharedPreferences preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                    if(preferences != null){

                        Intent intent = new Intent(ACTION);
                        startActivity(intent);
                    }

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TOSETTINGS);
                startActivity(intent);
            }
        });

        authors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TOAUTHORS);
                startActivity(intent);
            }
        });

        contents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TOCONTENTS);
                startActivity(intent);
            }
        });
    }
}
