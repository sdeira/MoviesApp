package com.sebas.sysfishapp.videofeed.main;

import android.view.View;

import com.sebas.sysfishapp.videofeed.BaseAdapter;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainAdapter extends BaseAdapter<MovieViewHolder, Movie> {
    private List<Movie> list = new ArrayList<>();
    private WeakReference<OnItemClickListener> listenerWeakReference;

    /**
     * Constructor
     */
    public MainAdapter() {

    }

    public void setMovies(final List<Movie> movies) {
        list.clear();
        list.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.listenerWeakReference = new WeakReference<>(listener);
    }

    public void addMovies(final List<Movie> newMovies) {
        final int initialSize = list.size();
        for (final Movie movie : newMovies) {
            if (!list.contains(movie)) {
                list.add(movie);
            }
        }
        notifyItemRangeInserted(initialSize, list.size() - initialSize);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    protected int getChildItemCount() {
        return list.size();
    }

    @Override
    protected void onBindChildViewHolder(MovieViewHolder holder, int position) {
        final Movie movie = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerWeakReference.get().onMovieClick(movie);
            }
        });
        holder.bind(movie.getPosterPath(), movie.getName(), movie.getFirstAirDate(), movie.getOverview(), movie.getVoteAverage());
    }

    @Override
    protected int getViewLayout() {
        return R.layout.movie_row;
    }

    @Override
    protected MovieViewHolder onCreateChildViewHolder(View itemView) {
        return new MovieViewHolder(itemView);
    }
}
