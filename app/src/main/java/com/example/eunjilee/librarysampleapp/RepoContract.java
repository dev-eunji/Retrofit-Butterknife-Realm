package com.example.eunjilee.librarysampleapp;

import android.net.Uri;

/**
 * Created by eunjilee on 26/01/2017.
 */

public class RepoContract {
    public static final String AUTHORITY = "com.example.eunjilee.librarysampleapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_TASKS = "repolist";

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

}
