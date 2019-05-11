package com.android.vlad.movieappmvp.repository.source;

import com.android.vlad.movieappmvp.repository.model.Movie;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

public interface MoviesDataSource {

    Flowable<List<Movie>> getMovies();

    Flowable<Movie> getMovieById(String movieId);

    Completable insertMovie(Movie movie);
}
