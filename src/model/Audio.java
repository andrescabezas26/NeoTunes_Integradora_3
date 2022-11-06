package model;

import java.time.LocalTime;

public class Audio {
    private String creator;
    private String name;
    private String url;
    private LocalTime duration;
    private int playbacks;

    public Audio(String creator,String name, String url, LocalTime duration) {
        this.creator=creator;
        this.name = name;
        this.url = url;
        this.duration = duration;
        this.playbacks = 0;
    }

    public String getCreator() {
        return creator;
    }
    public String getName() {
        return name;
    }
}
