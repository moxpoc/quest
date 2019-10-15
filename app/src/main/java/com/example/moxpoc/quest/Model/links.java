package com.example.moxpoc.quest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class links {

    private String name;
    private String link;
    @JsonIgnore
    private String pid;

    public links(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
