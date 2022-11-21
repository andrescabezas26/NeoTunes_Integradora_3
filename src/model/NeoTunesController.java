package model;

import java.util.ArrayList;
import java.util.Collections;


public class NeoTunesController {
    private String msj;
    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    public NeoTunesController() {
        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();
        msj = "";
        initNeoTunes();
    }

    /**
     * initNeoTunes=This method creates consumers,producers,playlists and audios to
     * test the program
     */
    public void initNeoTunes() {
        addProducer("MF_DOOM", "mf", "_Image.png", " Daniel_Dumile", 1);
        addProducer("JORDI_WILD", "p1", "_Image.png", "Jorge_Carrillo_de_Albornoz_Torres", 2);
        for (int i = 0; i < 5; i++) {
            addProducer("A" + i, "A" + i, "", "", 1);
            addProducer("C" + i, "C" + i, "", "", 2);
            addConsumer("Consumidor" + i, "s" + i, 1);
            addConsumer("Consumidor" + i, "p" + i, 2);
            addPlaylist("s" + i, "Favoritos" + i);
            addPlaylist("p" + i, "Musica" + i);
            addAudio("JORDI_WILD", "THE_WILD_PROJECT_139" + i, "https://www.youtube.com/watch?v=ZyZIM__B48A", 4, 14, 27,
                    "The_Wild_Project", 0, 0,
                    "IlloJuan, uno de los streamers más grandes del mundo, visita a Jordi Wild para un podcast de los largos e intensos, con muchos temas, humor y diversión; pero también, con importantes reflexiones que pueden servir para todos. ¡No os lo podéis perder!",
                    2, 2);
            addAudio("MF_DOOM", "KON_QUESO" + i, "https://music.youtube.com/watch?v=xmPmtYRB1TE&list=RDAMVMgQtKJbptcns",
                    0, 04,
                    01,
                    "MM...FOOD", 2, 20, "", 0, 1);
            addAudio("A" + i, "Song" + i, "", 0, 2, 34, "Almbun", 2, 10, "", 0, 1);
            addAudio("C" + i, "Podcast" + i, "", 1, 23, 34, "", 0, 0, "Description", 2, 2);
            editPlaylist("s" + i, 0, "KON_QUESO" + i, "Mf_doom", "", 1);
            simulateAudioPlayback("s" + i, 1, 1);
        }
    }

    /**
     * addProducer= This method Add a producer to the program
     * 
     * @param nickName: String = The nickName of the producer to add
     * @param id:       String = The id of the producer to add
     * @param imageUrl: String = The url of the image of the producer
     * @param name:     String = The name of the Producer to add
     * @param option:   int = The type of producer to add 1=Artist or
     *                  2=CreatorContent
     * @return msj: String = A message that confirms if the producer was added
     *         successfully
     */
    public String addProducer(String nickName, String id, String imageUrl, String name, int option) {
        msj = "El Id se encuentra repetido";
        if (searchUserById(id) == -1) {
            msj = "El nickName se encuentra repetido";
            if (searchUserByNickName(nickName) == -1) {
                switch (option) {
                    case 1:
                        User newArtist = new Artist(nickName, id, imageUrl, name);
                        users.add(newArtist);
                        msj = "Artista Agregado Correctamente";

                        break;

                    case 2:
                        User newContentCreator = new ContentCreator(nickName, id, imageUrl, name);
                        users.add(newContentCreator);
                        msj = "Creador de Contenido Agregado Correctamente";

                        break;
                }
            }
        }
        return msj;
    }

    /**
     * addConsumer= This method Add a Consumer to the program
     * 
     * @param nickName: String = The nickName of the Consumer to add
     * @param id:       String = The id of the Consumer to add
     * @param option:   int = The type of Consumer 1=standard or 2=Premium
     * @return msj: String = A message that Confirms if the Consumer was added
     *         successfully
     */
    public String addConsumer(String nickName, String id, int option) {
        msj = "El Id se encuentra repetido";
        if (searchUserById(id) == -1) {
            msj = "El nickName se encuentra repetido";
            if (searchUserByNickName(nickName) == -1) {
                switch (option) {
                    case 1:
                        User newStandard = new Standard(nickName, id);
                        users.add(newStandard);
                        msj = "Consumidor Estandar Agregado Correctamente";

                        break;

                    case 2:
                        User newPremium = new Premium(nickName, id);
                        users.add(newPremium);
                        msj = "Consumidor Premium Agregado Correctamente";

                        break;
                }
            }
        }
        return msj;
    }

