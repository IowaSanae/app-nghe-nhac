package tdtu.finalproject.homescreen.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tdtu.finalproject.homescreen.Adapter.SearchAdapter;
import tdtu.finalproject.homescreen.Model.Song;
import tdtu.finalproject.homescreen.R;
import tdtu.finalproject.homescreen.Service.APIService;
import tdtu.finalproject.homescreen.Service.Dataservice;

public class Fragment_Search extends Fragment {
    View view;
    Toolbar toolbar;
    RecyclerView songSearch;
    TextView noData;
    SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        songSearch = view.findViewById(R.id.recyclerview);
        noData = view.findViewById(R.id.tvNoData);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.toolbarStyle);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                IndexTermSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void IndexTermSearch(String query) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.SongSearch(query);
        callback.enqueue(new Callback<List<Song>>() {

            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> arraySong = (ArrayList<Song>) response.body();
                if (!arraySong.isEmpty()) {
                    searchAdapter = new SearchAdapter(getActivity(), arraySong);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    songSearch.setLayoutManager(linearLayoutManager);
                    songSearch.setAdapter(searchAdapter);
                    noData.setVisibility(View.GONE);
                    songSearch.setVisibility(View.VISIBLE);
                } else {
                    songSearch.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
