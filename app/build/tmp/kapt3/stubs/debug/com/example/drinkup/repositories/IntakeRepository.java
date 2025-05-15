package com.example.drinkup.repositories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J<\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\rH\u0002J\u0006\u0010\u0015\u001a\u00020\bJ*\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J:\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001a0\n2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ*\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0018J<\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b!\u0010\u0011J\u000e\u0010\"\u001a\u00020#2\u0006\u0010\u000f\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006$"}, d2 = {"Lcom/example/drinkup/repositories/IntakeRepository;", "", "waterEntryDao", "Lcom/example/drinkup/data/dao/WaterEntryDao;", "userDao", "Lcom/example/drinkup/data/dao/UserDao;", "(Lcom/example/drinkup/data/dao/WaterEntryDao;Lcom/example/drinkup/data/dao/UserDao;)V", "currentTestDate", "Ljava/time/LocalDate;", "addIntake", "Lkotlin/Result;", "Lcom/example/drinkup/data/entities/DailyIntakeSummary;", "userId", "", "volume", "date", "addIntake-BWLJW6A", "(IILjava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculatePercentage", "", "goal", "getCurrentDate", "getCurrentStreak", "getCurrentStreak-gIAlu-s", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIntakeHistory", "", "days", "getIntakeHistory-0E7RQCE", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTodayIntake", "getTodayIntake-gIAlu-s", "removeIntake", "removeIntake-BWLJW6A", "setTestDate", "", "app_debug"})
public final class IntakeRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.drinkup.data.dao.WaterEntryDao waterEntryDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.drinkup.data.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull
    private java.time.LocalDate currentTestDate;
    
    public IntakeRepository(@org.jetbrains.annotations.NotNull
    com.example.drinkup.data.dao.WaterEntryDao waterEntryDao, @org.jetbrains.annotations.NotNull
    com.example.drinkup.data.dao.UserDao userDao) {
        super();
    }
    
    public final void setTestDate(@org.jetbrains.annotations.NotNull
    java.time.LocalDate date) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.time.LocalDate getCurrentDate() {
        return null;
    }
    
    private final double calculatePercentage(int volume, int goal) {
        return 0.0;
    }
}