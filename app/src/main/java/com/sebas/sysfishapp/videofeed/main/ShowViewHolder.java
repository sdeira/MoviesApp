package com.sebas.sysfishapp.videofeed.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.Settings;
import com.sebas.sysfishapp.videofeed.widgets.ContentReview;

import java.math.BigDecimal;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class ShowViewHolder extends RecyclerView.ViewHolder {

    /**
     * Constructor
     * @param itemView view inflated in the view holder
     */
    public ShowViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(final String imageUri, final String name, final String airDate, final String overview, final String votes) {
        final SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.show_image);
        final TextView textViewName =  itemView.findViewById(R.id.show_name);
        final TextView textViewAirDate = itemView.findViewById(R.id.air_date);
        final TextView textViewOverview = itemView.findViewById(R.id.overview);
        final ContentReview contentReview = itemView.findViewById(R.id.main_activity_content_review);
        simpleDraweeView.setImageURI(Settings.SHOW_IMAGE_URL + imageUri);
        textViewName.setText(name);
        textViewAirDate.setText(airDate);
        textViewOverview.setText(overview);
        final BigDecimal bigDecimal = new BigDecimal(votes);
        final BigDecimal half = bigDecimal.divide(new BigDecimal("2"));
        contentReview.setReview(half.intValue());
    }
}
