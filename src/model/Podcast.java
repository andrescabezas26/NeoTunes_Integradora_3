package model;

import java.time.LocalTime;

public class Podcast extends Audio {
    private String description;
    private PodcastCategory category;
    
    public Podcast(String producer,String name, String url, LocalTime duration, String description, int category) {
        super(producer,name, url, duration);
        this.description = description;
        this.category = PodcastCategory.values()[category-1];
    }
}
