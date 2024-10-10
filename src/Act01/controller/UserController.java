package Act01.controller;

import Act01.model.Respuestas;
import Act01.model.User;
import Act01.service.UserService;

public class UserController {
    private final UserService us;

    public UserController(UserService us) {
        this.us = us;
    }

    public Respuestas insertUser(String gmail, String password, boolean isAdmin) {
        try {
            if (us.getUser(gmail) != null) {
                return new Respuestas(409, "El usuario ya existe", null);
            }
            return us.insertUser(gmail, password, isAdmin) != null ?
                    new Respuestas(201, "Usuario insertado correctamente", null) :
                    new Respuestas(400, "Error al insertar el usuario", null);
        } catch (Exception e) {
            return new Respuestas(500, "Error interno en el servidor", null);
        }
    }

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
                    new Respuestas(400, "Bad request", null);
        } catch (Exception e) {
            return new Respuestas(500, "Error interno en el servidor", null);
        }
    }
}
