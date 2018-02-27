package com.sebas.sysfishapp.videofeed.detail;

import android.view.View;

import com.sebas.sysfishapp.videofeed.BaseAdapter;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.model.Show;

/**
 * Created by sebastiandeira on 25/2/18.
 */

public class DetailAdapter extends BaseAdapter<RelatedShowViewHolder> {

    /**
     * Default Constructor
     */
    public DetailAdapter() {
    }

    @Override
    protected void onBindChildViewHolder(RelatedShowViewHolder holder, int position) {
        final Show show = list.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenerWeakReference.get().onShowClick(show);
            }
        });
        holder.bind(show.getPosterPath(), show.getName());
    }

    @Override
    protected int getViewLayout() {
        return R.layout.related_show_row;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    protected RelatedShowViewHolder onCreateChildViewHolder(View itemView) {
        return new RelatedShowViewHolder(itemView);
    }
}
