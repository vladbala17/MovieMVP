package com.android.vlad.movieappmvp.repository.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String movieId;

    @NonNull
    private String voteAverage;

    @NonNull
    private String title;

    @NonNull
    private String genres;

    public Movie(@NonNull String movieId, @NonNull String voteAverage, @NonNull String title, @NonNull String genres) {
        this.movieId = movieId;
        this.voteAverage = voteAverage;
        this.title = title;
        this.genres = genres;
    }

    @NonNull
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(@NonNull String movieId) {
        this.movieId = movieId;
    }

    @NonNull
    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(@NonNull String voteAverage) {
        this.voteAverage = voteAverage;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getGenres() {
        return genres;
    }

    public void setGenres(@NonNull String genres) {
        this.genres = genres;
    }
}
