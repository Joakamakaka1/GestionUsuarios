package Act01.repository;

import Act01.model.User;

/**
 * The interface User repository api.
 */
public interface UserRepositoryAPI {

    /**
     * Insert user user.
     *
     * @param gmail    the gmail
     * @param password the password
     * @param isAdmin  the is admin
     * @return the user
     */
    User insertUser(String gmail, String password, boolean isAdmin);

    /**
     * Gets user.
     *
     * @param gmail the gmail
     * @return the user
     */
    User getUser(String gmail);

    /**
     * Update user user.
     *
     * @param u the u
     * @return the user
     */
    User updateUser(User u);

    /**
     * Delete user boolean.
     *
     * @param gmail the gmail
     * @return the boolean
     */
    boolean deleteUser(String gmail);
}
