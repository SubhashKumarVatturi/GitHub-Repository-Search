package com.blue.githhubsearch.repo;

import com.blue.githhubsearch.api.ApiClient;
import com.blue.githhubsearch.api.ApiInterface;
import com.blue.githhubsearch.model.RepoDetails;

import retrofit2.Call;
import retrofit2.Callback;

public class RepoModel implements IRespos.IModel {

    Call<RepoDetails> detailsCall;

    @Override
    public void callRepos(Callback<RepoDetails> callback, String query) {
        if (detailsCall != null) detailsCall.cancel();
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        detailsCall = apiInterface.getRepoDetails(query);
        detailsCall.enqueue(callback);
    }

}
