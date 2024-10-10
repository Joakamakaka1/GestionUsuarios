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
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        if (!UserUtils.isValidEmail(gmail)) {
            throw new IllegalArgumentException("El formato del correo es inválido");
        }

        String encryptedPassword = UserUtils.encryptPassword(password);

        return ur.insertUser(gmail, encryptedPassword, isAdmin);
    }

    public User getUser(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            return null;
        }
        return ur.getUser(gmail);
    }

    public User updateUser(User u) {
        if (u == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }

        if (!UserUtils.isValidEmail(u.getGmail())) {
            throw new IllegalArgumentException("El formato del correo es inválido");
        }

        return ur.updateUser(u);
    }

    public boolean deleteUser(String gmail) {
        if (gmail == null || gmail.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        return ur.deleteUser(gmail);
    }
}
