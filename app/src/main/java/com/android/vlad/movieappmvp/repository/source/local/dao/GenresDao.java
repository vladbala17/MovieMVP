package com.android.vlad.movieappmvp.repository.source.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.android.vlad.movieappmvp.repository.model.Genre;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface GenresDao {

    @Insert
    Completable insertGenre(Genre genre);

    @Query("SELECT * FROM genres WHERE id= :genreId")
    Flowable<Genre> getGenreById(String genreId);
}
