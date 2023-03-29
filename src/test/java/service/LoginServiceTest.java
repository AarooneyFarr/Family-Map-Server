package service;

import Service.ClearService;
import Service.LoginService;
import Service.RegisterService;
import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.RegisterRequest;
import result.LoginResponse;
import result.GenericResponse;
import result.RegisterResponse;

import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginServiceTest{

    private LoginService loginService;

    private LoginRequest loginRequest;

    //Setup for authtoken and user crap
    private RegisterService registerService;

    private RegisterRequest registerRequest;

    private RegisterResponse registerResponse;

    @BeforeEach
    public void setUp() throws DataAccessException{

        registerService = new RegisterService();
        registerRequest = new RegisterRequest("Test", "pass", "email", "tod", "jones", "m");

        registerResponse = registerService.register(registerRequest);

        loginService = new LoginService();

    }

    @AfterEach
    public void tearDown(){
        new ClearService().clear();
    }

    @Test
    public void loginPass() throws DataAccessException{

        loginRequest = new LoginRequest("Test", "pass");

        LoginResponse res = loginService.login(loginRequest);

        assertTrue(res.isSuccess());
        assertEquals(res.getUsername(), "Test");

    }

    @Test
    public void loginFail() throws DataAccessException{
        loginRequest = new LoginRequest("Test", "password");
        LoginResponse res = loginService.login(loginRequest);

        assertFalse(res.isSuccess());
        assertTrue(res.getMessage().contains("error"));
    }

}
