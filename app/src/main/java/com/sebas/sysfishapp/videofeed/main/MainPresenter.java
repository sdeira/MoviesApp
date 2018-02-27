package com.sebas.sysfishapp.videofeed.main;

import android.content.Context;

import com.sebas.sysfishapp.videofeed.ShowsApi;
import com.sebas.sysfishapp.videofeed.MvpPresenter;
import com.sebas.sysfishapp.videofeed.model.Show;
import com.sebas.sysfishapp.videofeed.model.ShowsPaging;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainPresenter extends MvpPresenter<MainView> implements ShowsApi.ShowsListener {

    private int page = 1;

    /**
     * Default Constructor
     */
    public MainPresenter() {

    }

    /**
     * Constructor
     * @param view to be called in the activity
     */
    public MainPresenter(MainView view) {
        super(view);
    }

    public void loadShows(final Context context) {
        getView().showLoading(true);
        ShowsApi.getShows(context, page, this);
    }

    @Override
    public void onSuccess(final ShowsPaging showsPaging) {
        final MainView view = getView();
        view.showLoading(false);
        List<Show> results = showsPaging.getResults();
        if (showsPaging.getPage() == 0) {
            view.setDataToView(results);
        } else {
            view.addDataToView(results);
        }
        page = showsPaging.getPage() + 1;
    }

    @Override
    public void onFailed() {
        final MainView view = getView();
        view.showLoading(false);
        view.showError();
    }
}
