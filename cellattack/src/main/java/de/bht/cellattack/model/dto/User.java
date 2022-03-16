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


    /**
     * Constructor
     * 
     * @param id
     * @param email
     * @param scores
     */
    public User(int id, String email, List<Score> scores) {
        this.id = id;
        this.email = email;
        this.scores = scores;
    }

    /**
     * Constructor
     */
    public User(){
        this.setBlocked(false);
    }

    
    /** 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }

    
    /** 
     * @return List<Score>
     */
    public List<Score> getScores() {
        return scores;
    }

    
    /** 
     * @return String
     */
    public String getToken() {
        return token;
    }

    
    /** 
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    
    /** 
     * @return boolean
     */
    public boolean isBlocked() {
        return isBlocked;
    }

    
    /** 
     * @param isBlocked
     */
    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    
    /** 
     * @return int
     */
    public int getGameScore() {
        return gameScore;
    }

    
    /** 
     * @param gameScore
     */
    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    
    /** 
     * @return boolean
     */
    public boolean isWinner() {
        return winner;
    }

    
    /** 
     * @param winner
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

}
