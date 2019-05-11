package com.android.vlad.movieappmvp.repository.source.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.android.vlad.movieappmvp.repository.model.Genre;
import com.android.vlad.movieappmvp.repository.model.Movie;

@Database(entities = { Movie.class, Genre.class }, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase INSTANCE;

    private static final Object sLock = new Object();

    public static MovieDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "Movies.db").build();
            }
            return INSTANCE;
        }
    }
}
