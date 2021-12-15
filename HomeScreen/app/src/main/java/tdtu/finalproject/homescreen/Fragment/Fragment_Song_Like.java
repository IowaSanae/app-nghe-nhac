package tdtu.finalproject.homescreen.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalproject.homescreen.Adapter.SongLikeAdapter;
import tdtu.finalproject.homescreen.Model.LikeSong;
import tdtu.finalproject.homescreen.R;
import tdtu.finalproject.homescreen.Service.APIService;
import tdtu.finalproject.homescreen.Service.Dataservice;

public class Fragment_Song_Like extends Fragment {
    View view;
    RecyclerView recyclerViewHotSong;
    SongLikeAdapter songLikeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_like, container, false);
        recyclerViewHotSong = view.findViewById(R.id.recyclerviewHotSong);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<LikeSong>> callback = dataservice.GetSongLike();
        callback.enqueue(new Callback<List<LikeSong>>() {
            @Override
            public void onResponse(Call<List<LikeSong>> call, Response<List<LikeSong>> response) {
                ArrayList<LikeSong> likeSongArrayList = (ArrayList<LikeSong>) response.body();
                songLikeAdapter = new SongLikeAdapter(getActivity(), likeSongArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewHotSong.setLayoutManager(linearLayoutManager);
                recyclerViewHotSong.setAdapter(songLikeAdapter);
            }

            @Override
            public void onFailure(Call<List<LikeSong>> call, Throwable t) {

            }
        });
    }
}
