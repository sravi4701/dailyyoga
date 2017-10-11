package ravi.minuteyogas.justgeek.yogafitnes.Model;

/**
 * Created by Ravi on 05-10-2017.
 */

public class Video {
    private String videourl;

    public Video(){

    }
    public Video(String videourl) {
        this.videourl = videourl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
