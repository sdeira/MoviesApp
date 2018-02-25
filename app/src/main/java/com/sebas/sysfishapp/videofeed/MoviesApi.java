package com.sebas.sysfishapp.videofeed;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sebas.sysfishapp.videofeed.model.MoviesPaging;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MoviesApi {

    public static void getRelatedMovies(final Context context, final long movieId, final MoviesListener listener) {
        final String idTag = "{id}";
        final String relatedMoviesUrl = Settings.RELATED_MOVIES_URL.replace(idTag, String.valueOf(movieId));
        Ion.with(context)
                .load(relatedMoviesUrl)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        //TODO : check if the result has error and show error in the view
                        if (result == null) {
                            listener.onFailed();
                        } else {
                            final Gson gson = new Gson();
                            final MoviesPaging moviesPaging = gson.fromJson(result, MoviesPaging.class);
                            listener.onSuccess(moviesPaging);
                        }
                    }
                });
    }

    public static void getMovies(final Context context, final int page, final MoviesListener listener) {
        final String pageTag = "{page}";
        final String popularMoviesUrl = Settings.POPULAR_MOVIES_URL.replace(pageTag, String.valueOf(page));
        Ion.with(context)
                .load(popularMoviesUrl)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        //TODO : check if the result has error and show error in the view
                        if (result == null) {
                            listener.onFailed();
                        } else {
                            final Gson gson = new Gson();
                            final MoviesPaging moviesPaging = gson.fromJson(result, MoviesPaging.class);
                            listener.onSuccess(moviesPaging);
                        }
                    }
                });
    }

    public interface MoviesListener {
        void onSuccess(final MoviesPaging moviesPaging);
        void onFailed();
    }
}
