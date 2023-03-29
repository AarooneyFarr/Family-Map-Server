package Service;

import dao.*;
import model.User;
import result.GenericResponse;
import result.RegisterResponse;

/**
 * The Fill service class.
 */
public class FillService{
    /**
     * Fill service method.
     *
     * @return the generic response class
     */
    public GenericResponse fill(String username, Integer generations){
        Database db = new Database();

        try{
            db.openConnection();

            UserDAO uDAO = new UserDAO(db.getConnection());
            new PersonDAO(db.getConnection()).clearPersonsByUser(username);
            new EventDAO(db.getConnection()).clearEventsByUser(username);

            uDAO.fill(username, generations);

            int sum = 0;
            for(int i = 1; i <= generations; i++){
                sum += Math.pow(2, i);
            }

            db.closeConnection(true);

            GenericResponse response = new GenericResponse(true,
                                                           "Successfully added " + (sum + 1) + " persons and " + ((sum * 3) + 1) + " events to the database");

            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            GenericResponse response = new GenericResponse(false, "Error: " + ex.getMessage());

            return response;
        }

    }
}
