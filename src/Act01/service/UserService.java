package Act01.service;

import Act01.model.User;
import Act01.repository.UserRepository;
import Act01.utils.UserUtils;

public class UserService {

    private final UserRepository ur;

    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public User insertUser(String gmail, String password, boolean isAdmin) {
        if (gmail == null || gmail.trim().isEmpty()) {
            return null;
        }

        if (!UserUtils.isValidEmail(gmail)) {
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        String encryptedPassword = UserUtils.encryptPassword(password);

        return ur.insertUser(gmail, encryptedPassword, isAdmin);
    }

    public User getUser(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            return null;
        }
        return ur.getUser(gmail.trim());
    }

    public User updateUser(User u) {
        if (u == null) {
            return null;
        }

        if (!UserUtils.isValidEmail(u.getGmail())) {
            return null;
        }

        return ur.updateUser(u);
    }

    public boolean deleteUser(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            return false;
        }
        return ur.deleteUser(gmail);
    }
}
