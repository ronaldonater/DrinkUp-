package com.example.drinkup.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u001b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0004H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/example/drinkup/data/dao/UserDao;", "", "getAllOtherUsers", "", "Lcom/example/drinkup/data/entities/User;", "userId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserById", "getUserByUsername", "username", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUser", "", "user", "(Lcom/example/drinkup/data/entities/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateDailyGoal", "", "dailyGoal", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUser", "app_debug"})
@androidx.room.Dao
public abstract interface UserDao {
    
    @androidx.room.Insert(onConflict = 3)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertUser(@org.jetbrains.annotations.NotNull
    com.example.drinkup.data.entities.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateUser(@org.jetbrains.annotations.NotNull
    com.example.drinkup.data.entities.User user, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE id = :userId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUserById(int userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.drinkup.data.entities.User> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE username = :username LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUserByUsername(@org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.drinkup.data.entities.User> $completion);
    
    @androidx.room.Query(value = "UPDATE users SET dailyGoal = :dailyGoal WHERE id = :userId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateDailyGoal(int userId, int dailyGoal, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM users WHERE id != :userId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllOtherUsers(int userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.drinkup.data.entities.User>> $completion);
}