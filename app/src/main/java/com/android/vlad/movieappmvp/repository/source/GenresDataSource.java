package com.android.vlad.movieappmvp.repository.source;

import com.android.vlad.movieappmvp.repository.model.Genre;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface GenresDataSource {

    Completable insertGenre(Genre genre);

    Flowable<Genre> getGenreById(String genreId);
}
