package request;

/**
 * The Register request class.
 */
public class RegisterRequest{
    /**
     * The Username.
     */
    private String username;

    /**
     * The Password.
     */
    private String password;

    /**
     * The Email.
     */
    private String email;

    /**
     * The First name.
     */
    private String firstName;

    /**
     * The Last name.
     */
    private String lastName;

    /**
     * The Gender.
     */
    private String gender;

    /**
     * Instantiates a new Register request.
     *
     * @param username  the username
     * @param password  the password
     * @param email     the email
     * @param firstName the first name
     * @param lastName  the last name
     * @param gender    the gender
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    /**
     * Get email string.
     *
     * @return the string
     */
    public String getEmail(){
        return email;
    }

    /**
     * Set email.
     *
     * @param email the email
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Get first name string.
     *
     * @return the string
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Set first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * Get last name string.
     *
     * @return the string
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Set last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * Get gender string.
     *
     * @return the string
     */
    public String getGender(){
        return gender;
    }

    /**
     * Set gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender){
        this.gender = gender;
    }
}
