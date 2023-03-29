package model;

import java.util.Objects;

/**
 * The Person model type.
 */
public class Person{
    /**
     * The personId string
     * <p>
     * Type String
     */
    private String personID;

    /**
     * The associatedUsername string
     * <p>
     * Type String
     */
    private String associatedUsername;

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
     * The fatherId string
     * <p>
     * Type String
     */
    private String fatherID;

    /**
     * The motherId string
     * <p>
     * Type String
     */
    private String motherID;

    /**
     * The spouseId string
     * <p>
     * Type String
     */
    private String spouseID;

    /**
     * Instantiates a new Person.
     *
     * @param personID           the person id
     * @param associatedUsername the associated username
     * @param firstName          the first name
     * @param lastName           the last name
     * @param gender             the gender
     * @param fatherID           the father id
     * @param motherID           the mother id
     * @param spouseID           the spouse id
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID){
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
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

    /**
     * Get associated username string.
     *
     * @return the string
     */
    public String getAssociatedUsername(){
        return this.associatedUsername;
    }

    /**
     * Set associated username.
     *
     * @param associatedUsername the associated username
     */
    public void setAssociatedUsername(final String associatedUsername){
        this.associatedUsername = associatedUsername;
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
     * Get gender string.
     *
     * @return the string
     */
    public String getGender(){
        return this.gender;
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
     * Get father id string.
     *
     * @return the string
     */
    public String getFatherID(){
        return this.fatherID;
    }

    /**
     * Set father id.
     *
     * @param fatherID the father id
     */
    public void setFatherID(final String fatherID){
        this.fatherID = fatherID;
    }

    /**
     * Get mother id string.
     *
     * @return the string
     */
    public String getMotherID(){
        return this.motherID;
    }

    /**
     * Set mother id.
     *
     * @param motherID the mother id
     */
    public void setMotherID(final String motherID){
        this.motherID = motherID;
    }

    /**
     * Get spouse id string.
     *
     * @return the string
     */
    public String getSpouseID(){
        return this.spouseID;
    }

    /**
     * Set spouse id.
     *
     * @param spouseID the spouse id
     */
    public void setSpouseID(final String spouseID){
        this.spouseID = spouseID;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personID, person.personID) && Objects.equals(associatedUsername, person.associatedUsername) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) && Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
    }
}
