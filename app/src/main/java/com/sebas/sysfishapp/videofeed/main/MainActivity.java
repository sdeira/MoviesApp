package com.sebas.sysfishapp.videofeed.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sebas.sysfishapp.videofeed.R;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainActivity extends Activity implements MainView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final MainPresenter presenter = new MainPresenter(this);

    }

}
