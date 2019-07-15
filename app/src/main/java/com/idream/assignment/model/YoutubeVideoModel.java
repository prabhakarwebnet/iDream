package com.idream.assignment.model;



public class YoutubeVideoModel {
    private String videoId,videoCategory, title,duration;

    public YoutubeVideoModel(String videoId, String videoCategory, String title, String duration) {
        this.videoId = videoId;
        this.videoCategory = videoCategory;
        this.title = title;
        this.duration = duration;
    }



    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }


    public String getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(String videoCategory) {
        this.videoCategory = videoCategory;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "YoutubeVideoModel{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
