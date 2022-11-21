package model;

public class Premium extends Consumer {

    public Premium(String nickName, String id) {
        super(nickName, id);
    }
    /**
     * addPlaylist= This method add a playlist to the arraylist of the Consumer 
     * 
     * @param playlist: Playlist = The playlist to add to the arraylist of the
     *                  consumer
     * 
     * @return A confirmation of the process to add playlist
     */
    @Override
    public String addPlaylist(PlayList playList) {
        if (super.searchPlaylist(playList.getName()) == -1) {
            super.getPlayLists().add(playList);
            return "Playlist agregada Correctamente";
        } else {
            return "Esta playlist ya existe";
        }
    }
    /**
     * buySong = This method add a song to the arraylist and a bill to his bills of the user 
     * 
     * @param song: Audio = The song that will be adde to the arraylist of songs
     * @return boolean = Confimation of the successfull add of the song
     */
    @Override
    public boolean buySong(Audio song) {
        super.getSongs().add(song);
        Bill newBill= new Bill(song, super.getId());
        super.getBills().add(newBill);
        return true;
    }
     /**
     * playAudio = This method plays a playlist audio
     * playback according to the audio genre or category
     * 
     * @param playlistPos: int = The position of the playlist in the arraylist
     * @param audioPos:    int = The position of the audio in the playlist
     * @return msj: String = Plays a playlist audio 
     */
    @Override
    public String playAudio(int playlistPos, int audioPos) {
        String msj = "";
        if (super.getPlayLists().get(playlistPos).getAudios().get(audioPos) instanceof Song) {
            msj += super.getPlayLists().get(playlistPos).playAudio(audioPos);
        } else {
            msj += super.getPlayLists().get(playlistPos).playAudio(audioPos);
        }
        return msj;
    }
}
