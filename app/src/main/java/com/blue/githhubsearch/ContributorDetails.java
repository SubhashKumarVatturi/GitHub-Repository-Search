package com.blue.githhubsearch;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blue.githhubsearch.adapter.RepoAdapter;
import com.blue.githhubsearch.contribution.ContributionPresenter;
import com.blue.githhubsearch.contribution.IContributionDetails;
import com.blue.githhubsearch.model.Contributions;
import com.blue.githhubsearch.model.ContributorData;
import com.blue.githhubsearch.model.RepoData;
import com.blue.githhubsearch.repo.IOnclickView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContributorDetails extends AppCompatActivity implements IContributionDetails.IView {

    @BindView(R.id.ivAvathar)
    ImageView ivAvathar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvLoginAs)
    TextView tvLoginAs;
    @BindView(R.id.rvRepositories)
    RecyclerView rvRepositories;
    @BindView(R.id.vProgressView)
    ProgressBar vProgressView;
    @BindView(R.id.tvCompany)
    TextView tvCompany;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    private IContributionDetails.IPresenter mPresenter;
    private RepoAdapter mRepoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor_details);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContributorDetails.super.onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {

            mPresenter = new ContributionPresenter(this);

            Contributions data = intent.getParcelableExtra(RepoDetails.KEY_CONTRIBUTION_DATA);
            if (data != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ivAvathar.setTransitionName(data.getLogin());
                }
                mPresenter.getContributorDetails(data.getUrl());
            }
        }

        mRepoAdapter = new RepoAdapter(new IOnclickView<RepoData>() {
            @Override
            public void onClick(RepoData item, View view) {
                Intent repoDetailsIntent = new Intent(ContributorDetails.this, RepoDetails.class);
                repoDetailsIntent.putExtra(RepoDetails.KEY_REPO_DATA, item);
                startActivity(repoDetailsIntent);
            }
        });
        rvRepositories.setLayoutManager(new LinearLayoutManager(this));
        rvRepositories.setNestedScrollingEnabled(false);
        rvRepositories.setAdapter(mRepoAdapter);

    }

    @Override
    public void onReposLoaded(List<RepoData> repos) {
        mRepoAdapter.setRepos(repos);
    }

    @Override
    public void onContributorDataLoaded(ContributorData data) {
        if (data != null) {
            tvName.setText(data.getName());
            tvLoginAs.setText(getString(R.string.login_as, data.getLogin()));
            tvCompany.setText(data.getCompany());
            tvLocation.setText(data.getLocation());

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.github_logo)
                    .error(R.mipmap.github_logo);

            Glide.with(this).load(data.getAvatarUrl())
                    .apply(options)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivAvathar);

            mPresenter.getRepos(data.getReposUrl());
        }
    }


    @Override
    public void showLoading(boolean show) {
        vProgressView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        vProgressView.setVisibility(View.GONE);
    }

}
