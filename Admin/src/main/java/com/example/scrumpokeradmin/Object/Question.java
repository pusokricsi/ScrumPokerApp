package com.example.scrumpokeradmin.Object;

public class Question {
    private String question;
    private String minute;
    private String hour;
    private String key;

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, String minute, String hour, String key) {
        this.question = question;
        this.minute = minute;
        this.hour = hour;
        this.key = key;
    }

    public Question(String question, String minute, String hour) {
        this.question = question;
        this.minute = minute;
        this.hour = hour;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", minute='" + minute + '\'' +
                ", hour='" + hour +- '\'' +
                '}';
    }


}
