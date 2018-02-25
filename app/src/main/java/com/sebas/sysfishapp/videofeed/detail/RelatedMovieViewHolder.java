package com.sebas.sysfishapp.videofeed.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.Settings;

/**
 * Created by sebastiandeira on 25/2/18.
 */

public class RelatedMovieViewHolder extends RecyclerView.ViewHolder {

    /**
     * Constructor
     * @param itemView view inflated in the view holder
     */
    public RelatedMovieViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(final String imageUri, final String name) {
        final SimpleDraweeView simpleDraweeView = itemView.findViewById(R.id.related_movie_image);
        final TextView textViewName =  itemView.findViewById(R.id.related_movie_name);

        simpleDraweeView.setImageURI(Settings.MOVIE_IMAGE_URL + imageUri);
        textViewName.setText(name);
    }
}
