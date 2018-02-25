package com.sebas.sysfishapp.videofeed.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.detail.DetailActivity;
import com.sebas.sysfishapp.videofeed.model.Movie;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainActivity extends Activity implements MainView, OnItemClickListener {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initRecyclerView();

        presenter = new MainPresenter(this);
        presenter.loadMovies(this);

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_main_activity);
        mainAdapter = new MainAdapter();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        mainAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    loadMoreIfNeeded(linearLayoutManager);
                }
            }
        });
    }

    private void loadMoreIfNeeded(final LinearLayoutManager linearLayoutManager) {
        final int itemCount = linearLayoutManager.getItemCount();
        final int lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
        if (lastVisiblePosition == itemCount - 1) {
            presenter.loadMovies(this);
        }
    }

    @Override
    public void addDataToView(List<Movie> data) {
        mainAdapter.addMovies(data);
    }

    @Override
    public void setDataToView(List<Movie> data) {
        mainAdapter.setMovies(data);
    }

    @Override
    public void onMovieClick(Movie movie) {
        final Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
