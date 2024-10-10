package Act01.model;

public class User {

    private int Id;
    private String gmail;
    private String password;
    private boolean isAdmin;

    public User(int id, String gmail, String password, boolean isAdmin) {
        this.Id = id;
        this.gmail = gmail;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return Id;
    }

    public String getGmail() {
        return gmail;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
