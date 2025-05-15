package com.example.drinkup.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0007\b\t\n\u000bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0005\f\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/example/drinkup/navigation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "History", "Home", "Leaderboard", "Login", "Settings", "Lcom/example/drinkup/navigation/Screen$History;", "Lcom/example/drinkup/navigation/Screen$Home;", "Lcom/example/drinkup/navigation/Screen$Leaderboard;", "Lcom/example/drinkup/navigation/Screen$Login;", "Lcom/example/drinkup/navigation/Screen$Settings;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String route = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/drinkup/navigation/Screen$History;", "Lcom/example/drinkup/navigation/Screen;", "()V", "app_debug"})
    public static final class History extends com.example.drinkup.navigation.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.drinkup.navigation.Screen.History INSTANCE = null;
        
        private History() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/drinkup/navigation/Screen$Home;", "Lcom/example/drinkup/navigation/Screen;", "()V", "app_debug"})
    public static final class Home extends com.example.drinkup.navigation.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.drinkup.navigation.Screen.Home INSTANCE = null;
        
        private Home() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/drinkup/navigation/Screen$Leaderboard;", "Lcom/example/drinkup/navigation/Screen;", "()V", "app_debug"})
    public static final class Leaderboard extends com.example.drinkup.navigation.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.drinkup.navigation.Screen.Leaderboard INSTANCE = null;
        
        private Leaderboard() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/drinkup/navigation/Screen$Login;", "Lcom/example/drinkup/navigation/Screen;", "()V", "app_debug"})
    public static final class Login extends com.example.drinkup.navigation.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.drinkup.navigation.Screen.Login INSTANCE = null;
        
        private Login() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/drinkup/navigation/Screen$Settings;", "Lcom/example/drinkup/navigation/Screen;", "()V", "app_debug"})
    public static final class Settings extends com.example.drinkup.navigation.Screen {
        @org.jetbrains.annotations.NotNull
        public static final com.example.drinkup.navigation.Screen.Settings INSTANCE = null;
        
        private Settings() {
        }
    }
}