package com.android.vlad.movieappmvp.repository.source.local;

import androidx.test.runner.AndroidJUnit4;
import com.android.vlad.movieappmvp.repository.model.Genre;
import io.reactivex.functions.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GenreDaoTest extends AbstractDaoTest {

    @Test
    public void insertAndGetGenreById() {
        final Genre testGenre = new Genre("111", "drama");
        database.getGenreDao().insertGenre(testGenre).blockingAwait();

        database.getGenreDao().getGenreById(testGenre.getGenreId()).test().assertValue(new Predicate<Genre>() {
            @Override
            public boolean test(Genre genre) throws Exception {
                return genre.getGenreId().equals(testGenre.getGenreId());
            }
        });
    }
}
