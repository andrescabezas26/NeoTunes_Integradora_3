package model;

public class Artist extends Producer implements Comparable<Artist> {

    public Artist(String nickName, String id, String imageUrl, String name) {
        super(nickName, id, imageUrl, name);
    }

    /**
     * compareTo= Compares the playbacks of this artist with other one
     * 
     * @param artist: Artist =The artist that will be
     *                compareted
     * @return int = 1 or 0 if the other artist has more playbacks than
     *         this, or -1 if this has more
     */
    @Override
    public int compareTo(Artist artist) {
        if (artist.getPlaybacks() > super.getPlaybacks()) {
            return 1;
        } else if (artist.getPlaybacks() > super.getPlaybacks()) {
            return 0;
        } else {
            return -1;
        }
    }
}
