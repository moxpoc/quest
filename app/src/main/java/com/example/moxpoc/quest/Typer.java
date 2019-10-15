package com.example.moxpoc.quest;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Type;

public class Typer extends android.support.v7.widget.AppCompatTextView {

    private CharSequence textSeq;
    private int index;
    private long delay = 150;

    public Typer(Context context){
        super(context);
    }

    public Typer(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    private Handler handler = new Handler();

    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(textSeq.subSequence(0, index++));

            if(index < textSeq.length() + 1){
                handler.postDelayed(characterAdder, delay);
            }
        }
    };

    public void animateText(CharSequence txt){
        textSeq = txt;
        index = 0;

        //setText("");
        handler.removeCallbacks(characterAdder);
        handler.postDelayed(characterAdder, delay);
    }

    public void setCharacterDelay(long d){
        delay = d;
    }
}
