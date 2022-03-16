package de.bht.cellattack.model.dto;

/**
 * Score
 */
public class Score {

    private final int score;
    private final String date;

    /**
     * Constructor
     * 
     * @param score
     * @param date
     */
    public Score(int score, String date) {
        this.score = score;
        this.date = date;
    }

    
    /** 
     * @return String
     */
    public String getDate() {
        return date;
    }

    
    /** 
     * @return int
     */
    public int getScore() {
        return score;
    }
    
}
