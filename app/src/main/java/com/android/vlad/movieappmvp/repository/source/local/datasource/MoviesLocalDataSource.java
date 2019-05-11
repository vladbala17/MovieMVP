package com.android.vlad.movieappmvp.repository.source.local.datasource;

import androidx.annotation.VisibleForTesting;
import com.android.vlad.movieappmvp.repository.model.Movie;
import com.android.vlad.movieappmvp.repository.source.MoviesDataSource;
import com.android.vlad.movieappmvp.repository.source.local.dao.MoviesDao;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

public class MoviesLocalDataSource implements MoviesDataSource {

    private static volatile MoviesLocalDataSource INSTANCE;
    private MoviesDao moviesDao;

    private MoviesLocalDataSource(MoviesDao moviesDao) {
        this.moviesDao = moviesDao;
    }

    public static MoviesLocalDataSource getInstance(MoviesDao moviesDao) {
        if (INSTANCE == null) {
            synchronized (MoviesLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MoviesLocalDataSource(moviesDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<Movie>> getMovies() {
        return moviesDao.getMovies();
    }

    @Override
    public Flowable<Movie> getMovieById(String movieId) {
        return moviesDao.getMovieById(movieId);
    }

    @Override
    public Completable insertMovie(Movie movie) {
        return moviesDao.insertMovie(movie);
    }

    @VisibleForTesting
    public static void clearInstance() {
        INSTANCE = null;
    }
}