    /**
     * addAudio= This method adds a Audio to NeoTunes
     * 
     * @param producerId:  String = The id of the audio producer
     * @param name:        String = The name of the audio
     * @param url:         String = The url of the image of the
     * @param hours:       int = int = The hours of duration of the audio
     * @param minutes:     int = The minutes of duration of the audio
     * @param seconds:     int = The seconds of duration of the audio
     * @param album:       String = The album of the song
     * @param genre:       int = The genre of the song
     * @param price:       doble = The price of the song
     * @param description: String = The description of the podcast
     * @param category:    int = The category of the podcast
     * @param option:      int = The type of audio to add 1=Song or 2=Podcast
     * @return msj: String = A message that confirms if the Audio was Added
     *         successfully
     */
    public String addAudio(String producerNickName, String name, String url, int hours, int minutes, int seconds,
            String album, int genre, double price, String description, int category, int option) {
        msj = "No se encontro al Productor";
        if (searchUserByNickName(producerNickName) != -1) {
            msj = "Este Productor ya registro este Audio";
            if (searchAudioByProducerAndName(name, producerNickName) == -1) {
                switch (option) {
                    case 1:
                        msj = "El Id no pertenece a un Artista";
                        if (users.get(searchUserByNickName(producerNickName)) instanceof Artist) {
                            Audio newSong = new Song(producerNickName.toUpperCase(), name.toUpperCase(), url, hours,
                                    minutes, seconds, album, genre,
                                    price);
                            audios.add(newSong);
                            msj = "Cancion Agregada Correctamente";
                        }
                        break;

                    case 2:
                        msj = "El Id no pertenece a un Creador de Contenido";
                        if (users.get(searchUserByNickName(producerNickName)) instanceof ContentCreator) {
                            Audio newPodcast = new Podcast(producerNickName.toUpperCase(), name.toUpperCase(), url,
                                    hours, minutes, seconds,
                                    description, category);
                            audios.add(newPodcast);
                            msj = "Podcast Agregado Correctamente";
                        }
                        break;
                }
            }
        }
        return msj;
    }

    /**
     * addPlaylist= This method add a Playlist to a Consumer
     * 
     * @param idConsumer:   String = The id of the Consumer
     * @param namePlaylist: String = The Name of the New Playlist
     * @return msj: String = A message that confirms if the Playlist was added
     *         successfully
     */
    public String addPlaylist(String idConsumer, String namePlaylist) {
        msj = "No se encontro al Consumidor " + idConsumer;
        if (searchUserById(idConsumer) != -1) {
            msj = "El id " + idConsumer + " no pertenece a un Consumidor";
            if (users.get(searchUserById(idConsumer)) instanceof Consumer) {
                PlayList newPlayList = new PlayList(namePlaylist);
                if (users.get(searchUserById(idConsumer)) instanceof Standard) {
                    msj = ((Standard) (users.get(searchUserById(idConsumer)))).addPlaylist(newPlayList);
                } else if (users.get(searchUserById(idConsumer)) instanceof Premium) {
                    msj = ((Consumer) (users.get(searchUserById(idConsumer)))).addPlaylist(newPlayList);
                }
            }
        }
        return msj;
    }

