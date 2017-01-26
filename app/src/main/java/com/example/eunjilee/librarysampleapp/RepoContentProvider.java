package com.example.eunjilee.librarysampleapp;

import android.content.ClipData;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by eunjilee on 26/01/2017.
 */

public class RepoContentProvider extends ContentProvider {

    static final String[] sColumns = {"name", "full_name", "avatar_url"};
    @Override
    public boolean onCreate() {

        return false;
    }

    public static final int REPO_LIST = 100;
    public static final int REPO_LIST_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(RepoContract.AUTHORITY, RepoContract.PATH_TASKS, REPO_LIST);
        uriMatcher.addURI(RepoContract.AUTHORITY, RepoContract.PATH_TASKS + "/#", REPO_LIST_ID);
        return uriMatcher;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Repo> query = realm.where(Repo.class);
        RealmResults<Repo> results = query.findAll();


        MatrixCursor matrixCursor = new MatrixCursor(sColumns);
        for(Repo repo : results){
            Object[] rowData = new Object[]{repo.getOwnerName(), repo.getOwnerFullName(), repo.getOwner().getOwnerImg()};
            matrixCursor.addRow(rowData);
        }
        return matrixCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
