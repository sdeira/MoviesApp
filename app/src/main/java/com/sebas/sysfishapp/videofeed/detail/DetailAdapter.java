package com.sebas.sysfishapp.videofeed.detail;

import android.view.View;

import com.sebas.sysfishapp.videofeed.BaseAdapter;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.model.Movie;

/**
 * Created by sebastiandeira on 25/2/18.
 */

public class DetailAdapter extends BaseAdapter<RelatedMovieViewHolder> {

    /**
     * Default Constructor
     */
    public DetailAdapter() {
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
