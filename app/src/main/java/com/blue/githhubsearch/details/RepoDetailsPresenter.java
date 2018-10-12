package com.blue.githhubsearch.details;

import com.blue.githhubsearch.model.Contributions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoDetailsPresenter implements IRepoDetails.IPresenter {

    private IRepoDetails.IView mDetailsView;
    private IRepoDetails.IModel mDetailsModel;

    public RepoDetailsPresenter(IRepoDetails.IView details) {
        this.mDetailsView = details;
        mDetailsModel = new RepoDetailsModel();
    }

    @Override
    public void getContributions(String url) {
        mDetailsView.showLoading(true);
        mDetailsModel.getContributions(new Callback<List<Contributions>>() {
            @Override
            public void onResponse(Call<List<Contributions>> call, Response<List<Contributions>> response) {
                if (mDetailsView != null) {
                    mDetailsView.onContributionsLoaded(response.body());
                    mDetailsView.showLoading(false);
                }
            }

            @Override
            public void onFailure(Call<List<Contributions>> call, Throwable t) {
                if (mDetailsView != null) {
                    mDetailsView.onContributionsLoaded(null);
                    mDetailsView.showLoading(false);
                }
            }
        }, url);

    }

    @Override
    public void onDestroy() {
        mDetailsModel.onDestroy();
        mDetailsView = null;
        mDetailsModel = null;
    }
}
