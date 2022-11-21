package model;

public class Podcast extends Audio implements IPlayable, Comparable<Podcast> {
    private String description;
    private PodcastCategory category;

    public Podcast(String producer, String name, String url, int hours, int minutes, int seconds, String description,
            int category) {
        super(producer, name, url, hours, minutes, seconds);
        this.description = description;
        this.category = PodcastCategory.values()[category - 1];
    }

    public PodcastCategory getCategory() {
        return category;
    }

    /**
     * play= This method implement a method of the Iplayable interface to show info
     * releated to this podcast
     * 
     * @return String = Info releated to this podcast
     */
    @Override
    public String play() {
        return "\n(Podcast) " + super.toString() + "\nDescripcion: " + description + "\nCategoria: " + category;
    }

    /**
     * compareTo= Compares the playbacks of this podcast with other one
     * 
     * @param podcast: Podcast =The podcast that will be
     *                 compareted
     * @return int = 1 or 0 if the other podcast has more playbacks than
     *         this, or -1 if this has more
     */
    @Override
    public int compareTo(Podcast podcast) {
        if (podcast.getPlaybacks() > super.getPlaybacks()) {
            return 1;
        } else if (podcast.getPlaybacks() > super.getPlaybacks()) {
            return 0;
        } else {
            return -1;
        }
    }
}
