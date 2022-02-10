package de.bht.cellattack.model.dto;

/**
 * HighscoreApiResponse 
 */
public class HighscoreApiResponse {
    
    private final Score score;

    public HighscoreApiResponse(Score score) {
        this.score = score;
    }

    public Score getHighscore() {
        return score;
    }
    
}
