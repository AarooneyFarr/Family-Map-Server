package Service;

import dao.AuthtokenDAO;
import dao.Database;
import dao.PersonDAO;
import dao.UserDAO;
import model.Authtoken;
import model.Person;
import result.PersonResponse;
import result.PersonsResponse;

/**
 * The Persons service class.
 */
public class PersonsService{
    /**
     * Persons service class.
     *
     * @return the persons response class
     */
    public PersonsResponse persons(String authToken){
        Database db = new Database();

        try{
            db.openConnection();

            PersonDAO pDAO = new PersonDAO(db.getConnection());
            UserDAO uDAO = new UserDAO(db.getConnection());
            AuthtokenDAO aDAO = new AuthtokenDAO(db.getConnection());

            Authtoken token = aDAO.find(authToken);
            Person[] persons = pDAO.findPersonsByUser(token.getUsername());

            PersonsResponse response = new PersonsResponse(persons, true, null);
            db.closeConnection(true);

            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            PersonsResponse response = new PersonsResponse(null, false,
                                                           "Error: " + ex.getMessage());
            return response;
        }
    }
}
