package de.bht.cellattack.model.dto;

import java.util.List;

/**
 * User
 */
public class User {

    private final int id;
    private final String email;
    private final List<Score> scores;

    public User(int id, String email, List<Score> scores) {
        this.id = id;
        this.email = email;
        this.scores = scores;
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
    
}
