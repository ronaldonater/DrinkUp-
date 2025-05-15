package com.example.drinkup.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0007\u001a,\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000eH\u0007\u001a*\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016\u00a8\u0006\u0017"}, d2 = {"CompactDailyProgressItem", "", "day", "", "current", "", "goal", "progress", "HistoryScreen", "navController", "Landroidx/navigation/NavController;", "intakeViewModel", "Lcom/example/drinkup/viewmodels/IntakeViewModel;", "onNavigate", "Lkotlin/Function1;", "getWeekData", "", "Lcom/example/drinkup/data/entities/DailyIntakeSummary;", "intakeHistory", "currentDate", "Ljava/time/LocalDate;", "weekOffset", "", "app_debug"})
public final class HistoryScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable
    public static final void HistoryScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull
    com.example.drinkup.viewmodels.IntakeViewModel intakeViewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onNavigate) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final java.util.List<com.example.drinkup.data.entities.DailyIntakeSummary> getWeekData(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.drinkup.data.entities.DailyIntakeSummary> intakeHistory, @org.jetbrains.annotations.NotNull
    java.time.LocalDate currentDate, int weekOffset) {
        return null;
    }
    
    @androidx.compose.runtime.Composable
    public static final void CompactDailyProgressItem(@org.jetbrains.annotations.NotNull
    java.lang.String day, float current, float goal, float progress) {
    }
}