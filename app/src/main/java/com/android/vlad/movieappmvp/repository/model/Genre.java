package com.android.vlad.movieappmvp.repository.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "genres")
public class Genre {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String genreId;

    @NonNull
    private String name;

    public Genre(@NonNull String genreId, @NonNull String name) {
        this.genreId = genreId;
        this.name = name;
    }

    @NonNull
    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(@NonNull String genreId) {
        this.genreId = genreId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
