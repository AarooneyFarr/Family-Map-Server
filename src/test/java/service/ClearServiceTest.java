package service;

import Service.ClearService;
import dao.AuthtokenDAO;
import dao.DataAccessException;
import dao.Database;
import model.Authtoken;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.GenericResponse;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ClearServiceTest{

    private Database db;

    private ClearService clearService;

    private Authtoken bestAuthtoken;

    private User dummyboi;

    private AuthtokenDAO aDAO;

    @BeforeEach
    public void setUp() throws DataAccessException{

        clearService = new ClearService();

    }

    @AfterEach
    public void tearDown(){

    }

    @Test
    public void clearPass() throws DataAccessException{
        GenericResponse res = clearService.clear();

        assertTrue(res.isSuccess());
        assertEquals(res.getMessage(), "Clear succeeded");

    }

}
