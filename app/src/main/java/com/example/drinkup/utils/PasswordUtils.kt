package com.example.drinkup.utils

import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.SecureRandom

object PasswordUtils {
    private const val SALT_LENGTH = 16
    private const val TAG = "PasswordUtils"

    fun hashPassword(password: String): String {
        try {
            Log.d(TAG, "Hashing password")

            // Generate a random salt
            val salt = ByteArray(SALT_LENGTH)
            SecureRandom().nextBytes(salt)

            // Hash the password with the salt
            val md = MessageDigest.getInstance("SHA-256")
            md.update(salt)
            val hashedPassword = md.digest(password.toByteArray())

            // Combine salt and hash
            val combined = ByteArray(salt.size + hashedPassword.size)
            System.arraycopy(salt, 0, combined, 0, salt.size)
            System.arraycopy(hashedPassword, 0, combined, salt.size, hashedPassword.size)

            // Encodes as Base64
            val result = Base64.encodeToString(combined, Base64.NO_WRAP)
            Log.d(TAG, "Password hashed successfully")
            return result
        } catch (e: Exception) {
            Log.e(TAG, "Error hashing password", e)
            throw Exception("Failed to hash password: ${e.message}")
        }
    }

    fun checkPassword(password: String, storedHash: String): Boolean {
        try {
            Log.d(TAG, "Checking password")

            // Decode the hash
            val combined = Base64.decode(storedHash, Base64.NO_WRAP)

            // Extract the salt
            val salt = ByteArray(SALT_LENGTH)
            System.arraycopy(combined, 0, salt, 0, salt.size)

            // Hash with extracted salt
            val md = MessageDigest.getInstance("SHA-256")
            md.update(salt)
            val hashedPassword = md.digest(password.toByteArray())


            var result = 0
            for (i in hashedPassword.indices) {
                result = result or (hashedPassword[i].toInt() xor combined[salt.size + i].toInt())
            }

            val matches = result == 0
            Log.d(TAG, "Password check result: $matches")
            return matches
        } catch (e: Exception) {
            Log.e(TAG, "Error checking password", e)
            throw Exception("Failed to verify password: ${e.message}")
        }
    }
}
