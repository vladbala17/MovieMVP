package com.android.vlad.movieappmvp.repository.source.remote;

import com.android.vlad.movieappmvp.BuildConfig;
import com.android.vlad.movieappmvp.repository.model.Movie;
import com.android.vlad.movieappmvp.repository.source.MoviesDataSource;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MoviesRemoteDataSource implements MoviesDataSource {


    private static MoviesRemoteDataSource INSTANCE;

    private MovieWebService movieWebService;

    private MoviesRemoteDataSource(MovieWebService movieWebService) {
        this.movieWebService = movieWebService;
    }

    public static MoviesRemoteDataSource getInstance(MovieWebService movieWebService) {
        if (INSTANCE == null) {
            INSTANCE = new MoviesRemoteDataSource(movieWebService);
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<Movie>> getMovies() {
       return movieWebService.getTopRatedMovies(BuildConfig.ApiKey).subscribeOn(Schedulers.io()).map(
           MovieResponse::getResults);

    }

    @Override
    public Flowable<Movie> getMovieById(String movieId) {
        return movieWebService.getMovieById(movieId, BuildConfig.ApiKey).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable insertMovie(Movie movie) {
        return null;
    }
}
