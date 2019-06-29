package com.android.vlad.movieappmvp.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.vlad.movieappmvp.R;
import com.android.vlad.movieappmvp.repository.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment implements MoviesContract.View {

    private MoviesContract.Presenter moviePresenter;
    private MoviesAdapter moviesAdapter;

    public MoviesFragment() {}

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesAdapter = new MoviesAdapter(new ArrayList<Movie>(0), movieItemListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        moviePresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        moviePresenter.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);

        RecyclerView movieRecyclerView = root.findViewById(R.id.movie_list);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        movieRecyclerView.setAdapter(moviesAdapter);
        return root;
    }

    @Override
    public void showMovies(List<Movie> movies) {
        moviesAdapter.replaceData(movies);
    }

    @Override
    public void setPresenter(MoviesContract.Presenter presenter) {
        this.moviePresenter = presenter;
    }

    MovieItemListener movieItemListener = clickedMovie -> {
        //moviePresenter.openMovieDetails(clickedMovie);
    };
    private static class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

        private List<Movie> movieList;
        private MovieItemListener movieItemListener;

        public MoviesAdapter(List<Movie> movieList, MovieItemListener movieItemListener) {
            setList(movieList);
            this.movieItemListener = movieItemListener;
        }

        public void replaceData(List<Movie> movies) {
            setList(movies);
            notifyDataSetChanged();
        }
        private void setList(List<Movie> movieList) {
            this.movieList = movieList;
        }

        @NonNull
        @Override
        public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rowView = parent;
            LayoutInflater inflater = LayoutInflater.from(rowView.getContext());
            rowView = inflater.inflate(R.layout.movie_item, parent, false);
            return new MovieHolder(rowView);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
            final Movie movie = movieList.get(position);

            holder.movieTitle.setText(movie.getTitle());

            //rowView.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View view) {
            //        movieItemListener.onMovieClick(movie);
            //    }
            //});
        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }

        private class MovieHolder extends RecyclerView.ViewHolder {
            private ImageView moviePoster;
            private TextView movieTitle;
            private TextView movieGenre;
            private TextView movieRating;

            public MovieHolder(@NonNull View itemView) {
                super(itemView);
                moviePoster = itemView.findViewById(R.id.movie_poster_image);
                movieTitle = itemView.findViewById(R.id.movie_title_text);
                movieGenre = itemView.findViewById(R.id.movie_genre_text);
                movieRating = itemView.findViewById(R.id.movie_rating_text);
            }
        }
    }

    public interface MovieItemListener {
        void onMovieClick(Movie clickedMovie);
    }
}
