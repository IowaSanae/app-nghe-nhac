package tdtu.finalproject.homescreen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import tdtu.finalproject.homescreen.Adapter.ViewPagerAdapter;
import tdtu.finalproject.homescreen.Fragment.Fragment_Cover;
import tdtu.finalproject.homescreen.Fragment.Fragment_Songlist;
import tdtu.finalproject.homescreen.Model.Song;
import tdtu.finalproject.homescreen.R;

public class MusicPlayerActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewpager;
    TextView startTime, endTime;
    SeekBar songDuration;
    ImageButton likeButton, shuffleButton, previousButton, playButton, fastForwardButton, repeatButton;

    public static ArrayList<Song> arraySong = new ArrayList<>();
    public static ViewPagerAdapter viewpagerMusicAdapter;
    Fragment_Songlist fragmentSonglist;
    Fragment_Cover fragmentCover;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean shuffle = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getDataFromIntent();
        init();
        onClickEvent();
    }

    private void onClickEvent() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (viewpagerMusicAdapter.getItem(1) != null) {
                    if (!arraySong.isEmpty()) {
                        fragmentCover.Play(arraySong.get(0).getSongImage());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 500);
                    }
                }
            }
        }, 1000);
        playButton.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                playButton.setImageResource(R.drawable.play);
            } else {
                mediaPlayer.start();
                playButton.setImageResource(R.drawable.pause);
            }
        });
        shuffleButton.setOnClickListener(view -> {
            if (!shuffle) {
                if (repeat) repeat = false;
                shuffleButton.setImageResource(R.drawable.shuffle_activated);
                shuffle = true;
            } else {
                shuffleButton.setImageResource(R.drawable.shuffle);
                shuffle = false;
            }
        });
        previousButton.setOnClickListener(view -> {

        });
        fastForwardButton.setOnClickListener(view -> {

        });
        repeatButton.setOnClickListener(view -> {
            // Nếu repeat == false
            if (!repeat) {
                if (shuffle) shuffle = false;
                repeatButton.setImageResource(R.drawable.repeat_activated);
                repeat = true;
            } else {
                repeatButton.setImageResource(R.drawable.repeat);
                repeat = false;
            }
        });
        songDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // TODO
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(songDuration.getProgress());
            }
        });
        previousButton.setOnClickListener(view -> {
            if (!arraySong.isEmpty()) {
                if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                if (position < (arraySong.size())) {
                    playButton.setImageResource(R.drawable.pause);
                    position -= 1;
                    if (position < 0) position = arraySong.size();
                    // Nếu repeat == true
                    if (repeat) {
                        if (position == 0) position = arraySong.size();
                        position += 1;
                    }
                    // Nếu shuffle == true
                    if (shuffle) {
                        Random random = new Random();
                        int rValue = random.nextInt(arraySong.size());
                        if (rValue == position) position = rValue - 1;
                        position = rValue;
                    }
                    new mp3().execute(arraySong.get(position).getSongLink());
                    fragmentCover.Play(arraySong.get(position).getSongImage());
                    getSupportActionBar().setTitle(arraySong.get(position).getSongName());
                    ChangeTime();
                }
            }
            // Hạn chế người dùng bấm quá nhanh dẫn đến crash
            previousButton.setClickable(false);
            fastForwardButton.setClickable(false);
            Handler handler12 = new Handler();
            handler12.postDelayed(() -> {
                previousButton.setClickable(true);
                fastForwardButton.setClickable(true);
            }, 5000);
        });
        fastForwardButton.setOnClickListener(view -> {
            if (!arraySong.isEmpty()) {
                if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                if (position < (arraySong.size())) {
                    playButton.setImageResource(R.drawable.pause);
                    position += 1;
                    // Nếu repeat == true
                    if (repeat) {
                        if (position == 0) position = arraySong.size();
                        position -= 1;
                    }
                    // Nếu shuffle == true
                    if (shuffle) {
                        Random random = new Random();
                        int rValue = random.nextInt(arraySong.size());
                        if (rValue == position) position = rValue - 1;
                        position = rValue;
                    }
                    if (position > (arraySong.size() - 1)) position = 0;
                    new mp3().execute(arraySong.get(position).getSongLink());
                    fragmentCover.Play(arraySong.get(position).getSongImage());
                    getSupportActionBar().setTitle(arraySong.get(position).getSongName());
                    ChangeTime();
                }
            }
            // Hạn chế người dùng bấm quá nhanh dẫn đến crash
            previousButton.setClickable(false);
            fastForwardButton.setClickable(false);
            Handler handler1 = new Handler();
            handler1.postDelayed(() -> {
                previousButton.setClickable(true);
                fastForwardButton.setClickable(true);
            }, 5000);
        });
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
                ArrayList<Song> songlist = intent.getParcelableArrayListExtra("Songs");
                arraySong = songlist;
            }
        }
    }

    private void init() {
        toolbar = findViewById(R.id.ToolbarMusicPlayerScreen);
        viewpager = findViewById(R.id.ViewPagerMusicPlayerScreen);
        startTime = findViewById(R.id.tvStartTime);
        endTime = findViewById(R.id.tvEndTime);
        songDuration = findViewById(R.id.seekBar);
        shuffleButton = findViewById(R.id.btnShuffle);
        previousButton = findViewById(R.id.btnPrevious);
        playButton = findViewById(R.id.btnPlay);
        fastForwardButton = findViewById(R.id.btnFastForward);
        repeatButton = findViewById(R.id.btnRepeat);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
            mediaPlayer.stop();
            arraySong.clear();
        });

        toolbar.setTitleTextColor(Color.WHITE);
        fragmentSonglist = new Fragment_Songlist();
        fragmentCover = new Fragment_Cover();
        viewpagerMusicAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewpagerMusicAdapter.addFragment(fragmentSonglist);
        viewpagerMusicAdapter.addFragment(fragmentCover);
        viewpager.setAdapter(viewpagerMusicAdapter);
        fragmentCover = (Fragment_Cover) viewpagerMusicAdapter.getItem(1);

        if (!arraySong.isEmpty()) {
            getSupportActionBar().setTitle(arraySong.get(0).getSongName());
            new mp3().execute(arraySong.get(0).getSongLink());
            playButton.setImageResource(R.drawable.pause);
        }
    }

     class mp3 extends AsyncTask<String, Void, String> {

         @Override
         protected String doInBackground(String... strings) {
             return strings[0];
         }

         @Override
         protected void onPostExecute(String song) {
             super.onPostExecute(song);

             try {
                 mediaPlayer = new MediaPlayer();
                 mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                 mediaPlayer.setOnCompletionListener(mediaPlayer -> {
                     mediaPlayer.stop();
                     mediaPlayer.reset();
                 });

                 mediaPlayer.setDataSource(song);
                 mediaPlayer.prepare();
             } catch (IOException e) {
                 e.printStackTrace();
             }

             mediaPlayer.start();
             SongDuration();
             ChangeTime();
         }
     }

    private void SongDuration() {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        endTime.setText(sdf.format(mediaPlayer.getDuration()));
        songDuration.setMax(mediaPlayer.getDuration());
    }
    private void ChangeTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    songDuration.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                    startTime.setText(sdf.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 500);
                    mediaPlayer.setOnCompletionListener(view -> {
                        next = true;
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                }
            }
        }, 1000);

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < (arraySong.size())) {
                        playButton.setImageResource(R.drawable.pause);
                        position += 1;
                        // Nếu repeat == true
                        if (repeat) {
                            if (position == 0) position = arraySong.size();
                            position -= 1;
                        }
                        // Nếu shuffle == true
                        if (shuffle) {
                            Random random = new Random();
                            int rValue = random.nextInt(arraySong.size());
                            if (rValue == position) position = rValue - 1;
                            position = rValue;
                        }
                        if (position > (arraySong.size() - 1)) position = 0;
                        new mp3().execute(arraySong.get(position).getSongLink());
                        fragmentCover.Play(arraySong.get(position).getSongImage());
                        getSupportActionBar().setTitle(arraySong.get(position).getSongName());
                        }
                    // Hạn chế người dùng bấm quá nhanh dẫn đến crash
                    previousButton.setClickable(false);
                    fastForwardButton.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(() -> {
                        previousButton.setClickable(true);
                        fastForwardButton.setClickable(true);
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 2000);
                }
            }
        }, 2000);
    }
}