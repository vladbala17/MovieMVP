package com.android.vlad.movieappmvp.repository.source.local;

import androidx.room.Insert;
import androidx.room.Query;
import com.android.vlad.movieappmvp.repository.model.Genre;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface GenresDao {

    @Insert
    Completable insertMovie(Genre genre);

    @Query("SELECT * FROM genres WHERE id= :genreId")
    Flowable<Genre> getGenreById(String genreId);
}
