package dao;

import com.google.gson.Gson;
import model.Event;
import model.Person;
import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * The Event data access object.
 */
public class EventDAO{
    /**
     * The database connection
     * <p>
     * Type Connection
     */
    private final Connection conn;

    /**
     * Instantiates a new Event dao.
     *
     * @param conn the connection
     */
    public EventDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Find event.
     *
     * @param eventID the event id
     * @return the event
     * @throws DataAccessException the data access exception
     */
    public Event find(String eventID) throws DataAccessException{
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE EventID = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if(rs.next()){
                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                                  rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                                  rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                                  rs.getInt("Year"));
                return event;
            } else{
                return null;
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }

    }

    /**
     * Load new data into Event table.
     *
     * @param events the events to load
     */
    public void load(Event[] events) throws DataAccessException{
        clear();

        for(Event event : events){
            insert(event);
        }
    }

    /**
     * Clear Event table.
     *
     * @throws DataAccessException the data access exception
     */
    public void clear() throws DataAccessException{
        String sql = "DELETE FROM Events";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

    /**
     * Insert event.
     *
     * @param event the event
     * @throws DataAccessException the data access exception
     */
    public void insert(Event event) throws DataAccessException{
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    public Event[] findEventsByUser(String username) throws DataAccessException{
        ArrayList<Event> events = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE AssociatedUsername = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()){
                Event event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                                        rs.getInt("Year"));
                events.add(event);

            }

            Event[] arr = new Event[events.size()];
            arr = events.toArray(arr);

            return arr;
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }
    }

    public void createBirthAndDeathEvents(Person child, Person person, String baseUsername) throws DataAccessException{
        //Create birth event
        String birthID = UUID.randomUUID().toString();

        Location birthLocation = genRandomLocation();

        Integer yearsBefore = new Random().nextInt(33) + 14;
        Integer birthYear = getBirthYear(child) - yearsBefore;

        Event birth = new Event(birthID, baseUsername, person.getPersonID(), Float.parseFloat(birthLocation.latitude),
                                Float.parseFloat(birthLocation.longitude), birthLocation.country, birthLocation.city,
                                "birth", birthYear);

        //Create death event
        String deathID = UUID.randomUUID().toString();

        Location deathLocation = genRandomLocation();

        Integer yearsLived = new Random().nextInt(33) + 14;
        Integer deathYear = birthYear + yearsLived;

        Event death = new Event(deathID, baseUsername, person.getPersonID(), Float.parseFloat(deathLocation.latitude),
                                Float.parseFloat(deathLocation.longitude), deathLocation.country, deathLocation.city,
                                "death", deathYear);

        insert(birth);
        insert(death);
    }

    private Location genRandomLocation(){
        try{
            File file = new File(
                    "C:\\Users\\TheAa\\Documents\\School\\CS 240\\familyMapServer\\FamilyMapServerStudent-master\\json\\locations.json");
            Reader reader = new FileReader(file);
            Gson gson = new Gson();
            LocationData mData = (LocationData) gson.fromJson(reader, LocationData.class);

            int rnd = new Random().nextInt(mData.data.length);

            return mData.data[rnd];
        } catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

    }

    private Integer getBirthYear(Person person) throws DataAccessException{
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Events WHERE PersonID = \'" + person.getPersonID() + "\' AND eventType = \'birth\';";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
//            stmt.setString(1, person.getPersonID());
//            stmt.setString(2, "birth");

            rs = stmt.executeQuery();
            if(rs.next()){
//                event = new Event(rs.getString("EventID"), rs.getString("AssociatedUsername"),
//                                  rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
//                                  rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
//                                  rs.getInt("Year"));

                return rs.getInt("Year");
            } else{
                return null;
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a birth year in the database");
        }

    }

    public void createBirthAndDeathEvents(int manualBirthYear, Person person, String baseUsername) throws
                                                                                                   DataAccessException{
        if(manualBirthYear < 0){
            throw new NullPointerException();
        }
        //Create birth event
        String birthID = UUID.randomUUID().toString();

        Location birthLocation = genRandomLocation();

        Integer birthYear = manualBirthYear;

        Event birth = new Event(birthID, baseUsername, person.getPersonID(), Float.parseFloat(birthLocation.latitude),
                                Float.parseFloat(birthLocation.longitude), birthLocation.country, birthLocation.city,
                                "birth", birthYear);

        //Create death event
        String deathID = UUID.randomUUID().toString();

        Location deathLocation = genRandomLocation();

        Integer yearsLived = new Random().nextInt(33) + 14;
        Integer deathYear = birthYear + yearsLived;

        Event death = new Event(deathID, baseUsername, person.getPersonID(), Float.parseFloat(deathLocation.latitude),
                                Float.parseFloat(deathLocation.longitude), deathLocation.country, deathLocation.city,
                                "death", deathYear);

        insert(birth);
        insert(death);
    }

    public void createMarriageEvents(Person father, Person mother, String baseUsername) throws DataAccessException{
        //Create birth event
        String fatherID = UUID.randomUUID().toString();
        String motherID = UUID.randomUUID().toString();

        Location marriageLocation = genRandomLocation();

        Integer yearsAfter = new Random().nextInt(33) + 14;
        Integer fatherBirth = getBirthYear(father);
        Integer motherBirth = getBirthYear(mother);

        Integer marriageYear = fatherBirth + yearsAfter;

        while(marriageYear < (motherBirth + 14) || marriageYear < (fatherBirth + 14)){
            marriageYear++;
        }

        Event fatherMarriage = new Event(fatherID, baseUsername, father.getPersonID(),
                                         Float.parseFloat(marriageLocation.latitude),
                                         Float.parseFloat(marriageLocation.longitude), marriageLocation.country,
                                         marriageLocation.city,
                                         "marriage", marriageYear);

        Event motherMarriage = new Event(motherID, baseUsername, mother.getPersonID(),
                                         Float.parseFloat(marriageLocation.latitude),
                                         Float.parseFloat(marriageLocation.longitude), marriageLocation.country,
                                         marriageLocation.city,
                                         "marriage", marriageYear);

        insert(motherMarriage);
        insert(fatherMarriage);

    }

    public void clearEventsByUser(String username) throws DataAccessException{
        String sql = "DELETE FROM Events WHERE associatedUsername=\'" + username + "\';";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

}

class Location{
    String latitude;

    String longitude;

    String city;

    String country;

}

class LocationData{
    Location[] data;
}
