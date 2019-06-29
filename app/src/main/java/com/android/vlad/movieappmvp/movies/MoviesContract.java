package com.android.vlad.movieappmvp.movies;

import com.android.vlad.movieappmvp.BasePresenter;
import com.android.vlad.movieappmvp.BaseView;
import com.android.vlad.movieappmvp.repository.model.Movie;
import java.util.List;

public interface MoviesContract {

    interface View extends BaseView<Presenter> {
        void showMovies(List<Movie> movies);
    }

    interface Presenter extends BasePresenter {
        void loadMovies();
    }
}
