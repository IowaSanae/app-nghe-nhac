package tdtu.finalproject.homescreen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalproject.homescreen.Model.Song;
import tdtu.finalproject.homescreen.R;
import tdtu.finalproject.homescreen.Service.APIService;
import tdtu.finalproject.homescreen.Service.Dataservice;

public class MusicPlayerAdapter extends RecyclerView.Adapter<MusicPlayerAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> arraySong;

    public MusicPlayerAdapter(Context context, ArrayList<Song> arraySong) {
        this.context = context;
        this.arraySong = arraySong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.music_row_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = arraySong.get(position);
        holder.artistName.setText(song.getArtist());
        holder.musicPlayer.setText(position + 1 + "");
        holder.songName.setText(song.getSongName());
    }

    @Override
    public int getItemCount() {
        return arraySong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView musicPlayer, songName, artistName;

        public ViewHolder (View itemView) {
            super(itemView);

            musicPlayer = itemView.findViewById(R.id.tvIdSong);
            songName = itemView.findViewById(R.id.tvSongName);
            artistName = itemView.findViewById(R.id.tvArtistName);
        }
    }
}
