package com.example.videodemo;

public class VideoYoutube {
    private String title;
    private String Thumbnail;
    private String IdVideo;
    private String Channel;


    public VideoYoutube(String title, String thumbnail, String idVideo, String channel) {
        this.title = title;
        Thumbnail = thumbnail;
        IdVideo = idVideo;
        Channel = channel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return IdVideo;
    }

    public void setIdVideo(String idVideo) {
        IdVideo = idVideo;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }
}
