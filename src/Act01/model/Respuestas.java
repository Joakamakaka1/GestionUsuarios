package Act01.model;

/**
 * The type Respuestas.
 */
public class Respuestas {

    private int status;
    private String message;
    private Object data;

    /**
     * Instantiates a new Respuestas.
     *
     * @param status  the status
     * @param message the message
     * @param data    the data
     */
    public Respuestas(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

}
