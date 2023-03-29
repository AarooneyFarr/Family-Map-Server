package dao;

import model.Event;
import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class EventDAOTest{
    private Database db;

    private Event bestEvent;

    private Event bestEvent2;

    private Event bestEvent3;

    private Person personOne;

    private Person personTwo;

    private EventDAO eDao;

    @BeforeEach
    public void setUp() throws DataAccessException{
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data
        bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
                              35.9f, 140.1f, "Japan", "Ushiku",
                              "Biking_Around", 2016);

        bestEvent2 = new Event("Biking_123Am", "Gale", "dummyboi1",
                               35.9f, 140.1f, "Japan", "Ushiku",
                               "birth", 2016);

        bestEvent3 = new Event("Biking_123Aj", "tom", "dummyboi2",
                               35.9f, 140.1f, "Japan", "Ushiku",
                               "birth", 2016);

        personOne = new Person("dummyboi2", "dummmy", "theboi@gmail.com", "dummy", "m", "dummyboi1", "dummyboi1",
                               "dummyboi1");

        personTwo = new Person("dummyboi1", "dummmy", "theboi@gmail.com", "dummy", "m", "boidaddy", "sackboiID",
                               "dummyboi2");

        // Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Then we pass that connection to the EventDAO, so it can access the database.
        eDao = new EventDAO(conn);
        //Let's clear the database as well so any lingering data doesn't affect our tests
        eDao.clear();
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
        // Start by inserting an event into the database.
        eDao.insert(bestEvent);
        // Let's use a find method to get the event that we just put in back out.
        Event compareTest = eDao.find(bestEvent.getEventID());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the Event class.
        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException{
        // Let's do this test again, but this time lets try to make it fail.
        // If we call the method the first time the event will be inserted successfully.
        eDao.insert(bestEvent);

        // However, our sql table is set up so that the column "eventID" must be unique, so trying to insert
        // the same event again will cause the insert method to throw an exception, and we can verify this
        // behavior by using the assertThrows assertion as shown below.

        // Note: This call uses a lambda function. A lambda function runs the code that comes after
        // the "()->", and the assertThrows assertion expects the code that ran to throw an
        // instance of the class in the first parameter, which in this case is a DataAccessException.
        assertThrows(DataAccessException.class, () -> eDao.insert(bestEvent));
    }

    @Test
    public void findPass() throws DataAccessException{

        eDao.insert(bestEvent);

        Event compareTest = eDao.find(bestEvent.getEventID());

        assertNotNull(compareTest);

        assertEquals(bestEvent, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException{

        assertNull(eDao.find("borkedID"));
    }

    @Test
    public void loadPass() throws DataAccessException{
        Event[] events = new Event[]{bestEvent, bestEvent2, bestEvent3};

        eDao.load(events);

        Event compareTest = eDao.find(bestEvent.getEventID());
        Event compareTest2 = eDao.find(bestEvent2.getEventID());
        Event compareTest3 = eDao.find(bestEvent3.getEventID());

        assertNotNull(compareTest);
        assertNotNull(compareTest2);
        assertNotNull(compareTest3);

        assertEquals(bestEvent, compareTest);
        assertEquals(bestEvent2, compareTest2);
        assertEquals(bestEvent3, compareTest3);
    }

    @Test
    public void loadFail() throws DataAccessException{
        Event[] events = new Event[]{bestEvent, bestEvent, bestEvent};

        assertThrows(DataAccessException.class, () -> eDao.load(events));
    }

    @Test
    public void clearPass() throws DataAccessException{
        Event[] events = new Event[]{bestEvent, bestEvent2, bestEvent3};

        eDao.load(events);
        eDao.clear();

        assertNull(eDao.find(bestEvent.getEventID()));
        assertNull(eDao.find(bestEvent2.getEventID()));
        assertNull(eDao.find(bestEvent3.getEventID()));
    }

    @Test
    public void clearPass2() throws DataAccessException{

        eDao.insert(bestEvent);
        eDao.clear();

        assertNull(eDao.find(bestEvent.getEventID()));

    }

    @Test
    public void findEventsByUserPass() throws DataAccessException{
        Event[] events = new Event[]{bestEvent, bestEvent2, bestEvent3};

        eDao.load(events);

        Event[] compareTest = eDao.findEventsByUser(bestEvent.getAssociatedUsername());

        assertNotNull(compareTest[0]);
        assertNotNull(compareTest[1]);

        assertEquals(bestEvent, compareTest[0]);
        assertEquals(bestEvent2, compareTest[1]);

    }

    @Test
    public void findEventsByUserFail() throws DataAccessException{
        Event[] events = new Event[]{bestEvent, bestEvent2, bestEvent3};

        eDao.load(events);

        Event[] compareTest = eDao.findEventsByUser("borkyName");

        assertEquals(0, compareTest.length);
    }

    @Test
    public void createBirthAndDeathPass() throws DataAccessException{
        eDao.clear();

        //Add birth event for child
        eDao.insert(bestEvent3);

        eDao.createBirthAndDeathEvents(personOne, personTwo, "dummy");

        Event[] personTwoEvents = eDao.findEventsByUser("dummy");

        assertEquals(2, personTwoEvents.length);
        assertTrue((personTwoEvents[0].getEventType().equals("birth") || personTwoEvents[0].getEventType()
                                                                                           .equals("death")) && (personTwoEvents[1]
                .getEventType().equals("birth") || personTwoEvents[1].getEventType().equals("death")));
    }

    @Test
    public void createBirthAndDeathFail() throws DataAccessException{
        eDao.clear();

        assertThrows(NullPointerException.class, () -> eDao.createBirthAndDeathEvents(personOne, personTwo, "dummy"));
    }

    @Test
    public void createBirthAndDeathPassManual() throws DataAccessException{
        eDao.clear();

        eDao.createBirthAndDeathEvents(2015, personTwo, "dummy");

        Event[] personTwoEvents = eDao.findEventsByUser("dummy");

        assertEquals(2, personTwoEvents.length);
        assertTrue((personTwoEvents[0].getEventType().equals("birth") || personTwoEvents[0].getEventType()
                                                                                           .equals("death")) && (personTwoEvents[1]
                .getEventType().equals("birth") || personTwoEvents[1].getEventType().equals("death")));
    }

    @Test
    public void createBirthAndDeathFailManual() throws DataAccessException{
        eDao.clear();

        assertThrows(NullPointerException.class, () -> eDao.createBirthAndDeathEvents(-12, personTwo, "dummy"));
    }

    @Test
    public void createMarriagePass() throws DataAccessException{
        eDao.clear();

        eDao.insert(bestEvent3);
        eDao.insert(bestEvent2);

        eDao.createMarriageEvents(personOne, personTwo, "dummy");

        Event[] personTwoEvents = eDao.findEventsByUser("dummy");

        assertEquals(2, personTwoEvents.length);
        assertEquals(personTwoEvents[0].getEventType(), personTwoEvents[1].getEventType());
        assertEquals(personTwoEvents[0].getCity(), personTwoEvents[1].getCity());
    }

    @Test
    public void createMarriageFail() throws DataAccessException{
        eDao.clear();

        assertThrows(NullPointerException.class, () -> eDao.createMarriageEvents(personOne, personTwo, "dummy"));
    }

    @Test
    public void clearByUserPass() throws DataAccessException{
        Event[] events = new Event[]{bestEvent, bestEvent2, bestEvent3};

        eDao.load(events);
        eDao.clearEventsByUser("Gale");

        assertNull(eDao.find(bestEvent.getEventID()));
        assertNull(eDao.find(bestEvent2.getEventID()));

    }

    @Test
    public void clearByUserFail() throws DataAccessException{
        Event[] events = new Event[]{bestEvent, bestEvent2, bestEvent3};

        eDao.load(events);
        eDao.clearEventsByUser("Gale");
        
        assertNotNull(eDao.find(bestEvent3.getEventID()));
    }
}
