package com.sebas.sysfishapp.videofeed.db;

import android.provider.BaseColumns;

/**
 * Created by sebastiandeira on 27/2/18.
 */

public final class ShowReaderContract {
    private ShowReaderContract() {
    }

    public static class ShowEntry implements BaseColumns {
        public static final String TABLE_SHOWS = "shows";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_FIRST_AIR_DATE = "first_air_date";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_POSTER_PATH = "poster_path";
    }
}
