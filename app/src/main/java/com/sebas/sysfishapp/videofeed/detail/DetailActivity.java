package com.sebas.sysfishapp.videofeed.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.main.OnItemClickListener;
import com.sebas.sysfishapp.videofeed.model.Show;
import com.sebas.sysfishapp.videofeed.widgets.ContentReview;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class DetailActivity extends AppCompatActivity implements DetailView, OnItemClickListener {
    public final static String EXTRA_SHOW = "extra_show";

    private DetailAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Show show = null;
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            show = bundle.getParcelable(EXTRA_SHOW);
        }

        final DetailPresenter presenter = new DetailPresenter(this);
        presenter.initView(this, show);
    }

    @Override
    public void setupView(String imageUrl, String name, String firstAirDate, String overview, String averageVote) {
        final SimpleDraweeView simpleDraweeView = findViewById(R.id.detail_show_image);
        final TextView nameTextView = findViewById(R.id.detail_show_name);
        final TextView firstAirDateTextView = findViewById(R.id.detail_show_air_datee);
        final TextView overviewTextView = findViewById(R.id.detail_show_overview);
        final ContentReview averageVoteTextView = findViewById(R.id.detail_activity_review) ;
        simpleDraweeView.setImageURI(imageUrl);
        nameTextView.setText(name);
        firstAirDateTextView.setText(firstAirDate);
        overviewTextView.setText(overview);
        final BigDecimal bigDecimal = new BigDecimal(averageVote);
        final BigDecimal half = bigDecimal.divide(new BigDecimal("2"));
        averageVoteTextView.setReview(half.intValue());

    }

    @Override
    public void setupRelatedShows(List<Show> relatedShows) {
        final RecyclerView recyclerView = findViewById(R.id.detail_show_related_shows_recycle_view);
        adapter = new DetailAdapter();
        adapter.setShows(relatedShows);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideRelatedShows() {
        final TextView relatedShowsTitle = findViewById(R.id.detail_show_related_shows_title);
        final RecyclerView recyclerView = findViewById(R.id.detail_show_related_shows_recycle_view);
        relatedShowsTitle.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onShowClick(Show show) {
        final Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_SHOW, show);
        startActivity(intent);
    }
}
