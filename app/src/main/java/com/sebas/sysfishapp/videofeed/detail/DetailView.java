package com.sebas.sysfishapp.videofeed.detail;

import com.sebas.sysfishapp.videofeed.model.Show;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public interface DetailView {
    /**
     * Setup the view with the show data
     * @param imageUrl the url of the image
     * @param name the name of the show
     * @param firstAirDate the first air date of the show
     * @param overview the overview of the show
     * @param avergeVote the average vote of the show
     */
    void setupView(final String imageUrl, final String name, final String firstAirDate,
                   final String overview, final String avergeVote);

    /**
     * Setup the view with the related shows
     * @param relatedShows the shows to show
     */
    void setupRelatedShows(final List<Show> relatedShows);

    /**
     * The show don't have related shows so we hide it
     */
    void hideRelatedShows();

}
