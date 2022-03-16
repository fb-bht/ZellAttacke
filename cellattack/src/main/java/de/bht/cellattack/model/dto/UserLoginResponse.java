package de.bht.cellattack.model.dto;

/**
 * UserLoginResponse
 */
public class UserLoginResponse {
    private final String jwt;

    /**
     * Constructor
     * 
     * @param jwt
     */
    public UserLoginResponse(String jwt) {
        this.jwt = jwt;
    }

    
    /** 
     * @return String
     */
    public String getJwt() {
        return jwt;
    }

}
