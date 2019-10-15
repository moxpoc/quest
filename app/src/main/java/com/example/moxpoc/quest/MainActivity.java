package com.example.moxpoc.quest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moxpoc.quest.Model.Story;
import com.example.moxpoc.quest.Model.links;
import com.example.moxpoc.quest.Model.passages;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    long time;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    String json;
    Story story;
    List<passages> enablePassages = new ArrayList<>();
    List<String> answerList = new ArrayList<>();
    Map<String, passages> passagesMap = new HashMap<>();
    SharedPreferences preferences;
    Button leftBtn, rightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaPlayer pressPl = MediaPlayer.create(this, R.raw.press2);
        pressPl.setVolume(100,100);
        //Создаём файл с сохраненными настройками
        preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.mainRecView);
        recyclerView.addItemDecoration(new CustomItemDecoration(8));

        leftBtn = findViewById(R.id.leftDecisionBtn);
        rightBtn = findViewById(R.id.rightDecisionBtn);

        layoutManager = new LinearLayoutManager(this);

        //Объявляем и используем фабрику конвертации JSON
        ObjectMapper mapper = new ObjectMapper();
        try{
            json = fromFile("timeIsland.json");
            story = mapper.readValue(json, Story.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Восстанавливаем настройки если есть
        setMap();
        if(preferences.contains("saveSet")) {
            getPref();

        }
        else
            enablePassages.add(passagesMap.get("Безымянный параграф"));
        overrrideBtn();

        adapter = new QuestAdapter(MainActivity.this, enablePassages, answerList);
        recyclerView.setAdapter(adapter);
        int tmp = adapter.getItemCount();

        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(adapter.getItemCount()-1,0);
        recyclerView.setLayoutManager(layoutManager);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SystemClock.currentThreadTimeMillis() >= time) {
                    pressPl.start();
                    String key = leftBtn.getText().toString();
                    enablePassages.add(passagesMap.get(key));
                    //time = SystemClock.currentThreadTimeMillis() + passagesMap.get(key).getPause();
                    answerList.add(leftBtn.getText().toString());
                    adapter = new QuestAdapter(MainActivity.this, enablePassages, answerList);
                    recyclerView.setAdapter(adapter);
                    overrrideBtn();
                    ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(adapter.getItemCount() - 1, 0);
                }
                else {
                    String key = leftBtn.getText().toString();
                    passages tmpP = passagesMap.get(key);
                    tmpP.setText(tmpP.getBeforePauseText());
                    enablePassages.add(tmpP);
                    //time = SystemClock.currentThreadTimeMillis() + passagesMap.get(key).getPause();
                    answerList.add(leftBtn.getText().toString());
                    adapter = new QuestAdapter(MainActivity.this, enablePassages, answerList);
                    recyclerView.setAdapter(adapter);
                    overrrideBtn();
                    ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(adapter.getItemCount() - 1, 0);
                }

                //recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressPl.start();
                String key = rightBtn.getText().toString();
                enablePassages.add(passagesMap.get(key));
                answerList.add(rightBtn.getText().toString());
                adapter = new QuestAdapter(MainActivity.this, enablePassages,answerList);
                recyclerView.setAdapter(adapter);
                overrrideBtn();
                ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(adapter.getItemCount()-1,0);
                //recyclerView.scrollToPosition(adapter.getItemCount() - 1);

            }
        });
    }

    public void overrrideBtn(){
        leftBtn.setText(enablePassages.get(enablePassages.size() - 1).getLinks().get(0).getName());
        if(enablePassages.get(enablePassages.size() - 1).getLinks().size() >= 2)
            rightBtn.setText(enablePassages.get(enablePassages.size() - 1).getLinks().get(1).getName());
    }
    //Метод читает файл квеста
    public String fromFile(String path) throws IOException{
        InputStream is = getAssets().open(path);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }

    //Метод сохраняет прогресс в preferences
    public void savePref(List<passages> passagesList, List<String> answerList){
        Set<String> saveSet = new HashSet<>();
        Set<String> saveAns = new HashSet<>(answerList);
        for(int i = passagesList.size()-1 ; i >= 0; i--){
            saveSet.add(passagesList.get(i).getName());

        }
        SharedPreferences.Editor e = preferences.edit();
        e.putStringSet("saveSet", saveSet);
        e.putStringSet("saveAns", saveAns);
        e.apply();
    }

    //Метод получает preferences
    public void getPref(){
        setMap();
        Set<String> saveSet = preferences.getStringSet("saveSet", new HashSet<String>());
        Set<String> saveAns = preferences.getStringSet("saveAns", new HashSet<String>());
        enablePassages.clear();
        answerList.clear();
        answerList.addAll(saveAns);
        for(String s : saveSet){
            enablePassages.add(passagesMap.get(s));
        }
        //Collections.reverse(enablePassages);
        Collections.reverse(answerList);
    }

    public void setMap(){
        passagesMap.clear();
        for(int i =0; i< story.getPassages().size(); i++){
            passagesMap.put(story.getPassages().get(i).getName(), story.getPassages().get(i));
        }
    }

    public void bgMedia(){
        //Объявляем плеер для проигрывания музыки в фоне
        mediaPlayer = MediaPlayer.create(this, R.raw.qsensx);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0,0);
        //mediaPlayer.start();
    }



    @Override
    protected void onStop(){
        super.onStop();
        savePref(enablePassages, answerList);
        stopService(new Intent(this, MediaService.class));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(preferences.contains("saveSet"))
            getPref();
        startService(new Intent(this, MediaService.class));
    }

    @Override
    protected void onStart(){
        super.onStart();
        /*if (preferences!=null)
            getPref();*/
        startService(new Intent(this, MediaService.class));
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        startService(new Intent(this, MediaService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, MediaService.class));
    }
}
