package com.android.vlad.movieappmvp.movies;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.android.vlad.movieappmvp.R;
import com.android.vlad.movieappmvp.utils.Injection;

public class MoviesActivity extends AppCompatActivity {

    private MoviesPresenter moviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        MoviesFragment moviesFragment =
            (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (moviesFragment == null) {
            moviesFragment = MoviesFragment.newInstance();

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, moviesFragment);
            transaction.commit();
        }

        moviesPresenter =
            new MoviesPresenter(Injection.provideMoviesRepository(getApplicationContext()), moviesFragment);
    }
}
