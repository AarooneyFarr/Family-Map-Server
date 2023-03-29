package model;

import java.util.Objects;

/**
 * The Authtoken model type.
 */
public class Authtoken{
    /**
     * The authtoken string
     * <p>
     * Type String
     */
    private String authtoken;

    /**
     * The username string
     * <p>
     * Type String
     */
    private String username;

    /**
     * Instantiates a new Authtoken.
     *
     * @param authtoken the authtoken
     * @param username  the username
     */
    public Authtoken(final String authtoken, final String username){
        this.authtoken = authtoken;
        this.username = username;
    }

    /**
     * Get authtoken string.
     *
     * @return the string
     */
    public String getAuthtoken(){
        return this.authtoken;
    }

    /**
     * Set authtoken.
     *
     * @param authtoken the authtoken
     */
    public void setAuthtoken(final String authtoken){
        this.authtoken = authtoken;
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

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Authtoken token = (Authtoken) o;
        return Objects.equals(authtoken, token.authtoken) && Objects.equals(username, token.username);
    }
}
