package ui;

import model.*;
import java.util.Scanner;

public class Main {

	private NeoTunesController controller;
	private Scanner reader;

	public Main() {
		controller = new NeoTunesController();
		reader = new Scanner(System.in);

	}

	public static void main(String[] args) {

		Main main = new Main();
		int option = 0;

		do {

			option = main.getOptionShowMenu();
			main.executeOption(option);

		} while (option != 0);

		main.getReader().close();

	}

	public int getOptionShowMenu() {
		int option = 0;
		System.out.println("<<<<< Welcome to NeoTunes >>>>>");
		System.out.println(
				"1. Registrar Productor\n" +
						"2. Registrar Consumidor\n" +
						"3. Registrar Canciones y Podcasts \n" +
						"4. Crear una lista de Reproduccion \n" +
						"5. Editar una lista de Reproduccion \n" +
						"6. Compartir una lista de Reproduccion \n" +
						"7. Simular la reproduccion de una cancion o podcast \n" +
						"8. Comprar una cancion \n" +
						"9. Generar informes con los datos registrados \n" +
						"0. Exit. ");
		option = validateIntegerInput();
		return option;
	}

	public void executeOption(int option) {

		switch (option) {
			case 1:
				addProducer();
				break;

			case 2:
				addConsumer();
				break;
			case 3:
				addAudio();
				break;

			case 4:
				addPlaylist();
				break;

			case 5:
				editPlaylist();
				break;

			case 6:
				sharePlaylist();
				break;

			case 7:
				simulateAudioPlayback();
				break;

			case 8:
				buySong();
				break;

			case 9:
				generateReports();
				break;

			case 0:
				System.out.println("Exit program.");
				break;

			default:
				System.out.println("Invalid Option");
				break;
		}
	}

	public Scanner getReader() {
		return reader;
	}

	/**
	 * Ui Method
	 * This method request info and add a Producer to NeoTunes
	 */
	public void addProducer() {
		System.out.println("Elige una opcion");
		System.out.println("1. Artista \n" + "2. Creador de Contenido \n");
		int option = validateIntegerInput();
		while (option > 2 || option < 1) {
			System.out.println("Elige una opcion Valida");
			System.out.println("1. Artista \n" + "2. Creador de Contenido \n");
			option = validateIntegerInput();
		}
		System.out.println("Escribe el Nickname");
		String nickName = reader.next();
		System.out.println("Escribe el Id");
		String id = reader.next();
		System.out.println("Escribe la Url de la Imagen");
		String imageUrl = reader.next();
		System.out.println("Escribe su Nombre");
		String name = reader.next();
		System.out.println(controller.addProducer(nickName, id, imageUrl, name, option));
	}

	/**
	 * Ui Method
	 * This method request info and add a Consumer to NeoTunes
	 */
	public void addConsumer() {
		System.out.println("Elige una opcion");
		System.out.println("1. Consumidor Estandar \n" + "2. Consumidor Premium \n");
		int option = validateIntegerInput();
		while (option > 2 || option < 1) {
			System.out.println("Elige una opcion Valida");
			System.out.println("1. Consumidor Estandar \n" + "2. Consumidor Premium \n");
			option = validateIntegerInput();
		}
		System.out.println("Escribe el Nickname");
		String nickName = reader.next();
		System.out.println("Escribe el Id");
		String id = reader.next();
		System.out.println(controller.addConsumer(nickName, id, option));
	}

	/**
	 * Ui Method
	 * This method request info and add a Audio to NeoTunes
	 */
	public void addAudio() {
		System.out.println("Elige una opcion");
		System.out.println("1. Cancion \n" + "2. Podcast \n");
		int option = validateIntegerInput();
		while (option > 2 || option < 1) {
			System.out.println("Elige una opcion Valida");
			System.out.println("1. Cancion \n" + "2. Podcast \n");
			option = validateIntegerInput();
		}
		System.out.println("Escribe el NickName del Productor");
		String producer = reader.next();
		System.out.println("Escribe el Nombre del Audio");
		String name = reader.next();
		System.out.println("Escribe su Url");
		String url = reader.next();
		System.out.println("Escribe las horas de Duracion");
		int hours = validateIntegerInput();
		while (hours > 23 || hours < 0) {
			System.out.println("Escribe las horas (0-23)");
			hours = validateIntegerInput();
		}
		System.out.println("Escribe los minutos de Duracion");
		int minutes = validateIntegerInput();
		while (minutes > 59 || minutes < 0) {
			System.out.println("Escribe los minutos (0-59)");
			minutes = validateIntegerInput();
		}
		System.out.println("Escribe los segundos de Duracion");
		int seconds = validateIntegerInput();
		while (seconds > 59 || seconds < 0) {
			System.out.println("Escribe los segundos (0-59)");
			seconds = validateIntegerInput();
		}
		if (option == 1) {
			System.out.println(controller.showSongGenre());
			int genre = validateIntegerInput();
			while (genre > 4 || genre < 1) {
				System.out.println("Escribe una opcion Valida");
				genre = validateIntegerInput();
			}
			System.out.println("Escribe el precio de venta");
			double price = validateDoubleInput();
			while (price == -1) {
				System.out.println("Escribe un Precio Valido");
				price = validateDoubleInput();
			}
			System.out.println(controller.addAudio(producer, name, url, hours, minutes, seconds, name, genre, price,
					null, 0, option));
		} else {
			System.out.println("Escribe la Descripcion del Podcast");
			String description = reader.next();
			System.out.println(controller.showPodcastCategory());
			int category = validateIntegerInput();
			while (category > 4 || category < 1) {
				System.out.println("Escribe una opcion Valida");
				category = validateIntegerInput();
			}
			System.out.println(controller.addAudio(producer, name, url, hours, minutes, seconds, null, 0, 0,
					description, category, option));
		}

	}

