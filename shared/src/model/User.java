package model;

import java.util.Objects;

/**
 * The User model type.
 */
public class User{
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
     * The email string
     * <p>
     * Type String
     */
    private String email;

    /**
     * The firstName string
     * <p>
     * Type String
     */
    private String firstName;

    /**
     * The lastName string
     * <p>
     * Type String
     */
    private String lastName;

    /**
     * The gender string
     * <p>
     * Type String
     */
    private String gender;

    /**
     * The personId string
     * <p>
     * Type String
     */
    private String personID;

    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;

    }

    /**
     * Get username string.
     *
     * @return the string
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Set username.
     *
     * @param username the username
     */
    public void setUsername(final String username){
        this.username = username;
    }

    /**
     * Get password string.
     *
     * @return the string
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Set password.
     *
     * @param password the password
     */
    public void setPassword(final String password){
        this.password = password;
    }

    /**
     * Get email string.
     *
     * @return the string
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Set email.
     *
     * @param email the email
     */
    public void setEmail(final String email){
        this.email = email;
    }

    /**
     * Get first name string.
     *
     * @return the string
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Set first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(final String firstName){
        this.firstName = firstName;
    }

    /**
     * Get last name string.
     *
     * @return the string
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Set last name.
     *
     * @param lastName the last name
     */
    public void setLastName(final String lastName){
        this.lastName = lastName;
    }

    /**
     * Get gender gender.
     *
     * @return the gender
     */
    public String getGender(){
        return this.gender.toString();
    }

    /**
     * Set gender.
     *
     * @param gender the gender
     */
    public void setGender(final String gender){
        this.gender = gender;
    }

    /**
     * Get person id string.
     *
     * @return the string
     */
    public String getPersonID(){
        return this.personID;
    }

    /**
     * Set person id.
     *
     * @param personID the person id
     */
    public void setPersonID(final String personID){
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(personID, user.personID);
    }
}
