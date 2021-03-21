package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class VideoActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
private YouTubePlayerView youTubePlayerView;
Bundle bundleMovieVideo;
int receivedMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_video);
        bundleMovieVideo = getIntent().getExtras();
        receivedMovie = bundleMovieVideo.getInt("trailerMessage");
        RequestQueue queue= Volley.newRequestQueue(this);

        String url="https://api.themoviedb.org/3/movie/"+receivedMovie+"/videos?api_key=fc73b580050359be605bc05ef910989c&language=en-US";

        JsonObjectRequest request=new JsonObjectRequest(url,null,this,this);
        queue.add(request);

        youTubePlayerView=findViewById(R.id.activity_main_youtubePlayerView);
        youTubePlayerView = findViewById(R.id.activity_main_youtubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = trailerKey;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

    }

    @Override
    public void onErrorResponse(VolleyError error) {


    }
String trailerKey;
    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray results= response.getJSONArray("results");
            for (int i = 0; i <1; i++) {
                JSONObject jsonObject = results.getJSONObject(i);
                trailerKey=jsonObject.getString("key");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}