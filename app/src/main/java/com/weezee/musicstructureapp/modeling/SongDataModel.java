package com.weezee.musicstructureapp.modeling;

import java.io.Serializable;

public class SongDataModel implements Serializable {
    private String title;
    private String artist;
    private int albumArtResId;
    private String albumTitle;

    public SongDataModel(String title, String artist, int albumArt, String albumTitle) {
        this.title = title;
        this.artist = artist;
        this.albumArtResId = albumArt;
        this.albumTitle = albumTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }


    public int getAlbumArtResId() {
        return albumArtResId;
    }


    public String getAlbumTitle() {
        return albumTitle;
    }


}
