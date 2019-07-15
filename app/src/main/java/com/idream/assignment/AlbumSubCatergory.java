package com.idream.assignment;

public class AlbumSubCatergory {
    private String name;
    private String id;
    private String thumbnail;


    public AlbumSubCatergory() {
    }

    public AlbumSubCatergory(String name, String id, String thumbnail) {
        this.name = name;
        this.id = id;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
