package tdtu.finalproject.homescreen.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import tdtu.finalproject.homescreen.Adapter.PlaylistAdapter;
import tdtu.finalproject.homescreen.Model.Playlist;
import tdtu.finalproject.homescreen.R;
import tdtu.finalproject.homescreen.Service.APIService;
import tdtu.finalproject.homescreen.Service.Dataservice;

public class Fragment_Playlist extends Fragment {

    View view;
    RecyclerView recyclerPlaylistHot, recyclerPlaylistRecommend,
                recyclerPlaylistSad, recyclerPlaylistHappy, recyclerPlaylistChill,
                recyclerPlaylistArtist;
    TextView topHot, recommend4u, sadTitle, happyTitle, chillTitle, artist;
    PlaylistAdapter playlistAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        recyclerPlaylistHot = view.findViewById(R.id.recyclerPlaylistHot);
        topHot = view.findViewById(R.id.topHot);

        recommend4u = view.findViewById(R.id.recommended4u);
        recyclerPlaylistRecommend = view.findViewById(R.id.recyclerPlaylistRecommend4u);

        sadTitle = view.findViewById(R.id.sadTitle);
        recyclerPlaylistSad = view.findViewById(R.id.recyclerPlaylistSad);

        happyTitle = view.findViewById(R.id.happyTitle);
        recyclerPlaylistHappy = view.findViewById(R.id.recyclerPlaylistHappy);

        chillTitle = view.findViewById(R.id.chillTitle);
        recyclerPlaylistChill = view.findViewById(R.id.recyclerPlaylistChill);

        artist = view.findViewById(R.id.artist);
        recyclerPlaylistArtist = view.findViewById(R.id.recyclerPlaylistArtist);

        GetDataHot();
        GetDataRecommend();
        GetDataSad();
        GetDataHappy();
        GetDataChill();
        GetDataArtist();
        return view;
    }

    private void GetDataHot() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.getPlaylistHot();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(),arrayPlaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerPlaylistHot.setLayoutManager(linearLayoutManager);
                recyclerPlaylistHot.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void GetDataRecommend() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.getPlaylistRecommend();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(),arrayPlaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerPlaylistRecommend.setLayoutManager(linearLayoutManager);
                recyclerPlaylistRecommend.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void GetDataSad() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.getPlaylistSad();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(),arrayPlaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerPlaylistSad.setLayoutManager(linearLayoutManager);
                recyclerPlaylistSad.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void GetDataHappy() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.getPlaylistHappy();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(),arrayPlaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerPlaylistHappy.setLayoutManager(linearLayoutManager);
                recyclerPlaylistHappy.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void GetDataChill() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.getPlaylistChill();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(),arrayPlaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerPlaylistChill.setLayoutManager(linearLayoutManager);
                recyclerPlaylistChill.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void GetDataArtist() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.getPlaylistArtist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> arrayPlaylist = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(),arrayPlaylist);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerPlaylistArtist.setLayoutManager(linearLayoutManager);
                recyclerPlaylistArtist.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
