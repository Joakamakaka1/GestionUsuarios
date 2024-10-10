package Act01.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type User utils.
 */
public class UserUtils {
    /**
     * Encrypt password string.
     *
     * @param password the password
     * @return the string
     */
    public static String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("¡Algoritmo SHA3-256 no encontrado!", e);
        }
    }

    /**
     * Check password boolean.
     *
     * @param plainPassword     the plain password
     * @param encryptedPassword the encrypted password
     * @return the boolean
     */
    public static boolean checkPassword(String plainPassword, String encryptedPassword) {
        String encryptedPlainPassword = encryptPassword(plainPassword);
        return encryptedPlainPassword.equals(encryptedPassword);
    }

    /**
     * Is valid email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isValidEmail(String email) {
        String regexEmail = "^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(regexEmail);
    }
}
