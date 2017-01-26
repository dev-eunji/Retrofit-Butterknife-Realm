package com.example.eunjilee.librarysampleapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by eunjilee on 26/01/2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private Context mContext;
    private List<Repo> mIssueList;

    public RepoAdapter(Context context) {
        mContext = context;
    }
    public void setIssueList(List<Repo> issueList){
        mIssueList=issueList;
        notifyDataSetChanged();
    }
    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item_list, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Repo repo = mIssueList.get(position);

        Realm.init(mContext);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        final Repo managedRepo = realm.copyToRealm(repo);
        realm.commitTransaction();
        Log.v("dd", managedRepo.getOwnerFullName());
        Log.v("dd", managedRepo.getOwnerName());
        Log.v("dd", managedRepo.getOwner().getOwnerImg());
        holder.ownerAvatarTextView.setText(repo.getOwner().getOwnerImg());
        holder.ownerFullNameTextView.setText(repo.getOwnerFullName());
        holder.ownerNameTextView.setText(repo.getOwnerName());
    }


    @Override
    public int getItemCount() {
        if(mIssueList==null)
            return 0;
        else
            return mIssueList.size();
    }


    class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_owner_avatar_url)
        TextView ownerAvatarTextView;
        @BindView(R.id.tv_owner_full_name)
        TextView ownerFullNameTextView;
        @BindView(R.id.tv_owner_name)
        TextView ownerNameTextView;


        public RepoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //mOnClickListener.onListItemClick(clickedPosition, clickedIssueId);
        }
    }
}