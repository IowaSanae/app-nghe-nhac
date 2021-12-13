package tdtu.finalproject.homescreen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalproject.homescreen.Activity.MusicPlayerActivity;
import tdtu.finalproject.homescreen.Model.Song;
import tdtu.finalproject.homescreen.R;
import tdtu.finalproject.homescreen.Service.APIService;
import tdtu.finalproject.homescreen.Service.Dataservice;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ArrayList<Song> arraySong;

    public SearchAdapter(Context context, ArrayList<Song> arraySong) {
        this.context = context;
        this.arraySong = arraySong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = arraySong.get(position);
        holder.songName.setText(song.getSongName());
        holder.artistName.setText(song.getArtist());
        Picasso.with(context).load(song.getSongImage()).into(holder.songImage);
    }

    @Override
    public int getItemCount() {
        return arraySong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView songName, artistName;
        ImageView songImage, likes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songName = itemView.findViewById(R.id.tvSearchSongName);
            artistName = itemView.findViewById(R.id.tvSearchArtistName);
            songImage = itemView.findViewById(R.id.ivSongImage);
            likes = itemView.findViewById(R.id.ivLikes);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, MusicPlayerActivity.class);
                intent.putExtra("SongName", arraySong.get(getPosition()));
                context.startActivity(intent);
            });
            likes.setOnClickListener(view -> {
                likes.setImageResource(R.drawable.heartselected);
                Dataservice dataservice = APIService.getService();
                Call<String> callback = dataservice.Likes("1", arraySong.get(getPosition()).getIdSong());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        if (result.equals("Success")) {
                            Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "An error has occurred", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                likes.setEnabled(false);
            });
        }
    }
}
