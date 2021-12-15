package tdtu.finalproject.homescreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class LikeSong {

    @SerializedName("idSong")
    @Expose
    private String idSong;
    @SerializedName("songName")
    @Expose
    private String songName;
    @SerializedName("Artist")
    @Expose
    private String artist;
    @SerializedName("SongImage")
    @Expose
    private String songImage;
    @SerializedName("SongLink")
    @Expose
    private String songLink;
    @SerializedName("LikeSong")
    @Expose
    private String likeSong;

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getLikeSong() {
        return likeSong;
    }

    public void setLikeSong(String likeSong) {
        this.likeSong = likeSong;
    }

}
