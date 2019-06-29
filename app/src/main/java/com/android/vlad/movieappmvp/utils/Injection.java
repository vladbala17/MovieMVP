package com.android.vlad.movieappmvp.utils;

import android.content.Context;
import com.android.vlad.movieappmvp.repository.MovieRepository;
import com.android.vlad.movieappmvp.repository.source.local.MovieDatabase;
import com.android.vlad.movieappmvp.repository.source.local.datasource.MoviesLocalDataSource;
import com.android.vlad.movieappmvp.repository.source.remote.MovieWebService;
import com.android.vlad.movieappmvp.repository.source.remote.MoviesRemoteDataSource;
import com.android.vlad.movieappmvp.repository.source.remote.RetrofitMovieClient;

public class Injection {

    public static MovieRepository provideMoviesRepository(Context context) {
        MovieDatabase database = MovieDatabase.getInstance(context);
        return MovieRepository.getInstance(MoviesLocalDataSource.getInstance(database.getMovieDao()),
            MoviesRemoteDataSource.getInstance(RetrofitMovieClient.getInstance().create(MovieWebService.class)));
    }
}
