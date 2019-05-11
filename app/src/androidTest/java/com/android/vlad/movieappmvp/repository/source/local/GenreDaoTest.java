package com.android.vlad.movieappmvp.repository.source.local;

import androidx.test.runner.AndroidJUnit4;
import com.android.vlad.movieappmvp.repository.model.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GenreDaoTest extends AbstractDaoTest {

    private static final Genre GENRE = new Genre("111", "drama");

    @Test
    public void insertAndGetGenreById() {

        database.getGenreDao().insertGenre(GENRE).blockingAwait();

        database.getGenreDao().getGenreById(GENRE.getGenreId()).test().assertValue(
            genre -> genre.getGenreId().equals(GENRE.getGenreId()));
    }
}
