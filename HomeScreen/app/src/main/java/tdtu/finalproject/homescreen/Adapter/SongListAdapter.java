package tdtu.finalproject.homescreen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tdtu.finalproject.homescreen.Model.Song;
import tdtu.finalproject.homescreen.R;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> arraySong;
    public SongListAdapter(Context context, ArrayList<Song> arraySong){
        this.context = context;
        this.arraySong = arraySong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.song_row_interface,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = arraySong.get(position);
        holder.txtSongName.setText(song.getSongName());
        holder.txtArtistName.setText(song.getArtist());
        Picasso.with(context).load(song.getSongImage()).into(holder.imgSong);
    }

    @Override
    public int getItemCount() {
        return arraySong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtSongName, txtArtistName;
        ImageView imgSong, imgViewLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSongName = itemView.findViewById(R.id.txtSongName);
            txtArtistName = itemView.findViewById(R.id.txtArtistName);
            imgSong = itemView.findViewById(R.id.imgSong);
            imgViewLike = itemView.findViewById(R.id.imgViewLike);
        }
    }
}
