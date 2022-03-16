package de.bht.cellattack.model.dto;

/**
 * ApiResponse
 */
public class ApiResponse {
    
    private Boolean success;
    private String message;

    /**
     * Constructor
     * 
     * @param success
     * @param message
     */
    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    
    /** 
     * @return Boolean
     */
    public Boolean getSuccess() {
        return success;
    }

    
    /** 
     * @param success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    
    /** 
     * @return String
     */
    public String getMessage() {
        return message;
    }

    
    /** 
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
