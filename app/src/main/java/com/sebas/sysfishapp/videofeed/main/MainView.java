package com.sebas.sysfishapp.videofeed.main;

import com.sebas.sysfishapp.videofeed.model.Movie;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public interface MainView {
    /**
     * Add the movies to the view
     * @param data of the movies
     */
    void addDataToView(final List<Movie> data);

    void setDataToView(final List<Movie> data);
}
