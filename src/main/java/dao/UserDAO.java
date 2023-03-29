package dao;

import model.Event;
import model.Person;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The User data access object.
 */
public class UserDAO{
    /**
     * The database connection
     * <p>
     * Type Connection
     */
    private final Connection conn;

    /**
     * Instantiates a new User dao.
     *
     * @param conn the connection
     */
    public UserDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Create user.
     *
     * @param user the user
     */
    public User createUser(String username, String password, String email, String firstName, String lastName,
                           String gender) throws DataAccessException{
        String personID = UUID.randomUUID().toString();
        User user = new User(username, password, email, firstName, lastName, gender, personID);
        this.insert(user);

        return user;
    }

    public void insert(User user) throws DataAccessException{
        String sql = "INSERT INTO User ( Username, Password, Email, FirstName, " +
                "LastName, Gender, PersonId) VALUES(?,?,?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a user into the database");
        }
    }

    /**
     * Validate username and password for user.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean validate(String username, String password) throws DataAccessException{
        User user;
        ResultSet rs;
        String sql = "SELECT * FROM User WHERE Username = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){

                if(rs.getString("Password").equals(password)){
                    return true;
                }
                return false;
            } else{
                return false;
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("User information not valid");
        }
    }

    /**
     * Get user by id.
     *
     * @param id the id
     * @return the user
     */
    User getUserByID(String id){
        return null;
    }

    /**
     * Load new data into User table.
     *
     * @param users the users
     */
    public void load(User[] users) throws DataAccessException{
        clear();

        for(User user : users){
            insert(user);
        }
    }

    /**
     * Clear User table.
     */
    public void clear() throws DataAccessException{
        String sql = "DELETE FROM User";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the user table");
        }
    }

    /**
     * Fill users family with 4 generations.
     *
     * @return the number of generations made (4)
     */
    public int fill(String username, int generations) throws DataAccessException{

        PersonDAO pDAO = new PersonDAO(this.conn);
        User currentUser = find(username);

        //Gen random gender here doesn't matter because we override it
        Person userPerson = pDAO.genRandomPerson(true);
        userPerson.setPersonID(currentUser.getPersonID());
        userPerson.setAssociatedUsername(username);
        userPerson.setFirstName(currentUser.getFirstName());
        userPerson.setLastName(currentUser.getLastName());
        userPerson.setGender(currentUser.getGender());

        pDAO.createPerson(userPerson);
        EventDAO eDAO = new EventDAO(conn);
        eDAO.createBirthAndDeathEvents(2023, userPerson, username);

        //Create person for individual user
        if(generations > 0){
            fill_helper(generations, pDAO, userPerson, username);
        }

        return 2;
    }

    public User find(String username) throws DataAccessException{
        User user;
        ResultSet rs;
        String sql = "SELECT * FROM User WHERE Username = ?;";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                user = new User(rs.getString("Username"), rs.getString("Password"),
                                rs.getString("Email"), rs.getString("FirstName"), rs.getString("LastName"),
                                rs.getString("Gender"), rs.getString("PersonId"));
                return user;
            } else{
                return null;
            }
        } catch(SQLException e){
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an user in the database");
        }
    }

    private int fill_helper(int totalGenerations, PersonDAO pDAO, Person child, String baseUsername) throws
                                                                                                     DataAccessException{
        int currentGen = 1;
        ArrayList<Person> currentGeneration = new ArrayList<>();
        currentGeneration.add(child);

        while(currentGen < totalGenerations){
            ArrayList<Person> nextGeneration = addParents(pDAO, baseUsername, currentGeneration, false);

            currentGen++;
            currentGeneration = nextGeneration;
        }

        addParents(pDAO, baseUsername, currentGeneration, true);

        return -1;
    }

    private ArrayList<Person> addParents(PersonDAO pDAO, String baseUsername,
                                         ArrayList<Person> currentGeneration, boolean isLast) throws
                                                                                              DataAccessException{
        ArrayList<Person> nextGen = new ArrayList<>();
        EventDAO eDAO = new EventDAO(conn);

        for(Person person : currentGeneration){
            Person father = addFather(pDAO, person, baseUsername, isLast);

            Person mother = addMother(pDAO, person, baseUsername, isLast);

            eDAO.createMarriageEvents(father, mother, baseUsername);

            nextGen.add(mother);
            nextGen.add(father);
        }

        return nextGen;
    }

    private Person addFather(PersonDAO pDAO, Person child, String baseUsername, boolean isLast) throws
                                                                                                DataAccessException{
        Person father = pDAO.genRandomPerson(true);
        EventDAO eDAO = new EventDAO(conn);

        // Set data for the person object that is important
        father.setAssociatedUsername(baseUsername);
        father.setLastName(child.getLastName());
        father.setPersonID(child.getFatherID());
        father.setSpouseID(child.getMotherID());

        if(isLast){
            father.setFatherID(null);
            father.setMotherID(null);
        }

        pDAO.createPerson(father);
        eDAO.createBirthAndDeathEvents(child, father, baseUsername);

        return father;
    }

    private Person addMother(PersonDAO pDAO, Person child, String baseUsername, boolean isLast) throws
                                                                                                DataAccessException{
        Person mother = pDAO.genRandomPerson(false);

        mother.setAssociatedUsername(baseUsername);
        mother.setPersonID(child.getMotherID());
        mother.setSpouseID(child.getFatherID());

        if(isLast){
            mother.setFatherID(null);
            mother.setMotherID(null);
        }

        pDAO.createPerson(mother);

        EventDAO eDAO = new EventDAO(conn);
        eDAO.createBirthAndDeathEvents(child, mother, baseUsername);

        return mother;
    }

}


