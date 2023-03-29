package service;

import Service.ClearService;
import Service.FillService;
import Service.RegisterService;
import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.GenericResponse;
import result.RegisterResponse;

import static org.junit.jupiter.api.Assertions.assertNull;

public class FillServiceTest{

    private FillService fillService;

    //Setup for authtoken and user crap
    private RegisterService registerService;

    private RegisterRequest registerRequest;

    private RegisterResponse registerResponse;

    @BeforeEach
    public void setUp() throws DataAccessException{

        registerService = new RegisterService();
        registerRequest = new RegisterRequest("Test", "pass", "email", "tod", "jones", "m");

        registerResponse = registerService.register(registerRequest);

        fillService = new FillService();

    }

    @AfterEach
    public void tearDown(){
        new ClearService().clear();
    }

    @Test
    public void fillPass() throws DataAccessException{

        GenericResponse res = fillService.fill("Test", 2);

        assertTrue(res.isSuccess());
        assertTrue(res.getMessage().contains("7"));
        assertTrue(res.getMessage().contains("19"));

    }

    @Test
    public void fillFail() throws DataAccessException{
        GenericResponse res = fillService.fill("Testy", 5);

        assertFalse(res.isSuccess());
        assertTrue(res.getMessage().contains("Error"));
    }

}