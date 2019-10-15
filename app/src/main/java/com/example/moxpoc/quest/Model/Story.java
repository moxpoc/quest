package com.example.moxpoc.quest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Story {

    @JsonProperty("passages")
    private List<passages> passages;
    @JsonProperty("name")
    private String name;
    @JsonProperty("startnode")
    private String startnode;
    @JsonProperty("creator")
    private String creator;
    @JsonProperty("creator-version")
    private String creatorVersion;
    @JsonProperty("ifid")
    private String ifid;

    public Story(){

    }

    public List<com.example.moxpoc.quest.Model.passages> getPassages() {
        return passages;
    }

    public void setPassages(List<com.example.moxpoc.quest.Model.passages> passages) {
        this.passages = passages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartnode() {
        return startnode;
    }

    public void setStartnode(String startnode) {
        this.startnode = startnode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorVersion() {
        return creatorVersion;
    }

    public void setCreatorVersion(String creatorVersion) {
        this.creatorVersion = creatorVersion;
    }

    public String getIfid() {
        return ifid;
    }

    public void setIfid(String ifid) {
        this.ifid = ifid;
    }
}
