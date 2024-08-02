package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    // Generate a random salt
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 16 bytes salt
        random.nextBytes(salt);
        return salt;
    }

    // Hash the password with the given salt
    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt); // Add salt to the digest
            byte[] hashedPassword = md.digest(password.getBytes());

            // Combine salt and hashed password
            byte[] saltedHashedPassword = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, saltedHashedPassword, 0, salt.length);
            System.arraycopy(hashedPassword, 0, saltedHashedPassword, salt.length, hashedPassword.length);

            // Encode combined salt and hashed password as Base64 for storage
            return Base64.getEncoder().encodeToString(saltedHashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Verify a password against a stored hash
    public static boolean verifyPassword(String password, String storedHash) {
        // Decode the stored hash from Base64
        byte[] decodedHash = Base64.getDecoder().decode(storedHash);

        // Extract the salt from the stored hash (first 16 bytes)
        byte[] salt = new byte[16];
        System.arraycopy(decodedHash, 0, salt, 0, 16);

        // Extract the hashed password (remaining bytes)
        byte[] storedPasswordHash = new byte[decodedHash.length - 16];
        System.arraycopy(decodedHash, 16, storedPasswordHash, 0, storedPasswordHash.length);

        // Hash the provided password with the extracted salt
        String hashedPassword = hashPassword(password, salt);

        // Compare the newly hashed password with the stored hash
        return storedHash.equals(hashedPassword);
    }

    public static void main(String[] args) {
        String password = "123qaz";
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);

        System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
        System.out.println("Hashed Password: " + hashedPassword);

        // Verify the password
        boolean isPasswordCorrect = verifyPassword(password, hashedPassword);
        System.out.println("Password Verification: " + isPasswordCorrect);
    }
}


