package de.bht.cellattack.model.dto;

/**
 * Score
 */
public class Score {

    private final int score;
    private final String date;

    public Score(int score, String date) {
        this.score = score;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }
    
}
