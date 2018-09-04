package com.blue.githhubsearch.api;

import com.blue.githhubsearch.model.RepoDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("repositories?sort=stars&order=desc&per_page=10")
    Call<RepoDetails> getRepoDetails(@Query("q") String query);
}
