package com.example.eunjilee.librarysampleapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_repo)
    RecyclerView mRepolist;
    private RepoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mRepolist.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RepoAdapter(this);
        mRepolist.setAdapter(mAdapter);

        RepoService.getResponseListByAsync("eunjilee", new RepoService.RetrofitCallback() {
            @Override
            public void success(List<Repo> gitIssues) {
                mAdapter.setIssueList(gitIssues);
            }
            @Override
            public void error(Throwable throwable) {
                Log.d("Retrofit", throwable.getMessage());
            }
        });

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(RepoContract.CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        Log.d("@@@@@", cursor.getString(1));
    }
}
