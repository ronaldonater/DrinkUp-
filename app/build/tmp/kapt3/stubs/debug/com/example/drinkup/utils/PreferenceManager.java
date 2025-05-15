package com.example.drinkup.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0016\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\fJ\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/drinkup/utils/PreferenceManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "clearUserInfo", "", "getUserId", "", "getUsername", "", "isLoggedIn", "", "isNotificationsEnabled", "saveUserInfo", "userId", "username", "setNotificationsEnabled", "enabled", "Companion", "app_debug"})
public final class PreferenceManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREF_NAME = "drinkup_preferences";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_USER_ID = "user_id";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_USERNAME = "username";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "PreferenceManager";
    @org.jetbrains.annotations.NotNull
    public static final com.example.drinkup.utils.PreferenceManager.Companion Companion = null;
    
    public PreferenceManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final void saveUserInfo(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String username) {
    }
    
    public final int getUserId() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getUsername() {
        return null;
    }
    
    public final void clearUserInfo() {
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    public final void setNotificationsEnabled(boolean enabled) {
    }
    
    public final boolean isNotificationsEnabled() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/drinkup/utils/PreferenceManager$Companion;", "", "()V", "KEY_USERNAME", "", "KEY_USER_ID", "PREF_NAME", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}