package Service;

import dao.*;
import model.Authtoken;
import model.Event;
import model.Person;
import result.EventsResponse;
import result.PersonsResponse;

/**
 * The Events service class.
 */
public class EventsService{
    /**
     * Events service method.
     *
     * @return the events response class
     */
    public EventsResponse events(String authToken){
        Database db = new Database();

        try{
            db.openConnection();

            EventDAO eDAO = new EventDAO(db.getConnection());

            AuthtokenDAO aDAO = new AuthtokenDAO(db.getConnection());

            Authtoken token = aDAO.find(authToken);
            Event[] events = eDAO.findEventsByUser(token.getUsername());

            EventsResponse response = new EventsResponse(events, true, null);
            db.closeConnection(true);

            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            EventsResponse response = new EventsResponse(null, false,
                                                         "Error: " + ex.getMessage());
            return response;
        }
    }
}
