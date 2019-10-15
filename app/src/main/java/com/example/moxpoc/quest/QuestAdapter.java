package com.example.moxpoc.quest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moxpoc.quest.Model.passages;

import java.util.List;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.QViewHolder> {

    private List<passages> passagesList;
    private List<String> answerList;
    private LayoutInflater inflater;


    public static class QViewHolder extends RecyclerView.ViewHolder{
        public  Typer quesView;
        public TextView ansView;
        public QViewHolder(View v){
            super(v);
            quesView = v.findViewById(R.id.quesText);
            ansView = v.findViewById(R.id.answerText);

        }
    }

    public QuestAdapter(Context context, List<passages> passagesList, List<String> answerList){
        this.inflater = LayoutInflater.from(context);
        this.passagesList = passagesList;
        this.answerList = answerList;
    }

    @NonNull
    @Override
    public QuestAdapter.QViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.listitem, parent, false);
        QViewHolder viewHolder = new QViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QViewHolder holder, int position){
        holder.quesView.setCharacterDelay(35);
        holder.quesView.animateText(passagesList.get(position).getText());
        holder.quesView.setVisibility(View.VISIBLE);

        if(answerList.size() !=0 && position < answerList.size() ){
            holder.ansView.setText(answerList.get(position));
            holder.ansView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount(){
        return passagesList.size();
    }



}
