package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Song extends Audio {
   
    private String album;
    private SongGenre genre;
    private double price;
    private int sales;
    private LocalDate saleDate;

    public Song(String artist,String name, String url, LocalTime duration, String album, int genre, double price) {
        super(artist,name, url, duration);
        this.album = album;
        this.genre = SongGenre.values()[genre-1];
        this.price = price;
        this.sales = 0;
    }
    
}
