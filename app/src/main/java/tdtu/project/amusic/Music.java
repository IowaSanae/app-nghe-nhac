package tdtu.project.amusic;

public class Music {
    int id;
    String name;
    String image;
    String playlist;

    public Music(int id, String name, String image, String playlist) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.playlist = playlist;
    }

    public Music(String name, String image, String playlist) {
        super();
        this.name = name;
        this.image = image;
        this.playlist = playlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }
}
