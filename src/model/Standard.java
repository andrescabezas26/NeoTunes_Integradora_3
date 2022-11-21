package model;

import java.lang.Math;

public class Standard extends Consumer {

    public static final int MAX_PLAYLIST = 20;
    public static final int MAX_SONGS = 100;
    private int songsPlayback;

    public Standard(String nickName, String id) {
        super(nickName, id);
        songsPlayback = 0;
    }

    /**
     * addPlaylist= This method add a playlist to the arraylist of the Consumer if there´s an empty space
     * 
     * @param playlist: Playlist = The playlist to add to the arraylist of the
     *                  consumer
     * 
     * @return A confirmation or error in the process to add playlist
     */
    @Override
    public String addPlaylist(PlayList playList) {
        if (super.searchPlaylist(playList.getName()) == -1) {
            if (super.getPlayLists().size() < MAX_PLAYLIST) {
                super.getPlayLists().add(playList);
                return "Playlist agregada correctamente";
            } else {
                return "El Consumidor " + super.getId()
                        + " ha alcanzado el limite de Playlists, pasate a Premium para tener Playlists ilimitadas";
            }
        } else {
            return "Esta playlist ya existe";
        }
    }

    /**
     * playAudio = This method plays a playlist audio, show an ad if applies and add
     * playback according to the audio genre or category
     * 
     * @param playlistPos: int = The position of the playlist in the arraylist
     * @param audioPos:    int = The position of the audio in the playlist
     * @return msj: String = Plays a playlist audio and show an ad if applies
     */
    @Override
    public String playAudio(int playlistPos, int audioPos) {
        String msj = "";
        if (super.getPlayLists().get(playlistPos).getAudios().get(audioPos) instanceof Song) {
            if (songsPlayback != 0 && songsPlayback % 2 == 0) {
                msj = Advertisement.values()[((int) (Math.random() * 3))].toString();
                msj += super.getPlayLists().get(playlistPos).playAudio(audioPos);
                int duration = super.getPlayLists().get(playlistPos).getAudios().get(audioPos).getDuration();
                super.setPlaybackTime(super.getPlaybackTime() + (duration));
                switch (((Song) super.getPlayLists().get(playlistPos).getAudios().get(audioPos)).getGenre()) {
                    case HOUSE:
                        super.setGenres(0, getGenres()[0] += 1);
                        break;

                    case POP:
                        super.setGenres(1, getGenres()[1] += 1);
                        break;

                    case ROCK:
                        super.setGenres(2, getGenres()[2] += 1);
                        break;

                    case TRAP:
                        super.setGenres(3, getGenres()[3] += 1);
                        break;
                }
                songsPlayback++;
            } else {
                msj = super.getPlayLists().get(playlistPos).playAudio(audioPos);
                if (songsPlayback == 0) {
                    songsPlayback += 2;
                } else {
                    songsPlayback++;
                }
                switch (((Song) super.getPlayLists().get(playlistPos).getAudios().get(audioPos)).getGenre()) {
                    case HOUSE:
                        super.setGenres(0, getGenres()[0] += 1);
                        break;

                    case POP:
                        super.setGenres(1, getGenres()[1] += 1);
                        break;

                    case ROCK:
                        super.setGenres(2, getGenres()[2] += 1);
                        break;

                    case TRAP:
                        super.setGenres(3, getGenres()[3] += 1);
                        break;
                }
            }
        } else {
            msj = Advertisement.values()[((int) (Math.random() * 3))].toString();
            msj += super.getPlayLists().get(playlistPos).playAudio(audioPos);
            int duration = super.getPlayLists().get(playlistPos).getAudios().get(audioPos).getDuration();
            super.setPlaybackTime(super.getPlaybackTime() + (duration));
            switch (((Podcast) super.getPlayLists().get(playlistPos).getAudios().get(audioPos)).getCategory()) {
                case POLITICA:
                    super.setCategorys(0, super.getCategorys()[0] + 1);
                    break;

                case ENTRETENIMIENTO:
                    super.setCategorys(1, super.getCategorys()[1] + 1);
                    break;

                case VIDEOJUEGOS:
                    super.setCategorys(2, super.getCategorys()[2] + 1);
                    break;

                case MODA:
                    super.setCategorys(3, super.getCategorys()[3] + 1);
                    break;
            }
        }
        return msj;
    }

    /**
     * buySong = This method add a song to the arraylist of the user if it isn´t
     * full and add a bill to the arraylist of the Consumer
     * 
     * @param song: Audio = The song that will be adde to the arraylist of songs
     * @return boolean = True or false if the process was successfull
     */
    @Override
    public boolean buySong(Audio song) {
        if (super.getSongs().size() < MAX_SONGS) {
            super.getSongs().add(song);
            Bill newBill= new Bill(song, super.getId());
            super.getBills().add(newBill);
            return true;
        } else {
            return false;
        }
    }
}
