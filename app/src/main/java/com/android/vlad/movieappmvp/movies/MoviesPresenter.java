package com.android.vlad.movieappmvp.movies;

import androidx.annotation.NonNull;
import com.android.vlad.movieappmvp.repository.MovieRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviesPresenter implements MoviesContract.Presenter {

    @NonNull
    private final MovieRepository movieRepository;

    @NonNull
    private final MoviesContract.View moviesView;

    @NonNull
    private CompositeDisposable compositeDisposable;

    public MoviesPresenter(@NonNull MovieRepository movieRepository, MoviesContract.View moviesView) {
        this.movieRepository = movieRepository;
        this.moviesView = moviesView;

        this.compositeDisposable = new CompositeDisposable();
        moviesView.setPresenter(this);
    }

    @Override
    public void loadMovies() {
        compositeDisposable.clear();
        Disposable disposable = movieRepository.getMovies().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()).subscribe(moviesView::showMovies);

        compositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() {
        loadMovies();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
