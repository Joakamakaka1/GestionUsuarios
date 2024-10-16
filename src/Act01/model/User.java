package Act01.model;

/**
 * The type User.
 */
public class User {

    private int Id;
    private String gmail;
    private String password;
    private boolean isAdmin;

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param gmail    the gmail
     * @param password the password
     * @param isAdmin  the is admin
     */
    public User(int id, String gmail, String password, boolean isAdmin) {
        this.Id = id;
        this.gmail = gmail;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return Id;
    }

    /**
     * Gets gmail.
     *
     * @return the gmail
     */
    public String getGmail() {
        return gmail;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * Sets gmail.
     *
     * @param gmail the gmail
     */
    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets admin.
     *
     * @param isAdmin the is admin
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
