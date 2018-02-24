package com.sebas.sysfishapp.videofeed.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.Settings;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    /**
     * Constructor
     * @param itemView view inflated in the view holder
     */
    public MovieViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(final String imageUri, final String name, final String airDate, final String overview, final String votes) {
        final SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.movie_image);
        final TextView textViewName =  itemView.findViewById(R.id.movie_name);
        final TextView textViewAirDate = itemView.findViewById(R.id.air_date);
        final TextView textViewOverview = itemView.findViewById(R.id.overview);
        final TextView textViewVotes = itemView.findViewById(R.id.movie_votes);
        simpleDraweeView.setImageURI(Settings.MOVIE_IMAGE_URL + imageUri);
        textViewName.setText(name);
        textViewAirDate.setText(airDate);
        textViewOverview.setText(overview);
        //TODO: add votos to string resources with the language
        textViewVotes.setText("Votos: " + votes);
    }
}
