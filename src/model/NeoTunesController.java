package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class NeoTunesController {
    private String msj;
    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    public NeoTunesController(){
        users= new ArrayList<User>();
        audios= new ArrayList<Audio>();
        msj= "";
        initNeoTunes();
    }
    /**initNeoTunes=This method create 2 consumers,producers,playlists and audios to test the program
     */
    public void initNeoTunes(){
        addConsumer("Consumidor", "c1", 2);
        addConsumer("Consumidor1", "c2", 1);
        addProducer("MF_DOOM", "mf", "_Image.png", " Daniel_Dumile",1);
        addProducer("Jordi_Wild", "p1", "_Image.png", "Jorge_Carrillo_de_Albornoz_Torres",2);
        addPlaylist("c1", "Playlist1");
        addPlaylist("c2", "Playlist1");
        addAudio("p1", "The_Wild_Project_139", "https://www.youtube.com/watch?v=ZyZIM__B48A", 4, 14, 27, "The_Wild_Project", 0, 0, "IlloJuan, uno de los streamers más grandes del mundo, visita a Jordi Wild para un podcast de los largos e intensos, con muchos temas, humor y diversión; pero también, con importantes reflexiones que pueden servir para todos. ¡No os lo podéis perder!", 2, 2);
        addAudio("mf", "Kon_Queso", "https://music.youtube.com/watch?v=xmPmtYRB1TE&list=RDAMVMgQtKJbptcns",0, 04, 01, "MM...FOOD", 2, 20, "", 0, 1);
    }
    /**addProducer= This method Add a producer to the program
     * @param nickName: String = The nickName of the producer to add
     * @param id: String = The id of the producer to add
     * @param imageUrl: String = The url of the image of the producer
     * @param name: String = The name of the Producer to add
     * @param option: int = The type of producer to add 1=Artist or 2=CreatorContent
     * @return msj: String = A message that confirms if the producer was added successfully
     */
    public String addProducer(String nickName, String id, String imageUrl, String name, int option){
        msj="El Id se encuentra repetido";
        if(searchUserById(id)==-1){
            switch(option){
                case 1:
                    User newArtist= new Artist(nickName, id, imageUrl, name);
                    users.add(newArtist);
                    msj="Artista Agregado Correctamente";
                
                break;
    
                case 2:
                    User newContentCreator= new ContentCreator(nickName, id, imageUrl, name);
                    users.add(newContentCreator);
                    msj="Creador de Contenido Agregado Correctamente";
                
                break;
            }
        }
        return msj;
    }
    /**addConsumer= This method Add a Consumer to the program
     * @param nickName: String = The nickName of the Consumer to add
     * @param id: String = The id of the Consumer to add
     * @param option: int = The type of Consumer 1=Standar or 2=Premium
     * @return msj: String = A message that Confirms if the Consumer was added successfully
     */
    public String addConsumer(String nickName, String id, int option){
        msj="El Id se encuentra repetido";
        if(searchUserById(id)==-1){
            switch(option){
                case 1:
                    User newStandar= new Standar(nickName, id);
                    users.add(newStandar);
                    msj="Consumidor Estandar Agregado Correctamente";
                
                break;
    
                case 2:
                    User newPremium= new Premium(nickName, id);
                    users.add(newPremium);
                    msj="Consumidor Premium Agregado Correctamente";
        
                break;
            }
        }
        return msj;
    }
    /**addAudio= This method adds a Audio to NeoTunes
     * @param producerId: String = The id of the audio producer
     * @param name: String = The name of the audio
     * @param url: String = The url of the image of the 
     * @param hours: int = int = The hours of duration of the audio
     * @param minutes: int = The minutes of duration of the audio
     * @param seconds: int = The seconds of duration of the audio
     * @param album: String = The album of the song
     * @param genre: int = The genre of the song
     * @param price: doble = The price of the song
     * @param description: String = The description of the podcast
     * @param category: int = The category of the podcast 
     * @param option: int = The type of audio to add 1=Song or 2=Podcast
     * @return msj: String = A message that confirms if the Audio was Added successfully
     */
    public String addAudio(String producerId,String name, String url, int hours, int minutes, int seconds, String album, int genre, double price, String description, int category, int option){
        LocalTime duration=LocalTime.of(hours, minutes, seconds);
        msj="No se encontro al Productor";
        if(searchUserById(producerId)!=-1){
            msj="Este Productor ya registro este Audio";
            if(searchAudioByProducerAndName(name, producerId)==-1){
                switch(option){
                    case 1:
                    msj="El Id no pertenece a un Artista";
                    if(users.get(searchUserById(producerId)) instanceof Artist){
                        Audio newSong= new Song(producerId, name, url, duration, album, genre, price);
                        audios.add(newSong);
                        msj="Cancion Agregada Correctamente";
                    }   
                    break;
        
                    case 2:
                    msj="El Id no pertenece a un Creador de Contenido";
                    if(users.get(searchUserById(producerId)) instanceof ContentCreator){
                        Audio newPodcast= new Podcast(producerId,name, url, duration, description, category);
                        audios.add(newPodcast);
                        msj="Podcast Agregado Correctamente";
                    }
                    break;
                }
            } 
        }
        return msj;
    }
    /**addPlaylist= This method add a Playlist to a Consumer
     * @param idConsumer: String = The id of the Consumer
     * @param namePlaylist: String = The Name of the New Playlist
     * @return msj: String = A message that confirms if the Playlist was added successfully
     */
    public String addPlaylist(String idConsumer, String namePlaylist){
        msj="No se encontro al Consumidor "+ idConsumer;
        if(searchUserById(idConsumer)!=-1){
            msj="El id "+ idConsumer+ " no pertenece a un Consumidor";
            PlayList newPlayList= new PlayList(namePlaylist);
            if(users.get(searchUserById(idConsumer)) instanceof Standar){
                msj="El Consumidor" + idConsumer+ " ha alcanzado el limite de Playlists, pasate a Premium para tener Playlists ilimitadas";
                if(((Standar)(users.get(searchUserById(idConsumer)))).getPlayLists().size()<20){
                    ((Consumer)(users.get(searchUserById(idConsumer)))).getPlayLists().add(newPlayList);
                    msj="Playlist " + namePlaylist +" Agregada Correctamente";
                }
            }else if(users.get(searchUserById(idConsumer)) instanceof Premium){
                ((Consumer)(users.get(searchUserById(idConsumer)))).getPlayLists().add(newPlayList);
                msj="Playlist " + namePlaylist +" Agregada Correctamente";
            }
        }
        return msj;
    }
    /**editPlaylist= This method allows to edit a playlist (Add audio, delete audio or change the name)
     * @param idConsumer: String = The id of the Consumer
     * @param playListCode: String = The code of the Playlist 
     * @param nameAudio: String = The name of the audio to add or delete
     * @param producerId: String = The id of the producer of the audio to add or delete
     * @param playListName: String = The new Name of the playlis to change
     * @param option: int = The option that the user selects 1=Add Audio, 2=Delete Audio or 3=Change Name of playlist
     * @return msj: String = A message that confirms if the playlist was edited successfully
     */
    public String editPlaylist(String idConsumer, String playListCode, String nameAudio, String producerId, String playListName, int option){
        msj="No se encontro el Audio " + nameAudio + " del Productor " + producerId ;
        if(searchAudioByProducerAndName(nameAudio, producerId)!=-1 || option==3){
            msj="No se encontro al Usuario " + idConsumer;
            if(searchUserById(idConsumer)!=-1){
                msj="El Usuario " + idConsumer +" no es un Consumidor ";
                if(users.get(searchUserById(idConsumer)) instanceof Consumer){
                    msj="La Playlist "+ playListCode+ " no fue encontrada";
                    int playlistPos=((Consumer) users.get(searchUserById(idConsumer))).searchPlaylistByCode(playListCode);
                    if(playlistPos!=-1){
                        int songPos=((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).searchAudioByProducerAndName(nameAudio, producerId);
                        switch(option){
                            case 1:
                                msj=addAudioToPlaylist(nameAudio, producerId, idConsumer, playlistPos);
                            break;

                            case 2:
                                msj=deleteAudioOfPlaylist(nameAudio, producerId, idConsumer, playlistPos, songPos);
                            break;

                            case 3:
                                msj=editPlaylistName(playListName, idConsumer, playlistPos);
                            break;
                        }
                    }
                }
            }
        }
        return msj;
    }
    /**addAudioToPlaylist = This method deletes a Audio of a playlist
     * @param nameAudio: String = The name of the audio to add
     * @param producerId: String = The id of the producer of the audio to add
     * @param idConsumer: String = The id of the Consumer
     * @param playlistPos: int = The position of the Playlist in the arraylist of the Consumer
     * @return msj: String = A message that confirms the succesfully add of the Audio in the playlist
     */
    public String addAudioToPlaylist(String nameAudio, String producerId, String idConsumer, int playlistPos){
        if(audios.get(searchAudioByProducerAndName(nameAudio, producerId)) instanceof Song){
            Audio newSong= audios.get(searchAudioByProducerAndName(nameAudio, producerId));
            ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).addSong(newSong);
            msj="Cancion Agregada a la Playlist Correctamente";
        }else if(audios.get(searchAudioByProducerAndName(nameAudio, producerId)) instanceof Podcast){
            Audio newPodcast= audios.get(searchAudioByProducerAndName(nameAudio, producerId));
            ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).addPodcast(newPodcast);
            msj="Podcast Agregado a la Playlist Correctamente";
        }
        ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).createCodeTypePlaylist();
        return msj;
    }
    /**deleteAudioOfPlaylist= This method deletes a Audio of a playlist
     * @param nameAudio: String = The name of the audio to delete
     * @param producerId: String = The id of the producer of the audio to delete
     * @param idConsumer: String = The id of the Consumer
     * @param playlistPos: int = The position of the Playlist in the arraylist of the Consumer
     * @param audioPos: int = The position of the Audio in the playlist 
     * @return msj: String = A message that confirms the succesfully delete of the Audio in the playlist
     */
    public String deleteAudioOfPlaylist(String nameAudio, String producerId, String idConsumer, int playlistPos, int audioPos){
        if(audioPos!=-1){
            if(audios.get(searchAudioByProducerAndName(nameAudio, producerId)) instanceof Song){
                ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).deleteSong(audioPos);
                msj="Cancion Eliminda de la Playlist Correctamente";
            }else if(audios.get(searchAudioByProducerAndName(nameAudio, producerId)) instanceof Podcast){
                ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).deletePodcast(audioPos);
                msj="Podcast Eliminado de la Playlist Correctamente";
            }
            ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).createCodeTypePlaylist();
        }
        return msj;
    }
    /**editPlaylistName= This method edit the name of a Playlist
     * @param playlistName: String = The new Name of the playlis to change
     * @param idConsumer: String = The id of the Consumer
     * @param playlistPos: int = The position of the Playlist in the arraylist of the Consumer
     * @return msj: String = A message that confirms the successfully change of the Playlist name
     */
    public String editPlaylistName(String playlistName, String idConsumer, int playlistPos){
        ((Consumer)users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).setName(playlistName);
        msj="El nombre de la Playlist fue cambiado exitosamente a " + playlistName;
        return msj;
    }
    /**searchUserById= A method that searchs a user by his id, and return the position.
     * @param id: String = The id to search in the Arraylist of users
     * @return pos: int = The posisition of the user in the Arraylist or -1 if the person doesn´t exist
     */
    public int searchUserById(String id){
        int pos=-1;
        boolean isFound=false;
        for(int i=0;i < users.size() && !isFound;i++){
            if(id.equals(users.get(i).getId())){
                pos=i;
                isFound=true;
            }
        }
        return pos;
    }
    /**searchAudioByName= A method that searchs a audio by his Name, and return the position.
     * @param name: String = The name to search in the Arraylist of audios
     * @return pos: int = The posisition of the audio in the Arraylist or -1 if the audio doesn´t exist
     */
    public int searchAudioByName(String name){
        int pos=-1;
        boolean isFound=false;
        for(int i=0;i < audios.size() && !isFound;i++){
            if(name.equals(audios.get(i).getName())){
                pos=i;
                isFound=true;
            }
        }
        return pos;
    }
    /**searchAudioByProducerAndName= A method that searchs a audio by his Name, and return the position.
     * @param name: String = The name to search in the Arraylist of audios
     * @param producerId: String = The id of the producer of the audio
     * @return pos: int = The posisition of the audio in the Arraylist or -1 if the audio doesn´t exist
     */
    public int searchAudioByProducerAndName(String name, String producerId){
        boolean sameSong=false;
        int pos=-1;
        for(int i=0;i<audios.size() && !sameSong;i++){
            if(audios.get(i).getName().equalsIgnoreCase(name)){
                if((audios).get(i).getCreator().equalsIgnoreCase(producerId)){
                    sameSong=true;
                    pos=i;
                }
            }
        }
        return pos;
    }
    /**showAudio= This method Shows the Songs and Podcast of NeoTunes and their producers id
     * @return msj: Sting = An specification of the type of Audio, the name of the Audio and the Id of his producer
     */
    public String showAudio(){
        msj= "Nombre del Audio: Id del Productor\n";
        for(int i=0;i<audios.size();i++){
            if(audios.get(i) instanceof Song){
                msj+="(Cancion) ";
            }else{
                msj+="(Podcast) ";
            }
            msj+= audios.get(i).getName()+ ": "+ audios.get(i).getCreator()+ "\n";
        }
        return msj;
    }
    /**showConsumerPlaylists= Shows the playlist of a Consumer
     * @param idConsumer: String = The id of the Consumer
     * @return msj: String = The playlist of the Consumer and their Code
     */
    public String showConsumerPlaylists(String idConsumer){
        msj="";
        if(searchUserById(idConsumer)!=-1 && users.get(searchUserById(idConsumer)) instanceof Consumer){
            msj= "Nombre de la Playlist: Codigo De la Playlist\n";
            for(int i=0;i<((Consumer)(users.get(searchUserById(idConsumer)))).getPlayLists().size();i++){
                msj+=((Consumer)(users.get(searchUserById(idConsumer)))).getPlayLists().get(i).getName()+": " + ((Consumer)(users.get(searchUserById(idConsumer)))).getPlayLists().get(i).getPlaylistCode() + "\n";
            } 
        }
        return msj;
    }
    /**showSongGenreList= Shows a list of the SongGenre enmueration
     * @return msj: String= The list of the SongGenre enumeration
     */
    public String showSongGenre(){
        SongGenre songGenre[]= SongGenre.values();
        msj= "Generos Musicales: ";
        for(int i=0;i< songGenre.length;i++){
            msj += "\n" + (i+1) + " " + songGenre[i];
        }
        return msj;
    }
    /**showPodcastCategory= Shows a list of the PodcastCategory enmueration
     * @return msj: String= The list of the PodcastCategorys enumeration
     */
    public String showPodcastCategory(){
        PodcastCategory podcastCategory[]= PodcastCategory.values();
        msj= "Categorias de Podcasts: ";
        for(int i=0;i< podcastCategory.length;i++){
            msj += "\n" + (i+1) + " " + podcastCategory[i];
        }
        return msj;
    }
}