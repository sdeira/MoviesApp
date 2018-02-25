package com.sebas.sysfishapp.videofeed;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class Settings {
    public static final String API_KEY = "b2f05a16b32b01433b9d9811d1185e9d";
    public static final String POPULAR_SHOWS_URL = "https://api.themoviedb.org/3/tv/popular?api_key=" + API_KEY + "&page={page}";
    public static final String SHOW_IMAGE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2";
    public static final String RELATED_SHOWS_URL = "https://api.themoviedb.org/3/tv/{id}/similar?api_key=" + API_KEY;
}
