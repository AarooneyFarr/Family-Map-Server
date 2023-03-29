package Service;

import dao.Database;
import dao.EventDAO;
import dao.PersonDAO;
import dao.UserDAO;
import request.LoadRequest;
import result.GenericResponse;

/**
 * The Load service class.
 */
public class LoadService{
    /**
     * Load service method.
     *
     * @param request the LoadRequest class
     * @return the generic response class
     */
    public GenericResponse load(LoadRequest request){
        Database db = new Database();

        try{
            db.openConnection();

            UserDAO uDAO = new UserDAO(db.getConnection());
            EventDAO eDAO = new EventDAO(db.getConnection());
            PersonDAO pDAO = new PersonDAO(db.getConnection());

            uDAO.load(request.getUsers());
            pDAO.load(request.getPersons());
            eDAO.load(request.getEvents());

            db.closeConnection(true);

            GenericResponse response = new GenericResponse(true,
                                                           "Successfully added " + request.getUsers().length + " users, " + request.getPersons().length + " persons, and " + request.getEvents().length + " events to the database");

            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            GenericResponse response = new GenericResponse(false, "Error: " + ex.getMessage());

            return response;
        }
    }
}
