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

import tdtu.finalproject.homescreen.Activity.MusicPlayerActivity;
import tdtu.finalproject.homescreen.Adapter.MusicPlayerAdapter;
import tdtu.finalproject.homescreen.R;

public class Fragment_Songlist extends Fragment {

    View view;
    RecyclerView recycler;
    MusicPlayerAdapter musicPlayerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @NonNull Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_songlist, container, false);
        recycler = view.findViewById(R.id.recyclerViewFragmentSonglist);

        if (!MusicPlayerActivity.arraySong.isEmpty()) {
            musicPlayerAdapter = new MusicPlayerAdapter(getActivity(), MusicPlayerActivity.arraySong);
            recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycler.setAdapter(musicPlayerAdapter);
        }

        return view;
    }
}
