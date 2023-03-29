package request;

import model.Event;
import model.Person;
import model.User;

/**
 * The Load request class.
 */
public class LoadRequest{
    /**
     * The User[] of users to be added
     * <p>
     * Type User[]
     */
    private User[] users;

    /**
     * The Person[] of persons to be added
     * <p>
     * Type Person[]
     */
    private Person[] persons;

    /**
     * The Event[] of events to be added
     * <p>
     * Type Event[]
     */
    private Event[] events;

    /**
     * Instantiates a new Load request.
     *
     * @param users   the users
     * @param persons the persons
     * @param events  the events
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events){
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    /**
     * Get users user [ ].
     *
     * @return the user [ ]
     */
    public User[] getUsers(){
        return users;
    }

    /**
     * Set users.
     *
     * @param users the users
     */
    public void setUsers(User[] users){
        this.users = users;
    }

    /**
     * Get persons person [ ].
     *
     * @return the person [ ]
     */
    public Person[] getPersons(){
        return persons;
    }

    /**
     * Set persons.
     *
     * @param persons the persons
     */
    public void setPersons(Person[] persons){
        this.persons = persons;
    }

    /**
     * Get events event [ ].
     *
     * @return the event [ ]
     */
    public Event[] getEvents(){
        return events;
    }

    /**
     * Set events.
     *
     * @param events the events
     */
    public void setEvents(Event[] events){
        this.events = events;
    }
}
