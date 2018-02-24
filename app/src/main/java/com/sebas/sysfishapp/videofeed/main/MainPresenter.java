package com.sebas.sysfishapp.videofeed.main;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sebas.sysfishapp.videofeed.MoviesApi;
import com.sebas.sysfishapp.videofeed.MvpPresenter;
import com.sebas.sysfishapp.videofeed.Settings;
import com.sebas.sysfishapp.videofeed.model.Movie;
import com.sebas.sysfishapp.videofeed.model.MoviesPaging;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainPresenter extends MvpPresenter<MainView> implements MoviesApi.MoviesListener {

    private int page = 1;

    /**
     * Constructor
     * @param view to be called in the activity
     */
    public MainPresenter(MainView view) {
        super(view);
    }

    public void loadMovies(final Context context) {
        MoviesApi.getMovies(context, page, this);
    }

    @Override
    public void onSuccess(MoviesPaging moviesPaging) {
        List<Movie> results = moviesPaging.getResults();
        if (moviesPaging.getPage() == 0) {
            getView().setDataToView(results);
        } else {
            getView().addDataToView(results);
        }
        page = moviesPaging.getPage() + 1;
    }

    @Override
    public void onFailed() {

    }
}
