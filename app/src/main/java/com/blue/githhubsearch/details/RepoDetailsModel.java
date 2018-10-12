package com.blue.githhubsearch.details;

import com.blue.githhubsearch.api.ApiClient;
import com.blue.githhubsearch.api.ApiInterface;
import com.blue.githhubsearch.model.Contributions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RepoDetailsModel implements IRepoDetails.IModel {

    private Call<List<Contributions>> contributions;

    @Override
    public void getContributions(Callback<List<Contributions>> callback, String url) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        contributions = apiInterface.getContributions(url);
        contributions.enqueue(callback);
    }

    @Override
    public void onDestroy() {
        if (contributions != null) {
            contributions.cancel();
        }
    }
}
