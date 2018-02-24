package com.sebas.sysfishapp.videofeed.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class Movie implements Parcelable {
    private String name;

    @SerializedName("vote_count")
    private String voteCount;

    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("original_langueage")
    private String originalLanguage;

    @SerializedName("vote_average")
    private String voteAverage;
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;


    protected Movie(Parcel in) {
        name = in.readString();
        voteCount = in.readString();
        firstAirDate = in.readString();
        originalLanguage = in.readString();
        voteAverage = in.readString();
        overview = in.readString();
        posterPath = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(voteCount);
        parcel.writeString(firstAirDate);
        parcel.writeString(originalLanguage);
        parcel.writeString(voteAverage);
        parcel.writeString(overview);
        parcel.writeString(posterPath);
    }
}
