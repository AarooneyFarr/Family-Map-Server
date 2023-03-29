package service;

import Service.ClearService;
import Service.LoadService;
import Service.RegisterService;
import dao.DataAccessException;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import request.RegisterRequest;
import result.GenericResponse;
import result.RegisterResponse;

import static org.junit.jupiter.api.Assertions.assertNull;

public class LoadServiceTest{

    private LoadService fillService;

    private LoadRequest loadRequest;

    //Setup for authtoken and user crap
    private RegisterService registerService;

    private RegisterRequest registerRequest;

    private RegisterResponse registerResponse;

    private User bestUser;

    private User bestUser2;

    private User bestUser3;

    private Person bestPerson;

    private Person bestPerson2;

    private Person bestPerson3;

    private Event bestEvent;

    private Event bestEvent2;

    private Event bestEvent3;

    @BeforeEach
    public void setUp() throws DataAccessException{

        registerService = new RegisterService();
        registerRequest = new RegisterRequest("Test", "pass", "email", "tod", "jones", "m");

        bestUser = new User("dummyboi", "pass123", "thebori@gmail.com", "dummy", "boi", "m", "sackbroiID");
        bestUser2 = new User("dummyboir", "pass123", "theborri@gmail.com", "dummy", "boi", "m", "sackboriID");
        bestUser3 = new User("dummybori", "pass123", "theborti@gmail.com", "dummy", "boi", "m", "sackboirID");

        bestPerson = new Person("dummyboi", "dummmy", "theboi@gmail.com", "dummy", "m", "boidaddy", "sackboiID",
                                "spoose");
        bestPerson2 = new Person("dummyboid", "dummmy", "theboi@gmail.com", "dummy", "m", "boidaddy", "sackboiID",
                                 "spoose");
        bestPerson3 = new Person("dummyboic", "dummmyc", "theboi@gmail.com", "dummy", "m", "boidaddy", "sackboiID",
                                 "spoose");

        bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
                              35.9f, 140.1f, "Japan", "Ushiku",
                              "Biking_Around", 2016);
        bestEvent2 = new Event("Biking_123Am", "Gale", "dummyboi1",
                               35.9f, 140.1f, "Japan", "Ushiku",
                               "birth", 2016);
        bestEvent3 = new Event("Biking_123Aj", "tom", "dummyboi2",
                               35.9f, 140.1f, "Japan", "Ushiku",
                               "birth", 2016);

        registerResponse = registerService.register(registerRequest);

        fillService = new LoadService();

    }

    @AfterEach
    public void tearDown(){
        new ClearService().clear();
    }

    @Test
    public void fillPass() throws DataAccessException{

        User[] users = new User[]{bestUser, bestUser2, bestUser3};
        Event[] events = new Event[]{bestEvent, bestEvent2, bestEvent3};
        Person[] persons = new Person[]{bestPerson, bestPerson2, bestPerson3};

        loadRequest = new LoadRequest(users, persons, events);

        GenericResponse res = fillService.load(loadRequest);

        assertTrue(res.isSuccess());
        assertTrue(res.getMessage().contains("3"));

    }

    @Test
    public void fillFail() throws DataAccessException{
        User[] users = new User[]{bestUser, bestUser, bestUser3};
        Event[] events = new Event[]{bestEvent, bestEvent2, bestEvent3};
        Person[] persons = new Person[]{bestPerson, bestPerson2, bestPerson3};

        loadRequest = new LoadRequest(users, persons, events);

        GenericResponse res = fillService.load(loadRequest);

        assertFalse(res.isSuccess());
        assertTrue(res.getMessage().contains("Error"));
    }

}
