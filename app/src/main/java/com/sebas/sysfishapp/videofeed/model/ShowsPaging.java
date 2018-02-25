package com.sebas.sysfishapp.videofeed.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class ShowsPaging implements Parcelable {
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;
    private List<Show> results;

    protected ShowsPaging(Parcel in) {
        page = in.readInt();
        totalResults = in.readInt();
        totalPages = in.readInt();
        results = in.createTypedArrayList(Show.CREATOR);
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

    public static final Creator<ShowsPaging> CREATOR = new Creator<ShowsPaging>() {
        @Override
        public ShowsPaging createFromParcel(Parcel in) {
            return new ShowsPaging(in);
        }

        @Override
        public ShowsPaging[] newArray(int size) {
            return new ShowsPaging[size];
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


    public List<Show> getResults() {
        return results;
    }
}
