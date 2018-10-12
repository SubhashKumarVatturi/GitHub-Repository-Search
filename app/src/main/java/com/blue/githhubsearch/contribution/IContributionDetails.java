package com.blue.githhubsearch.contribution;


import com.blue.githhubsearch.model.ContributorData;
import com.blue.githhubsearch.model.RepoData;

import java.util.List;

import retrofit2.Callback;

public interface IContributionDetails {
    interface IView {

        void onReposLoaded(List<RepoData> repos);

        void onContributorDataLoaded(ContributorData data);

        void showLoading(boolean show);
    }

    interface IModel {
        void getRepos(Callback<List<RepoData>> callback, String url);

        void getContributorDetails(Callback<ContributorData> callback, String url);
        void onDestroy();
    }

    interface IPresenter {
        void getRepos(String url);

        void getContributorDetails(String url);

        void onDestroy();
    }
}
