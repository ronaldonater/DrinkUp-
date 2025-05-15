package com.example.drinkup.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a,\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f\u00a8\u0006\u0010"}, d2 = {"LeaderboardItem", "", "user", "Lcom/example/drinkup/data/entities/LeaderboardEntry;", "onLongPress", "Lkotlin/Function1;", "LeaderboardScreen", "navController", "Landroidx/navigation/NavController;", "friendViewModel", "Lcom/example/drinkup/viewmodels/FriendViewModel;", "onNavigate", "", "getOrdinalSuffix", "n", "", "app_debug"})
public final class LeaderboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void LeaderboardScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull
    com.example.drinkup.viewmodels.FriendViewModel friendViewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNavigate) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.ExperimentalFoundationApi.class})
    @androidx.compose.runtime.Composable
    public static final void LeaderboardItem(@org.jetbrains.annotations.NotNull
    com.example.drinkup.data.entities.LeaderboardEntry user, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.drinkup.data.entities.LeaderboardEntry, kotlin.Unit> onLongPress) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String getOrdinalSuffix(int n) {
        return null;
    }
}