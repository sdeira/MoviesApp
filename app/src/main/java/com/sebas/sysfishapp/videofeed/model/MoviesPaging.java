package com.sebas.sysfishapp.videofeed.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MoviesPaging implements Parcelable {
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;
    private List<Movie> results;

    protected MoviesPaging(Parcel in) {
        page = in.readInt();
        totalResults = in.readInt();
        totalPages = in.readInt();
        results = in.createTypedArrayList(Movie.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(totalResults);
        dest.writeInt(totalPages);
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MoviesPaging> CREATOR = new Creator<MoviesPaging>() {
        @Override
        public MoviesPaging createFromParcel(Parcel in) {
            return new MoviesPaging(in);
        }

        @Override
        public MoviesPaging[] newArray(int size) {
            return new MoviesPaging[size];
        }
    };

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Is the last page of the movies
     * @return true if yes, otherwise false
     */
    public boolean isLastPage() {
        return page == totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }
}