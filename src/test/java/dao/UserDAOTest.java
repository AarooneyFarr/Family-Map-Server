package dao;

import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest{

    private Database db;

    private User bestUser;

    private User bestUser2;

    private User bestUser3;

    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException{

        db = new Database();

        bestUser = new User("dummyboi", "pass123", "thebori@gmail.com", "dummy", "boi", "m", "sackbroiID");
        bestUser2 = new User("dummyboir", "pass123", "theborri@gmail.com", "dummy", "boi", "m", "sackboriID");
        bestUser3 = new User("dummybori", "pass123", "theborti@gmail.com", "dummy", "boi", "m", "sackboirID");

        Connection conn = db.getConnection();

        uDao = new UserDAO(conn);

        uDao.clear();
    }

    @AfterEach
    public void tearDown(){
        // Here we close the connection to the database file, so it can be opened again later.
        // We will set commit to false because we do not want to save the changes to the database
        // between test cases.
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException{

        uDao.insert(bestUser);
        // Let's use a find method to get the event that we just put in back out.
        User compareTest = uDao.find(bestUser.getUsername());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the Event class.
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException{
        // Let's do this test again, but this time lets try to make it fail.
        // If we call the method the first time the event will be inserted successfully.
        uDao.insert(bestUser);

        // However, our sql table is set up so that the column "eventID" must be unique, so trying to insert
        // the same event again will cause the insert method to throw an exception, and we can verify this
        // behavior by using the assertThrows assertion as shown below.

        // Note: This call uses a lambda function. A lambda function runs the code that comes after
        // the "()->", and the assertThrows assertion expects the code that ran to throw an
        // instance of the class in the first parameter, which in this case is a DataAccessException.
        assertThrows(DataAccessException.class, () -> uDao.insert(bestUser));
    }

    @Test
    public void createPass() throws DataAccessException{

        uDao.clear();

        uDao.createUser(bestUser.getUsername(), bestUser.getPassword(), bestUser.getEmail(), bestUser.getFirstName(),
                        bestUser.getLastName(), bestUser.getGender());

        User compareTest = uDao.find(bestUser.getUsername());

        assertNotNull(compareTest);

        assertEquals(bestUser.getUsername(), compareTest.getUsername());
    }

    @Test
    public void createFail() throws DataAccessException{

        uDao.createUser(bestUser.getUsername(), bestUser.getPassword(), bestUser.getEmail(), bestUser.getFirstName(),
                        bestUser.getLastName(), bestUser.getGender());

        assertThrows(DataAccessException.class,
                     () -> uDao.createUser(bestUser.getUsername(), bestUser.getPassword(), bestUser.getEmail(),
                                           bestUser.getFirstName(),
                                           bestUser.getLastName(), bestUser.getGender()));
    }

    @Test
    public void retrievePass() throws DataAccessException{

        uDao.insert(bestUser);

        User compareTest = uDao.find(bestUser.getUsername());

        assertNotNull(compareTest);

        assertEquals(bestUser, compareTest);
    }

    @Test
    public void retrieveFail() throws DataAccessException{

        assertNull(uDao.find("dummy"));
    }

    @Test
    public void clearPass() throws DataAccessException{
        uDao.insert(bestUser);
        uDao.clear();
        assertNull(uDao.find(bestUser.getUsername()));
    }

    @Test
    public void clearPassTwo() throws DataAccessException{
        User[] users = new User[]{bestUser, bestUser2, bestUser3};

        uDao.load(users);
        uDao.clear();

        assertNull(uDao.find(bestUser.getUsername()));
        assertNull(uDao.find(bestUser2.getUsername()));
        assertNull(uDao.find(bestUser3.getUsername()));
    }

    @Test
    public void validatePass() throws DataAccessException{

        uDao.clear();

        uDao.createUser(bestUser.getUsername(), bestUser.getPassword(), bestUser.getEmail(), bestUser.getFirstName(),
                        bestUser.getLastName(), bestUser.getGender());

        assertTrue(uDao.validate(bestUser.getUsername(), bestUser.getPassword()));
    }

    @Test
    public void validateFail() throws DataAccessException{

        uDao.clear();

        uDao.createUser(bestUser.getUsername(), bestUser.getPassword(), bestUser.getEmail(), bestUser.getFirstName(),
                        bestUser.getLastName(), bestUser.getGender());

        assertFalse(uDao.validate(bestUser.getUsername(), "hahaha"));
    }

    @Test
    public void loadPass() throws DataAccessException{
        User[] users = new User[]{bestUser, bestUser2, bestUser3};

        uDao.load(users);

        User compareTest = uDao.find(bestUser.getUsername());
        User compareTest2 = uDao.find(bestUser2.getUsername());
        User compareTest3 = uDao.find(bestUser3.getUsername());

        assertNotNull(compareTest);
        assertNotNull(compareTest2);
        assertNotNull(compareTest3);

        assertEquals(bestUser, compareTest);
        assertEquals(bestUser2, compareTest2);
        assertEquals(bestUser3, compareTest3);
    }

    @Test
    public void loadFail() throws DataAccessException{
        User[] users = new User[]{bestUser, bestUser, bestUser};

        assertThrows(DataAccessException.class, () -> uDao.load(users));
    }

    @Test
    public void fillPass() throws DataAccessException{
        uDao.insert(bestUser);
        PersonDAO pDAO = new PersonDAO(db.getConnection());
        pDAO.clear();

        uDao.fill(bestUser.getUsername(), 2);

        User compareTest = uDao.find(bestUser.getUsername());

        Person[] persons = pDAO.findPersonsByUser(bestUser.getUsername());

        assertEquals(persons.length, 7);

    }

    @Test
    public void fillFail() throws DataAccessException{

        assertThrows(NullPointerException.class, () -> uDao.fill(bestUser.getUsername(), -2));

    }
}
