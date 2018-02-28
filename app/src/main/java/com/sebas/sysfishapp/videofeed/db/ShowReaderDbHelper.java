package com.sebas.sysfishapp.videofeed.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sebas.sysfishapp.videofeed.model.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 28/2/18.
 */

public class ShowReaderDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ShowReaderContract.ShowEntry.TABLE_SHOWS + " (" +
                    ShowReaderContract.ShowEntry.COLUMN_ID + " LONG PRIMARY KEY," +
                    ShowReaderContract.ShowEntry.COLUMN_NAME + " TEXT," +
                    ShowReaderContract.ShowEntry.COLUMN_VOTE_COUNT + " TEXT," +
                    ShowReaderContract.ShowEntry.COLUMN_FIRST_AIR_DATE + " TEXT," +
                    ShowReaderContract.ShowEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT," +
                    ShowReaderContract.ShowEntry.COLUMN_VOTE_AVERAGE + " TEXT," +
                    ShowReaderContract.ShowEntry.COLUMN_OVERVIEW + " TEXT," +
                    ShowReaderContract.ShowEntry.COLUMN_POSTER_PATH + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ShowReaderContract.ShowEntry.TABLE_SHOWS;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ShowReader.db";

    public ShowReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void saveShowsInDB(final List<Show> shows) {
        final SQLiteDatabase db = getWritableDatabase();
        for (final Show show : shows) {
            final ContentValues values = new ContentValues();
            values.put(ShowReaderContract.ShowEntry.COLUMN_ID, show.getId());
            values.put(ShowReaderContract.ShowEntry.COLUMN_NAME, show.getName());
            values.put(ShowReaderContract.ShowEntry.COLUMN_VOTE_COUNT, show.getVoteCount());
            values.put(ShowReaderContract.ShowEntry.COLUMN_FIRST_AIR_DATE, show.getFirstAirDate());
            values.put(ShowReaderContract.ShowEntry.COLUMN_ORIGINAL_LANGUAGE, show.getOriginalLanguage());
            values.put(ShowReaderContract.ShowEntry.COLUMN_VOTE_AVERAGE, show.getVoteAverage());
            values.put(ShowReaderContract.ShowEntry.COLUMN_OVERVIEW, show.getOverview());
            values.put(ShowReaderContract.ShowEntry.COLUMN_POSTER_PATH, show.getPosterPath());

            // Insert the new row, returning the primary key value of the new row
            db.insert(ShowReaderContract.ShowEntry.TABLE_SHOWS, null, values);
        }
    }

    public List<Show> getShowsFromDB() {
        String[] projection = {
                ShowReaderContract.ShowEntry.COLUMN_ID,
                ShowReaderContract.ShowEntry.COLUMN_NAME,
                ShowReaderContract.ShowEntry.COLUMN_VOTE_COUNT,
                ShowReaderContract.ShowEntry.COLUMN_FIRST_AIR_DATE,
                ShowReaderContract.ShowEntry.COLUMN_ORIGINAL_LANGUAGE,
                ShowReaderContract.ShowEntry.COLUMN_VOTE_AVERAGE,
                ShowReaderContract.ShowEntry.COLUMN_OVERVIEW,
                ShowReaderContract.ShowEntry.COLUMN_POSTER_PATH
        };
        List<Show> shows = new ArrayList<>();
        final Cursor cursor = getWritableDatabase().query(ShowReaderContract.ShowEntry.TABLE_SHOWS,
                projection, null, null, null, null, null);
        while (cursor.moveToNext()) {
            final Show show = new Show();
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ShowReaderContract.ShowEntry.COLUMN_ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(ShowReaderContract.ShowEntry.COLUMN_NAME));
            String voteCount = cursor.getString(
                    cursor.getColumnIndexOrThrow(ShowReaderContract.ShowEntry.COLUMN_VOTE_COUNT));
            String firstAirDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(ShowReaderContract.ShowEntry.COLUMN_FIRST_AIR_DATE));
            String originalLanguage = cursor.getString(
                    cursor.getColumnIndexOrThrow(ShowReaderContract.ShowEntry.COLUMN_ORIGINAL_LANGUAGE));
            String voteAverage = cursor.getString(
                    cursor.getColumnIndexOrThrow(ShowReaderContract.ShowEntry.COLUMN_VOTE_AVERAGE));
            String overview = cursor.getString(
                    cursor.getColumnIndexOrThrow(ShowReaderContract.ShowEntry.COLUMN_OVERVIEW));
            String posterPath = cursor.getString(
                    cursor.getColumnIndexOrThrow(ShowReaderContract.ShowEntry.COLUMN_POSTER_PATH));
            show.setId(itemId);
            show.setName(name);
            show.setVoteCount(voteCount);
            show.setFirstAirDate(firstAirDate);
            show.setOriginalLanguage(originalLanguage);
            show.setVoteAverage(voteAverage);
            show.setOverview(overview);
            show.setPosterPath(posterPath);
            shows.add(show);
        }

        return shows;
    }
}
