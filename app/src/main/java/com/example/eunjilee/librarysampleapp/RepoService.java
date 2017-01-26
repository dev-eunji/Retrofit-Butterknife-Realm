package com.example.eunjilee.librarysampleapp;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eunjilee on 26/01/2017.
 */

public class RepoService {

    public final static String GITHUB_HOST_URL = "https://api.github.com/";

    public interface GItHubIssue {
        @GET("users/{user_id}/repos")
        Call<List<Repo>> gitRepo(@Path("user_id") String user_id);
    }

    public interface RetrofitCallback {
        void success(List<Repo> gitIssues);
        void error(Throwable throwable);
    }

    public static void getResponseListByAsync(String userId, final RetrofitCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GItHubIssue gitHub = retrofit.create(GItHubIssue.class);
        Call<List<Repo>> call = gitHub.gitRepo(userId);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    List<Repo> gitIssues = response.body();
                    callback.success(gitIssues);
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }
}
