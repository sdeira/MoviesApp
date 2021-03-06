package com.sebas.sysfishapp.videofeed.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sebas.sysfishapp.videofeed.R;
import com.sebas.sysfishapp.videofeed.db.ShowReaderDbHelper;
import com.sebas.sysfishapp.videofeed.detail.DetailActivity;
import com.sebas.sysfishapp.videofeed.model.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public class MainActivity extends AppCompatActivity implements MainView, OnItemClickListener {
    private static final String FIRST_LOAD_STATE = "first_load_state";
    private static final String SCROLL_POSITION = "scroll_position";
    private static final String PAGE_LIST_STATE = "page_list_state";
    private static final String SHOWS_EXTRA = "shows_extra";
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private MainPresenter presenter;
    private boolean isFirstLoad = true;
    private ShowReaderDbHelper dbHelper;
    private int scrollPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.main_activity);
        initRecyclerView();

        List<Show> shows = null;
        int page = 1;
        if (savedInstanceState != null) {
            isFirstLoad = savedInstanceState.getBoolean(FIRST_LOAD_STATE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                shows = getDbHelper().getShowsFromDB();
            } else {
                shows = (ArrayList) savedInstanceState.getSerializable(SHOWS_EXTRA);
            }
            page = savedInstanceState.getInt(PAGE_LIST_STATE);
            scrollPosition = savedInstanceState.getInt(SCROLL_POSITION);
        }
        if (presenter == null) {
            presenter = new MainPresenter(this);
        }

        if (isFirstLoad) {
            isFirstLoad = false;
            presenter.loadShows(this);
        } else {
            presenter.setPage(page);
            mainAdapter.setShows(shows);
            recyclerView.scrollToPosition(scrollPosition);
        }

    }

    public ShowReaderDbHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new ShowReaderDbHelper(this);
        }
        return dbHelper;
    }

    @Override
    protected void onDestroy() {
        getDbHelper().close();
        super.onDestroy();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_main_activity);
        if (mainAdapter == null) {
            mainAdapter = new MainAdapter();
        }
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
    public void setTotalShowsCount(int totalShowsCount) {
        mainAdapter.setServerShowsCount(totalShowsCount);
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

    @Override
    protected void onPause() {
        super.onPause();
        scrollPosition = ((LinearLayoutManager)
                recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getDbHelper().saveShowsInDB(mainAdapter.getList());
        } else {
            outState.putSerializable(SHOWS_EXTRA, (ArrayList) mainAdapter.getList());
        }
        outState.putInt(SCROLL_POSITION, scrollPosition);
        outState.putInt(PAGE_LIST_STATE, presenter.getPage());
        outState.putBoolean(FIRST_LOAD_STATE, isFirstLoad);
    }
}
