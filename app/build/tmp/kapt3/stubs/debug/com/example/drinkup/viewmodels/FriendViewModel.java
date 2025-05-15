package com.example.drinkup.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010!\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u001cR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R#\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00070\u0006X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R#\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\u00070\u0006X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R&\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00070\u0010\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R&\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\u00070\u0010\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R \u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010\u00f8\u0001\u0000\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lcom/example/drinkup/viewmodels/FriendViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/drinkup/repositories/FriendRepository;", "(Lcom/example/drinkup/repositories/FriendRepository;)V", "_addFriendResult", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Result;", "", "_friends", "", "Lcom/example/drinkup/data/entities/UserProfile;", "_leaderboard", "Lcom/example/drinkup/data/entities/LeaderboardEntry;", "_removeFriendResult", "addFriendResult", "Landroidx/lifecycle/LiveData;", "getAddFriendResult", "()Landroidx/lifecycle/LiveData;", "friends", "getFriends", "leaderboard", "getLeaderboard", "removeFriendResult", "getRemoveFriendResult", "addFriend", "", "userId", "", "friendUsername", "", "loadFriends", "loadLeaderboard", "removeFriend", "friendId", "app_debug"})
public final class FriendViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.drinkup.repositories.FriendRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.UserProfile>>> _friends = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.UserProfile>>> friends = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<java.lang.Boolean>> _addFriendResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<java.lang.Boolean>> addFriendResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<java.lang.Boolean>> _removeFriendResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<java.lang.Boolean>> removeFriendResult = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.LeaderboardEntry>>> _leaderboard = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.LeaderboardEntry>>> leaderboard = null;
    
    public FriendViewModel(@org.jetbrains.annotations.NotNull
    com.example.drinkup.repositories.FriendRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.UserProfile>>> getFriends() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<java.lang.Boolean>> getAddFriendResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<java.lang.Boolean>> getRemoveFriendResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<kotlin.Result<java.util.List<com.example.drinkup.data.entities.LeaderboardEntry>>> getLeaderboard() {
        return null;
    }
    
    public final void loadFriends(int userId) {
    }
    
    public final void addFriend(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String friendUsername) {
    }
    
    public final void removeFriend(int userId, int friendId) {
    }
    
    public final void loadLeaderboard(int userId) {
    }
}