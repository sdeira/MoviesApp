package com.sebas.sysfishapp.videofeed;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(getViewLayout(), parent, false);

        return onCreateChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindChildViewHolder((T) holder, position);
    }

    @Override
    public int getItemCount() {
        return getChildItemCount();
    }

    protected abstract int getChildItemCount();

    protected abstract void onBindChildViewHolder(T holder, int position);

    @LayoutRes
    protected abstract int getViewLayout();

    protected abstract T onCreateChildViewHolder(final View itemView);
}
