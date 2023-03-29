package Service;

import dao.*;
import model.User;
import result.GenericResponse;
import result.RegisterResponse;

/**
 * The Clear service class.
 */
public class ClearService{
    /**
     * Clear service class method.
     *
     * @return the generic response class
     */
    public GenericResponse clear(){
        Database db = new Database();

        try{
            db.openConnection();

            new UserDAO(db.getConnection()).clear();
            new AuthtokenDAO(db.getConnection()).clear();
            new PersonDAO(db.getConnection()).clear();
            new EventDAO(db.getConnection()).clear();

            db.closeConnection(true);

            GenericResponse response = new GenericResponse(true, "Clear succeeded");

            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            GenericResponse response = new GenericResponse(false, "Error: " + ex.getMessage());

            return response;
        }
    }
}
