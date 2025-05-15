package com.example.drinkup.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0004\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/example/drinkup/data/dao/FriendshipDao;", "", "deleteFriendship", "", "userId", "friendId", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFriendsForUser", "", "Lcom/example/drinkup/data/entities/User;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFriendship", "Lcom/example/drinkup/data/entities/Friendship;", "insertFriendship", "", "friendship", "(Lcom/example/drinkup/data/entities/Friendship;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface FriendshipDao {
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertFriendship(@org.jetbrains.annotations.NotNull
    com.example.drinkup.data.entities.Friendship friendship, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM friendships WHERE (userId = :userId AND friendId = :friendId) OR (userId = :friendId AND friendId = :userId) LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getFriendship(int userId, int friendId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.drinkup.data.entities.Friendship> $completion);
    
    @androidx.room.Query(value = "DELETE FROM friendships WHERE (userId = :userId AND friendId = :friendId) OR (userId = :friendId AND friendId = :userId)")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteFriendship(int userId, int friendId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Transaction
    @androidx.room.Query(value = "SELECT u.* FROM users u INNER JOIN friendships f ON (u.id = f.friendId AND f.userId = :userId) OR (u.id = f.userId AND f.friendId = :userId)")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getFriendsForUser(int userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.drinkup.data.entities.User>> $completion);
}