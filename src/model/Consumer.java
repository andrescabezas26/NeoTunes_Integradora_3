package model;

import java.util.ArrayList;

public abstract class Consumer extends User {
    private ArrayList<PlayList> playLists;
    private ArrayList<Audio> songs;
    public Consumer(String nickName, String id) {
        super(nickName, id);
        playLists= new ArrayList<PlayList>();
        songs= new ArrayList<Audio>();
    }
    /**cratePlaylist= This method creates a playlist 
     * @param playList: Playlist = The playlist to create
     */
    public void cratePlaylist(PlayList playList){
        playLists.add(playList);
    }
    /**searchPlaylistByCode= This method searchs a playlist by his code
     * @param playlistCode: String= The code of the playlist to search
     * @return pos: int = The posisition of the playlist in the arrayList of palylists
     */
    public int searchPlaylistByCode(String playlistCode){
        int pos=-1;
        boolean isFound=false;
        for(int i=0;i<playLists.size() && !isFound;i++){
            if(playLists.get(i).getPlaylistCode().equals(playlistCode)){
                pos=i;
                isFound=true;
            }
        }
        return pos;
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(ArrayList<PlayList> playLists) {
        this.playLists = playLists;
    }

}
