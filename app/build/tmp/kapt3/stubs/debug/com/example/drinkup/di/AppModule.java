package com.example.drinkup.di;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0006J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/drinkup/di/AppModule;", "", "()V", "authRepository", "Lcom/example/drinkup/repositories/AuthRepository;", "database", "Lcom/example/drinkup/data/AppDatabase;", "friendRepository", "Lcom/example/drinkup/repositories/FriendRepository;", "intakeRepository", "Lcom/example/drinkup/repositories/IntakeRepository;", "preferencesManager", "Lcom/example/drinkup/utils/PreferenceManager;", "initialize", "", "context", "Landroid/content/Context;", "provideAuthViewModel", "Lcom/example/drinkup/viewmodels/AuthViewModel;", "provideDatabase", "provideFriendViewModel", "Lcom/example/drinkup/viewmodels/FriendViewModel;", "provideIntakeRepository", "provideIntakeViewModel", "Lcom/example/drinkup/viewmodels/IntakeViewModel;", "providePreferencesManager", "app_debug"})
public final class AppModule {
    private static com.example.drinkup.data.AppDatabase database;
    private static com.example.drinkup.utils.PreferenceManager preferencesManager;
    private static com.example.drinkup.repositories.AuthRepository authRepository;
    private static com.example.drinkup.repositories.IntakeRepository intakeRepository;
    private static com.example.drinkup.repositories.FriendRepository friendRepository;
    @org.jetbrains.annotations.NotNull
    public static final com.example.drinkup.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    public final void initialize(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.drinkup.viewmodels.AuthViewModel provideAuthViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.drinkup.viewmodels.IntakeViewModel provideIntakeViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.drinkup.viewmodels.FriendViewModel provideFriendViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.drinkup.utils.PreferenceManager providePreferencesManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.drinkup.data.AppDatabase provideDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.drinkup.repositories.IntakeRepository provideIntakeRepository() {
        return null;
    }
}