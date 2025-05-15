package com.example.drinkup.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\'J\'\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ/\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ#\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/example/drinkup/data/dao/WaterEntryDao;", "", "getAllEntriesForUser", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/drinkup/data/entities/WaterEntry;", "userId", "", "getDatesWithMinVolume", "Ljava/time/LocalDate;", "minVolume", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEntriesForDateRange", "startDate", "endDate", "(ILjava/time/LocalDate;Ljava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalVolumeForDate", "date", "(ILjava/time/LocalDate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWaterEntry", "", "waterEntry", "(Lcom/example/drinkup/data/entities/WaterEntry;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface WaterEntryDao {
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertWaterEntry(@org.jetbrains.annotations.NotNull
    com.example.drinkup.data.entities.WaterEntry waterEntry, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM water_entries WHERE userId = :userId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.drinkup.data.entities.WaterEntry>> getAllEntriesForUser(int userId);
    
    @androidx.room.Query(value = "SELECT SUM(volume) FROM water_entries WHERE userId = :userId AND date = :date")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalVolumeForDate(int userId, @org.jetbrains.annotations.NotNull
    java.time.LocalDate date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM water_entries WHERE userId = :userId AND date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getEntriesForDateRange(int userId, @org.jetbrains.annotations.NotNull
    java.time.LocalDate startDate, @org.jetbrains.annotations.NotNull
    java.time.LocalDate endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.drinkup.data.entities.WaterEntry>> $completion);
    
    @androidx.room.Query(value = "SELECT DISTINCT date FROM water_entries WHERE userId = :userId AND volume >= :minVolume ORDER BY date DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDatesWithMinVolume(int userId, int minVolume, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.time.LocalDate>> $completion);
}