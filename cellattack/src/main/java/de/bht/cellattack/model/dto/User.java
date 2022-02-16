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
    

    public User(int id, String email, List<Score> scores) {
        this.id = id;
        this.email = email;
        this.scores = scores;
    }

    public User(){}

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

}
