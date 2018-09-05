package com.blue.githhubsearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blue.githhubsearch.adapter.ContributorsAdapter;
import com.blue.githhubsearch.details.IRepoDetails;
import com.blue.githhubsearch.details.RepoDetailsPresenter;
import com.blue.githhubsearch.model.Contributions;
import com.blue.githhubsearch.model.RepoData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RepoDetails extends AppCompatActivity implements IRepoDetails.IView {

    @BindView(R.id.ivAvathar)
    ImageView ivAvathar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.rvContributors)
    RecyclerView rvContributors;
    @BindView(R.id.tvProjectLink)
    TextView tvProjectLink;
    @BindView(R.id.vProgress)
    ProgressBar vProgress;

    private IRepoDetails.IPresenter mPresenter;
    private ContributorsAdapter mContributorsAdapter;
    private RepoData mRepoDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepoDetails.super.onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {

            mPresenter = new RepoDetailsPresenter(this);

            mRepoDetails = intent.getParcelableExtra(SearchRepoActivity.REPO_DATA);
            applyRepoData(mRepoDetails);

            mPresenter.getContributions(mRepoDetails.getContributorsUrl());

        }

        mContributorsAdapter = new ContributorsAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, getSpan());
        rvContributors.setLayoutManager(manager);
        rvContributors.setAdapter(mContributorsAdapter);
        rvContributors.setNestedScrollingEnabled(false);


    }

    public int getSpan() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = getResources().getDisplayMetrics().density;
        float dpWidth = outMetrics.widthPixels / density;
        return Math.round(dpWidth / 70);
    }

    private void applyRepoData(RepoData data) {
        if (data == null) return;
        tvName.setText(data.getName());
        tvDescription.setText(data.getDescription());
        tvProjectLink.setText(data.getHtmlUrl());
    }

    @OnClick(R.id.tvProjectLink)
    public void onClick(View view) {
        if (mRepoDetails != null) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepoDetails.getHtmlUrl()));
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            browserIntent.setPackage("com.android.chrome");
            if (browserIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(browserIntent);
            } else {
                browserIntent.setPackage(null);
                startActivity(browserIntent);
            }
        }
    }


    @Override
    public void onContributionsLoaded(List<Contributions> contributions) {
        mContributorsAdapter.setContributions(contributions);
    }

    @Override
    public void showLoading(boolean show) {
        vProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
