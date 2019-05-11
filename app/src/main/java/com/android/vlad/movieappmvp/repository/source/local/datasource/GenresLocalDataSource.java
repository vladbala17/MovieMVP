package com.android.vlad.movieappmvp.repository.source.local.datasource;

import androidx.annotation.VisibleForTesting;
import com.android.vlad.movieappmvp.repository.model.Genre;
import com.android.vlad.movieappmvp.repository.source.GenresDataSource;
import com.android.vlad.movieappmvp.repository.source.local.dao.GenresDao;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class GenresLocalDataSource implements GenresDataSource {

    private static volatile GenresLocalDataSource INSTANCE;
    private GenresDao genresDao;

    private GenresLocalDataSource(GenresDao genresDao) {
        this.genresDao = genresDao;
    }

    public static GenresLocalDataSource getInstance(GenresDao genresDao) {
        if (INSTANCE == null) {
            synchronized (GenresLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GenresLocalDataSource(genresDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Completable insertGenre(Genre genre) {
        return genresDao.insertGenre(genre);
    }

    @Override
    public Flowable<Genre> getGenreById(String genreId) {
        return genresDao.getGenreById(genreId);
    }

    @VisibleForTesting
    public static void clearInstance() {
        INSTANCE = null;
    }
}
