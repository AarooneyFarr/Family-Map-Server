package result;

/**
 * The Person response class.
 */
public class PersonResponse{
    /**
     * The Associated username.
     */
    private String associatedUsername;

    /**
     * The Person id.
     */
    private String personID;

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
     * The Father id.
     */
    private String fatherID;

    /**
     * The Mother id.
     */
    private String motherID;

    /**
     * The Spouse id.
     */
    private String spouseID;

    /**
     * The Success.
     */
    private boolean success;

    /**
     * The Message.
     */
    private String message;

    /**
     * Instantiates a new Person response.
     *
     * @param associatedUsername the associated username
     * @param personID           the person id
     * @param firstName          the first name
     * @param lastName           the last name
     * @param gender             the gender
     * @param fatherID           the father id
     * @param motherID           the mother id
     * @param spouseID           the spouse id
     * @param success            the success
     * @param message            the message
     */
    public PersonResponse(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success, String message){
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.success = success;
        this.message = message;
    }

    /**
     * Get associated username string.
     *
     * @return the string
     */
    public String getAssociatedUsername(){
        return associatedUsername;
    }

    /**
     * Set associated username.
     *
     * @param associatedUsername the associated username
     */
    public void setAssociatedUsername(String associatedUsername){
        this.associatedUsername = associatedUsername;
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

    /**
     * Get father id string.
     *
     * @return the string
     */
    public String getFatherID(){
        return fatherID;
    }

    /**
     * Set father id.
     *
     * @param fatherID the father id
     */
    public void setFatherID(String fatherID){
        this.fatherID = fatherID;
    }

    /**
     * Get mother id string.
     *
     * @return the string
     */
    public String getMotherID(){
        return motherID;
    }

    /**
     * Set mother id.
     *
     * @param motherID the mother id
     */
    public void setMotherID(String motherID){
        this.motherID = motherID;
    }

    /**
     * Get spouse id string.
     *
     * @return the string
     */
    public String getSpouseID(){
        return spouseID;
    }

    /**
     * Set spouse id.
     *
     * @param spouseID the spouse id
     */
    public void setSpouseID(String spouseID){
        this.spouseID = spouseID;
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
