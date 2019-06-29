package com.android.vlad.movieappmvp.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.vlad.movieappmvp.repository.model.Movie;
import com.android.vlad.movieappmvp.repository.source.MoviesDataSource;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements MoviesDataSource {

    @Nullable
    private static MovieRepository INSTANCE = null;

    @NonNull
    private final MoviesDataSource localDataSource;

    @NonNull
    private final MoviesDataSource remoteDataSource;

    private List<Movie> cachedMovies;

    private MovieRepository(MoviesDataSource movieLocalDataSource, MoviesDataSource movieRemoteDataSource) {
        this.localDataSource = movieLocalDataSource;
        this.remoteDataSource = movieRemoteDataSource;
    }

    public static MovieRepository getInstance(MoviesDataSource movieLocalDataSource,
        MoviesDataSource movieRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository(movieLocalDataSource, movieRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<Movie>> getMovies() {
        if (cachedMovies != null) {
            return Flowable.fromIterable(cachedMovies).toList().toFlowable();
        } else {
            cachedMovies = new ArrayList<>();
        }

        Flowable<List<Movie>> remoteMovies = getAndSaveRemoteMovies();
        return remoteMovies;
    }

    private Flowable<List<Movie>> getAndSaveRemoteMovies() {
        return remoteDataSource.getMovies().flatMap(movies -> Flowable.fromIterable(movies).doOnNext(movie -> {
            localDataSource.insertMovie(movie);
            cachedMovies.add(movie);
        }).toList().toFlowable());
    }

    @Override
    public Flowable<Movie> getMovieById(String movieId) {
        Flowable<Movie> movie = remoteDataSource.getMovieById(movieId);
        return movie;
    }

    @Override
    public Completable insertMovie(Movie movie) {
        return null;
    }
}
