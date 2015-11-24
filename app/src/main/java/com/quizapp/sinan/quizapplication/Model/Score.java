package com.quizapp.sinan.quizapplication.Model;


public class Score {
    private int score;
    private int id;
    private String name;

    public Score() {
    }

    public Score( int score, String name) {

        this.score = score;
        this.name = name;
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

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
