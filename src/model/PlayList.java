package model;
import java.lang.Math;
import java.util.ArrayList;

public class PlayList {
    public static final int ROWS=6;
    public static final int COLUMNS=6;
    private String name;
    private ArrayList<Audio> audios;
    private int[][] matrix;
    private String playlistCode;

    public PlayList(String name){
        this.name=name;
        audios= new ArrayList<Audio>();
        matrix= new int[ROWS][COLUMNS];
        initMatrixCode();
        createCodeTypePlaylist();
    }
    /**initMatrixCode= This method init with random numbers (from 1 to 9) the matrix of the playlist
     */
    public void initMatrixCode(){
        for(int i=0;i<COLUMNS;i++){
            for(int j=0;j<ROWS;j++){
                matrix[i][j]=((int)(Math.random()*9))+1;
            }
        }
    } 
    /**showMatrix= This method shows the matrix of the playlist
     * @return
     */
    public String showMatrix(){
        String msj="";
        for (int i=0; i < matrix.length; i++) {
            System.out.print("|");
            for (int j=0; j < matrix[i].length; j++) {
              System.out.print (matrix[i][j]);
              if (j!=matrix[i].length-1) System.out.print("\t");
            }
            System.out.println("|");
          }
        return msj;
    }
    /**createCodeTypePlaylist= This method creates a code for the playlist according of his type of playlist
     */
    public void createCodeTypePlaylist(){
        int songs=0;
        int podcasts=0;
        for(int i=0;i<audios.size() && songs==0;i++){
            if(audios.get(i) instanceof Song){
                songs=1;
            }
        }
        for(int i=0;i<audios.size() && podcasts==0;i++){
            if(audios.get(i) instanceof Podcast){
                podcasts=1;
            }
        }
        if(podcasts>=1 && songs==0){
            playlistCode=onlyPodcastRoute();
        }else if(podcasts==0 && songs>=1){
            playlistCode=onlySongsRoute();
        }else{
            playlistCode=songsAndPodcastRoute();
        }
    }
    /**onlySongsRoute= This method create a code for a only Songs playlist
     * @return code: String = The code of the playlist
     */
    public String onlySongsRoute(){
        String code="";
        for(int i=ROWS-1;i>=1;i--){
            code+= Integer.toString(matrix[i][0]);
        }
        for(int i=0;i<COLUMNS;i++){
            code+= Integer.toString(matrix[i][i]);
        }
        for(int i=ROWS-2;i>=0;i--){
            code+= Integer.toString(matrix[i][5]);
        }
        return code;
    }
    /**onlyPodcastRoute= This method create a code for a only Podcast playlist
     * @return code: String = The code of the playlist
     */
    public String onlyPodcastRoute(){
        String code="";
        for(int i=0;i<(matrix.length/2);i++){
            code+= Integer.toString(matrix[0][i]);
        }
        for(int i=1;i<ROWS-1;i++){
            code+= Integer.toString(matrix[i][(matrix.length/2)-1]);
        }
        for(int i=(matrix.length/2)-1;i<(matrix.length/2);i++){
            code+= Integer.toString(matrix[5][i]);
        }
        for(int i=ROWS-1;i>=1;i--){
            code+= Integer.toString(matrix[i][matrix.length/2]);
        }
        for(int i=matrix.length/2;i<matrix.length;i++){
            code+= Integer.toString(matrix[0][i]);
        }
        return code;
    }
    /**songsAndPodcastRoute= This method create a code for a combined playlist
     * @return code: String = The code of the playlist
     */
    public String songsAndPodcastRoute(){
        String code="";
        for(int i=COLUMNS-2;i>=0;i-=2){
            code+= Integer.toString(matrix[ROWS-1][i]);
        }
        for(int i=COLUMNS-1;i>=1;i-=2){
            code+= Integer.toString(matrix[ROWS-2][i]);
        }
        for(int i=COLUMNS-2;i>=0;i-=2){
            code+= Integer.toString(matrix[ROWS-3][i]);
        }
        for(int i=COLUMNS-1;i>=1;i-=2){
            code+= Integer.toString(matrix[ROWS-4][i]);
        }
        for(int i=COLUMNS-2;i>=(matrix.length/2)-1;i-=2){
            code+= Integer.toString(matrix[ROWS-5][i]);
        }
        for(int i=COLUMNS-1;i>=(matrix.length/2);i-=2){
            code+= Integer.toString(matrix[ROWS-6][i]);
        }
        return code;
    }
    /**searchAudioByProducerAndName= A method that searchs a audio by his Name, and return the position.
     * @param name: String = The name to search in the Arraylist of audios
     * @param producerId: String = The id of the producer of the audio
     * @return pos: int = The posisition of the audio in the Arraylist or -1 if the audio doesn´t exist
     */
    public int searchAudioByProducerAndName(String name, String producerId){
        boolean sameSong=false;
        int pos=0;
        for(int i=0;i<audios.size() && !sameSong;i++){
            if(audios.get(i).getName().equalsIgnoreCase(name)){
                if((audios).get(i).getCreator().equals(producerId)){
                    sameSong=true;
                    pos=i;
                }
            }
        }
        return pos;
    }
    /**showAudio= This method Shows the Songs and Podcast of the Playlist and their producers id
     * @return msj: Sting = An specification of the type of Audio, the name of the Audio and the Id of his producer
     */
    public String showAudio(){
        String msj= "Nombre de la Cancion \t"+ "Id del Productor\n";
        for(int i=0;i<audios.size();i++){
            if(audios.get(i) instanceof Song){
                msj+="(Cancion) ";
            }else{
                msj+="(Podcast) ";
            }
            msj+= audios.get(i).getName()+ "\t" + audios.get(i).getCreator();
        }
        return msj;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPlaylistCode() {
        return playlistCode;
    }

    public void addSong(Audio song){
        audios.add(song);
    }

    public void addPodcast(Audio podcast){
        audios.add(podcast);
    }

    public void deleteSong(int songPos){
        audios.remove(songPos);
    }

    public void deletePodcast(int podcastPos){
        audios.remove(podcastPos);
    }
}
