package service;

import Service.ClearService;
import Service.PersonService;
import Service.PersonsService;
import Service.RegisterService;
import dao.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.RegisterRequest;
import result.PersonResponse;
import result.PersonsResponse;
import result.GenericResponse;
import result.RegisterResponse;

import static org.junit.jupiter.api.Assertions.assertNull;

public class PersonsServiceTest{

    private PersonService personService;

    private PersonsService personsService;

    //Setup for authtoken and user crap
    private RegisterService registerService;

    private RegisterRequest registerRequest;

    private RegisterResponse registerResponse;

    @BeforeEach
    public void setUp() throws DataAccessException{

        registerService = new RegisterService();
        registerRequest = new RegisterRequest("Test", "pass", "email", "tod", "jones", "m");

        registerResponse = registerService.register(registerRequest);

        personService = new PersonService();

        personsService = new PersonsService();

    }

    @AfterEach
    public void tearDown(){
        new ClearService().clear();
    }

    @Test
    public void personPass() throws DataAccessException{
        PersonsResponse persons = personsService.persons(registerResponse.getAuthtoken());

        PersonResponse res = personService.person(registerResponse.getAuthtoken(), persons.getData()[0].getPersonID());

        assertTrue(res.isSuccess());
        assertEquals(res.getPersonID(), persons.getData()[0].getPersonID());

    }

    @Test
    public void personFail() throws DataAccessException{
        PersonResponse res = personService.person(registerResponse.getAuthtoken(), "saddBoyfakeID");

        assertFalse(res.isSuccess());
        assertTrue(res.getMessage().contains("Error"));
    }

}
