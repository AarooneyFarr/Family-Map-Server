package request;

/**
 * The Login request class.
 */
public class LoginRequest{
    /**
     * The username string
     * <p>
     * Type String
     */
    private String username;

    /**
     * The password string
     * <p>
     * Type String
     */
    private String password;

    /**
     * Instantiates a new Login request.
     *
     * @param username the username
     * @param password the password
     */
    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
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
     * Get password string.
     *
     * @return the string
     */
    public String getPassword(){
        return password;
    }

    /**
     * Set password.
     *
     * @param password the password
     */
    public void setPassword(String password){
        this.password = password;
    }
}
