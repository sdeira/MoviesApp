package com.sebas.sysfishapp.videofeed.main;

import android.view.View;

import com.sebas.sysfishapp.videofeed.BaseAdapter;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.model.Show;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainAdapter extends BaseAdapter<ShowViewHolder> {

    /**
     * Constructor
     */
    public MainAdapter() {

    }

    @Override
    protected void onBindChildViewHolder(ShowViewHolder holder, int position) {
        final Show show = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerWeakReference.get().onShowClick(show);
            }
        });
        holder.bind(show.getPosterPath(), show.getName(), show.getFirstAirDate(), show.getOverview(), show.getVoteAverage());
    }

    @Override
    protected int getViewLayout() {
        return R.layout.show_row;
    }

    @Override
    protected ShowViewHolder onCreateChildViewHolder(View itemView) {
        return new ShowViewHolder(itemView);
    }
}
