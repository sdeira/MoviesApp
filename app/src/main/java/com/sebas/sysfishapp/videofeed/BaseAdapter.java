package com.sebas.sysfishapp.videofeed;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sebas.sysfishapp.videofeed.main.MovieViewHolder;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, I> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder;
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return onCreateChildViewHolder(itemView, viewType);
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

    protected abstract T onCreateChildViewHolder(final View itemView, @LayoutRes int viewType);
}
