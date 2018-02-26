package com.sebas.sysfishapp.videofeed.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.detail.DetailActivity;
import com.sebas.sysfishapp.videofeed.model.Show;

import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initRecyclerView();

        presenter = new MainPresenter(this);
        presenter.loadShows(this);

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
            presenter.loadShows(this);
        }
    }

    @Override
    public void addDataToView(List<Show> data) {
        mainAdapter.addShows(data);
    }

    @Override
    public void setDataToView(List<Show> data) {
        mainAdapter.setShows(data);
    }

    @Override
    public void showLoading(boolean showLoading) {
        final ProgressBar loading = findViewById(R.id.main_activity_loading);
        loading.setVisibility(showLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError() {
        final TextView error = findViewById(R.id.main_activity_error);
        error.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowClick(Show show) {
        final Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_SHOW, show);
        startActivity(intent);
    }
}
