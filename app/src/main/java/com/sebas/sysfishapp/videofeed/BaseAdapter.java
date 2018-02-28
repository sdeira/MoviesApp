package com.sebas.sysfishapp.videofeed;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sebas.sysfishapp.videofeed.main.DefaultViewHolder;
import com.sebas.sysfishapp.videofeed.main.OnItemClickListener;
import com.sebas.sysfishapp.videofeed.model.Show;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int SHOW_TYPE = 22;
    private static final int LOADING_TYPE = 23;
    private static final int NO_MORE_SHOWS = 24;
    private int serverShowsCount;
    protected List<Show> list = new ArrayList<>();
    protected WeakReference<OnItemClickListener> listenerWeakReference;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        final View itemView;
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case NO_MORE_SHOWS:
                itemView = inflater.inflate(R.layout.no_more_shows_row, parent, false);
                viewHolder = new DefaultViewHolder(itemView);
                break;
            case LOADING_TYPE:
                itemView = inflater.inflate(R.layout.loading_row, parent, false);
                viewHolder = new DefaultViewHolder(itemView);
                break;
            case SHOW_TYPE:
                itemView = inflater.inflate(getViewLayout(), parent, false);
                viewHolder = onCreateChildViewHolder(itemView);
                break;

        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (SHOW_TYPE == holder.getItemViewType()) {
            onBindChildViewHolder((T) holder, position);
        }
    }

    public void setShows(final List<Show> shows) {
        list.clear();
        list.addAll(shows);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.listenerWeakReference = new WeakReference<>(listener);
    }

    @Override
    public int getItemViewType(int position) {
        if (serverShowsCount > 0 && position >= serverShowsCount) {
            return NO_MORE_SHOWS;
        } else if (position >= list.size()) {
            return LOADING_TYPE;
        } else {
            return SHOW_TYPE;
        }
    }

    public void setServerShowsCount(final int serverShowsCount) {
        this.serverShowsCount = serverShowsCount;
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
        return list.isEmpty() ? 0 : list.size() + 1;
    }

    /**
     * Get the list shows
     * @return the list of the shows
     */
    public final List<Show> getList() {
        return list;
    }

    protected abstract void onBindChildViewHolder(T holder, int position);

    @LayoutRes
    protected abstract int getViewLayout();

    protected abstract T onCreateChildViewHolder(final View itemView);
}
