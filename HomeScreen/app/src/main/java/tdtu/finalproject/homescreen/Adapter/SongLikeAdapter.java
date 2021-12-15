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

import tdtu.finalproject.homescreen.Model.LikeSong;
import tdtu.finalproject.homescreen.R;

public class SongLikeAdapter extends RecyclerView.Adapter<SongLikeAdapter.ViewHolder> {
    Context context;
    ArrayList<LikeSong> likeSongArrayList;
    public SongLikeAdapter(Context context, ArrayList<LikeSong> likeSongArrayList) {
        this.context = context;
        this.likeSongArrayList = likeSongArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_song_like, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LikeSong likesong = likeSongArrayList.get(position);
        holder.txtArtist.setText(likesong.getArtist());
        holder.txtSongName.setText(likesong.getSongName());
        Picasso.with(context) .load(likesong.getSongImage()).into(holder.imgSong);
    }

    @Override
    public int getItemCount() {
        return likeSongArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtSongName, txtArtist;
        ImageView imgSong, imgLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSongName = itemView.findViewById(R.id.textViewHotSongName);
            txtArtist = itemView.findViewById(R.id.textViewArtistHotSong);
            imgSong = itemView.findViewById(R.id.imageViewHotSong);
            imgLike = itemView.findViewById(R.id.imageViewLikeSong);
        }
    }
}
