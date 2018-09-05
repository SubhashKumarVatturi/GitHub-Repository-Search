package com.blue.githhubsearch.api;

import com.blue.githhubsearch.model.Contributions;
import com.blue.githhubsearch.model.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET("repositories?sort=stars&order=desc&per_page=10")
    Call<Repos> getRepos(@Query("q") String query);

    @GET
    Call<List<Contributions>> getContributions(@Url String url);

}
