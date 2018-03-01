package com.sebas.sysfishapp.videofeed;

import com.sebas.sysfishapp.videofeed.main.MainPresenter;
import com.sebas.sysfishapp.videofeed.main.MainView;
import com.sebas.sysfishapp.videofeed.model.Show;
import com.sebas.sysfishapp.videofeed.model.ShowsPaging;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastiandeira on 26/2/18.
 */

public class MainPresenterTest {
    private MainPresenter mainPresenter;
    private MainView mainView;

    @Before
    public void setUp() {
        mainPresenter = Mockito.spy(new MainPresenter());
        mainView = Mockito.mock(MainView.class);
        Mockito.when(mainPresenter.getView()).thenReturn(mainView);
    }

    @Test
    public void testSuccess() throws Exception {
        final ShowsPaging showsPaging = Mockito.mock(ShowsPaging.class);
        final List<Show> list = new ArrayList<>();
        Mockito.when(showsPaging.getResults()).thenReturn(list);
        Mockito.when(showsPaging.getPage()).thenReturn(0);
        mainPresenter.onSuccess(showsPaging);
        Mockito.verify(mainView).addDataToView(list);

    }

}
