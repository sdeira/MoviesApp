package com.sebas.sysfishapp.videofeed.detail;

import com.sebas.sysfishapp.videofeed.model.Movie;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public interface DetailView {
    /**
     * Setup the view with the movie data
     * @param imageUrl the url of the image
     * @param name the name of the moview
     * @param firstAirDate the first air date of the movie
     * @param overview the overview of the movie
     * @param avergeVote the average vote of the movie
     */
    void setupView(final String imageUrl, final String name, final String firstAirDate,
                   final String overview, final String avergeVote);

    /**
     * Setup the view with the related movies
     * @param relatedMovies the movies to show
     */
    void setupRelatedMovies(final List<Movie> relatedMovies);

}
