package com.sebas.sysfishapp.videofeed.main;

import com.sebas.sysfishapp.videofeed.model.Show;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public interface MainView {
    /**
     * Add the shows to the view
     * @param data of the shows
     */
    void addDataToView(final List<Show> data);

    /**
     * Set the shows to the view
     * @param data of the shows
     */
    void setDataToView(final List<Show> data);
}
