package com.example.drinkup.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.drinkup.data.dao.FriendshipDao;
import com.example.drinkup.data.dao.FriendshipDao_Impl;
import com.example.drinkup.data.dao.UserDao;
import com.example.drinkup.data.dao.UserDao_Impl;
import com.example.drinkup.data.dao.WaterEntryDao;
import com.example.drinkup.data.dao.WaterEntryDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile WaterEntryDao _waterEntryDao;

  private volatile FriendshipDao _friendshipDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT NOT NULL, `passwordHash` TEXT NOT NULL, `dailyGoal` INTEGER NOT NULL, `createdAt` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `water_entries` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `volume` INTEGER NOT NULL, `timestamp` TEXT NOT NULL, `date` TEXT NOT NULL, FOREIGN KEY(`userId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_water_entries_userId` ON `water_entries` (`userId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `friendships` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `friendId` INTEGER NOT NULL, `createdAt` TEXT NOT NULL, FOREIGN KEY(`userId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`friendId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_friendships_userId` ON `friendships` (`userId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_friendships_friendId` ON `friendships` (`friendId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f2ba49b435c333f97a8409e6211a1534')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `water_entries`");
        db.execSQL("DROP TABLE IF EXISTS `friendships`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(5);
        _columnsUsers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("passwordHash", new TableInfo.Column("passwordHash", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("dailyGoal", new TableInfo.Column("dailyGoal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.example.drinkup.data.entities.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsWaterEntries = new HashMap<String, TableInfo.Column>(5);
        _columnsWaterEntries.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWaterEntries.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWaterEntries.put("volume", new TableInfo.Column("volume", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWaterEntries.put("timestamp", new TableInfo.Column("timestamp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWaterEntries.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWaterEntries = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysWaterEntries.add(new TableInfo.ForeignKey("users", "CASCADE", "NO ACTION", Arrays.asList("userId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesWaterEntries = new HashSet<TableInfo.Index>(1);
        _indicesWaterEntries.add(new TableInfo.Index("index_water_entries_userId", false, Arrays.asList("userId"), Arrays.asList("ASC")));
        final TableInfo _infoWaterEntries = new TableInfo("water_entries", _columnsWaterEntries, _foreignKeysWaterEntries, _indicesWaterEntries);
        final TableInfo _existingWaterEntries = TableInfo.read(db, "water_entries");
        if (!_infoWaterEntries.equals(_existingWaterEntries)) {
          return new RoomOpenHelper.ValidationResult(false, "water_entries(com.example.drinkup.data.entities.WaterEntry).\n"
                  + " Expected:\n" + _infoWaterEntries + "\n"
                  + " Found:\n" + _existingWaterEntries);
        }
        final HashMap<String, TableInfo.Column> _columnsFriendships = new HashMap<String, TableInfo.Column>(4);
        _columnsFriendships.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFriendships.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFriendships.put("friendId", new TableInfo.Column("friendId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFriendships.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFriendships = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysFriendships.add(new TableInfo.ForeignKey("users", "CASCADE", "NO ACTION", Arrays.asList("userId"), Arrays.asList("id")));
        _foreignKeysFriendships.add(new TableInfo.ForeignKey("users", "CASCADE", "NO ACTION", Arrays.asList("friendId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesFriendships = new HashSet<TableInfo.Index>(2);
        _indicesFriendships.add(new TableInfo.Index("index_friendships_userId", false, Arrays.asList("userId"), Arrays.asList("ASC")));
        _indicesFriendships.add(new TableInfo.Index("index_friendships_friendId", false, Arrays.asList("friendId"), Arrays.asList("ASC")));
        final TableInfo _infoFriendships = new TableInfo("friendships", _columnsFriendships, _foreignKeysFriendships, _indicesFriendships);
        final TableInfo _existingFriendships = TableInfo.read(db, "friendships");
        if (!_infoFriendships.equals(_existingFriendships)) {
          return new RoomOpenHelper.ValidationResult(false, "friendships(com.example.drinkup.data.entities.Friendship).\n"
                  + " Expected:\n" + _infoFriendships + "\n"
                  + " Found:\n" + _existingFriendships);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f2ba49b435c333f97a8409e6211a1534", "2603beab3bf649f11ef6e385aa85db5a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","water_entries","friendships");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `water_entries`");
      _db.execSQL("DELETE FROM `friendships`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WaterEntryDao.class, WaterEntryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FriendshipDao.class, FriendshipDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public WaterEntryDao waterEntryDao() {
    if (_waterEntryDao != null) {
      return _waterEntryDao;
    } else {
      synchronized(this) {
        if(_waterEntryDao == null) {
          _waterEntryDao = new WaterEntryDao_Impl(this);
        }
        return _waterEntryDao;
      }
    }
  }

  @Override
  public FriendshipDao friendshipDao() {
    if (_friendshipDao != null) {
      return _friendshipDao;
    } else {
      synchronized(this) {
        if(_friendshipDao == null) {
          _friendshipDao = new FriendshipDao_Impl(this);
        }
        return _friendshipDao;
      }
    }
  }
}
