package Act01;

import Act01.controller.UserController;
import Act01.model.Respuestas;
import Act01.model.User;
import Act01.repository.UserRepository;
import Act01.service.UserService;
import Act01.utils.UserUtils;

import java.util.Scanner;

/**
 * The type Main.
 */
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);

        User currentUser = null;
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("--- Bienvenido al Sistema de Gestión de Usuarios ---");
            System.out.print("Ingresa tu correo electrónico: ");
            String gmail = sc.nextLine();

            System.out.print("Ingresa tu contraseña: ");
            String password = sc.nextLine();

            User user = userService.getUser(gmail);
            if (user != null && UserUtils.checkPassword(password, user.getPassword())) {
                loggedIn = true;
                currentUser = user;
                System.out.println("¡Inicio de sesión exitoso! Bienvenido, " + user.getGmail());
            } else {
                System.out.println("Correo o contraseña incorrectos. Intenta de nuevo.");
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\n --- Menú Principal ---");
            System.out.println("1. Consultar un usuario");
            if (currentUser.isAdmin()) {
                System.out.println("2. Registrar un nuevo usuario");
                System.out.println("3. Modificar un usuario");
                System.out.println("4. Eliminar un usuario");
            }
            System.out.println("5. Salir");

            System.out.print("Selecciona una opción: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    getUserCase(userController);
                    break;

                case 2:
                    if (currentUser.isAdmin()) {
                        registerUserCase(userController, currentUser);
                    } else {
                        System.out.println("No tienes permisos para realizar esta operación.");
                    }
                    break;

                case 3:
                    if (currentUser.isAdmin()) {
                        modifyUserCase(userService, userController, currentUser);
                    } else {
                        System.out.println("No tienes permisos para realizar esta operación.");
                    }
                    break;

                case 4:
                    if (currentUser.isAdmin()) {
                        deleteUserCase(userController, currentUser);
                    } else {
                        System.out.println("No tienes permisos para realizar esta operación.");
                    }
                    break;

                case 5:
                    System.out.println("Gracias por usar la aplicacion. ¡Hasta luego!");
                    exit = true;
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        sc.close();
    }

    private static void getUserCase(UserController userController) {

        System.out.print("Ingresa el correo del usuario a consultar: ");
        String searchEmail = sc.nextLine();
        Respuestas response = userController.getUser(searchEmail);
        handleResponse(response);
    }

    private static void registerUserCase(UserController userController, User currentUser) {

        System.out.print("Ingresa el correo del nuevo usuario: ");
        String newEmail = sc.nextLine();
        System.out.print("Ingresa la contraseña del nuevo usuario: ");
        String newPassword = sc.nextLine();
        System.out.print("¿El usuario será administrador? (true/false): ");
        boolean isAdmin = sc.nextBoolean();
        sc.nextLine();
        Respuestas response = userController.insertUser(newEmail, newPassword, isAdmin, currentUser);
        handleResponse(response);
    }

    private static void modifyUserCase(UserService userService, UserController userController, User currentUser) {

        System.out.print("Ingresa el correo del usuario a modificar: ");
        String modifyEmail = sc.nextLine();
        User userToUpdate = userService.getUser(modifyEmail);
        if (userToUpdate != null) {
            System.out.print("Ingresa el nuevo correo: ");
            String updatedEmail = sc.nextLine();
            System.out.print("Ingresa la nueva contraseña: ");
            String updatedPassword = sc.nextLine();
            System.out.print("¿El usuario seguirá siendo administrador? (true/false): ");
            boolean updatedAdmin = sc.nextBoolean();
            sc.nextLine();
            userToUpdate.setGmail(updatedEmail);
            userToUpdate.setPassword(UserUtils.encryptPassword(updatedPassword));
            userToUpdate.setAdmin(updatedAdmin);
            Respuestas response = userController.updateUser(currentUser, userToUpdate);
            handleResponse(response);
        }
    }

    private static void deleteUserCase(UserController userController, User currentUser) {

        System.out.print("Ingresa el correo del usuario a eliminar: ");
        String deleteEmail = sc.nextLine();
        Respuestas response = userController.deleteUser(currentUser, deleteEmail);
        handleResponse(response);
    }

    private static void handleResponse(Respuestas response) {
        if (response.getStatus() == 200 || response.getStatus() == 201) {
            System.out.println(response.getMessage());
        } else {
            System.err.println(response.getMessage() + "\nIntentelo de nuevo");
        }
    }
}
