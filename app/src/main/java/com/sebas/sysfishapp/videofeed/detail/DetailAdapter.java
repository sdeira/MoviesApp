package com.sebas.sysfishapp.videofeed.detail;

import android.view.View;

import com.sebas.sysfishapp.videofeed.BaseAdapter;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.main.OnItemClickListener;
import com.sebas.sysfishapp.videofeed.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 25/2/18.
 */

public class DetailAdapter extends BaseAdapter<RelatedMovieViewHolder> {
    private List<Movie> list = new ArrayList<>();
    private WeakReference<OnItemClickListener> listenerWeakReference;

    /**
     * Default Constructor
     */
    public DetailAdapter() {
    }

    public void setMovies(final List<Movie> movies) {
        list.clear();
        list.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.listenerWeakReference = new WeakReference<>(listener);
    }

    @Override
    protected int getChildItemCount() {
        return list.size();
    }

    @Override
    protected void onBindChildViewHolder(RelatedMovieViewHolder holder, int position) {
        final Movie movie = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerWeakReference.get().onMovieClick(movie);
            }
        });
        holder.bind(movie.getPosterPath(), movie.getName());
    }

    @Override
    protected int getViewLayout() {
        return R.layout.related_movie_row;
    }

    @Override
    protected RelatedMovieViewHolder onCreateChildViewHolder(View itemView) {
        return new RelatedMovieViewHolder(itemView);
    }
}
