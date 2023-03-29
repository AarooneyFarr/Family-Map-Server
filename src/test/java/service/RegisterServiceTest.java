package service;

import Service.ClearService;
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

public class RegisterServiceTest{

    //Setup for authtoken and user crap
    private RegisterService registerService;

    private RegisterRequest registerRequest;

    private RegisterResponse registerResponse;

    @BeforeEach
    public void setUp() throws DataAccessException{

        registerService = new RegisterService();
        registerRequest = new RegisterRequest("Test", "pass", "email", "tod", "jones", "m");

    }

    @AfterEach
    public void tearDown(){
        new ClearService().clear();
    }

    @Test
    public void registerPass() throws DataAccessException{
        registerResponse = registerService.register(registerRequest);

        assertTrue(registerResponse.isSuccess());
        assertEquals(registerResponse.getUsername(), "Test");

    }

    @Test
    public void registerFail() throws DataAccessException{
        registerResponse = registerService.register(registerRequest);
        RegisterResponse res2 = registerService.register(registerRequest);

        assertTrue(registerResponse.isSuccess());
        assertEquals(registerResponse.getUsername(), "Test");

        assertFalse(res2.isSuccess());
        assertTrue(res2.getMessage().contains("Error"));
    }

}
