package com.blue.githhubsearch.contribution;

import com.blue.githhubsearch.api.ApiClient;
import com.blue.githhubsearch.api.ApiInterface;
import com.blue.githhubsearch.model.ContributorData;
import com.blue.githhubsearch.model.RepoData;
import com.blue.githhubsearch.model.Repos;

import java.util.List;

import retrofit2.Callback;

public class ContributionModel implements IContributionDetails.IModel {

    @Override
    public void getRepos(Callback<List<RepoData>> callback, String url) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        apiInterface.getReposWithUrl(url).enqueue(callback);
    }

    @Override
    public void getContributorDetails(Callback<ContributorData> callback, String url) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        apiInterface.getContributorDetails(url).enqueue(callback);
    }
}
