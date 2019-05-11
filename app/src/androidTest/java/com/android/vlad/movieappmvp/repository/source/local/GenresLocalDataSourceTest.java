package com.android.vlad.movieappmvp.repository.source.local;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.android.vlad.movieappmvp.repository.model.Genre;
import com.android.vlad.movieappmvp.repository.source.local.dao.GenresDao;
import com.android.vlad.movieappmvp.repository.source.local.datasource.GenresLocalDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GenresLocalDataSourceTest {

    private static final Genre GENRE = new Genre("111", "drama");

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieDatabase movieDatabase =
        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), MovieDatabase.class)
            .allowMainThreadQueries()
            .build();

    private GenresLocalDataSource genresLocalDataSource;

    @Before
    public void setup() {
        GenresDao genresDao = movieDatabase.getGenreDao();

        GenresLocalDataSource.clearInstance();
        genresLocalDataSource = GenresLocalDataSource.getInstance(genresDao);
    }

    @After
    public void cleanUp() {
        movieDatabase.close();
        GenresLocalDataSource.clearInstance();
    }

    @Test
    public void saveGenreRetrieveGenre() {
        genresLocalDataSource.insertGenre(GENRE).blockingAwait();

        genresLocalDataSource.getGenreById(GENRE.getGenreId())
            .test()
            .assertValue(genre -> genre.getGenreId().equals(GENRE.getGenreId()));
    }
}