    /**
     * editPlaylist= This method allows to edit a playlist (Add audio, delete audio
     * or change the name)
     * 
     * @param idConsumer:       String = The id of the Consumer
     * @param playListCode:     String = The code of the Playlist
     * @param nameAudio:        String = The name of the audio to add or delete
     * @param producerNickName: String = The nickName of the producer of the audio
     *                          to add or
     *                          delete
     * @param newPlayListName:  String = The new Name of the playlis to change
     * @param option:           int = The option that the user selects 1=Add Audio,
     *                          2=Delete Audio or 3=Change Name of playlist
     * @return msj: String = A message that confirms if the playlist was edited
     *         successfully
     */
    public String editPlaylist(String idConsumer, int playlistPos, String nameAudio, String producerNickName,
            String newPlayListName, int option) {
        msj = "No se encontro el Audio " + nameAudio + " del Productor " + producerNickName;
        if (searchAudioByProducerAndName(nameAudio, producerNickName) != -1 || option == 3) {
            msj = "No se encontro al Usuario " + idConsumer;
            if (searchUserById(idConsumer) != -1) {
                msj = "El Usuario " + idConsumer + " no es un Consumidor ";
                if (users.get(searchUserById(idConsumer)) instanceof Consumer) {
                    msj = "La Playlist no fue encontrada";
                    if (playlistPos < ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().size()) {

                        int songPos = ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists()
                                .get(playlistPos)
                                .searchAudioByProducerAndName(nameAudio, producerNickName);
                        switch (option) {
                            case 1:
                                msj = addAudioToPlaylist(nameAudio, producerNickName, idConsumer, playlistPos);
                                break;

                            case 2:
                                msj = deleteAudioOfPlaylist(nameAudio, producerNickName, idConsumer, playlistPos,
                                        songPos);
                                break;

                            case 3:
                                msj = editPlaylistName(newPlayListName, idConsumer, playlistPos);
                                break;
                        }
                    }
                }
            }
        }
        return msj;
    }

