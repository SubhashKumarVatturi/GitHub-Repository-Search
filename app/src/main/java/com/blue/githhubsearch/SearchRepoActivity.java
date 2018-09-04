package com.blue.githhubsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.blue.githhubsearch.adapter.RepoAdapter;
import com.blue.githhubsearch.model.RepoDetails;
import com.blue.githhubsearch.repo.IRespos;
import com.blue.githhubsearch.repo.RepoPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchRepoActivity extends AppCompatActivity implements IRespos.IView {


    @BindView(R.id.rvRepoList)
    RecyclerView rvRepoList;
    @BindView(R.id.vProgres)
    ProgressBar vProgress;
    @BindView(R.id.ibClose)
    ImageButton ibClose;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.vNoData)
    View vNoData;

    private IRespos.IPresenter mRepoPresenter;
    private RepoAdapter mRepoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repo);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRepoPresenter = new RepoPresenter(this);

        mRepoPresenter.doSearchOn(etSearch, ibClose);

        rvRepoList.setLayoutManager(new LinearLayoutManager(this));
        mRepoAdapter = new RepoAdapter();
        rvRepoList.setAdapter(mRepoAdapter);

        if (!Utils.isOnline(this)) {
            Utils.showToast(this, getString(R.string.check_internet_connection));
        }

    }

    @OnClick(R.id.ibClose)
    public void onClick(View view) {
        etSearch.getText().clear();
    }

    @Override
    public void onReposLoaded(List<RepoDetails.Item> repos) {
        mRepoAdapter.setRepos(repos);
        vNoData.setVisibility(repos == null ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoading(boolean show) {
        vProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRepoPresenter.onDestroy();
    }

}
