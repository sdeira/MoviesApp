package com.sebas.sysfishapp.videofeed.detail;

import com.sebas.sysfishapp.videofeed.MvpPresenter;
import com.sebas.sysfishapp.videofeed.Settings;
import com.sebas.sysfishapp.videofeed.model.Movie;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class DetailPresenter extends MvpPresenter<DetailView> {

    public DetailPresenter(DetailView view) {
        super(view);
    }

    public void initView(final Movie movie) {
        if (movie != null) {
            getView().setupView(Settings.MOVIE_IMAGE_URL + movie.getPosterPath(), movie.getName(),
                    movie.getFirstAirDate(), movie.getOverview(), movie.getVoteAverage());
        }
    }
}
