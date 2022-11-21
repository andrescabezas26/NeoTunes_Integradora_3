package model;

import java.time.LocalDate;

public class Song extends Audio implements IPlayable, Comparable<Song> {

    private String album;
    private SongGenre genre;
    private double price;
    private int sales;
    private LocalDate saleDate;

    public Song(String artist, String name, String url, int hours, int minutes, int seconds, String album, int genre,
            double price) {
        super(artist, name, url, hours, minutes, seconds);
        this.album = album;
        this.genre = SongGenre.values()[genre - 1];
        this.price = price;
        this.sales = 0;
    }

    public SongGenre getGenre() {
        return genre;
    }

    public int getSales() {
        return sales;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate() {
        this.saleDate = LocalDate.now();
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
    /**
     * play= This method implement a method of the Iplayable interface to show info releated to this song
     * @return String = Info releated to this song
     */
    @Override
    public String play() {
        return "\n(Cancion) " + super.toString() + "\nAlbum: " + album + "\nGenero: " + genre;
    }
    
    /**
     * compareTo= Compares the playbacks of this song with other one
     * 
     * @param song: Song =The song that will be
     *                 compareted
     * @return int = 1 or 0 if the other song has more playbacks than
     *         this, or -1 if this has more
     */
    @Override
    public int compareTo(Song song) {
        if (song.getPlaybacks() > super.getPlaybacks()) {
            return 1;
        } else if (song.getPlaybacks() > super.getPlaybacks()) {
            return 0;
        } else {
            return -1;
        }
    }

}
