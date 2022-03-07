package de.bht.cellattack.model.dto;

import java.util.List;

/**
 * User
 */
public class User {

    private int id;
    private String email;
    private List<Score> scores;
    private String token;
    private boolean isBlocked;
    private int gameScore; //TODO
    private boolean winner; //TODO


    public User(int id, String email, List<Score> scores) {
        this.id = id;
        this.email = email;
        this.scores = scores;
    }

    public User(){
        this.setBlocked(false);
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public List<Score> getScores() {
        return scores;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

}
