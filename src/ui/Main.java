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

		do{

			option = main.getOptionShowMenu(); 
			main.executeOption(option);

		}while(option != 0);

		main.getReader().close();

	}

	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("<<<<< Welcome to NeoTunes >>>>>");
		System.out.println(
				"1. Registrar Productor\n" +
				"2. Registrar Consumidor\n" +
				"3. Registrar Canciones y Podcasts \n" +
                "4. Crear una lista de Reproduccion \n" +
                "5. Editar una lista de Reproduccion \n" +
				"0. Exit. ");
		option =  validateIntegerInput();
		return option; 
	}

	public void executeOption(int option){

		switch(option){
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

			case 0: 
				System.out.println("Exit program.");
				break; 

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}

	public Scanner getReader(){
		return reader; 
	}
	/**
	 * Ui Method
	 * This method request info and add a Producer to NeoTunes
	 */
	public void addProducer(){
		System.out.println("Elige una opcion");
		System.out.println("1. Artista \n" + "2. Creador de Contenido \n");
		int option=validateIntegerInput();
		while(option>2 || option<1){
			System.out.println("Elige una opcion Valida");
			System.out.println("1. Artista \n" + "2. Creador de Contenido \n");
			option=validateIntegerInput();
		}
		System.out.println("Escribe el Nickname");
		String nickName=reader.next();
		System.out.println("Escribe el Id");
		String id= reader.next();
		System.out.println("Escribe la Url de la Imagen");
		String imageUrl=reader.next();
		System.out.println("Escribe su Nombre");
		String name=reader.next();
		System.out.println(controller.addProducer(nickName, id, imageUrl, name, option));
	}
	/**
	 * Ui Method
	 * This method request info and add a Consumer to NeoTunes
	 */
	public void addConsumer(){
		System.out.println("Elige una opcion");
		System.out.println("1. Consumidor Estandar \n" + "2. Consumidor Premium \n");
		int option=validateIntegerInput();
		while(option>2 || option<1){
			System.out.println("Elige una opcion Valida");
			System.out.println("1. Consumidor Estandar \n" + "2. Consumidor Premium \n");
			option=validateIntegerInput();
		}
		System.out.println("Escribe el Nickname");
		String nickName=reader.next();
		System.out.println("Escribe el Id");
		String id= reader.next();
		System.out.println(controller.addConsumer(nickName, id, option));
	}
	/**
	 * Ui Method
	 * This method request info and add a Audio to NeoTunes
	 */
	public void addAudio(){
		System.out.println("Elige una opcion");
		System.out.println("1. Cancion \n" + "2. Podcast \n");
		int option=validateIntegerInput();
		while(option>2 || option<1){
			System.out.println("Elige una opcion Valida");
			System.out.println("1. Cancion \n" + "2. Podcast \n");
			option=validateIntegerInput();
		}
		System.out.println("Escribe el Id del Productor");
		String producer= reader.next();
		System.out.println("Escribe el Nombre del Audio");
		String name=reader.next();
		System.out.println("Escribe su Url");
    	String url=reader.next();
		System.out.println("Escribe las horas de Duracion");
    	int hours=validateIntegerInput();
		while(hours>23 || hours<0){
			System.out.println("Escribe las horas (0-23)");
			hours=validateIntegerInput();
		}
		System.out.println("Escribe los minutos de Duracion");
    	int minutes=validateIntegerInput();
		while(minutes>59 || minutes<0){
			System.out.println("Escribe los minutos (0-59)");
			minutes=validateIntegerInput();
		}
		System.out.println("Escribe los segundos de Duracion");
    	int seconds=validateIntegerInput();
		while(seconds>59 || seconds<0){
			System.out.println("Escribe los segundos (0-59)");
			seconds=validateIntegerInput();
		}
		if(option==1){
			System.out.println(controller.showSongGenre());
			int genre=validateIntegerInput();
			while(genre>4 || genre<1){
				System.out.println("Escribe una opcion Valida");
				genre=validateIntegerInput();
			}
			System.out.println("Escribe el precio de venta");
			double price=validateDoubleInput();
			while (price==-1) {
				System.out.println("Escribe un Precio Valido");
				price=validateDoubleInput();
			}
			System.out.println(controller.addAudio(producer,name, url, hours, minutes, seconds, name, genre, price, null, 0, option));
		}else{
			System.out.println("Escribe la Descripcion del Podcast");
			String description=reader.next();
			System.out.println(controller.showPodcastCategory());
			int category=validateIntegerInput();
			while(category>4 || category<1){
				System.out.println("Escribe una opcion Valida");
				category=validateIntegerInput();
			}
			System.out.println(controller.addAudio(producer,name, url, hours, minutes, seconds, null, 0, 0, description, category, option));
		}

	}
	/**
	 * Ui Method
	 * This method request info and add a Playlist to a Consumer
	 */
	public void addPlaylist(){
		System.out.println("Escribe el Id del Consumidor");
		String id= reader.next();
		System.out.println("Escribe el Nombre de la playlist");
		String namePlayList= reader.next();
		System.out.println(controller.addPlaylist(id, namePlayList));
	}
	/**
	 * Ui Method
	 * This method request info and edit a Consumer Playlist
	 */
	public void editPlaylist(){
		System.out.println("Escribe el Id del Consumidor");
		String idConsumer=reader.next();
		System.out.println(controller.showConsumerPlaylists(idConsumer));
		System.out.println("Escribe el Codigo de la Playlist");
		String playlistCode=reader.next();
		System.out.println("1) Añadir Audio \n"+ "2) Eliminar Audio \n" + "3) Cambiar nombre de la Playlist");
		int option= validateIntegerInput();
		while(option>3 || option<1){
			System.out.println("1) Añadir Audio \n"+ "2) Eliminar Auido \n" + "3) Cambiar nombre de la Playlist");
			option=validateIntegerInput();
		}
		switch(option){
			case 1:
			System.out.println("Catalogo de Audio NeoTunes");
			System.out.println(controller.showAudio());
			System.out.println("Escribe el Nombre del Audio");
			String nameAudio= reader.next(); 
			System.out.println("Escribe el Id del Productor");
			String producerId= reader.next(); 
			System.out.println(controller.editPlaylist(idConsumer, playlistCode, nameAudio, producerId, "", option));
			break;

			case 2:
			System.out.println("Catalogo de Audio NeoTunes");
			System.out.println(controller.showAudio());
			System.out.println("Escribe el Nombre del Audio");
			nameAudio= reader.next(); 
			System.out.println("Escribe el Id del Productor");
			producerId= reader.next(); 
			System.out.println(controller.editPlaylist(idConsumer, playlistCode, nameAudio, producerId, "", option));
			break;

			case 3:
			System.out.println("Escribe el nuevo Nombre de la Playlist");
			String playListName= reader.next(); 
			System.out.println(controller.editPlaylist(idConsumer, playlistCode, "", "", playListName, option));
			break;
		}
		
	}
	/**validateIntegerInput= This method check if a Input is a Integer
	 * @return option= Return the input if was and Integer or -1 if not
	 */
	public int validateIntegerInput(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}
	/**validateDoubleInput= This method check if a Input is a Double
	 * @return option= Return the input if was and Double or -1 if not
	 */
	public double validateDoubleInput(){
		double option = 0; 

		if(reader.hasNextDouble()){
			option = reader.nextDouble(); 
		}
		else{
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}
}