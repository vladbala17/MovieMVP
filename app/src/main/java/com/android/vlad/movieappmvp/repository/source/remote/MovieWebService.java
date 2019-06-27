package com.android.vlad.movieappmvp.repository.source.remote;

import com.android.vlad.movieappmvp.repository.model.Movie;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieWebService {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    @GET("3/movie/top_rated")
    Flowable<MovieResponse> getTopRatedMovies(@Query("api_key") String key);

    @GET("movie/{movie_id}")
    Flowable<Movie> getMovieById(@Path("movie_id") String movieId, @Query("api_key") String key);
}
