package com.blue.githhubsearch.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue.githhubsearch.R;
import com.blue.githhubsearch.model.Contributions;
import com.blue.githhubsearch.repo.IOnclickView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ContributorViewer> {

    private List<Contributions> mContributions;
    private Context mContext;

    public ContributorsAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ContributorViewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContributorViewer(LayoutInflater.from(mContext)
                .inflate(R.layout.inflate_contributor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorViewer holder, int position) {
        Contributions contribution = mContributions.get(position);
        holder.tvName.setText(contribution.getLogin());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.ivAvathar.setTransitionName(contribution.getLogin());
        }

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_person)
                .error(R.mipmap.ic_error);
        Glide.with(mContext).load(contribution.getAvatarUrl())
                .apply(options)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivAvathar);
    }

    @Override
    public int getItemCount() {
        return mContributions == null ? 0 : mContributions.size();
    }

    public void setContributions(List<Contributions> contributions) {
        this.mContributions = contributions;
        notifyDataSetChanged();
    }

    class ContributorViewer extends RecyclerView.ViewHolder {

        @BindView(R.id.ivAvathar)
        ImageView ivAvathar;
        @BindView(R.id.tvName)
        TextView tvName;

        public ContributorViewer(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext instanceof IOnclickView) {
                        ((IOnclickView) mContext).onClick(mContributions.get(getAdapterPosition()), ivAvathar);
                    }
                }
            });
        }

    }
}
