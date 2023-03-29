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

public class EventServiceTest{

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
    public void eventPass() throws DataAccessException{
        EventsResponse events = eventsService.events(registerResponse.getAuthtoken());

        EventResponse res = eventService.event(registerResponse.getAuthtoken(), events.getData()[0].getEventID());

        assertTrue(res.isSuccess());
        assertEquals(res.getEventID(), events.getData()[0].getEventID());

    }

    @Test
    public void eventFail() throws DataAccessException{
        EventResponse res = eventService.event(registerResponse.getAuthtoken(), "saddBoyfakeID");

        assertFalse(res.isSuccess());
        assertTrue(res.getMessage().contains("Error"));
    }

}