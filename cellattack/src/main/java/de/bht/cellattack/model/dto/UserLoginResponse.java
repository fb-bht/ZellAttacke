package de.bht.cellattack.model.dto;

/**
 * UserLoginResponse
 */
public class UserLoginResponse {
    private final String jwt;

    public UserLoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
