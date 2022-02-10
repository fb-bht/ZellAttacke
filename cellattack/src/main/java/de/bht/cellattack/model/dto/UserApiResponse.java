package de.bht.cellattack.model.dto;

/**
 * UserResponse
 */
public class UserApiResponse {
    private final User user;

    public UserApiResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
