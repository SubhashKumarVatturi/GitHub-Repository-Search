package com.blue.githhubsearch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blue.githhubsearch.R;
import com.blue.githhubsearch.model.RepoDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {

    private List<RepoDetails.Item> mRepos;

    @NonNull
    @Override
    public RepoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RepoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepoHolder holder, int position) {
        RepoDetails.Item item = mRepos.get(position);
        holder.tvName.setText(item.getFullName());
        holder.tvDescription.setText(item.getDescription());
        holder.tvUpdatedAt.setText(String.format("Updated on %s", getDate(item.getUpdatedAt())));
        holder.tvWatchers.setText(String.valueOf(item.getWatchersCount()));
    }

    private String getDate(String time) {

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }

    @Override
    public int getItemCount() {
        return mRepos == null ? 0 : mRepos.size();
    }

    public void setRepos(List<RepoDetails.Item> repos) {
        this.mRepos = repos;
        notifyDataSetChanged();
    }

    public static class RepoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvWatchers)
        TextView tvWatchers;
        @BindView(R.id.tvUpdatedAt)
        TextView tvUpdatedAt;

        public RepoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
