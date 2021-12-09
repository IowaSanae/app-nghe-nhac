package tdtu.finalproject.homescreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

    @SerializedName("idPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Image")
    @Expose
    private String image;

    public String getIdPlaylist() {
    return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
    this.idPlaylist = idPlaylist;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getImage() {
    return image;
    }

    public void setImage(String image) {
    this.image = image;
    }

}