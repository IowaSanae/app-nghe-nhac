package tdtu.finalproject.homescreen.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalproject.homescreen.Adapter.SongListAdapter;
import tdtu.finalproject.homescreen.Model.Playlist;
import tdtu.finalproject.homescreen.Model.Song;
import tdtu.finalproject.homescreen.R;
import tdtu.finalproject.homescreen.Service.APIService;
import tdtu.finalproject.homescreen.Service.Dataservice;

public class SongListActivity extends AppCompatActivity {

    Playlist playlist;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerSongList;
    FloatingActionButton floatingActionButton;
    ImageView imageViewSongList;
    ArrayList<Song> arraySong;
    SongListAdapter songListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbarlist);
        recyclerSongList = findViewById(R.id.recyclerSongList);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imageViewSongList = findViewById(R.id.imageViewSongList);

        init1();

        if (playlist != null && !playlist.getName().equals("")) {
            setValueInView(playlist.getName(), playlist.getImage());
            getDataPlaylist(playlist.getIdPlaylist());
        }
    }

    private void getDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.getSongList(idplaylist);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                arraySong = (ArrayList<Song>) response.body();
                songListAdapter = new SongListAdapter(SongListActivity.this, arraySong);
                recyclerSongList.setLayoutManager(new LinearLayoutManager(SongListActivity.this));
                recyclerSongList.setAdapter(songListAdapter);
                eventOnClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String name, String img) {
        collapsingToolbarLayout.setTitle(name);
        try {
            URL url = new URL(img);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(img).into(imageViewSongList);
    }

    private void init1() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("itemplaylist")) {
            playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
        }
    }

    private void eventOnClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(SongListActivity.this, MusicPlayerActivity.class);
            intent.putExtra("Songs", arraySong);
            startActivity(intent);
        });
    }
}