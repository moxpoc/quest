package com.example.moxpoc.quest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class passages {

    @JsonProperty("text")
    private String text;
    private List<links> links;
    private String name;
    private String pid;
    private position position;
    /*@JsonIgnore
    private long pause;*/
    @JsonIgnore
    @JsonProperty("tags")
    private String [] tags;

    public passages(){

    }

    public String getText() {
        String[] wait = text.split("©");
        text = wait[0];
        return text;
    }

    public String getBeforePauseText(){
        if(getText().contains("<")){
            String[] beforeText = getText().split("<");
            return beforeText[0];
        } else{
            return getText();
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<com.example.moxpoc.quest.Model.links> getLinks() {
        return links;
    }

    public void setLinks(List<com.example.moxpoc.quest.Model.links> links) {
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public com.example.moxpoc.quest.Model.position getPosition() {
        return position;
    }

    public void setPosition(com.example.moxpoc.quest.Model.position position) {
        this.position = position;
    }

    /*public long getPause() {
        return pause;
    }

    public void setPause(long pause) {
        this.pause = pause;
    }*/
}
