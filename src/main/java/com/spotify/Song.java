package com.spotify;

public class Song {
    private String name;
    private String artist;
    private String imglink;
    private String audiolink;
    private int length;  // Assuming length is in seconds

    // Constructor (optional)
    public Song(String name, String artist, String imglink, String audiolink, int length) {
        this.name = name;
        this.artist = artist;
        this.imglink = imglink;
        this.audiolink = audiolink;
        this.length = length;
    }

    // Default constructor (required if using the object without parameters)
    public Song() {}

    // Getters and Setters for each field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImgLink() {
        return imglink;
    }

    public void setImgLink(String imglink) {
        this.imglink = imglink;
    }

    public String getAudioLink() {
        return audiolink;
    }

    public void setAudioLink(String audiolink) {
        this.audiolink = audiolink;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
