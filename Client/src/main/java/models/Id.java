package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Id object
 */
public class Id {
    @JsonProperty("userid")
    private String uid = "";
    private String name = "";
    private String github = "";

    public Id() {
    }

    public Id(String uid, String name, String github) {
        this.uid = uid;
        this.name = name;
        this.github = github;
    }

    public Id (String name, String githubId) {}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.github + ") ";
    }
}