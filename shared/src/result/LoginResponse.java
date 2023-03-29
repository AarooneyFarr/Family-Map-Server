package result;

/**
 * The Login response class.
 */
public class LoginResponse{
    /**
     * The Authtoken.
     */
    private String authtoken;

    /**
     * The Username.
     */
    private String username;

    /**
     * The Person id.
     */
    private String personID;

    /**
     * The Success.
     */
    private boolean success;

    /**
     * The Message.
     */
    private String message;

    /**
     * Instantiates a new Login response.
     *
     * @param authtoken the authtoken
     * @param username  the username
     * @param personID  the person id
     * @param success   the success
     * @param message   the message
     */
    public LoginResponse(String authtoken, String username, String personID, boolean success, String message){
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
        this.message = message;
    }

    /**
     * Get authtoken string.
     *
     * @return the string
     */
    public String getAuthtoken(){
        return authtoken;
    }

    /**
     * Set authtoken.
     *
     * @param authtoken the authtoken
     */
    public void setAuthtoken(String authtoken){
        this.authtoken = authtoken;
    }

    /**
     * Get username string.
     *
     * @return the string
     */
    public String getUsername(){
        return username;
    }

    /**
     * Set username.
     *
     * @param username the username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Get person id string.
     *
     * @return the string
     */
    public String getPersonID(){
        return personID;
    }

    /**
     * Set person id.
     *
     * @param personID the person id
     */
    public void setPersonID(String personID){
        this.personID = personID;
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
