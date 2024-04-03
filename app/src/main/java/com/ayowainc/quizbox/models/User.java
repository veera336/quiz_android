package com.ayowainc.quizbox.models;

public class User {
    private String username;
    private long logicalScore;
    private long fractionalScore;
    private long reasoningScore;
    private long aptitudeScore;
    private long score;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String username, long score, long logicalScore, long fractionalScore, long aptitudeScore, long reasoningScore) {
        this.username = username;
        this.logicalScore = logicalScore;
        this.fractionalScore = fractionalScore;
        this.aptitudeScore = aptitudeScore;
        this.reasoningScore = reasoningScore;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public long getScore() {
        return score;
    }
    public long getLogicalScore() { return logicalScore; }
    public long getFractionalScore() { return fractionalScore; }
    public long getAptitudeScore() { return aptitudeScore; }
    public long getReasoningScore() { return reasoningScore; }
}
