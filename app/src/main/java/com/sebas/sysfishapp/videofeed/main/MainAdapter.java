package com.sebas.sysfishapp.videofeed.main;

import android.view.View;

import com.sebas.sysfishapp.videofeed.BaseAdapter;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.model.Movie;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainAdapter extends BaseAdapter<MovieViewHolder> {

    /**
     * Constructor
     */
    public MainAdapter() {

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
