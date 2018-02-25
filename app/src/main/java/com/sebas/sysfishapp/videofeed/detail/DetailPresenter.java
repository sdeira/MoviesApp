package com.sebas.sysfishapp.videofeed.detail;

import android.content.Context;

import com.sebas.sysfishapp.videofeed.ShowsApi;
import com.sebas.sysfishapp.videofeed.MvpPresenter;
import com.sebas.sysfishapp.videofeed.Settings;
import com.sebas.sysfishapp.videofeed.model.Show;
import com.sebas.sysfishapp.videofeed.model.ShowsPaging;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class DetailPresenter extends MvpPresenter<DetailView> implements ShowsApi.ShowsListener {

    public DetailPresenter(DetailView view) {
        super(view);
    }

    public void initView(final Context context, final Show show) {
        if (show != null) {
            getView().setupView(Settings.SHOW_IMAGE_URL + show.getPosterPath(), show.getName(),
                    show.getFirstAirDate(), show.getOverview(), show.getVoteAverage());
            ShowsApi.getRelatedShows(context, show.getId(), this);
        }
    }

    @Override
    public void onSuccess(ShowsPaging showsPaging) {
        final List<Show> results = showsPaging.getResults();
        if (results.isEmpty()) {
            getView().hideRelatedShows();
        } else {
            getView().setupRelatedShows(results);
        }
    }

    @Override
    public void onFailed() {

    }
}
