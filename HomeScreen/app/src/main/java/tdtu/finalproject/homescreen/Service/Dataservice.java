package tdtu.finalproject.homescreen.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import tdtu.finalproject.homescreen.Model.Playlist;
import tdtu.finalproject.homescreen.Model.Song;

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

    @FormUrlEncoded
    @POST("songlist.php")
    Call<List<Song>> getSongList(@Field("idPlaylist") String idPlaylist);

    @FormUrlEncoded
    @POST("likes.php")
    Call<String> Likes(@Field("likes") String likes, @Field("id") String id);

    @FormUrlEncoded
    @POST("search.php")
    Call<List<Song>> SongSearch(@Field("index_term") String indexTerm);
}
