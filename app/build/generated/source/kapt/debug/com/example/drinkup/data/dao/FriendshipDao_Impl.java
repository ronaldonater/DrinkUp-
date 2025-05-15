package com.example.drinkup.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.drinkup.data.Converters;
import com.example.drinkup.data.entities.Friendship;
import com.example.drinkup.data.entities.User;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FriendshipDao_Impl implements FriendshipDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Friendship> __insertionAdapterOfFriendship;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDeleteFriendship;

  public FriendshipDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFriendship = new EntityInsertionAdapter<Friendship>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `friendships` (`id`,`userId`,`friendId`,`createdAt`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Friendship entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getFriendId());
        final String _tmp = __converters.dateTimeToTimestamp(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
      }
    };
    this.__preparedStmtOfDeleteFriendship = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM friendships WHERE (userId = ? AND friendId = ?) OR (userId = ? AND friendId = ?)";
        return _query;
      }
    };
  }

  @Override
  public Object insertFriendship(final Friendship friendship,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFriendship.insertAndReturnId(friendship);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFriendship(final int userId, final int friendId,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFriendship.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, friendId);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, friendId);
        _argIndex = 4;
        _stmt.bindLong(_argIndex, userId);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteFriendship.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getFriendship(final int userId, final int friendId,
      final Continuation<? super Friendship> $completion) {
    final String _sql = "SELECT * FROM friendships WHERE (userId = ? AND friendId = ?) OR (userId = ? AND friendId = ?) LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, friendId);
    _argIndex = 3;
    _statement.bindLong(_argIndex, friendId);
    _argIndex = 4;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Friendship>() {
      @Override
      @Nullable
      public Friendship call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfFriendId = CursorUtil.getColumnIndexOrThrow(_cursor, "friendId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Friendship _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final int _tmpFriendId;
            _tmpFriendId = _cursor.getInt(_cursorIndexOfFriendId);
            final LocalDateTime _tmpCreatedAt;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            _tmpCreatedAt = __converters.fromTimestamp(_tmp);
            _result = new Friendship(_tmpId,_tmpUserId,_tmpFriendId,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getFriendsForUser(final int userId,
      final Continuation<? super List<User>> $completion) {
    final String _sql = "SELECT u.* FROM users u INNER JOIN friendships f ON (u.id = f.friendId AND f.userId = ?) OR (u.id = f.userId AND f.friendId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<List<User>>() {
      @Override
      @NonNull
      public List<User> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
            final int _cursorIndexOfPasswordHash = CursorUtil.getColumnIndexOrThrow(_cursor, "passwordHash");
            final int _cursorIndexOfDailyGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyGoal");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
            final List<User> _result = new ArrayList<User>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final User _item;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final String _tmpUsername;
              if (_cursor.isNull(_cursorIndexOfUsername)) {
                _tmpUsername = null;
              } else {
                _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
              }
              final String _tmpPasswordHash;
              if (_cursor.isNull(_cursorIndexOfPasswordHash)) {
                _tmpPasswordHash = null;
              } else {
                _tmpPasswordHash = _cursor.getString(_cursorIndexOfPasswordHash);
              }
              final int _tmpDailyGoal;
              _tmpDailyGoal = _cursor.getInt(_cursorIndexOfDailyGoal);
              final LocalDateTime _tmpCreatedAt;
              final String _tmp;
              if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
                _tmp = null;
              } else {
                _tmp = _cursor.getString(_cursorIndexOfCreatedAt);
              }
              _tmpCreatedAt = __converters.fromTimestamp(_tmp);
              _item = new User(_tmpId,_tmpUsername,_tmpPasswordHash,_tmpDailyGoal,_tmpCreatedAt);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
