package com.android.vlad.movieappmvp.repository.source.local;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.android.vlad.movieappmvp.repository.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoviesLocalDataSourceTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private static final Movie MOVIE = new Movie("123", "9.5", "Avengers", "26,66");

    private MovieDatabase movieDatabase =
        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), MovieDatabase.class)
            .allowMainThreadQueries()
            .build();

    private MoviesLocalDataSource moviesLocalDataSource;

    @Before
    public void setup() {
        MoviesDao moviesDao = movieDatabase.getMovieDao();

        MoviesLocalDataSource.clearInstance();
        moviesLocalDataSource = MoviesLocalDataSource.getInstance(moviesDao);
    }

    @After
    public void cleanUp() {
        movieDatabase.close();
        MoviesLocalDataSource.clearInstance();
    }

    @Test
    public void saveMovieRetrieveMovie() {
        moviesLocalDataSource.insertMovie(MOVIE).blockingAwait();

        moviesLocalDataSource.getMovieById(MOVIE.getMovieId())
            .test()
            .assertValue(movie -> movie != null && movie.getMovieId().equals(MOVIE.getMovieId()));
    }
}