	/**
	 * Ui Method
	 * This method request info and add a Playlist to a Consumer
	 */
	public void addPlaylist() {
		System.out.println("Escribe el Id del Consumidor");
		String id = reader.next();
		System.out.println("Escribe el Nombre de la playlist");
		String namePlayList = reader.next();
		System.out.println(controller.addPlaylist(id, namePlayList));
	}

	/**
	 * Ui Method
	 * This method request info and edit a Consumer Playlist
	 */
	public void editPlaylist() {
		System.out.println("Escribe el Id del Consumidor");
		String idConsumer = reader.next();
		System.out.println(controller.showConsumerPlaylists(idConsumer));
		System.out.println("Escribe el indice de la Playlist");
		int playlistPos = validateIntegerInput();
		while (playlistPos == -1) {
			System.out.println("Escribe un numero entero");
			playlistPos = validateIntegerInput();
		}
		System.out.println("1) Añadir Audio \n" + "2) Eliminar Audio \n" + "3) Cambiar nombre de la Playlist");
		int option = validateIntegerInput();
		while (option > 3 || option < 1) {
			System.out.println("1) Añadir Audio \n" + "2) Eliminar Auido \n" + "3) Cambiar nombre de la Playlist");
			option = validateIntegerInput();
		}
		switch (option) {
			case 1:
				System.out.println("Catalogo de Audio NeoTunes");
				System.out.println(controller.showAudio());
				System.out.println("Escribe el Nombre del Audio");
				String nameAudio = reader.next();
				System.out.println("Escribe el NickName del Productor");
				String producerNickName = reader.next();
				System.out
						.println(controller.editPlaylist(idConsumer, playlistPos - 1, nameAudio, producerNickName, "",
								option));
				break;

			case 2:
				System.out.println(controller.showPlaylistAudios(idConsumer, playlistPos - 1));
				System.out.println("Escribe el Nombre del Audio");
				nameAudio = reader.next();
				System.out.println("Escribe el NickName del Productor");
				producerNickName = reader.next();
				System.out
						.println(controller.editPlaylist(idConsumer, playlistPos - 1, nameAudio, producerNickName, "",
								option));
				break;

			case 3:
				System.out.println("Escribe el nuevo Nombre de la Playlist");
				String playListName = reader.next();
				System.out.println(controller.editPlaylist(idConsumer, playlistPos - 1, "", "", playListName, option));
				break;
		}

	}

	/**
	 * Ui Method
	 * This method request info and share a Playlist
	 */
	public void sharePlaylist() {
		System.out.println("Escribe el id del Consumidor");
		String idConsumer = reader.next();
		System.out.println(controller.showConsumerPlaylists(idConsumer));
		System.out.println("Escribe el indice de la playlist");
		int playListpos = validateIntegerInput();
		while (playListpos == -1 || playListpos < 1) {
			System.out.println("Escribe un numero entero mayor a 1");
			playListpos = validateIntegerInput();
		}
		System.out.println(controller.sharePlaylist(idConsumer, playListpos - 1));
	}

