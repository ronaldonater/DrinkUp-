package com.example.drinkup.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J*\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0006J\u0006\u0010\u001e\u001a\u00020\u001bJ \u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010 \u001a\u00020\u0017J2\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010%R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\bX\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R\u001f\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\bX\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R\u001f\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t0\bX\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\u000f\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\u000f\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t0\u000f\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006&"}, d2 = {"Lcom/example/drinkup/viewmodels/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/drinkup/repositories/AuthRepository;", "(Lcom/example/drinkup/repositories/AuthRepository;)V", "TAG", "", "_loginResult", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Result;", "Lcom/example/drinkup/data/entities/AuthResult;", "_registerResult", "_userProfile", "Lcom/example/drinkup/data/entities/UserProfile;", "loginResult", "Landroidx/lifecycle/LiveData;", "getLoginResult", "()Landroidx/lifecycle/LiveData;", "registerResult", "getRegisterResult", "userProfile", "getUserProfile", "userId", "", "getUserProfile-gIAlu-s", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "", "username", "password", "logout", "register", "dailyGoal", "updateDailyGoal", "", "newGoal", "updateDailyGoal-0E7RQCE", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.drinkup.repositories.AuthRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "AuthViewModel";
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<com.example.drinkup.data.entities.AuthResult>> _loginResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.AuthResult>> loginResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<com.example.drinkup.data.entities.AuthResult>> _registerResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.AuthResult>> registerResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<com.example.drinkup.data.entities.UserProfile>> _userProfile = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.UserProfile>> userProfile = null;
    
    public AuthViewModel(@org.jetbrains.annotations.NotNull
    com.example.drinkup.repositories.AuthRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.AuthResult>> getLoginResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.AuthResult>> getRegisterResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.UserProfile>> getUserProfile() {
        return null;
    }
    
    public final void login(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    java.lang.String password) {
    }
    
    public final void register(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    java.lang.String password, int dailyGoal) {
    }
    
    public final void logout() {
    }
}