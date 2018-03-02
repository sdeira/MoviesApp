package com.sebas.sysfishapp.videofeed;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sebas.sysfishapp.videofeed.model.ShowsPaging;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class ShowsApi {

    public static void getRelatedShows(final Context context, final long showId, final ShowsListener listener) {
        final String idTag = "{id}";
        final String relatedShowsUrl = Settings.RELATED_SHOWS_URL.replace(idTag, String.valueOf(showId));
        Ion.with(context)
                .load(relatedShowsUrl)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            listener.onFailed();
                        } else {
                            final Gson gson = new Gson();
                            final ShowsPaging showsPaging = gson.fromJson(result, ShowsPaging.class);
                            listener.onSuccess(showsPaging);
                        }
                    }
                });
    }

    public static void getShows(final Context context, final int page, final ShowsListener listener) {
        final String pageTag = "{page}";
        final String popularShowsUrl = Settings.POPULAR_SHOWS_URL.replace(pageTag, String.valueOf(page));
        Ion.with(context)
                .load(popularShowsUrl)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result == null) {
                            listener.onFailed();
                        } else {
                            final Gson gson = new Gson();
                            final ShowsPaging showsPaging = gson.fromJson(result, ShowsPaging.class);
                            listener.onSuccess(showsPaging);
                        }
                    }
                });
    }

    public interface ShowsListener {
        void onSuccess(final ShowsPaging showsPaging);
        void onFailed();
    }
}
