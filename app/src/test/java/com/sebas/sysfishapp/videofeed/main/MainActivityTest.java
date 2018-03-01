package com.sebas.sysfishapp.videofeed.main;

import android.app.Activity;
import android.app.Application;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.soloader.SoLoader;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.model.Show;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 28/2/18.
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Before
    public void setUp() {
        SoLoader.setInTestMode();
        Fresco.initialize(RuntimeEnvironment.application);
    }

    @Test
    public void testRecyclerData() {
        final MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        final RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_main_activity);
        final List<Show> list = new ArrayList<Show>();
        final Show show = Mockito.mock(Show.class);
        Mockito.when(show.getVoteAverage()).thenReturn("8");

        list.add(show);
        activity.addDataToView(list);
        // We expect one more item in the recycler view because the loading is showing
        Assert.assertEquals(2, recyclerView.getAdapter().getItemCount());
    }

}