	/**
	 * Ui Method
	 * This method request info and simulate an Audio Playback
	 */
	public void simulateAudioPlayback() {
		int playbackAgain=1;
		while (playbackAgain==1) {
			System.out.println("Escribe el id del Consumidor");
			String idConsumer = reader.next();
			System.out.println(controller.showConsumerPlaylists(idConsumer));
			System.out.println("Escribe el indice de la Playlist");
			int playlistPos = validateIntegerInput();
			while (playlistPos == -1) {
				System.out.println("Escribe un numero entero");
				playlistPos = validateIntegerInput();
			}
			System.out.println(controller.showPlaylistAudios(idConsumer, playlistPos-1));
			System.out.println("Escribe el indice del audio");
			int audioPos = validateIntegerInput();
			while (audioPos == -1 || audioPos<1) {
				System.out.println("Escribe un numero entero");
				audioPos = validateIntegerInput();
			}
			System.out.println(controller.simulateAudioPlayback(idConsumer, playlistPos-1, audioPos-1));
			System.out.println("¿Desea reproducir otro audio? \n1)Si \n2)No");
			playbackAgain=validateIntegerInput();
			while (playbackAgain>2 || playbackAgain<1) {
				System.out.println("Escriba una opción valida");
				playbackAgain=validateIntegerInput();
			}
		}
	}
	/**
	 * Ui Method
	 * This method request info and buy a song
	 */
	public void buySong(){
		System.out.println("Escribe el id del Consumidor");
		String idConsumer=reader.next();
		System.out.println(controller.showAudio());
		System.out.println("Escribe el nombre de la canción");
		String nameSong=reader.next();
		System.out.println("Escribe el nombre del Productor");
		String nickNameProducer=reader.next();
		System.out.println(controller.buySong(idConsumer, nameSong, nickNameProducer));
	}
	/**
	 * Ui Method
	 * This method request info and generate Reports
	 */
	public void generateReports(){
		System.out.println("1.Para cada tipo de audio, canciones y podcast, informar el acumulado total de reproducciones en toda la plataforma.\n"+
		"2.Informar el género de canción más escuchado para un usuario específico y para toda la plataforma.\n"+
		"3.Informar la categoría de podcast más escuchada para un usuario específico y para toda la plataforma.\n"+
		"4.Top 5 de artistas y del Top 5 de creadores de contenido en la plataforma\n"+
		"5.Top 10 de canciones y del Top 10 de podcast\n"+
		"6.De cada género, informar el número de canciones vendidas y el valor total de ventas ($).\n"+
		"7.De la canción más vendida en la plataforma, informar el número total de ventas y el valor total de venta ($).");
		int subMenuOption=validateIntegerInput();
		while (subMenuOption<1 || subMenuOption>7) {
			System.out.println("Escriba una opcion valida");
			subMenuOption=validateIntegerInput();
		}
		switch (subMenuOption) {
			case 1:
				System.out.println(controller.showAudioPlaybacks());
				break;
		
			case 2:
				System.out.println("1)Para un usuario en especifico \n2)Para toda la plataforma ");
				int option= validateIntegerInput();
				while (option<1 || option>2) {
					System.out.println("Escriba una opcion valida");
					option=validateIntegerInput();
				}
				if (option==2) {
					System.out.println(controller.showMostListenGenre("", option));
				}else { 
					System.out.println("Escribe el id del Consumidor");
					String idConsumer=reader.next();
					System.out.println(controller.showMostListenGenre(idConsumer, option));
				}
				break;

			case 3:
				System.out.println("1)Para un usuario en especifico \n2)Para toda la plataforma ");
				option= validateIntegerInput();
				while (option<1 || option>2) {
					System.out.println("Escriba una opcion valida");
					option=validateIntegerInput();
				}
				if (option==2) {
					System.out.println(controller.showMostListenCartegory("", option));
				}else { 
					System.out.println("Escribe el id del Consumidor");
					String idConsumer=reader.next();
					System.out.println(controller.showMostListenCartegory(idConsumer, option));
				}
				break;

			case 4:
				System.out.println(controller.showTop5Producers());
				break;

			case 5:
				System.out.println(controller.showTop10Audios());
				break;

			case 6:
				System.out.println(controller.showGenreInfo());
				break;

			case 7:
				System.out.println(controller.showMostSaleSong());
				break;
		}
	}

	/**
	 * validateIntegerInput= This method check if a Input is a Integer
	 * 
	 * @return option= Return the input if was and Integer or -1 if not
	 */
	public int validateIntegerInput() {
		int option = 0;

		if (reader.hasNextInt()) {
			option = reader.nextInt();
		} else {
			reader.nextLine();
			option = -1;
		}

		return option;
	}

	/**
	 * validateDoubleInput= This method check if a Input is a Double
	 * 
	 * @return option= Return the input if was and Double or -1 if not
	 */
	public double validateDoubleInput() {
		double option = 0;

		if (reader.hasNextDouble()) {
			option = reader.nextDouble();
		} else {
			reader.nextLine();
			option = -1;
		}

		return option;
	}
}