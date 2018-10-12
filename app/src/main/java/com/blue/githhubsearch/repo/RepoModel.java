package com.blue.githhubsearch.repo;

import com.blue.githhubsearch.api.ApiClient;
import com.blue.githhubsearch.api.ApiInterface;
import com.blue.githhubsearch.model.Repos;

import retrofit2.Call;
import retrofit2.Callback;

public class RepoModel implements IRespos.IModel {

    Call<Repos> detailsCall;

    @Override
    public void callRepos(Callback<Repos> callback, String query) {
        if (detailsCall != null) detailsCall.cancel();
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        detailsCall = apiInterface.getRepos(query);
        detailsCall.enqueue(callback);
    }

    @Override
    public void destroy() {
        if (detailsCall != null) {
            detailsCall.cancel();
        }
    }

}
