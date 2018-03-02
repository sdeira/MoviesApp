package com.sebas.sysfishapp.videofeed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.sebas.sysfishapp.videofeed.main.DefaultViewHolder;
import com.sebas.sysfishapp.videofeed.model.Show;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 1/3/18.
 */
public class BaseAdapterTest extends AbstractRobolectricUnitTest {
    private static final int SHOW_TYPE = 22;
    private static final int LOADING_TYPE = 23;
    private static final int NO_MORE_SHOWS = 24;
    private BaseAdapter baseAdapter;

    @Override
    public void setUp() {
        super.setUp();
        baseAdapter = new TestAdapter();
    }

    @Test
    public void testItemCount() {
        final List<Show> showList = new ArrayList<>();
        final Show show1 = Mockito.mock(Show.class);
        Mockito.when(show1.getName()).thenReturn("Show1");
        showList.add(show1);
        final Show show2 = Mockito.mock(Show.class);
        Mockito.when(show2.getName()).thenReturn("Show2");
        showList.add(show2);

        baseAdapter.setShows(showList);
        //We expected 2 items and the loading
        Assert.assertEquals(3, baseAdapter.getItemCount());
    }

    @Test
    public void testCreatingViewHolders() {
        final LinearLayout viewgroup = new LinearLayout(getContext());
        final RecyclerView.ViewHolder viewHolder = baseAdapter.onCreateViewHolder(viewgroup, SHOW_TYPE);
        Assert.assertNotNull(viewHolder.itemView.findViewById(R.id.show_image));
        final RecyclerView.ViewHolder loadingViewHolder = baseAdapter.onCreateViewHolder(viewgroup, LOADING_TYPE);
        Assert.assertNotNull(loadingViewHolder.itemView.findViewById(R.id.loading_row_progress_bar));
        final RecyclerView.ViewHolder noShowsViewHolder = baseAdapter.onCreateViewHolder(viewgroup, NO_MORE_SHOWS);
        Assert.assertNotNull(noShowsViewHolder.itemView.findViewById(R.id.no_more_shows));
    }

    private class TestAdapter extends BaseAdapter {

        @Override
        protected void onBindChildViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        protected int getViewLayout() {
            return R.layout.show_row;
        }

        @Override
        protected RecyclerView.ViewHolder onCreateChildViewHolder(View itemView) {
            return new DefaultViewHolder(itemView);
        }
    }
}