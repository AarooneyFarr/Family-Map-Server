package service;

import Service.ClearService;
import Service.EventService;
import Service.EventsService;
import Service.RegisterService;
import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.EventResponse;
import result.EventsResponse;
import result.GenericResponse;
import result.RegisterResponse;

import static org.junit.jupiter.api.Assertions.assertNull;

public class EventsServiceTest{

    private EventService eventService;

    private EventsService eventsService;

    //Setup for authtoken and user crap
    private RegisterService registerService;

    private RegisterRequest registerRequest;

    private RegisterResponse registerResponse;

    @BeforeEach
    public void setUp() throws DataAccessException{

        registerService = new RegisterService();
        registerRequest = new RegisterRequest("Test", "pass", "email", "tod", "jones", "m");

        registerResponse = registerService.register(registerRequest);

        eventService = new EventService();

        eventsService = new EventsService();

    }

    @AfterEach
    public void tearDown(){
        new ClearService().clear();
    }

    @Test
    public void eventsPass() throws DataAccessException{
        EventsResponse events = eventsService.events(registerResponse.getAuthtoken());

        assertTrue(events.isSuccess());
        assertEquals(92, events.getData().length);

    }

    @Test
    public void eventsFail() throws DataAccessException{
        EventsResponse events = eventsService.events("FakeToken");

        assertFalse(events.isSuccess());
        assertTrue(events.getMessage().contains("Error"));
    }

}
