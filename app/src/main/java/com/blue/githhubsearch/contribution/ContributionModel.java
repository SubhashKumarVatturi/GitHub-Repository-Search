package com.blue.githhubsearch.contribution;

import com.blue.githhubsearch.api.ApiClient;
import com.blue.githhubsearch.api.ApiInterface;
import com.blue.githhubsearch.model.ContributorData;
import com.blue.githhubsearch.model.RepoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ContributionModel implements IContributionDetails.IModel {

    private Call<List<RepoData>> repoData;
    private Call<ContributorData> contributorData;

    @Override
    public void getRepos(Callback<List<RepoData>> callback, String url) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        repoData = apiInterface.getReposWithUrl(url);
        repoData.enqueue(callback);
    }

    @Override
    public void getContributorDetails(Callback<ContributorData> callback, String url) {
        ApiInterface apiInterface = ApiClient.getInstance().create(ApiInterface.class);
        contributorData = apiInterface.getContributorDetails(url);
        contributorData.enqueue(callback);
    }

    @Override
    public void onDestroy() {
        if (repoData != null) repoData.cancel();
        if (contributorData != null) contributorData.cancel();
    }
}
