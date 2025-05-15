package com.example.drinkup.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/example/drinkup/utils/PasswordUtils;", "", "()V", "SALT_LENGTH", "", "TAG", "", "checkPassword", "", "password", "storedHash", "hashPassword", "app_debug"})
public final class PasswordUtils {
    private static final int SALT_LENGTH = 16;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "PasswordUtils";
    @org.jetbrains.annotations.NotNull
    public static final com.example.drinkup.utils.PasswordUtils INSTANCE = null;
    
    private PasswordUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String hashPassword(@org.jetbrains.annotations.NotNull
    java.lang.String password) {
        return null;
    }
    
    public final boolean checkPassword(@org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    java.lang.String storedHash) {
        return false;
    }
}