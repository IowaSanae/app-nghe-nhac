package tdtu.finalproject.homescreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tdtu.finalproject.homescreen.Model.Song;
import tdtu.finalproject.homescreen.R;

public class MusicPlayerActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewpager;
    TextView startTime, endTime;
    SeekBar songDuration;
    ImageButton likeButton, shuffleButton, previousButton, playButton, fastForwardButton, repeatButton;

    public static ArrayList<Song> arraySong = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        init();
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        arraySong.clear();
        if (intent != null) {
            if (intent.hasExtra("SongName")) {
                Song song = intent.getParcelableExtra("SongName");
                arraySong.add(song);
            }
            if (intent.hasExtra("Songs")) {
                ArrayList<Song> arrayListSong = intent.getParcelableArrayListExtra("Songs");
                arraySong = arrayListSong;
            }
        }
    }

    private void init() {
        toolbar = findViewById(R.id.ToolbarMusicPlayerScreen);
        viewpager = findViewById(R.id.ViewPagerMusicPlayerScreen);
        startTime = findViewById(R.id.tvStartTime);
        endTime = findViewById(R.id.tvEndTime);
        songDuration = findViewById(R.id.seekBar);
        likeButton = findViewById(R.id.likeButton);
        shuffleButton = findViewById(R.id.shuffleButton);
        previousButton = findViewById(R.id.previousButton);
        playButton = findViewById(R.id.playButton);
        fastForwardButton = findViewById(R.id.fastForwardButton);
        repeatButton = findViewById(R.id.repeatButton);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
    }
}