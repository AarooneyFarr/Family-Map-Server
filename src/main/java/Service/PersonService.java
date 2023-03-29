package Service;

import dao.AuthtokenDAO;
import dao.Database;
import dao.PersonDAO;
import dao.UserDAO;
import model.Authtoken;
import model.Person;
import model.User;
import result.LoginResponse;
import result.PersonResponse;

/**
 * The Person service class.
 */
public class PersonService{
    /**
     * Person service method.
     *
     * @return the person response class
     */
    public PersonResponse person(String authToken, String id){
        Database db = new Database();

        try{
            db.openConnection();

            PersonDAO pDAO = new PersonDAO(db.getConnection());
            UserDAO uDAO = new UserDAO(db.getConnection());
            AuthtokenDAO aDAO = new AuthtokenDAO(db.getConnection());

            Authtoken token = aDAO.find(authToken);
            Person person = pDAO.find(id);
            if(person.getAssociatedUsername().equals(token.getUsername())){
                PersonResponse response = new PersonResponse(person.getAssociatedUsername(), person.getPersonID(),
                                                             person.getFirstName(), person.getLastName(),
                                                             person.getGender(), person.getFatherID(),
                                                             person.getMotherID(), person.getSpouseID(), true,
                                                             null);
                db.closeConnection(true);

                return response;
            }

            PersonResponse response = new PersonResponse(null, null, null, null, null, null, null, null, false,
                                                         "Error: no match");

            db.closeConnection(true);
            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            PersonResponse response = new PersonResponse(null, null, null, null, null, null, null, null, false,
                                                         "Error: " + ex.getMessage());
            return response;
        }
    }
}
