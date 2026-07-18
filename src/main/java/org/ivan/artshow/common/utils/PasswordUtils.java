package org.ivan.artshow.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Password Encryption Utility
 *
 * <p>Provides secure password encryption and verification using BCrypt algorithm.
 * BCrypt is a password hashing function designed for secure password storage.</p>
 *
 * <p>Key Features:</p>
 * <ul>
 *   <li>Uses BCrypt algorithm with salt</li>
 *   <li>Each encryption generates unique hash (even for same password)</li>
 *   <li>Computationally expensive to prevent brute-force attacks</li>
 *   <li>Thread-safe implementation</li>
 * </ul>
 *
 * <p>Security Benefits:</p>
 * <ul>
 *   <li>One-way encryption (cannot be decrypted)</li>
 *   <li>Automatic salt generation</li>
 *   <li>Resistant to rainbow table attacks</li>
 *   <li>Adjustable computational cost (strength parameter)</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class PasswordUtils {

    /**
     * BCrypt encoder instance with default strength (10)
     *
     * <p>Strength parameter determines computational cost:
     * - Higher value = more secure but slower
     * - Default 10 provides good balance
     * - Range: 4-31</p>
     */
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * Encrypt plain text password
     *
     * <p>Uses BCrypt to generate a secure hash of the password.
     * Each call generates a unique hash due to automatic salt generation,
     * even for the same input password.</p>
     *
     * <p>Example:</p>
     * <pre>
     * String plainPassword = "myPassword123";
     * String hashedPassword = PasswordUtils.encrypt(plainPassword);
     * // Result: $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy
     * </pre>
     *
     * @param plainPassword Plain text password
     * @return BCrypt hashed password (60 characters)
     * @throws IllegalArgumentException if plainPassword is null
     */
    public static String encrypt(String plainPassword) {
        if (plainPassword == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        return ENCODER.encode(plainPassword);
    }

    /**
     * Verify if plain text password matches encrypted password
     *
     * <p>Compares the plain password with the BCrypt hash.
     * This is a timing-safe comparison that prevents timing attacks.</p>
     *
     * <p>Example:</p>
     * <pre>
     * String plainPassword = "myPassword123";
     * String storedHash = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy";
     * boolean isValid = PasswordUtils.matches(plainPassword, storedHash);
     * // Returns: true if password matches, false otherwise
     * </pre>
     *
     * @param plainPassword Plain text password entered by user
     * @param encryptedPassword BCrypt hashed password from database
     * @return true if password matches, false otherwise
     * @throws IllegalArgumentException if any parameter is null
     */
    public static boolean matches(String plainPassword, String encryptedPassword) {
        if (plainPassword == null || encryptedPassword == null) {
            throw new IllegalArgumentException("Password and encrypted password cannot be null");
        }
        return ENCODER.matches(plainPassword, encryptedPassword);
    }

    /**
     * Check if a string is a valid BCrypt hash
     *
     * <p>BCrypt hashes always start with "$2a$", "$2b$", or "$2y$"
     * and are 60 characters long.</p>
     *
     * @param password String to check
     * @return true if it's a valid BCrypt hash format
     */
    public static boolean isBCryptHash(String password) {
        if (password == null || password.length() != 60) {
            return false;
        }
        return password.startsWith("$2a$") ||
               password.startsWith("$2b$") ||
               password.startsWith("$2y$");
    }
}
