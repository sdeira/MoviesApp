package com.sebas.sysfishapp.videofeed;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sebas.sysfishapp.videofeed.main.OnItemClickListener;
import com.sebas.sysfishapp.videofeed.model.Show;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<Show> list = new ArrayList<>();
    protected WeakReference<OnItemClickListener> listenerWeakReference;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(getViewLayout(), parent, false);

        return onCreateChildViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindChildViewHolder((T) holder, position);
    }

    public void setShows(final List<Show> shows) {
        list.clear();
        list.addAll(shows);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.listenerWeakReference = new WeakReference<>(listener);
    }

    public void addShows(final List<Show> newShows) {
        final int initialSize = list.size();
        for (final Show show : newShows) {
            if (!list.contains(show)) {
                list.add(show);
            }
        }
        notifyItemRangeInserted(initialSize, list.size() - initialSize);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected abstract void onBindChildViewHolder(T holder, int position);

    @LayoutRes
    protected abstract int getViewLayout();

    protected abstract T onCreateChildViewHolder(final View itemView);
}
