package dao;

import model.Authtoken;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthtokenDAOTest{

    private Database db;

    private Authtoken bestAuthtoken;

    private User dummyboi;

    private AuthtokenDAO aDAO;

    @BeforeEach
    public void setUp() throws DataAccessException{

        db = new Database();

        bestAuthtoken = new Authtoken("jammmeiei235--r", "asdklajfladkjfio");

        dummyboi = new User("adsf", "aasdsa", "asaasd", "asdasfg", "asfads", "m", "asfadgd");

        Connection conn = db.getConnection();

        aDAO = new AuthtokenDAO(conn);

        aDAO.clear();
    }

    @AfterEach
    public void tearDown(){

        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException{

        String tokenString = aDAO.createAuthtoken(dummyboi);

        Authtoken compareTest = aDAO.find(tokenString);

        Authtoken token = new Authtoken(tokenString, dummyboi.getUsername());

        assertNotNull(compareTest);

        assertEquals(token, compareTest);
    }

    @Test
    public void insertPassTwo() throws DataAccessException{

        String tokenString = aDAO.createAuthtoken(dummyboi);
        String tokenString2 = aDAO.createAuthtoken(dummyboi);

        Authtoken compareTest = aDAO.find(tokenString);
        Authtoken compareTest2 = aDAO.find(tokenString2);

        Authtoken token = new Authtoken(tokenString, dummyboi.getUsername());
        Authtoken token2 = new Authtoken(tokenString2, dummyboi.getUsername());

        assertNotNull(compareTest);
        assertNotNull(compareTest2);

        assertEquals(token, compareTest);
        assertEquals(token2, compareTest2);
    }

    @Test
    public void retrievePass() throws DataAccessException{

        String authtoken = aDAO.createAuthtoken(dummyboi);

        Authtoken token = new Authtoken(authtoken, dummyboi.getUsername());

        Authtoken compareTest = aDAO.find(authtoken);

        assertNotNull(compareTest);

        assertEquals(token, compareTest);
    }

    @Test
    public void retrieveFail() throws DataAccessException{

        assertNull(aDAO.find("dummy"));
    }

    @Test
    public void clearPass() throws DataAccessException{
        aDAO.createAuthtoken(dummyboi);
        aDAO.clear();
        assertNull(aDAO.find(bestAuthtoken.getAuthtoken()));
    }

    @Test
    public void clearPass2() throws DataAccessException{
        aDAO.createAuthtoken(dummyboi);
        aDAO.createAuthtoken(dummyboi);
        aDAO.createAuthtoken(dummyboi);
        aDAO.clear();
        assertNull(aDAO.find(bestAuthtoken.getAuthtoken()));
    }

}
