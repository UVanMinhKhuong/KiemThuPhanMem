package com.example.myapplication.utility;


import org.mindrot.jbcrypt.BCrypt;

/**
 * This class provides all methods to decrypt characters.
 *
 * @author Pham Thanh Sang - 1954112073
 */
public class BCryptUtil {

    /**
     * Decode password
     *
     * @param password The password of the client input.
     * @return The pass after decode password.
     */
    public static String decodePassword(String password) {
        String passwordDecoded = BCrypt.hashpw(password, BCrypt.gensalt());
        return passwordDecoded;
    }

    /**
     * Verify the password is correct.
     *
     * @param passwordUser  The password in database
     * @param passwordInput The password from client input.
     * @return True if the password is correct.
     */
    public static boolean verifyPassword(String passwordUser, String passwordInput) {
        return BCrypt.checkpw(passwordInput, passwordUser);
    }
}
