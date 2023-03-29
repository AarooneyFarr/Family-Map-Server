package result;

/**
 * The Event response class.
 */
public class EventResponse{
    /**
     * The Associated username.
     */
    private String associatedUsername;

    /**
     * The Event id.
     */
    private String eventID;

    /**
     * The Person id.
     */
    private String personID;

    /**
     * The Latitude.
     */
    private float latitude;

    /**
     * The Longitude.
     */
    private float longitude;

    /**
     * The Country.
     */
    private String country;

    /**
     * The City.
     */
    private String city;

    /**
     * The Event type.
     */
    private String eventType;

    /**
     * The Year.
     */
    private int year;

    /**
     * The Success.
     */
    private boolean success;

    /**
     * The Message.
     */
    private String message;

    /**
     * Instantiates a new Event response.
     *
     * @param associatedUsername the associated username
     * @param eventID            the event id
     * @param personID           the person id
     * @param latitude           the latitude
     * @param longitude          the longitude
     * @param country            the country
     * @param city               the city
     * @param eventType          the event type
     * @param year               the year
     * @param success            the success
     * @param message            the message
     */
    public EventResponse(String associatedUsername, String eventID, String personID, float latitude, float longitude, String country, String city, String eventType, int year, boolean success, String message){
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
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
     * Get event id string.
     *
     * @return the string
     */
    public String getEventID(){
        return eventID;
    }

    /**
     * Set event id.
     *
     * @param eventID the event id
     */
    public void setEventID(String eventID){
        this.eventID = eventID;
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
     * Get latitude float.
     *
     * @return the float
     */
    public float getLatitude(){
        return latitude;
    }

    /**
     * Set latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(float latitude){
        this.latitude = latitude;
    }

    /**
     * Get longitude float.
     *
     * @return the float
     */
    public float getLongitude(){
        return longitude;
    }

    /**
     * Set longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(float longitude){
        this.longitude = longitude;
    }

    /**
     * Get country string.
     *
     * @return the string
     */
    public String getCountry(){
        return country;
    }

    /**
     * Set country.
     *
     * @param country the country
     */
    public void setCountry(String country){
        this.country = country;
    }

    /**
     * Get city string.
     *
     * @return the string
     */
    public String getCity(){
        return city;
    }

    /**
     * Set city.
     *
     * @param city the city
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * Get event type string.
     *
     * @return the string
     */
    public String getEventType(){
        return eventType;
    }

    /**
     * Set event type.
     *
     * @param eventType the event type
     */
    public void setEventType(String eventType){
        this.eventType = eventType;
    }

    /**
     * Get year int.
     *
     * @return the int
     */
    public int getYear(){
        return year;
    }

    /**
     * Set year.
     *
     * @param year the year
     */
    public void setYear(int year){
        this.year = year;
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
