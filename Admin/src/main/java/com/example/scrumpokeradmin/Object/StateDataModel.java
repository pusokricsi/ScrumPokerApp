package com.example.scrumpokeradmin.Object;

public class StateDataModel {
    private String username;
    private String state;

    public StateDataModel() {
    }

    public StateDataModel(String username, String state) {
        this.username = username;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "StateDataModel{" +
                "username='" + username + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
