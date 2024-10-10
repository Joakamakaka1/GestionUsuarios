package Act01.controller;

import Act01.model.Respuestas;
import Act01.model.User;
import Act01.service.UserService;

/**
 * The type User controller.
 */
public class UserController {
    private final UserService us;

    /**
     * Instantiates a new User controller.
     *
     * @param us the us
     */
    public UserController(UserService us) {
        this.us = us;
    }

    /**
     * Insert user respuestas.
     *
     * @param gmail       the gmail
     * @param password    the password
     * @param isAdmin     the is admin
     * @param currentUser the current user
     * @return the respuestas
     */
    public Respuestas insertUser(String gmail, String password, boolean isAdmin, User currentUser) {
        try {
            if (!currentUser.isAdmin()) {
                return new Respuestas(403, "No tienes permisos de administrador", null);
            }

            if (us.getUser(gmail) != null) {
                return new Respuestas(409, "El usuario ya existe. Prueba de nuevo", null);
            }

            return us.insertUser(gmail, password, isAdmin) != null ?
                    new Respuestas(201, "Usuario insertado correctamente", null) :
                    new Respuestas(400, "Error al insertar el usuario. Algunas credenciales no son correctas", null);
        } catch (Exception e) {
            return new Respuestas(500, "Error interno en el servidor", null);
        }
    }

    /**
     * Gets user.
     *
     * @param gmail the gmail
     * @return the user
     */
    public Respuestas getUser(String gmail) {
        try {
            if (us.getUser(gmail) == null) {
                return new Respuestas(404, "El usuario no existe", null);
            }
            return new Respuestas(200, "Usuario encontrado", us.getUser(gmail));
        } catch (Exception e) {
            return new Respuestas(500, "Error interno en el servidor", null);
        }
    }

    /**
     * Update user respuestas.
     *
     * @param currentUser the current user
     * @param updatedUser the updated user
     * @return the respuestas
     */
    public Respuestas updateUser(User currentUser, User updatedUser) {
        try {
            if (!currentUser.isAdmin()) {
                return new Respuestas(403, "No tienes permisos de administrador", null);
            }

            if (us.getUser(updatedUser.getGmail()) == null) {
                return new Respuestas(404, "El usuario no existe", null);
            }

            return us.updateUser(updatedUser) != null ?
                    new Respuestas(200, "Usuario actualizado correctamente", null) :
                    new Respuestas(403, "Prohibido: No se puede actualizar", null);
        } catch (Exception e) {
            return new Respuestas(500, "Error interno en el servidor", null);
        }
    }

    /**
     * Delete user respuestas.
     *
     * @param currentUser the current user
     * @param gmail       the gmail
     * @return the respuestas
     */
    public Respuestas deleteUser(User currentUser, String gmail) {
        try {
            if (!currentUser.isAdmin()) {
                return new Respuestas(403, "No tienes permisos de administrador", null);
            }

            if (us.getUser(gmail) == null) {
                return new Respuestas(404, "El usuario no existe", null);
            }

            return us.deleteUser(gmail) ?
                    new Respuestas(200, "Usuario eliminado con éxito", null) :
                    new Respuestas(400, "Error al eliminarel usuario", null);
        } catch (Exception e) {
            return new Respuestas(500, "Error interno en el servidor", null);
        }
    }
}
