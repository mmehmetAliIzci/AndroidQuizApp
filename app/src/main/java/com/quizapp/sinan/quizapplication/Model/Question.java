package com.quizapp.sinan.quizapplication.Model;


public class Question {
    private int id;
    private String question_body;
    private String correct_answer;
    private String first_answer,second_answer,third_answer,forth_answer;

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question(String question_body, String correct_answer, String first_answer, String second_answer, String third_answer, String forth_answer) {
        this.question_body = question_body;
        this.correct_answer = correct_answer;
        this.first_answer = first_answer;
        this.second_answer = second_answer;
        this.third_answer = third_answer;
        this.forth_answer = forth_answer;
    }

    public String getQuestion_body() {
        return question_body;
    }

    public void setQuestion_body(String question_body) {
        this.question_body = question_body;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getFirst_answer() {
        return first_answer;
    }

    public void setFirst_answer(String first_answer) {
        this.first_answer = first_answer;
    }

    public String getSecond_answer() {
        return second_answer;
    }

    public void setSecond_answer(String second_answer) {
        this.second_answer = second_answer;
    }

    public String getThird_answer() {
        return third_answer;
    }

    public void setThird_answer(String third_answer) {
        this.third_answer = third_answer;
    }

    public String getForth_answer() {
        return forth_answer;
    }

    public void setForth_answer(String forth_answer) {
        this.forth_answer = forth_answer;
    }
}
