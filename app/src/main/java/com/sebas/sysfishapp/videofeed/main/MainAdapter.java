package com.sebas.sysfishapp.videofeed.main;

import android.view.View;

import com.sebas.sysfishapp.videofeed.BaseAdapter;
import com.sebas.sysfishapp.videofeed.model.Movie;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainAdapter extends BaseAdapter<MovieViewHolder, Movie> {
    private final List<Movie> list;

    /**
     * Constructor
     * @param list the list of movies
     */
    public MainAdapter(final List<Movie> list) {
        this.list = list;
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
        holder.bind(movie.getPosterPath(), movie.getName(), movie.getFirstAirDate(), movie.getOverview(), movie.getVoteAverage());
    }

    @Override
    protected MovieViewHolder onCreateChildViewHolder(View itemView, int viewType) {
        return new MovieViewHolder(itemView);
    }
}
