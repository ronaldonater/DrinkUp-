package com.example.drinkup.repositories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ2\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0002J0\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\n2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ0\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00170\n2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001aJ2\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b \u0010!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/example/drinkup/repositories/FriendRepository;", "", "friendshipDao", "Lcom/example/drinkup/data/dao/FriendshipDao;", "userDao", "Lcom/example/drinkup/data/dao/UserDao;", "waterEntryDao", "Lcom/example/drinkup/data/dao/WaterEntryDao;", "(Lcom/example/drinkup/data/dao/FriendshipDao;Lcom/example/drinkup/data/dao/UserDao;Lcom/example/drinkup/data/dao/WaterEntryDao;)V", "addFriend", "Lkotlin/Result;", "", "userId", "", "friendUsername", "", "addFriend-0E7RQCE", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculatePercentage", "", "volume", "goal", "getFriends", "", "Lcom/example/drinkup/data/entities/UserProfile;", "getFriends-gIAlu-s", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLeaderboard", "Lcom/example/drinkup/data/entities/LeaderboardEntry;", "getLeaderboard-gIAlu-s", "removeFriend", "friendId", "removeFriend-0E7RQCE", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class FriendRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.drinkup.data.dao.FriendshipDao friendshipDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.drinkup.data.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.drinkup.data.dao.WaterEntryDao waterEntryDao = null;
    
    public FriendRepository(@org.jetbrains.annotations.NotNull
    com.example.drinkup.data.dao.FriendshipDao friendshipDao, @org.jetbrains.annotations.NotNull
    com.example.drinkup.data.dao.UserDao userDao, @org.jetbrains.annotations.NotNull
    com.example.drinkup.data.dao.WaterEntryDao waterEntryDao) {
        super();
    }
    
    private final double calculatePercentage(int volume, int goal) {
        return 0.0;
    }
}