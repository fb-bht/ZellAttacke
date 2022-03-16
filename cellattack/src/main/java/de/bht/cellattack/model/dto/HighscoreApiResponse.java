package de.bht.cellattack.model.dto;

/**
 * HighscoreApiResponse 
 */
public class HighscoreApiResponse {
    
    private final Score score;

    /**
     * Constructor
     * 
     * @param score
     */
    public HighscoreApiResponse(Score score) {
        this.score = score;
    }

    
    /** 
     * @return Score
     */
    public Score getHighscore() {
        return score;
    }
    
}
