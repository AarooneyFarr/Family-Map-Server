package result;

import model.Event;

/**
 * The Events response class.
 */
public class EventsResponse{
    /**
     * The Data.
     */
    private Event[] data;

    /**
     * The Success.
     */
    private boolean success;

    /**
     * The Message.
     */
    private String message;

    /**
     * Instantiates a new Events response.
     *
     * @param data    the data
     * @param success the success
     * @param message the message
     */
    public EventsResponse(Event[] data, boolean success, String message){
        this.data = data;
        this.success = success;
        this.message = message;
    }

    /**
     * Get data event [ ].
     *
     * @return the event [ ]
     */
    public Event[] getData(){
        return data;
    }

    /**
     * Set data.
     *
     * @param data the data
     */
    public void setData(Event[] data){
        this.data = data;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess(){
        return success;
    }

    /**
     * Set success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success){
        this.success = success;
    }

    /**
     * Get message string.
     *
     * @return the string
     */
    public String getMessage(){
        return message;
    }

    /**
     * Set message.
     *
     * @param message the message
     */
    public void setMessage(String message){
        this.message = message;
    }
}
