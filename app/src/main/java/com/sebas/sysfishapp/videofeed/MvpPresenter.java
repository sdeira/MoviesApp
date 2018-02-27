package com.sebas.sysfishapp.videofeed;

/**
 * Created by sebastiandeira on 24/2/18.
 */

public abstract class MvpPresenter<V> {
    private V view;

    public MvpPresenter() {

    }

    public MvpPresenter(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
