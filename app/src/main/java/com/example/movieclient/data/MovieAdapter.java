package com.example.movieclient.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieclient.R;
import com.example.movieclient.model.MovieEntity;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieviewHolder> {

    private Context context;
    private ArrayList<MovieEntity> movies;

    public MovieAdapter(Context context, ArrayList<MovieEntity> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieviewHolder holder, int position) {
        MovieEntity currentMovies = movies.get(position);
        String text = currentMovies.getText();
        String time = currentMovies.getTime();

        holder.title.setText(text);
        holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


            public class MovieviewHolder extends RecyclerView.ViewHolder{

                ImageView posterImageView;
                TextView title;
                TextView time;

                public MovieviewHolder(@NonNull View itemView) {
                    super(itemView);

                    posterImageView = itemView.findViewById(R.id.posterImageView);
                    title = itemView.findViewById(R.id.titleTextView);
                    time = itemView.findViewById(R.id.time);

                }
            }
}