    /**
     * addAudioToPlaylist = This method adds a Audio of a playlist
     * 
     * @param nameAudio:        String = The name of the audio to add
     * @param producerNickName: String = The nickName of the producer of the audio
     *                          to add
     * @param idConsumer:       String = The id of the Consumer
     * @param playlistPos:      int = The position of the Playlist in the arraylist
     *                          of
     *                          the Consumer
     * @return msj: String = A message that confirms the succesfully add of the
     *         Audio in the playlist
     */
    public String addAudioToPlaylist(String nameAudio, String producerNickName, String idConsumer, int playlistPos) {
        if (audios.get(searchAudioByProducerAndName(nameAudio, producerNickName)) instanceof Song) {
            Audio newSong = audios.get(searchAudioByProducerAndName(nameAudio, producerNickName));

            ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).addSong(newSong);
            msj = "Cancion Agregada a la Playlist Correctamente";
        } else if (audios.get(searchAudioByProducerAndName(nameAudio, producerNickName)) instanceof Podcast) {
            Audio newPodcast = audios.get(searchAudioByProducerAndName(nameAudio, producerNickName));
            ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).addPodcast(newPodcast);
            msj = "Podcast Agregado a la Playlist Correctamente";
        }
        ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).createCodeTypePlaylist();
        return msj;
    }

    /**
     * deleteAudioOfPlaylist= This method deletes a Audio of a playlist
     * 
     * @param nameAudio:        String = The name of the audio to delete
     * @param producerNickName: String = The nickName of the producer of the audio
     *                          to delete
     * @param idConsumer:       String = The id of the Consumer
     * @param playlistPos:      int = The position of the Playlist in the arraylist
     *                          of
     *                          the Consumer
     * @param audioPos:         int = The position of the Audio in the playlist
     * @return msj: String = A message that confirms the succesfully delete of the
     *         Audio in the playlist
     */
    public String deleteAudioOfPlaylist(String nameAudio, String producerNickName, String idConsumer, int playlistPos,
            int audioPos) {
        msj = "El audio no se encuentra en la playlist";
        if (audioPos !=-1) {
            if (audios.get(searchAudioByProducerAndName(nameAudio, producerNickName)) instanceof Song) {
                ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).deleteSong(audioPos);
                msj = "Cancion Eliminda de la Playlist Correctamente";
            } else if (audios.get(searchAudioByProducerAndName(nameAudio, producerNickName)) instanceof Podcast) {
                ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos)
                        .deletePodcast(audioPos);
                msj = "Podcast Eliminado de la Playlist Correctamente";
            }
            ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).createCodeTypePlaylist();
        }
        return msj;
    }

    /**
     * editPlaylistName= This method edit the name of a Playlist
     * 
     * @param playlistName: String = The new Name of the playlis to change
     * @param idConsumer:   String = The id of the Consumer
     * @param playlistPos:  int = The position of the Playlist in the arraylist of
     *                      the Consumer
     * @return msj: String = A message that confirms the successfully change of the
     *         Playlist name
     */
    public String editPlaylistName(String playlistName, String idConsumer, int playlistPos) {
        ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).setName(playlistName);
        msj = "El nombre de la Playlist fue cambiado exitosamente a " + playlistName;
        return msj;
    }

    /**
     * sharePlaylist= This method shares a playlist matriz and code of a Consumer
     * 
     * @param idConsumer:  String = The id of the user to share the playlist
     * @param playListPos: int = The position of the playlists in the Consumer
     *                     arrarylist
     * @return msj: String = The playlist matriz and code of the Consumer
     */
    public String sharePlaylist(String idConsumer, int playlistPos) {
        msj = "El usuario no existe";
        if (searchUserById(idConsumer) != -1) {
            msj = "Este id no pertenece a un Consumidor";
            if (users.get(searchUserById(idConsumer)) instanceof Consumer) {
                msj = "No existe la playlist buscada";
                if (playlistPos < ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().size()) {
                    msj = ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos)
                            .showMatrix();
                    msj += "\n El codigo de la Playlist " + ((Consumer) users.get(searchUserById(idConsumer)))
                            .getPlayLists().get(playlistPos).getName() + " es: "
                            + ((Consumer) users.get(searchUserById(idConsumer)))
                                    .getPlayLists().get(playlistPos).getPlaylistCode();
                }
            }
        }
        return msj;
    }

    /**
     * simulateAudioPlayback= This method simulates an audio Playback of a playlist
     * 
     * @param idConsumer:  String = The id of the Consumer
     * @param playlistPos: int = The position of the playlist in the Consumer
     *                     arraylist
     * @param audioPos:    int = The position of the audio in the Consumer playlist
     * @return msj: String = The audio play or an add before this
     */
    public String simulateAudioPlayback(String idConsumer, int playlistPos, int audioPos) {
        msj = "Este id no existe";
        if (searchUserById(idConsumer) != -1) {
            msj = "Este id no pertenece a un Consumidor";
            if (users.get(searchUserById(idConsumer)) instanceof Consumer) {
                msj = "No existe la Playlist buscada";
                if (playlistPos < ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().size()) {
                    msj = "No existe el Audio buscado";
                    if (audioPos < ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos)
                            .getAudios().size()) {
                        String nickNameProducer = ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists()
                                .get(playlistPos).getAudios().get(audioPos).getCreator();
                        ((Producer) users.get(searchUserByNickName(nickNameProducer))).setPlaybacks(
                                ((Producer) users.get(searchUserByNickName(nickNameProducer))).getPlaybacks() + 1);
                        if (users.get(searchUserById(idConsumer)) instanceof Standard) {
                            msj = ((Standard) users.get(searchUserById(idConsumer))).playAudio(playlistPos, audioPos);
                        } else {
                            msj = ((Premium) users.get(searchUserById(idConsumer))).playAudio(playlistPos, audioPos);
                        }
                    }

                }
            }
        }
        return msj;
    }

    /**
     * buySong= This method allows to buy a song of NeoTunes
     * 
     * @param idConsumer:       String = The id of the Consumer that will buy the
     *                          song
     * @param nameAudio:        String = The name of the song that will be buyed
     * @param nickNameProducer: String = The nickname of the producer of the song
     * @return msj: String = A confirmation or error of the song process
     */
    public String buySong(String idConsumer, String nameAudio, String nickNameProducer) {
        msj = "El id no existe";
        if (searchUserById(idConsumer) != -1) {
            msj = "El id no pertenece a un consumidor";
            if (users.get(searchUserById(idConsumer)) instanceof Consumer) {
                msj = "No existe el audio " + nameAudio + " de " + nickNameProducer;
                if (searchAudioByProducerAndName(nameAudio, nickNameProducer) != -1) {
                    msj = "Esto no es una canción";
                    if (audios.get(searchAudioByProducerAndName(nameAudio, nickNameProducer)) instanceof Song) {
                        if (users.get(searchUserById(idConsumer)) instanceof Standard) {
                            Audio newSong = audios.get(searchAudioByProducerAndName(nameAudio, nickNameProducer));
                            if (((Standard) users.get(searchUserById(idConsumer))).buySong(newSong)) {
                                ((Song) (audios.get(searchAudioByProducerAndName(nameAudio, nickNameProducer))))
                                        .setSales(((Song) (audios
                                                .get(searchAudioByProducerAndName(nameAudio, nickNameProducer))))
                                                .getSales() + 1);
                                return "Cancion comprada correctamente";
                            } else {
                                return "El Consumidor" + idConsumer
                                        + " ha alcanzado el limite de canciones, pasate a Premium para tener Playlists ilimitadas";
                            }

                        } else {
                            Audio newSong = audios.get(searchAudioByProducerAndName(nameAudio, nickNameProducer));
                            ((Premium) users.get(searchUserById(idConsumer))).buySong(newSong);
                            ((Song) (audios.get(searchAudioByProducerAndName(nameAudio, nickNameProducer))))
                                    .setSales(((Song) (audios
                                            .get(searchAudioByProducerAndName(nameAudio, nickNameProducer))))
                                            .getSales() + 1);
                            msj = "Cancion comprada correctamente";
                        }
                    }
                }
            }
        }
        return msj;
    }

    /**
     * showAudioPlaybacks= This method shows the Total playback of each type of
     * Audio
     * 
     * @return String = The Total playback of each type of Audio
     */
    public String showAudioPlaybacks() {
        int songsPlayback = 0;
        int podcastPlayback = 0;
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                songsPlayback += audios.get(i).getPlaybacks();
            } else {
                podcastPlayback += audios.get(i).getPlaybacks();
            }
        }
        return "Canciones " + songsPlayback + " reproducciones totales" + "\nPodcasts " + podcastPlayback
                + " reproducciones totale";
    }

    /**
     * showMostListenGenre= This method shows the most listen genre in neoTunes or
     * for a specific person
     * 
     * @param idConsumer: String = The id of the Consumer
     * @param option:     int = 1) To show the most listen Genre in NeoTunes or 2)
     *                    for an specific person
     * @return msj: String = The most listen genre in NeoTunes or for an specific
     *         person
     */
    public String showMostListenGenre(String idConsumer, int option) {
        msj = "";
        if (option == 2) {
            int house = 0;
            int pop = 0;
            int rock = 0;
            int trap = 0;
            int mostListen = 0;
            for (int i = 0; i < audios.size(); i++) {
                if (audios.get(i) instanceof Song) {
                    switch (((Song) audios.get(i)).getGenre()) {
                        case HOUSE:
                            house += audios.get(i).getPlaybacks();
                            if (house >= mostListen) {
                                mostListen = house;
                                msj = "El genero mas escuhado en NeoTunes es HOUSE con " + house
                                        + " reproducciones";
                            }
                            break;
                        case POP:
                            pop += audios.get(i).getPlaybacks();
                            if (pop >= mostListen) {
                                mostListen = pop;
                                msj = "El genero mas escuhado en NeoTunes es POP con " + pop + " reproducciones";
                            }
                            break;

                        case ROCK:
                            rock += audios.get(i).getPlaybacks();
                            if (rock >= mostListen) {
                                mostListen = rock;
                                msj = "El genero mas escuhado en NeoTuneses es ROCK con " + rock
                                        + " reproducciones";
                            }
                            break;

                        case TRAP:
                            trap += audios.get(i).getPlaybacks();
                            if (trap >= mostListen) {
                                mostListen = trap;
                                msj = "El genero mas escuhado en NeoTunes es TRAP con " + trap + " reproducciones";
                            }
                            break;
                    }
                }
            }
        } else {
            msj = "Este id no existe";
            if (searchUserById(idConsumer) != -1) {
                msj = "Este id no pertenece a un Consumidor";
                if (users.get(searchUserById(idConsumer)) instanceof Consumer) {
                    msj = ((Consumer) users.get(searchUserById(idConsumer))).showMosListenGenre();
                }
            }
        }
        return msj;
    }

    /**
     * showMostListenCategory= This method shows the most listen category in
     * neoTunes or for a specific person
     * 
     * @param idConsumer: String = The id of the Consumer
     * @param option:     int = 1) To show the most listen category in NeoTunes or
     *                    2) for an specific person
     * @return msj: String = The most listen category in NeoTunes or for an specific
     *         person
     */
    public String showMostListenCartegory(String idConsumer, int option) {
        msj = "";
        if (option == 2) {
            int politica = 0;
            int entretenimiento = 0;
            int videojuegos = 0;
            int moda = 0;
            int mostListen = 0;
            for (int i = 0; i < audios.size(); i++) {
                if (audios.get(i) instanceof Podcast) {
                    switch (((Podcast) audios.get(i)).getCategory()) {
                        case POLITICA:
                            politica += audios.get(i).getPlaybacks();
                            if (politica >= mostListen) {
                                mostListen = politica;
                                msj = "La categoria mas escuhada en NeoTunes es POLITICA con " + politica
                                        + " reproducciones";
                            }
                            break;
                        case ENTRETENIMIENTO:
                            entretenimiento += audios.get(i).getPlaybacks();
                            if (entretenimiento >= mostListen) {
                                mostListen = entretenimiento;
                                msj = "La categoria mas escuhada en NeoTunes es ENTRETENIMIENTO con " + entretenimiento
                                        + " reproducciones";
                            }
                            break;

                        case VIDEOJUEGOS:
                            videojuegos += audios.get(i).getPlaybacks();
                            if (videojuegos >= mostListen) {
                                mostListen = videojuegos;
                                msj = "La categoria mas escuhada en NeoTuneses es VIDEOJUEGOS con " + videojuegos
                                        + " reproducciones";
                            }
                            break;

                        case MODA:
                            moda += audios.get(i).getPlaybacks();
                            if (moda >= mostListen) {
                                mostListen = moda;
                                msj = "La categoria mas escuhada en NeoTunes es MODA con " + moda + " reproducciones";
                            }
                            break;
                    }
                }
            }
        } else {
            msj = "Este id no existe";
            if (searchUserById(idConsumer) != -1) {
                msj = "Este id no pertenece a un Consumidor";
                if (users.get(searchUserById(idConsumer)) instanceof Consumer) {
                    msj = ((Consumer) users.get(searchUserById(idConsumer))).showMosListenCategory();
                }
            }
        }
        return msj;
    }

    /**
     * showTop5Producers= This method show the top 5 of artist and Content creator
     * according to their playbacks
     * 
     * @return msj: String = The top 5 of artist and Content creator according to
     *         their playbacks
     */
    public String showTop5Producers() {
        msj = "";
        ArrayList<Artist> artists = new ArrayList<Artist>();
        ArrayList<ContentCreator> contentCreators = new ArrayList<ContentCreator>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Artist) {
                artists.add(((Artist) users.get(i)));
            }
        }
        Collections.sort(artists);
        msj= "\nTop 5 Artistas:";
        for (int i = 0; i < 5; i++) {
            msj += (i + 1) + ") NickName: " + artists.get(i).getNickName() + " reproducciones "
                    + artists.get(i).getPlaybacks() + "\n";
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof ContentCreator) {
                contentCreators.add(((ContentCreator) users.get(i)));
            }
        }
        Collections.sort(contentCreators);
        msj+= "\nTop 5 Creadores de Contenido:";
        for (int i = 0; i < 5; i++) {
            msj += (i + 1) + ") NickName: " + contentCreators.get(i).getNickName() + " reproducciones "
                    + contentCreators.get(i).getPlaybacks() + "\n";
        }
        return msj;
    }

    /**
     * showTop10Audios= This methdod show the top 10 of songs and podcasts according
     * to their playbacks
     * 
     * @return msj: String = The top 10 of songs and podcasts according to their
     *         playbacks
     */
    public String showTop10Audios() {
        msj = "";
        ArrayList<Song> songs = new ArrayList<Song>();
        ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                songs.add(((Song) audios.get(i)));
            }
        }
        Collections.sort(songs);
        msj= "\nTop 10 canciones:";
        for (int i = 0; i < 10; i++) {
            msj += (i + 1) + ") NickName: " + songs.get(i).getName() + " genero " + songs.get(i).getGenre()
                    + " reproducciones " + songs.get(i).getPlaybacks() + "\n";
        }
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Podcast) {
                podcasts.add(((Podcast) audios.get(i)));
            }
        }
        Collections.sort(podcasts);
        msj+= "\nTop 10 podcasts:";
        for (int i = 0; i < 10; i++) {
            msj += (i + 1) + ") NickName: " + podcasts.get(i).getName() + " categoria " + podcasts.get(i).getCategory()
                    + " reproducciones " + podcasts.get(i).getPlaybacks() + "\n";
        }
        return msj;
    }

    /**
     * showGenreInfo= This method shows info about each Song Genre in NeoTunes
     * 
     * @return msj: String = Info about each Song Genre in NeoTunes
     */
    public String showGenreInfo() {
        msj = "";
        int house = 0;
        int pop = 0;
        int rock = 0;
        int trap = 0;
        double salesHouse = 0;
        double salesPop = 0;
        double salesRock = 0;
        double salesTrap = 0;
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                switch (((Song) audios.get(i)).getGenre()) {
                    case HOUSE:
                        house += ((Song) audios.get(i)).getSales();
                        salesHouse += (((Song) audios.get(i)).getPrice() * ((Song) audios.get(i)).getSales());
                        break;
                    case POP:
                        pop += ((Song) audios.get(i)).getSales();
                        salesPop += (((Song) audios.get(i)).getPrice() * ((Song) audios.get(i)).getSales());
                        break;

                    case ROCK:
                        rock += ((Song) audios.get(i)).getSales();
                        salesRock += (((Song) audios.get(i)).getPrice() * ((Song) audios.get(i)).getSales());
                        break;

                    case TRAP:
                        trap += ((Song) audios.get(i)).getSales();
                        salesTrap += (((Song) audios.get(i)).getPrice() * ((Song) audios.get(i)).getSales());
                        break;
                }
            }
        }
        msj = "HOUSE: " + house + " canciones vendidas con ganancias de $ " + salesHouse + "\nPOP: " + pop
                + " canciones vendidas con ganancias de $ "
                + salesPop + "\nROCK: " + rock + " canciones vendidas con ganancias de $ " + salesRock + "\nTRAP: "
                + trap
                + " canciones vendidas con ganancias de $ " + salesTrap;
        return msj;
    }

    /**
     * showMostSaleSong= This method shows the most sale song and his info
     * 
     * @return msj: String = The most sale song and his info
     */
    public String showMostSaleSong() {
        msj = "";
        int sales = 0;
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                if (((Song) audios.get(i)).getSales() >= sales) {
                    sales = ((Song) audios.get(i)).getSales();
                    msj = "La cancion " + ((Song) audios.get(i)).getName() + " es la mas vendida con "
                            + ((Song) audios.get(i)).getSales() + " ventas y con ganancias de $ "
                            + (((Song) audios.get(i)).getSales() * ((Song) audios.get(i)).getPrice());
                }
            }
        }
        return msj;
    }

    /**
     * searchUserById= A method that searchs a user by his id, and return the
     * position.
     * 
     * @param id: String = The id to search in the Arraylist of users
     * @return pos: int = The posisition of the user in the Arraylist or -1 if the
     *         person doesn´t exist
     */
    public int searchUserById(String id) {
        int pos = -1;
        boolean isFound = false;
        for (int i = 0; i < users.size() && !isFound; i++) {
            if (id.equals(users.get(i).getId())) {
                pos = i;
                isFound = true;
            }
        }
        return pos;
    }

    /**
     * searchUserByNickName= A method that searchs a user by his nickName, and
     * return the
     * position.
     * 
     * @param nickName: String = The nickName to search in the Arraylist of users
     * @return pos: int = The posisition of the user in the Arraylist or -1 if the
     *         person doesn´t exist
     */
    public int searchUserByNickName(String nickName) {
        int pos = -1;
        boolean isFound = false;
        for (int i = 0; i < users.size() && !isFound; i++) {
            if (nickName.equalsIgnoreCase(users.get(i).getNickName())) {
                pos = i;
                isFound = true;
            }
        }
        return pos;
    }

    /**
     * searchAudioByName= A method that searchs a audio by his Name, and return the
     * position.
     * 
     * @param name: String = The name to search in the Arraylist of audios
     * @return pos: int = The posisition of the audio in the Arraylist or -1 if the
     *         audio doesn´t exist
     */
    public int searchAudioByName(String name) {
        int pos = -1;
        boolean isFound = false;
        for (int i = 0; i < audios.size() && !isFound; i++) {
            if (name.equals(audios.get(i).getName())) {
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
    public int searchAudioByProducerAndName(String name, String producerNickname) {
        boolean sameSong = false;
        int pos = -1;
        for (int i = 0; i < audios.size() && !sameSong; i++) {
            if (audios.get(i).getName().equalsIgnoreCase(name)) {
                if ((audios).get(i).getCreator().equalsIgnoreCase(producerNickname)) {
                    sameSong = true;
                    pos = i;
                }
            }
        }
        return pos;
    }

    /**
     * showAudio= This method Shows the Songs and Podcast of NeoTunes and their
     * producers id
     * 
     * @return msj: Sting = An specification of the type of Audio, the name of the
     *         Audio and the Id of his producer
     */
    public String showAudio() {
        msj = "";
        if (audios.size() != 0) {
            msj = "Nombre del Audio: Id del Productor\n";
            for (int i = 0; i < audios.size(); i++) {
                if (audios.get(i) instanceof Song) {
                    msj += "(Cancion) ";
                } else {
                    msj += "(Podcast) ";
                }
                msj += audios.get(i).getName() + ": " + audios.get(i).getCreator() + "\n";
            }
        }
        return msj;
    }

    /**
     * showConsumerPlaylists= Shows the playlist of a Consumer
     * 
     * @param idConsumer: String = The id of the Consumer
     * @return msj: String = The playlist of the Consumer and their Code
     */
    public String showConsumerPlaylists(String idConsumer) {
        msj = "";
        if (searchUserById(idConsumer) != -1 && users.get(searchUserById(idConsumer)) instanceof Consumer) {
            int playlistSize = ((Consumer) (users.get(searchUserById(idConsumer)))).getPlayLists().size();
            if (playlistSize != 0) {
                msj = "Playlists del Consumidor " + idConsumer + "\nIndice de la playlist: Nombre de la Playlist\n";
                for (int i = 0; i < ((Consumer) (users.get(searchUserById(idConsumer)))).getPlayLists().size(); i++) {
                    msj += (i + 1) + ") "
                            + ((Consumer) (users.get(searchUserById(idConsumer)))).getPlayLists().get(i).getName()
                            + "\n";
                }
            } else {
                msj = "Uppss, parece que no existe una playlist";
            }
        }
        return msj;
    }

    public String showPlaylistAudios(String idConsumer, int playlistPos) {
        msj = "";
        if (searchUserById(idConsumer) != -1 && users.get(searchUserById(idConsumer)) instanceof Consumer) {
            if (playlistPos < ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().size()) {
                msj = ((Consumer) users.get(searchUserById(idConsumer))).getPlayLists().get(playlistPos).showAudio();
            }
        }
        return msj;
    }

    /**
     * showSongGenreList= Shows a list of the SongGenre enmueration
     * 
     * @return msj: String= The list of the SongGenre enumeration
     */
    public String showSongGenre() {
        SongGenre songGenre[] = SongGenre.values();
        msj = "Generos Musicales: ";
        for (int i = 0; i < songGenre.length; i++) {
            msj += "\n" + (i + 1) + " " + songGenre[i];
        }
        return msj;
    }

    /**
     * showPodcastCategory= Shows a list of the PodcastCategory enmueration
     * 
     * @return msj: String= The list of the PodcastCategorys enumeration
     */
    public String showPodcastCategory() {
        PodcastCategory podcastCategory[] = PodcastCategory.values();
        msj = "Categorias de Podcasts: ";
        for (int i = 0; i < podcastCategory.length; i++) {
            msj += "\n" + (i + 1) + " " + podcastCategory[i];
        }
        return msj;
    }
}