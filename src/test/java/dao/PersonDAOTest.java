package dao;

import model.Event;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PersonDAOTest{

    private Database db;

    private Person bestPerson;

    private Person bestPerson2;

    private Person bestPerson3;

    private PersonDAO pDao;

    @BeforeEach
    public void setUp() throws DataAccessException{

        db = new Database();

        bestPerson = new Person("dummyboi", "dummmy", "theboi@gmail.com", "dummy", "m", "boidaddy", "sackboiID",
                                "spoose");

        bestPerson2 = new Person("dummyboid", "dummmy", "theboi@gmail.com", "dummy", "m", "boidaddy", "sackboiID",
                                 "spoose");

        bestPerson3 = new Person("dummyboic", "dummmyc", "theboi@gmail.com", "dummy", "m", "boidaddy", "sackboiID",
                                 "spoose");

        Connection conn = db.getConnection();

        pDao = new PersonDAO(conn);

        pDao.clear();
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

        pDao.insert(bestPerson);
        // Let's use a find method to get the event that we just put in back out.
        Person compareTest = pDao.find(bestPerson.getPersonID());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the Event class.
        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException{
        // Let's do this test again, but this time lets try to make it fail.
        // If we call the method the first time the event will be inserted successfully.
        pDao.insert(bestPerson);

        // However, our sql table is set up so that the column "eventID" must be unique, so trying to insert
        // the same event again will cause the insert method to throw an exception, and we can verify this
        // behavior by using the assertThrows assertion as shown below.

        // Note: This call uses a lambda function. A lambda function runs the code that comes after
        // the "()->", and the assertThrows assertion expects the code that ran to throw an
        // instance of the class in the first parameter, which in this case is a DataAccessException.
        assertThrows(DataAccessException.class, () -> pDao.insert(bestPerson));
    }

    @Test
    public void retrievePass() throws DataAccessException{

        pDao.insert(bestPerson);

        Person compareTest = pDao.find(bestPerson.getPersonID());

        assertNotNull(compareTest);

        assertEquals(bestPerson, compareTest);
    }

    @Test
    public void retrieveFail() throws DataAccessException{

        assertNull(pDao.find("dummy"));
    }

    @Test
    public void clearPass() throws DataAccessException{
        pDao.insert(bestPerson);
        pDao.clear();
        assertNull(pDao.find(bestPerson.getPersonID()));
    }

    @Test
    public void clearPassTwo() throws DataAccessException{
        Person[] persons = new Person[]{bestPerson, bestPerson2, bestPerson3};

        pDao.load(persons);
        pDao.clear();

        assertNull(pDao.find(bestPerson.getPersonID()));
        assertNull(pDao.find(bestPerson2.getPersonID()));
        assertNull(pDao.find(bestPerson3.getPersonID()));
    }

    @Test
    public void genRandomPersonPass() throws DataAccessException{

        Person person = pDao.genRandomPerson(true);

        assertEquals(person.getGender(), "m");
    }

    @Test
    public void genRandomPersonFail() throws DataAccessException{

        Person person = pDao.genRandomPerson(false);

        assertNotEquals(person.getGender(), "m");
    }

    @Test
    public void findPersonsByUserPass() throws DataAccessException{
        pDao.clear();
        pDao.insert(bestPerson);
        pDao.insert(bestPerson2);

        Person[] persons = pDao.findPersonsByUser("dummmy");

        assertEquals(2, persons.length);
    }

    @Test
    public void findPersonsByUserFail() throws DataAccessException{

        Person[] persons = pDao.findPersonsByUser("dummydfhe");
        assertEquals(0, persons.length);
    }

    @Test
    public void loadPass() throws DataAccessException{
        Person[] persons = new Person[]{bestPerson, bestPerson2, bestPerson3};

        pDao.load(persons);

        Person compareTest = pDao.find(bestPerson.getPersonID());
        Person compareTest2 = pDao.find(bestPerson2.getPersonID());
        Person compareTest3 = pDao.find(bestPerson3.getPersonID());

        assertNotNull(compareTest);
        assertNotNull(compareTest2);
        assertNotNull(compareTest3);

        assertEquals(bestPerson, compareTest);
        assertEquals(bestPerson2, compareTest2);
        assertEquals(bestPerson3, compareTest3);
    }

    @Test
    public void loadFail() throws DataAccessException{
        Person[] persons = new Person[]{bestPerson, bestPerson, bestPerson};

        assertThrows(DataAccessException.class, () -> pDao.load(persons));
    }

}
