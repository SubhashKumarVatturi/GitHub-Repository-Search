package com.blue.githhubsearch.repo;

import android.widget.EditText;
import android.widget.ImageButton;

import com.blue.githhubsearch.model.RepoData;
import com.blue.githhubsearch.model.Repos;

import java.util.List;

import retrofit2.Callback;

public interface IRespos {

    interface IView {

        void onReposLoaded(List<RepoData> repos);

        void showLoading(boolean show);
    }

    interface IModel {
        void callRepos(Callback<Repos> callback, String query);

        void destroy();
    }

    interface IPresenter {
        void callRepos(String query);

        void onDestroy();

        void doSearchOn(EditText searchView, ImageButton closeButton);

    }
}
