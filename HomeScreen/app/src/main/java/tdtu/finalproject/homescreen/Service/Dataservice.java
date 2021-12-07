package tdtu.finalproject.homescreen.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tdtu.finalproject.homescreen.Model.Playlist;

public interface Dataservice {
    @GET("playlist.php")
    Call<List<Playlist>> getPlaylistHot();

    @GET("playlistRecommend.php")
    Call<List<Playlist>> getPlaylistRecommend();

    @GET("playlistSad.php")
    Call<List<Playlist>> getPlaylistSad();

    @GET("playlistHappy.php")
    Call<List<Playlist>> getPlaylistHappy();

    @GET("playlistChill.php")
    Call<List<Playlist>> getPlaylistChill();

    @GET("playlistArtist.php")
    Call<List<Playlist>> getPlaylistArtist();
}
