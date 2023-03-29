package result;

/**
 * The Generic response class.
 */
public class GenericResponse{
    /**
     * The Success.
     */
    private boolean success;

    /**
     * The Message.
     */
    private String message;

    /**
     * Instantiates a new Generic response.
     *
     * @param success the success
     * @param message the message
     */
    public GenericResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess(){
        return success;
    }

    /**
     * Set success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success){
        this.success = success;
    }

    /**
     * Get message string.
     *
     * @return the string
     */
    public String getMessage(){
        return message;
    }

    /**
     * Set message.
     *
     * @param message the message
     */
    public void setMessage(String message){
        this.message = message;
    }
}
