package com.example.moxpoc.quest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;


public class MediaService extends Service {
    private static final String TAG = "MediaService";
    MediaPlayer player;
    private int volume= 20;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();

        player = MediaPlayer.create(this, R.raw.qsensx);
        player.setVolume(volume,volume);
        player.setLooping(true); // зацикливаем
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        player.stop();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        //Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
        player.start();
    }

    public void setVolume(int volume){
       this.volume = volume;
    }
}
