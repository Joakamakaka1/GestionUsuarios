package Act01.repository;

import Act01.model.User;
import Act01.utils.UserUtils;

import java.util.ArrayList;

/**
 * The type User repository.
 */
public class UserRepository implements UserRepositoryAPI {

    /**
     * The Users db.
     */
    ArrayList<User> usersDB;

    /**
     * Instantiates a new User repository.
     */
    public UserRepository() {
        usersDB = new ArrayList<>();
        usersDB.add(new User(1, "prueba@gmail.com", UserUtils.encryptPassword("123456"), true));
        usersDB.add(new User(1, "prueba2@gmail.com", UserUtils.encryptPassword("123456"), false));
        usersDB.add(new User(1, "prueba3@gmail.com", UserUtils.encryptPassword("123456"), false));
    }

    @Override
    public User insertUser(String gmail, String password, boolean isAdmin) {
        if (usersDB.stream().anyMatch(u -> u.getGmail().equals(gmail))) {
            return null;
        }
        User newUser = new User(usersDB.size() + 1, gmail, password, isAdmin);
        usersDB.add(newUser);
        return newUser;
    }

    @Override
    public User getUser(String gmail) {
        return usersDB.stream().filter(u -> u.getGmail().equalsIgnoreCase(gmail)).findFirst().orElse(null);
    }

    @Override
    public User updateUser(User updatedUser) {
        User userToUpdate = usersDB.stream().filter(user -> user.getGmail().equals(updatedUser.getGmail())).findFirst().orElse(null);
        if (userToUpdate != null) {
            userToUpdate.setGmail(updatedUser.getGmail());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setAdmin(updatedUser.isAdmin());
            return userToUpdate;
        }
        return null;
    }

    @Override
    public boolean deleteUser(String gmail) {
        return usersDB.removeIf(u -> u.getGmail().equals(gmail));
    }
}
