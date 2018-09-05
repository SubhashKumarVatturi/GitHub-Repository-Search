package com.blue.githhubsearch.details;

import com.blue.githhubsearch.api.ApiClient;
import com.blue.githhubsearch.api.ApiInterface;
import com.blue.githhubsearch.model.Contributions;
import com.blue.githhubsearch.model.Details;

import java.util.List;

import retrofit2.Callback;

public class RepoDetailsModel implements IRepoDetails.IModel {

    @Override
    public void getContributions(Callback<List<Contributions>> callback, String url) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        apiInterface.getContributions(url).enqueue(callback);
    }
}
