package tdtu.finalproject.homescreen.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song implements Parcelable {

    @SerializedName("idSong")
    @Expose
    private String idSong;
    @SerializedName("idPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("SongName")
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

    protected Song(Parcel in) {
        idSong = in.readString();
        idPlaylist = in.readString();
        songName = in.readString();
        artist = in.readString();
        songImage = in.readString();
        songLink = in.readString();
        likeSong = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public String getIdSong() {
    return idSong;
    }

    public void setIdSong(String idSong) {
    this.idSong = idSong;
    }

    public String getIdPlaylist() {
    return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
    this.idPlaylist = idPlaylist;
    }

    public String getSongName() { return songName; }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idSong);
        parcel.writeString(idPlaylist);
        parcel.writeString(songName);
        parcel.writeString(artist);
        parcel.writeString(songImage);
        parcel.writeString(songLink);
        parcel.writeString(likeSong);
    }
}