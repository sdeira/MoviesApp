package com.sebas.sysfishapp.videofeed;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.soloader.SoLoader;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

/**
 * Created by sebastiandeira on 1/3/18.
 */
@RunWith(RobolectricTestRunner.class)
@Ignore
public class AbstractRobolectricUnitTest {

    @Before
    public void setUp() {
        SoLoader.setInTestMode();
        Fresco.initialize(getContext());
    }

    protected Context getContext() {
        return RuntimeEnvironment.application;
    }
}
