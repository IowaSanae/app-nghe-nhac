package tdtu.finalproject.homescreen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalproject.homescreen.Activity.SongListActivity;
import tdtu.finalproject.homescreen.Model.Playlist;
import tdtu.finalproject.homescreen.R;
import tdtu.finalproject.homescreen.Service.APIService;
import tdtu.finalproject.homescreen.Service.Dataservice;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>{

    Context context;
    ArrayList<Playlist> arrayPlaylist = new ArrayList<Playlist>();

    public PlaylistAdapter(Context context, ArrayList<Playlist> arrayPlaylist){
        this.context = context;
        this.arrayPlaylist = arrayPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.playlist_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = arrayPlaylist.get(position);
        holder.txtPlaylistTitle.setText(playlist.getName());
        Picasso.with(context).load(playlist.getImage()).into(holder.imagePlaylist);
    }

    @Override
    public int getItemCount() {
        return arrayPlaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePlaylist, imgViewLike;
        TextView txtPlaylistTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePlaylist = itemView.findViewById(R.id.imageViewPlaylist);
            txtPlaylistTitle = itemView.findViewById(R.id.txtPlaylistTitle);
            imgViewLike = itemView.findViewById(R.id.imgViewLike);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, SongListActivity.class);
                intent.putExtra("itemplaylist", arrayPlaylist.get(getAdapterPosition()));
                context.startActivity(intent);
            });
        }
    }
}
