package com.sebas.sysfishapp.videofeed.detail;

import android.content.Context;

import com.sebas.sysfishapp.videofeed.MoviesApi;
import com.sebas.sysfishapp.videofeed.MvpPresenter;
import com.sebas.sysfishapp.videofeed.Settings;
import com.sebas.sysfishapp.videofeed.model.Movie;
import com.sebas.sysfishapp.videofeed.model.MoviesPaging;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class DetailPresenter extends MvpPresenter<DetailView> implements MoviesApi.MoviesListener {

    public DetailPresenter(DetailView view) {
        super(view);
    }

    public void initView(final Context context, final Movie movie) {
        if (movie != null) {
            getView().setupView(Settings.MOVIE_IMAGE_URL + movie.getPosterPath(), movie.getName(),
                    movie.getFirstAirDate(), movie.getOverview(), movie.getVoteAverage());
            MoviesApi.getRelatedMovies(context, movie.getId(), this);
        }
    }

    @Override
    public void onSuccess(MoviesPaging moviesPaging) {
        final List<Movie> results = moviesPaging.getResults();
        if (results.isEmpty()) {
            getView().hideRelatedMovies();
        } else {
            getView().setupRelatedMovies(results);
        }
    }

    @Override
    public void onFailed() {

    }
}
