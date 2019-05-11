package com.android.vlad.movieappmvp.repository.source.local;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.android.vlad.movieappmvp.repository.model.Movie;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

public interface MoviesDao {

    @Query("SELECT * FROM movies")
    Flowable<List<Movie>> getMovies();

    @Query("SELECT * FROM movies WHERE id= :movieId")
    Flowable<Movie> getMovieById(String movieId);

    @Insert
    Completable insertMovie(Movie movie);

    @Update
    Completable updateMovie(Movie movie);
}
