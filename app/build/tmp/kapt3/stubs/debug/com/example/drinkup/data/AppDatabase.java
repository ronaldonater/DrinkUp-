package com.example.drinkup.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\n"}, d2 = {"Lcom/example/drinkup/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "friendshipDao", "Lcom/example/drinkup/data/dao/FriendshipDao;", "userDao", "Lcom/example/drinkup/data/dao/UserDao;", "waterEntryDao", "Lcom/example/drinkup/data/dao/WaterEntryDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.example.drinkup.data.entities.User.class, com.example.drinkup.data.entities.WaterEntry.class, com.example.drinkup.data.entities.Friendship.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.drinkup.data.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.example.drinkup.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.example.drinkup.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.drinkup.data.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.drinkup.data.dao.WaterEntryDao waterEntryDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.drinkup.data.dao.FriendshipDao friendshipDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/drinkup/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/drinkup/data/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.drinkup.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}