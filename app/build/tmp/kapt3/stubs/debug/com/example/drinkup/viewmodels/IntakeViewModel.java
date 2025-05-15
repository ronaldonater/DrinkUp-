package com.example.drinkup.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nJ\u0018\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\n2\b\b\u0002\u0010\u001e\u001a\u00020\nJ\u000e\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nJ\u0016\u0010 \u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\nR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R#\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u00070\u0006X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R \u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000f\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u000f\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R&\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u00070\u000f\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000f\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/example/drinkup/viewmodels/IntakeViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/drinkup/repositories/IntakeRepository;", "(Lcom/example/drinkup/repositories/IntakeRepository;)V", "_addIntakeResult", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Result;", "Lcom/example/drinkup/data/entities/DailyIntakeSummary;", "_currentStreak", "", "_intakeHistory", "", "_todayIntake", "addIntakeResult", "Landroidx/lifecycle/LiveData;", "getAddIntakeResult", "()Landroidx/lifecycle/LiveData;", "currentStreak", "getCurrentStreak", "intakeHistory", "getIntakeHistory", "todayIntake", "getTodayIntake", "addIntake", "", "userId", "volume", "loadCurrentStreak", "loadIntakeHistory", "days", "loadTodayIntake", "removeIntake", "app_debug"})
public final class IntakeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.drinkup.repositories.IntakeRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<com.example.drinkup.data.entities.DailyIntakeSummary>> _todayIntake = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.DailyIntakeSummary>> todayIntake = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<com.example.drinkup.data.entities.DailyIntakeSummary>> _addIntakeResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.DailyIntakeSummary>> addIntakeResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.DailyIntakeSummary>>> _intakeHistory = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.DailyIntakeSummary>>> intakeHistory = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<java.lang.Integer>> _currentStreak = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<java.lang.Integer>> currentStreak = null;
    
    public IntakeViewModel(@org.jetbrains.annotations.NotNull
    com.example.drinkup.repositories.IntakeRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.DailyIntakeSummary>> getTodayIntake() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<com.example.drinkup.data.entities.DailyIntakeSummary>> getAddIntakeResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.DailyIntakeSummary>>> getIntakeHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<java.lang.Integer>> getCurrentStreak() {
        return null;
    }
    
    public final void loadTodayIntake(int userId) {
    }
    
    public final void addIntake(int userId, int volume) {
    }
    
    public final void removeIntake(int userId, int volume) {
    }
    
    public final void loadIntakeHistory(int userId, int days) {
    }
    
    public final void loadCurrentStreak(int userId) {
    }
}