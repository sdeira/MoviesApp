package com.sebas.sysfishapp.videofeed.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sebas.sysfishapp.videofeed.AbstractRobolectricUnitTest;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.widgets.ContentReview;


import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sebastiandeira on 1/3/18.
 */
public class ShowViewHolderTest extends AbstractRobolectricUnitTest {
    private ShowViewHolder showViewHolder;

    @Override
    public void setUp() {
        super.setUp();
        final Context context = getContext();
        final View itemView = LayoutInflater.from(context).inflate(R.layout.show_row,
                new LinearLayout(context), false);
        showViewHolder = new ShowViewHolder(itemView);
    }

    @Test
    public void testBinding() {

        final String name = "name";
        final String airDate = "airDate";
        final String overview = "overview";
        final String votes = "4";
        showViewHolder.bind("imageuri", name, airDate, overview, votes);
        final SimpleDraweeView simpleDraweeView = showViewHolder.itemView.findViewById(R.id.show_image);
        final TextView textViewName =  showViewHolder.itemView.findViewById(R.id.show_name);
        final TextView textViewAirDate = showViewHolder.itemView.findViewById(R.id.air_date);
        final TextView textViewOverview = showViewHolder.itemView.findViewById(R.id.overview);
        final ContentReview contentReview = showViewHolder.itemView.findViewById(R.id.main_activity_content_review);
        Assert.assertEquals(name, textViewName.getText().toString());
        Assert.assertEquals(airDate, textViewAirDate.getText().toString());
        Assert.assertEquals(overview, textViewOverview.getText().toString());
        Assert.assertEquals("2", String.valueOf(contentReview.getScore()));
    }
}