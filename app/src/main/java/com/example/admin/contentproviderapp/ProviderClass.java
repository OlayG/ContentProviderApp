package com.example.admin.contentproviderapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.admin.contentproviderapp.data.local.DbHelper;

import java.util.HashMap;

/**
 * Created by Admin on 8/23/2017.
 */

public class ProviderClass extends ContentProvider {

    public static final String PROVIDER_NAME = "com.example.admin.contentproviderapp.ProviderClass";
    public static final String URL = "content://" + PROVIDER_NAME + "/cpstudents";
    public static final Uri CONTENT_URL = Uri.parse(URL);

    public static final String id = "id";
    public static final String firstName = "firstName";
    public static final String lastName = "lastName";
    public static final String gradDate = "gradDate";
    public static final String gpa = "gpa";

    public static final int uriCode = 1;

    private SQLiteDatabase sqlDb;
    private static HashMap<String, String> values;
    DbHelper dbHelper;

    static final UriMatcher uriMatcher;
    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cpstudents", uriCode);
    }



    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        sqlDb = dbHelper.getWritableDatabase();

        if(sqlDb != null)
            return true;

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DbHelper.TABLE_NAME);

        switch (uriMatcher.match(uri)){

            case uriCode:
                queryBuilder.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        Cursor cursor = queryBuilder.query(sqlDb, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)){

            case uriCode:
                return "vnd.android.cursor.dir/cpstudents";
            default:
                throw new IllegalArgumentException("Unsupported Uri " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long rowId = sqlDb.insert(DbHelper.TABLE_NAME, null, values);

        if(rowId > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URL, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        } else {
            Toast.makeText(getContext(), "Row Insert Failed", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int rowsDeleted = 0;

        switch (uriMatcher.match(uri)){

            case uriCode:
                rowsDeleted = sqlDb.delete(dbHelper.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int rowsUpdated = 0;

        switch (uriMatcher.match(uri)){

            case uriCode:
                rowsUpdated = sqlDb.update(dbHelper.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return rowsUpdated;
    }
}
