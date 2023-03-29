package model;

import java.util.Objects;

/**
 * The Event model type.
 */
public class Event{
    /**
     * The eventID string
     * <p>
     * Type String
     */
    private String eventID;

    /**
     * The associatedUsername string
     * <p>
     * Type String
     */
    private String associatedUsername;

    /**
     * The personID string
     * <p>
     * Type String
     */
    private String personID;

    /**
     * The latitude float
     * <p>
     * Type Float
     */
    private Float latitude;

    /**
     * The longitude float
     * <p>
     * Type Float
     */
    private Float longitude;

    /**
     * The country string
     * <p>
     * Type String
     */
    private String country;

    /**
     * The city string
     * <p>
     * Type String
     */
    private String city;

    /**
     * The eventType string
     * <p>
     * Type String
     */
    private String eventType;

    /**
     * The Year int
     * <p>
     * Type Integer
     */
    private Integer year;

    /**
     * Instantiates a new Event.
     *
     * @param eventID   the event id
     * @param username  the username
     * @param personID  the person id
     * @param latitude  the latitude
     * @param longitude the longitude
     * @param country   the country
     * @param city      the city
     * @param eventType the event type
     * @param year      the year
     */
    public Event(String eventID, String username, String personID, Float latitude, Float longitude,
                 String country, String city, String eventType, Integer year){
        this.eventID = eventID;
        this.associatedUsername = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    /**
     * Gets event id.
     *
     * @return the event id
     */
    public String getEventID(){
        return eventID;
    }

    /**
     * Sets event id.
     *
     * @param eventID the event id
     */
    public void setEventID(String eventID){
        this.eventID = eventID;
    }

    /**
     * Gets associated username.
     *
     * @return the associated username
     */
    public String getAssociatedUsername(){
        return associatedUsername;
    }

    /**
     * Sets associated username.
     *
     * @param associatedUsername the associated username
     */
    public void setAssociatedUsername(String associatedUsername){
        this.associatedUsername = associatedUsername;
    }

    /**
     * Gets person id.
     *
     * @return the person id
     */
    public String getPersonID(){
        return personID;
    }

    /**
     * Sets person id.
     *
     * @param personID the person id
     */
    public void setPersonID(String personID){
        this.personID = personID;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public Float getLatitude(){
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(Float latitude){
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public Float getLongitude(){
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(Float longitude){
        this.longitude = longitude;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry(){
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country){
        this.country = country;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity(){
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * Gets event type.
     *
     * @return the event type
     */
    public String getEventType(){
        return eventType;
    }

    /**
     * Sets event type.
     *
     * @param eventType the event type
     */
    public void setEventType(String eventType){
        this.eventType = eventType;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public Integer getYear(){
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(Integer year){
        this.year = year;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
    }

}