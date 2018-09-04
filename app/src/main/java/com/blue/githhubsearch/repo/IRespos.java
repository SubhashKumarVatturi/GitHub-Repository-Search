package com.blue.githhubsearch.repo;

import android.widget.EditText;
import android.widget.ImageButton;

import com.blue.githhubsearch.model.RepoDetails;

import java.util.List;

import retrofit2.Callback;

public interface IRespos {

    interface IView {

        void onReposLoaded(List<RepoDetails.Item> repos);

        void showLoading(boolean show);
    }

    interface IModel {
        void callRepos(Callback<RepoDetails> callback, String query);
    }

    interface IPresenter {
        void callRepos(String query);

        void onDestroy();

        void doSearchOn(EditText searchView, ImageButton closeButton);
    }
}
