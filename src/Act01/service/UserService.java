package Act01.service;

import Act01.model.User;
import Act01.repository.UserRepository;
import Act01.utils.UserUtils;

/**
 * The type User service.
 */
public class UserService {

    private final UserRepository ur;

    /**
     * Instantiates a new User service.
     *
     * @param ur the ur
     */
    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    /**
     * Insert user user.
     *
     * @param gmail    the gmail
     * @param password the password
     * @param isAdmin  the is admin
     * @return the user
     */
    public User insertUser(String gmail, String password, boolean isAdmin) {
        if (gmail == null || gmail.trim().isEmpty()) {
            return null;
        }

        if (!UserUtils.isValidEmail(gmail)) {
            return null;
        }

        if (password == null || password.trim().isEmpty() || password.length() < 6 || password.length() > 20) {
            return null;
        }

        String encryptedPassword = UserUtils.encryptPassword(password);

        return ur.insertUser(gmail, encryptedPassword, isAdmin);
    }

    /**
     * Gets user.
     *
     * @param gmail the gmail
     * @return the user
     */
    public User getUser(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            return null;
        }
        return ur.getUser(gmail.trim());
    }

    /**
     * Update user user.
     *
     * @param u the u
     * @return the user
     */
    public User updateUser(User u) {
        if (u == null) {
            return null;
        }

        if (!UserUtils.isValidEmail(u.getGmail())) {
            return null;
        }

        return ur.updateUser(u);
    }

    /**
     * Delete user boolean.
     *
     * @param gmail the gmail
     * @return the boolean
     */
    public boolean deleteUser(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            return false;
        }
        return ur.deleteUser(gmail);
    }
}
