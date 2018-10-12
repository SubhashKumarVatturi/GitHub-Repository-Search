package com.blue.githhubsearch.details;


import com.blue.githhubsearch.model.Contributions;

import java.util.List;

import retrofit2.Callback;

public interface IRepoDetails {
    interface IView {

        void onContributionsLoaded(List<Contributions> contributions);

        void showLoading(boolean show);
    }

    interface IModel {
        void getContributions(Callback<List<Contributions>> callback, String url);
        void onDestroy();
    }

    interface IPresenter {
        void getContributions(String url);

        void onDestroy();
    }
}
