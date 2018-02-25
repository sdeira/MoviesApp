package com.sebas.sysfishapp.videofeed.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.model.Movie;

import org.w3c.dom.Text;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class DetailActivity extends Activity implements DetailView {
    public final static String EXTRA_MOVIE = "extra_moview";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Movie movie = null;
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = bundle.getParcelable(EXTRA_MOVIE);
        }

        final DetailPresenter presenter = new DetailPresenter(this);
        presenter.initView(movie);
    }

    @Override
    public void setupView(String imageUrl, String name, String firstAirDate, String overview, String averageVote) {
        final SimpleDraweeView simpleDraweeView = findViewById(R.id.detail_movie_image);
        final TextView nameTextView = findViewById(R.id.detail_movie_name);
        final TextView firstAirDateTextView = findViewById(R.id.detail_movie_air_datee);
        final TextView overviewTextView = findViewById(R.id.detail_movie_overview);
        final TextView averageVoteTextView = findViewById(R.id.detail_movie_vote_average) ;
        simpleDraweeView.setImageURI(imageUrl);
        nameTextView.setText(name);
        firstAirDateTextView.setText(firstAirDate);
        overviewTextView.setText(overview);
        averageVoteTextView.setText(averageVote);

    }
}
