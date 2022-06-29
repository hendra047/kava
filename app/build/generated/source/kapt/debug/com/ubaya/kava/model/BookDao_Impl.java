package com.ubaya.kava.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BookDao_Impl implements BookDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Book> __insertionAdapterOfBook;

  private final EntityDeletionOrUpdateAdapter<Book> __deletionAdapterOfBook;

  private final EntityDeletionOrUpdateAdapter<Book> __updateAdapterOfBook;

  public BookDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBook = new EntityInsertionAdapter<Book>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Book` (`title`,`subtitle`,`book_no`,`pages`,`language`,`author`,`publisher`,`description`,`rating`,`cover_url`,`bookmarked`,`id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Book value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getSubtitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSubtitle());
        }
        if (value.getBookNumber() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBookNumber());
        }
        if (value.getPages() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getPages());
        }
        if (value.getLanguage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLanguage());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAuthor());
        }
        if (value.getPublisher() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPublisher());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDescription());
        }
        if (value.getRating() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindDouble(9, value.getRating());
        }
        if (value.getCoverUrl() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getCoverUrl());
        }
        stmt.bindLong(11, value.getBookmarked());
        stmt.bindLong(12, value.getId());
      }
    };
    this.__deletionAdapterOfBook = new EntityDeletionOrUpdateAdapter<Book>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Book` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Book value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfBook = new EntityDeletionOrUpdateAdapter<Book>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Book` SET `title` = ?,`subtitle` = ?,`book_no` = ?,`pages` = ?,`language` = ?,`author` = ?,`publisher` = ?,`description` = ?,`rating` = ?,`cover_url` = ?,`bookmarked` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Book value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getSubtitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSubtitle());
        }
        if (value.getBookNumber() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBookNumber());
        }
        if (value.getPages() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, value.getPages());
        }
        if (value.getLanguage() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLanguage());
        }
        if (value.getAuthor() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAuthor());
        }
        if (value.getPublisher() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPublisher());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDescription());
        }
        if (value.getRating() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindDouble(9, value.getRating());
        }
        if (value.getCoverUrl() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getCoverUrl());
        }
        stmt.bindLong(11, value.getBookmarked());
        stmt.bindLong(12, value.getId());
        stmt.bindLong(13, value.getId());
      }
    };
  }

  @Override
  public Object insertAllBook(final Book[] book, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBook.insert(book);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteBook(final Book book, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBook.handle(book);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateBook(final Book book, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfBook.handle(book);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object selectAllBook(final Continuation<? super List<Book>> continuation) {
    final String _sql = "SELECT * FROM book";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Book>>() {
      @Override
      public List<Book> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfSubtitle = CursorUtil.getColumnIndexOrThrow(_cursor, "subtitle");
          final int _cursorIndexOfBookNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "book_no");
          final int _cursorIndexOfPages = CursorUtil.getColumnIndexOrThrow(_cursor, "pages");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfAuthor = CursorUtil.getColumnIndexOrThrow(_cursor, "author");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "cover_url");
          final int _cursorIndexOfBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarked");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<Book> _result = new ArrayList<Book>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Book _item;
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpSubtitle;
            if (_cursor.isNull(_cursorIndexOfSubtitle)) {
              _tmpSubtitle = null;
            } else {
              _tmpSubtitle = _cursor.getString(_cursorIndexOfSubtitle);
            }
            final String _tmpBookNumber;
            if (_cursor.isNull(_cursorIndexOfBookNumber)) {
              _tmpBookNumber = null;
            } else {
              _tmpBookNumber = _cursor.getString(_cursorIndexOfBookNumber);
            }
            final Integer _tmpPages;
            if (_cursor.isNull(_cursorIndexOfPages)) {
              _tmpPages = null;
            } else {
              _tmpPages = _cursor.getInt(_cursorIndexOfPages);
            }
            final String _tmpLanguage;
            if (_cursor.isNull(_cursorIndexOfLanguage)) {
              _tmpLanguage = null;
            } else {
              _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            }
            final String _tmpAuthor;
            if (_cursor.isNull(_cursorIndexOfAuthor)) {
              _tmpAuthor = null;
            } else {
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Double _tmpRating;
            if (_cursor.isNull(_cursorIndexOfRating)) {
              _tmpRating = null;
            } else {
              _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final int _tmpBookmarked;
            _tmpBookmarked = _cursor.getInt(_cursorIndexOfBookmarked);
            _item = new Book(_tmpTitle,_tmpSubtitle,_tmpBookNumber,_tmpPages,_tmpLanguage,_tmpAuthor,_tmpPublisher,_tmpDescription,_tmpRating,_tmpCoverUrl,_tmpBookmarked);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
