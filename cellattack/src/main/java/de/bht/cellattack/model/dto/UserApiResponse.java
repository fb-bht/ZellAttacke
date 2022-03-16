package de.bht.cellattack.model.dto;

/**
 * UserResponse
 */
public class UserApiResponse {
    private final User user;

    /**
     * Constructor
     * 
     * @param user
     */
    public UserApiResponse(User user) {
        this.user = user;
    }

    
    /** 
     * @return User
     */
    public User getUser() {
        return user;
    }

}
