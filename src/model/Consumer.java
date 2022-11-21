package model;

import java.util.ArrayList;

public abstract class Consumer extends User {
    private ArrayList<PlayList> playLists;
    private ArrayList<Audio> songs;
    private ArrayList<Bill> bills;
    private int[] genres;
    private int[] categorys;
    private int playbackTime = 0;

    public Consumer(String nickName, String id) {
        super(nickName, id);
        playLists = new ArrayList<PlayList>();
        songs = new ArrayList<Audio>();
        bills = new ArrayList<Bill>();
        genres = new int[4];
        categorys = new int[4];
    }

    /**
     * cratePlaylist= This method creates a playlist
     * 
     * @param playList: Playlist = The playlist to create
     */
    public void cratePlaylist(PlayList playList) {
        playLists.add(playList);
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public ArrayList<Audio> getSongs() {
        return songs;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public int getPlaybackTime() {
        return playbackTime;
    }

    public void setPlaybackTime(int playbackTime) {
        this.playbackTime = playbackTime;
    }

    public void setPlayLists(ArrayList<PlayList> playLists) {
        this.playLists = playLists;
    }

    public String addPlaylist(PlayList playList) {
        return "";
    }

    public String playAudio(int playlistPos, int audioPos) {
        return "";
    }
    
    public boolean buySong(Audio song) {
        return true;
    }

    /**
     * searchPlaylist= Searchs a playlist by his name and return the position of
     * this
     * 
     * @param name: String = The name of the plyalist to search
     * @return pos: int = Th position of the playlist or -1 if it wasn´t found
     */
    public int searchPlaylist(String name) {
        int pos = -1;
        boolean isFound = false;
        for (int i = 0; i < playLists.size() && !isFound; i++) {
            if (name.equalsIgnoreCase(playLists.get(i).getName())) {
                pos = i;
                isFound = true;
            }
        }
        return pos;
    }

    /**
     * searchAudioByProducerAndName= A method that searchs a audio by his Name, and
     * return the position.
     * 
     * @param name:             String = The name to search in the Arraylist of
     *                          audios
     * @param producerNickName: String = The nickName of the producer of the audio
     * @return pos: int = The posisition of the audio in the Arraylist or -1 if the
     *         audio doesn´t exist
     */
    public int searchSongByProducerAndName(String name, String producerNickname) {
        boolean sameSong = false;
        int pos = -1;
        for (int i = 0; i < songs.size() && !sameSong; i++) {
            if (songs.get(i).getName().equalsIgnoreCase(name)) {
                if ((songs).get(i).getCreator().equalsIgnoreCase(producerNickname)) {
                    sameSong = true;
                    pos = i;
                }
            }
        }
        return pos;
    }

    /**
     * showMosListenGenre= This method shows the most listen genre of this consumer
     * 
     * @return msj: String = The most listen genre of this consumer
     */
    public String showMosListenGenre() {
        String msj = "";
        int mostListen = 0;
        if (genres[0] >= mostListen) {
            mostListen = genres[0];
            msj = "El genero mas escuhado por el consumidor " + getId() + " es HOUSE con " + genres[0]
                    + " reproducciones";
        }
        if (genres[1] >= mostListen) {
            mostListen = genres[1];
            msj = "El genero mas escuhado por el consumidor " + getId() + " es POP con " + genres[1]
                    + " reproducciones";
        }
        if (genres[2] >= mostListen) {
            mostListen = genres[2];
            msj = "El genero mas escuhado por el consumidor " + getId() + " es ROCK con " + genres[2]
                    + " reproducciones";
        }
        if (genres[3] >= mostListen) {
            mostListen = genres[3];
            msj = "El genero mas escuhado por el consumidor " + getId() + " es TRAP con " + genres[3]
                    + " reproducciones";
        }
        return msj;
    }

    /**
     * showMosListenCategory= This method shows the most listen category of this
     * consumer
     * 
     * @return msj: String = The most listen category of this consumer
     */
    public String showMosListenCategory() {
        String msj = "";
        int mostListen = 0;
        if (categorys[0] >= mostListen) {
            mostListen = categorys[0];
            msj = "La categoria mas escuhada por el consumidor " + getId() + " es POLITICA con " + categorys[0]
                    + " reproducciones";
        }
        if (categorys[1] >= mostListen) {
            mostListen = categorys[1];
            msj = "La categoria mas escuhada por el consumidor " + getId() + " es ENTRETENIMIENTO con " + categorys[1]
                    + " reproducciones";
        }
        if (categorys[2] >= mostListen) {
            mostListen = categorys[2];
            msj = "La categoria mas escuhada por el consumidor " + getId() + " es VIDEOJUEGOS con " + categorys[2]
                    + " reproducciones";
        }
        if (categorys[3] >= mostListen) {
            mostListen = categorys[3];
            msj = "La categoria mas escuhada por el consumidor " + getId() + " es MODA con " + categorys[3]
                    + " reproducciones";
        }
        return msj;
    }

    /**
     * showAudio= This method Shows the Songs and Podcast of the Playlist and their
     * producers id
     * 
     * @return msj: Sting = An specification of the type of Audio, the name of the
     *         Audio and the Id of his producer
     */
    public String showSong() {
        String msj = "\n" + "Indice de Audio: " + "Nombre del Audio: " + "NickName del Productor\n";
        for (int i = 0; i < songs.size(); i++) {
            msj += (i + 1) + ")" + " (Cancion) ";
            msj += songs.get(i).getName() + ": " + songs.get(i).getCreator() + "\n"
                    + ((Song) songs.get(i)).getSaleDate();
        }
        return msj;
    }

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int i, int j) {
        genres[i] = j;
    }

    public int[] getCategorys() {
        return categorys;
    }

    public void setCategorys(int i, int j) {
        categorys[i] = j;
    }

}
