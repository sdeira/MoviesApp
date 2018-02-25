package com.sebas.sysfishapp.videofeed.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.main.OnItemClickListener;
import com.sebas.sysfishapp.videofeed.model.Movie;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class DetailActivity extends Activity implements DetailView, OnItemClickListener {
    public final static String EXTRA_MOVIE = "extra_moview";

    private DetailAdapter adapter;

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
        presenter.initView(this, movie);
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

    @Override
    public void setupRelatedMovies(List<Movie> relatedMovies) {
        final RecyclerView recyclerView = findViewById(R.id.detail_movie_related_movies_recycle_view);
        adapter = new DetailAdapter();
        adapter.setMovies(relatedMovies);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onMovieClick(Movie movie) {
        final Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}
