package com.example.duanmot_nhomhai.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id, score;
    private String name, pass, email;

    public User (int id, int score, String name, String pass, String email){
        this.id = id;
        this.score = score;
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public User () {
        this.id = id;
        this.score = score;
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setRole(int role) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
