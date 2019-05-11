package com.android.vlad.movieappmvp.repository.source.local;

import androidx.test.runner.AndroidJUnit4;
import com.android.vlad.movieappmvp.repository.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MovieDaoTest extends AbstractDaoTest {
    private static final Movie MOVIE = new Movie("123", "9.5", "Avengers", "26,66");

    @Test
    public void insertAndGetMovieById() {
        database.getMovieDao().insertMovie(MOVIE).blockingAwait();

        database.getMovieDao().getMovieById(MOVIE.getMovieId()).test().assertValue(
            movie -> movie.getMovieId().equals(MOVIE.getMovieId()));
    }

}
