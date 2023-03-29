package dao;

import com.google.gson.Gson;
import model.Authtoken;
import model.Event;
import model.Person;
import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * The Person data access object.
 */
public class PersonDAO{
    /**
     * The database connection
     * <p>
     * Type Connection
     */
    private final Connection conn;

    /**
     * Instantiates a new Person dao.
     *
     * @param conn the connection
     */
    public PersonDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Create person.
     *
     * @param person the person
     */
    void createPerson(Person person) throws DataAccessException{
        insert(person);
    }

    public void insert(Person person) throws DataAccessException{
        String sql = "INSERT INTO Person ( PersonID, AssociatedUsername, FirstName, " +
                "LastName, Gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a person into the database");
        }
    }

    public Person genRandomPerson(Boolean male){

        String gender = "f";
        String firstName = "avery";
        String lastName = "autobots";

        if(male){
            gender = "m";
            try{
                File file = new File(
                        "C:\\Users\\TheAa\\Documents\\School\\CS 240\\familyMapServer\\FamilyMapServerStudent-master\\json\\mnames.json");
                Reader reader = new FileReader(file);
                Gson gson = new Gson();
                MaleNames mData = (MaleNames) gson.fromJson(reader, MaleNames.class);

                int rnd = new Random().nextInt(mData.data.length);
                firstName = mData.data[rnd];
            } catch(FileNotFoundException e){
                throw new RuntimeException(e);
            }

        } else{
            try{
                File file = new File(
                        "C:\\Users\\TheAa\\Documents\\School\\CS 240\\familyMapServer\\FamilyMapServerStudent-master\\json\\fnames.json");
                Reader reader = new FileReader(file);
                Gson gson = new Gson();
                FemaleNames mData = (FemaleNames) gson.fromJson(reader, FemaleNames.class);

                int rnd = new Random().nextInt(mData.data.length);
                firstName = mData.data[rnd];
            } catch(FileNotFoundException e){
                throw new RuntimeException(e);
            }
        }

        try{
            File file = new File(
                    "C:\\Users\\TheAa\\Documents\\School\\CS 240\\familyMapServer\\FamilyMapServerStudent-master\\json\\snames.json");
            Reader reader = new FileReader(file);
            Gson gson = new Gson();
            LastNames mData = (LastNames) gson.fromJson(reader, LastNames.class);

            int rnd = new Random().nextInt(mData.data.length);
            lastName = mData.data[rnd];
        } catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }

        String fatherID = UUID.randomUUID().toString();
        String motherID = UUID.randomUUID().toString();
        String personID = UUID.randomUUID().toString();
        String spouseID = UUID.randomUUID().toString();

        return new Person(personID, "boogawoof", firstName, lastName, gender, fatherID, motherID, spouseID);
    }

    /**
     * Get person by id.
     *
     * @param id the id
     * @return the person
     */
    Person getPersonByID(String id){
        return null;
    }

    /**
     * Get family of this person.
     *
     * @param token the token
     * @return Person[] - the persons family in a list
     */
    Person[] getFamily(Authtoken token){
        return null;
    }

    public Person[] findPersonsByUser(String username) throws DataAccessException{
        ArrayList<Person> persons = new ArrayList<>();

        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE associatedUsername = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()){
                Person person = new Person(rs.getString("PersonId"), rs.getString("AssociatedUsername"),
                                           rs.getString("FirstName"), rs.getString("LastName"),
                                           rs.getString("Gender"), rs.getString("FatherId"), rs.getString("MotherId"),
                                           rs.getString("SpouseId"));
                persons.add(person);
            }

            Person[] arr = new Person[persons.size()];
            arr = persons.toArray(arr);

            return arr;
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding people in the database");
        }
    }

    public void clearPersonsByUser(String username) throws DataAccessException{
        String sql = "DELETE FROM Person WHERE associatedUsername=\'" + username + "\';";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the user table");
        }
    }

    /**
     * Load people specified into the people table.
     *
     * @param people the people to be loaded into the Person table
     */
    public void load(Person[] people) throws DataAccessException{
        clear();

        for(Person person : people){
            insert(person);
        }
    }

    /**
     * Clear.
     */
    public void clear() throws DataAccessException{
        String sql = "DELETE FROM Person";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the user table");
        }
    }

    public Person find(String personId) throws DataAccessException{
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE PersonId = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, personId);
            rs = stmt.executeQuery();
            if(rs.next()){
                person = new Person(rs.getString("PersonId"), rs.getString("AssociatedUsername"),
                                    rs.getString("FirstName"), rs.getString("LastName"),
                                    rs.getString("Gender"), rs.getString("FatherId"), rs.getString("MotherId"),
                                    rs.getString("SpouseId"));
                return person;
            } else{
                return null;
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an person in the database");
        }
    }

}

class FemaleNames{
    String[] data;
}

class MaleNames{
    String[] data;
}

class LastNames{
    String[] data;
}
