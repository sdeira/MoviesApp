package com.sebas.sysfishapp.videofeed.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sebas.sysfishapp.videofeed.AbstractRobolectricUnitTest;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.detail.DetailActivity;
import com.sebas.sysfishapp.videofeed.model.Show;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import java.util.ArrayList;
import java.util.List;

import static org.robolectric.Shadows.shadowOf;

/**
 * Created by sebastiandeira on 28/2/18.
 */
public class MainActivityTest extends AbstractRobolectricUnitTest {
    private MainActivity activity;

    @Override
    public void setUp() {
        super.setUp();
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testRecyclerViewItemCount() {
        final RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_main_activity);
        final List<Show> list = new ArrayList<Show>();
        final Show show = Mockito.mock(Show.class);
        Mockito.when(show.getVoteAverage()).thenReturn("8");

        list.add(show);
        activity.addDataToView(list);
        // We expect one more item in the recycler view because the loading is showing
        Assert.assertEquals(2, recyclerView.getAdapter().getItemCount());
    }

    @Test
    public void testShowingError() {
        activity.showError();
        final TextView error = activity.findViewById(R.id.main_activity_error);
        Assert.assertEquals(View.VISIBLE, error.getVisibility());
    }

    @Test
    public void testShowingLoading() {
        activity.showLoading(true);
        final ProgressBar loading = activity.findViewById(R.id.main_activity_loading);
        Assert.assertEquals(View.VISIBLE, loading.getVisibility());
    }

    @Test
    public void testClickingShow() {
        activity.onShowClick(new Show());
        final ShadowActivity shadowActivity = shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        Assert.assertEquals(shadowIntent.getIntentClass().getName(),
                DetailActivity.class.getName());
    }

}