package com.example.movieclient;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieclient.data.MovieAdapter;
import com.example.movieclient.model.MovieEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private ArrayList<MovieEntity> movies;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movies = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);


        textView = findViewById(R.id.textTest);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Обновление", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if (!movies.isEmpty()) {
                    movies.clear();
                }
                getData();
            }
        });
    }

    private void getData() {
        //String url = "http://192.168.0.219:8080/message";
        String url1 = "http://192.168.0.219:8080/api/guds";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("content");
                    //Log.d("111111", String.valueOf(jsonArray.length()));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String text = jsonObject.getString("name");
                        //String time = jsonObject.getString("time");
                        //Log.d("222222", String.valueOf(jsonObject.getString("name")));

                        MovieEntity movie = new MovieEntity();
                        movie.setText(text);
                        movie.setTime("00-12");
                        //textView.setText(text);
                        //Log.d("33333333", movie.getText());

                        movies.add(movie);
                    }
                    movieAdapter = new MovieAdapter(MainActivity.this, movies);
                    recyclerView.setAdapter(movieAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
