package com.blue.githhubsearch.contribution;

import com.blue.githhubsearch.model.ContributorData;
import com.blue.githhubsearch.model.RepoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContributionPresenter implements IContributionDetails.IPresenter {

    private IContributionDetails.IView mView;
    private IContributionDetails.IModel mModel;

    public ContributionPresenter(IContributionDetails.IView details) {
        this.mView = details;
        mModel = new ContributionModel();
    }

    @Override
    public void getRepos(String url) {
        mModel.getRepos(new Callback<List<RepoData>>() {
            @Override
            public void onResponse(Call<List<RepoData>> call, Response<List<RepoData>> response) {
                if (mView != null)
                    mView.onReposLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<RepoData>> call, Throwable t) {
                if (mView != null)
                    mView.onReposLoaded(null);
            }
        }, url);

    }

    @Override
    public void getContributorDetails(String url) {
        mView.showLoading(true);
        mModel.getContributorDetails(new Callback<ContributorData>() {
            @Override
            public void onResponse(Call<ContributorData> call, Response<ContributorData> response) {
                if (mView != null) {
                    mView.onContributorDataLoaded(response.body());
                    mView.showLoading(false);
                }
            }

            @Override
            public void onFailure(Call<ContributorData> call, Throwable t) {
                if (mView != null) {
                    mView.onReposLoaded(null);
                    mView.showLoading(false);
                }
            }
        }, url);

    }

    @Override
    public void onDestroy() {
        mModel.onDestroy();
        mView = null;
        mModel = null;
    }
}
