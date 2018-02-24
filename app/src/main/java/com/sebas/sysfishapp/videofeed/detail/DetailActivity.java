package com.sebas.sysfishapp.videofeed.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class DetailActivity extends Activity implements DetailView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DetailPresenter presenter = new DetailPresenter(this);
    }
}
