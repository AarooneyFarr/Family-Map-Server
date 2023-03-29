package Service;

import dao.*;
import model.Authtoken;
import model.Event;
import model.Person;
import result.EventResponse;
import result.PersonResponse;

/**
 * The Event service class.
 */
public class EventService{
    /**
     * Event service method.
     *
     * @return the event response class
     */
    public EventResponse event(String authToken, String id){
        Database db = new Database();

        try{
            db.openConnection();

            EventDAO eDAO = new EventDAO(db.getConnection());
            AuthtokenDAO aDAO = new AuthtokenDAO(db.getConnection());

            Authtoken token = aDAO.find(authToken);
            Event event = eDAO.find(id);
            if(event.getAssociatedUsername().equals(token.getUsername())){
                EventResponse response = new EventResponse(event.getAssociatedUsername(), event.getEventID(),
                                                           event.getPersonID(), event.getLatitude(),
                                                           event.getLongitude(), event.getCountry(), event.getCity(),
                                                           event.getEventType(), event.getYear(), true, null);
                db.closeConnection(true);

                return response;
            }

            EventResponse response = new EventResponse(null, null, null, 0, 0, null, null, null, 0, false,
                                                       "Error: no match");

            db.closeConnection(true);
            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            EventResponse response = new EventResponse(null, null, null, 0, 0, null, null, null, 0, false,
                                                       "Error: " + ex.getMessage());
            return response;
        }
    }
}
