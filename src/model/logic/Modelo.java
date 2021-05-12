package model.logic;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;



import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
import model.data_structures.RedBlackTree;
import model.data_structures.TablaHashLinearProbing;
import model.data_structures.TablaHashSeparateChaining;
import model.logic.YouTubeVideo.ComparadorXLikes;
import utils.ComparadorXDiasTendencia;
import utils.ComparadorXViews;

import utils.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	private static final String VIDEO = "./data/videos-all.csv";
	/**
	 * Atributos del modelo del mundo
	 */
	private RedBlackTree<String, ArregloDinamico<Repeticion>> arbolContextContent;
	
	private ArregloDinamico<String> datos;
	
	private ListaEncadenada<YouTubeVideo> videos;
	
	private ArregloDinamico<YouTubeVideo> vidios;
	
//	private ArrayList<String> categorias;
	
	private ILista<YouTubeVideo> subLista;
	
	private ArrayList<Categoria> categorias;
	
	private TablaHashLinearProbing< String , String> tablaLinear;
	private TablaHashSeparateChaining<String , String> tablaSeparate;
	
	private ArregloDinamico<ContextContentFeatures> contextContent;

	private ArregloDinamico<String> artistas;
	private ArregloDinamico<String> canciones;
	
//	private Ordenamiento<YouTubeVideo> ordenamiento;
	
//	private Ordenamiento<YouTubeVideo> ordenamiento;
	
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo()
	{
		datos = new ArregloDinamico<String>();
		videos = new ListaEncadenada<YouTubeVideo>();
		vidios = new ArregloDinamico<YouTubeVideo>(100);
//		categorias = new ArrayList<String>(100);
		categorias = new ArrayList<Categoria>(100);
		arbolContextContent = new RedBlackTree<String, ArregloDinamico<Repeticion>>(); 
	}
	
	
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.size();
	}

	public void cargarDatos() throws Exception
	
{
		
		try
		{
//			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
//			
//			FileReader filereader = new FileReader("./data/videos-all.csv");
//		     
//			 CSVReader csvReader = ( new CSVReaderBuilder(filereader))
//                     .withCSVParser(parser) 
//                     .build();
//			for(int i =0 ; i< categorias.size();i++)
//			 {
//				 System.out.println(categorias.darElemento(i));
//			 }
			
			
			 Reader in = new FileReader("./data/context_content_features-small.csv");
			 
			 Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
			 
//			 String [] data = null;
			 int contador = 0;
			 
			 for (CSVRecord record : records) 
			 {
				 
//			 csvReader.readNext();         
			 
//		     while ((data = csvReader.readNext()) != null) 
//		     {
		       
					
//					int k = 0; 
					

					String instrumentalness = record.get(0);
					
					
					String liveness = record.get(1);
					
					String speechiness = record.get(2);
					
					String danceabilty = record.get(3);
					
					
					String valence = record.get(4);
					
					
					String loudness = record.get(5);
					
					
					String tempo = record.get(6);
					
					
					String acousticness = record.get(7);
					
					
					String energy = record.get(8);
					
					
					String mode = record.get(9);
					
					
					String key = record.get(10);
					
					
					String artistId = record.get(11);
					
					
					String tweetLang = record.get(12);
				
					
					String trackId = record.get(13);
					
					
					String createdAt = record.get(14);
					
					
					String language = record.get(15);
					

					String timeZone = record.get(16);
					
					String userId = record.get(17);
					
					String id = record.get(18);
					
					
					
					
					
					ContextContentFeatures entrada = new ContextContentFeatures(instrumentalness, liveness,speechiness, danceabilty, valence, loudness, 
							tempo, acousticness, energy, mode, key, artistId,
							tweetLang, trackId, createdAt, language, timeZone,userId,id );
					

					contextContent.addLast(entrada);
					contador++;
					
					System.out.println("Registros Cargados: " + contador);
					
					if(artistas.isPresent(userId) < 0)
					{
						artistas.addLast(userId);
					}
					else if(canciones.isPresent(trackId) < 0)
					{
						canciones.addLast(trackId);
					}
	    		  
			 }  
				 
			
					
		}	
		finally
		{
			
		}
}
	

					
						 
		  

	public void cargarCategorias() throws FileNotFoundException
	{
		try
		{
			
		Reader in = new FileReader("./data/category-id.csv");
		 
//		CSVFormat format = CSVFormat.RFC4180.withDelimiter('\t');
		
		Iterable<CSVRecord> records = CSVFormat.TDF.withDelimiter('\t').withFirstRecordAsHeader().parse(in);
	

//		 int contador = 0;
		 
		 	for (CSVRecord record : records) 
		 	{
		 		String iD = record.get(0);
			 
		 		String name = record.get(1);
			 
			 
		 		Categoria nuevo = new Categoria(iD, name);
			 
			 
		 		categorias.add(nuevo);
			 
//		 		System.out.println(nuevo.getiD()+"-"+nuevo.getName());
			 
		 	}
		 System.out.println(categorias.size());
		}
		catch(Exception e)
		{
			
		}
		 
		 
	}
/*	public void cargarArregloDinamico() throws Exception
	{
		
		try
		{
//			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
//			
//			FileReader filereader = new FileReader("./data/videos-all.csv");
//		     
//			 CSVReader csvReader = ( new CSVReaderBuilder(filereader))
//                     .withCSVParser(parser) 
//                     .build();
//			for(int i =0 ; i< categorias.size();i++)
//			 {
//				 System.out.println(categorias.darElemento(i));
//			 }
			
			
			 Reader in = new FileReader("./data/videos-all.csv");
			 
			 Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
			 
//			 String [] data = null;
			 int contador = 0;
			 
			 for (CSVRecord record : records) 
			 {
				 
//			 csvReader.readNext();         
			 
//		     while ((data = csvReader.readNext()) != null) 
//		     {
		       
					
//					int k = 0; 
					

					String videoID = record.get(0);
					
					
					String trendingDate = record.get(1);
					
					String title = record.get(2);
					
					String channelTitle = record.get(3);
					
					
					String categoryID = record.get(4);
					
					
					String publishTime = record.get(5);
					
					
					String tags = record.get(6);
					
					
					String views = record.get(7);
					
					
					String likes = record.get(8);
					
					
					String dislikes = record.get(9);
					
					
					String commentCount = record.get(10);
					
					
					String link = record.get(11);
					
					
					String commentsDisabled = record.get(12);
				
					
					String ratingsDisabled = record.get(13);
					
					
					String errorRemoved = record.get(14);
					
					
					String description = record.get(15);
					

					String country = record.get(16);
					
					
					
					
					
					YouTubeVideo video = new YouTubeVideo(videoID, trendingDate,title, channelTitle, categoryID, publishTime, 
							tags, views, likes, dislikes, commentCount, link,
							commentsDisabled, ratingsDisabled, errorRemoved, description, country);
					
					String llave = videoID;
					
					if( tablaLinear.contains(llave) )
					{
						String numeroActualID = tablaLinear.get(llave);
						tablaLinear.put(llave, numeroActualID + 1 ); 
						
					}
					else
					{		
						
						tablaLinear.put(llave, title);
						
					}
					vidios.addLast(video);
					contador++;
//					if(categorias.contains(categoryID))
//					{
//						
//					}
//					else if(contador >= 2) 
//					{
//						categorias.add(categoryID);
//					}
					
//					System.out.println(categoryID);
//					ordenamiento.ordenarQuickSort(vidios, vidios.darElemento(1).getCategoryID(), true);
					
					if(contador==2)
					{
						System.out.println(country);
						System.out.println("Primer Video :- \n" + "Titulo:" + title +"\n Titulo Canal:" +channelTitle +"\n fecha Trending:"+ trendingDate +"\n pais:"+ country +"\n # Vistas:"+ views +"\n # Likes:"+ likes +"\n # Dislikes:"+ dislikes);
					}
						 
		     }
			 
			 System.out.println("\nLista de categorias:- ");
			 for (int i =0; i < categorias.size();i++)
			 {
				 System.out.println(categorias.get(i).getiD() + "-" + categorias.get(i).getName());
			 }

			 
		     System.out.println("Numero de datos leidos: " + contador);
		     
		
		}     
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}*/
	public String cargar() throws ParseException, IOException{
		Reader in = new FileReader(VIDEO);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);	
		int i = 0;
		for (CSVRecord record : records) {
		    String instrumentalness = record.get(0);
		    String liveness = record.get(1);
		    String speechiness = record.get(2);
		    String danceability = record.get(3);
		    String valence = record.get(4);
		    String loudness = record.get(5);
		    String tempo = record.get(6);
		    String acousticness = record.get(7);
		    String energy  = record.get(8);
		    String mode = record.get(9);
		    String key = record.get(10);
		    String artist_id = record.get(11);
		    String tweet_lang = record.get(12);
		    String track_id = record.get(13);
		    String created_at = record.get(14);
		    String lang = record.get(15);
		    String time_zone = record.get(16);
		    String user_id = record.get(17);
		    String id = record.get(18);
		    //--------------------------------------------------------------------
		    if(!instrumentalness.equals("instrumentalness")){
		    SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date fechaPu = formato1.parse(created_at);			    
		    Repeticion nuevo = new Repeticion(Double.parseDouble(instrumentalness), Double.parseDouble(liveness), Double.parseDouble(speechiness), Double.parseDouble(danceability), Double.parseDouble(valence), Double.parseDouble(loudness), Double.parseDouble(tempo),Double.parseDouble(acousticness), Double.parseDouble(energy),(int) Double.parseDouble(mode), (int) Double.parseDouble(key), artist_id, tweet_lang, track_id, fechaPu, lang, time_zone, (int) Double.parseDouble(user_id), (int) Double.parseDouble(id)); 
		    Double llave = nuevo.darDanceability();
		    ArregloDinamico<Repeticion> valor = arbolContextContent.get(llave);
		    															
		    if(valor == null){
		    	ArregloDinamico<Repeticion> v = new ArregloDinamico<Repeticion>();
		    	v.addLast(nuevo);
		    	arbolContextContent.put(llave, v);
		    }
		    else{
		    	valor.addLast(nuevo);
		    	arbolContextContent.put(llave, valor);
		    }		    		  
		    }
		} 
		double menor = arbolContextContent.min();
		double mayor = arbolContextContent.max();
		return " Eventos escucha: "+arbolContextContent.size()+"\n Llaves: "+arbolContextContent.keySet().size()
		+"\n Altura: "+arbolContextContent.height()
		+"\n Menor: "+ menor +":"+ arbolContextContent.get(menor).size() 
		+ "\n mayor: "+ mayor +":"+arbolContextContent.get(mayor).size();
	}
	
	
	public String esCategoria(String pNombreCategoria)
	{
		String respuesta = "";
		for(int i = 0; i < categorias.size(); i++)
		{
			String nombre = categorias.get(i).getName();
			if(nombre.equals(pNombreCategoria))
			{
				String iD = categorias.get(i).getiD();
				respuesta= iD;
			}

		}
		return respuesta;
	}
	
	//	public void darViews()
	//	{
	//		
	//	}
	
	
	/*
	 * REQ. 1 
	 * n videos con mas views que son tendencia en un determinado pais, dada una pCategoria
	 * especifica
	 */
	public void videosConMasViews(String pNombreCategoria, String pPais, int pNumero)
	{
		

		String pCategoria = esCategoria(pNombreCategoria);
		
		ArregloDinamico<YouTubeVideo> videosPorCategoria = new ArregloDinamico<>(100); 
		
		for (int i =0; i < vidios.size(); i++)
		{
			String categoria = vidios.darElemento(i).getCategoryID();
			
			String pais = vidios.darElemento(i).getCountry();
			
			YouTubeVideo video = vidios.getElement(i);
			
			if (categoria.equals(pCategoria) && pais.equals(pPais))
			{		
				videosPorCategoria.addLast(video);
				
//				
			}
		}
		ordenarPorQuickSort((Comparator<YouTubeVideo>) videosPorCategoria, false);
		
		for(int i = 0; i < pNumero;i++)
		{
			YouTubeVideo videoCategory = videosPorCategoria.getElement(i);
			System.out.println(videoCategory.getTrendingDate()+videoCategory.getTitle()+videoCategory.getChannelTitle()
			+videoCategory.getPublishTime()+videoCategory.getViews()+videoCategory.getLikes()
			+videoCategory.getDislikes());
			
	
		}
		
		
	}
	
	
	
	
	
	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public ILista<YouTubeVideo> muestraDadaListaEncadenada(int pNumero)
	{
		subLista = videos.subList( 1 , pNumero);
		
		if(subLista.size()> videos.size())
		{
			subLista = videos;
		}
		
//		System.out.println(subLista.size());
		
		return subLista;
		
	}
	public ILista<YouTubeVideo> muestraDadaArregloDinamico(int pNumero)
	{
		subLista = vidios.subList( 1 , pNumero);
		
		if(subLista.size()> vidios.size())
		{
			subLista = vidios;
		}
		
//		System.out.println(subLista.size());
		
		return subLista;
		
	}
	
	
	
	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		datos.agregar(dato);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{
		return (String) datos.buscar(dato);
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato)
	{
		return (String) datos.eliminar(dato);
	}
	
	public void invertir()
	{
		 datos.invertir();
	}
	
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorSeleccion(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{	
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		
		Comparator<YouTubeVideo> comparadorXViews = new ComparadorXViews();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarSeleccion(subLista, comparadorXViews, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		
		System.out.println(elapsed_time);
	
	}
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorInsercion(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		
		Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarInsercion(subLista, comparadorXLikes, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		
		System.out.println(elapsed_time);
	}
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorShellSort(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarShellSort(subLista, comparadorXLikes, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		
		System.out.println(elapsed_time);
	}
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorMergeSort(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{
		
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		
		Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarMergeSort(subLista, comparadorXLikes, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		
		System.out.println(elapsed_time);
	}
	/**
	 * Ejecuta el algoritmo
	 * 
	 */
	public void ordenarPorQuickSort(Comparator<YouTubeVideo> criterio, boolean pAscendente)
	{
		
		
		
		long start_time = System.currentTimeMillis(); // # tiempo de referencia inicial (mseg)
		
		//Instrucción o funcion a cronometrar
		Comparator<YouTubeVideo> comparadorXViews = new ComparadorXViews();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarSeleccion(subLista, comparadorXViews, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		System.out.println(elapsed_time);
	}
	public boolean buscarTag(YouTubeVideo video, String tag)
	{
		String[] tags = video.getTags().split("|");
		boolean cent = false;
		for (int i = 0; i < tags.length && !cent; i++) 
		{
			if (tags[i].equals(tag))
			{
				cent = true;
			}
		}
		return cent;
	}
	public ILista videosDelPaisTag(String pais, String tag)
	{
		ListaEncadenada<YouTubeVideo> videosPaisTag = new ListaEncadenada<>();
		for (int i = 1; i <= vidios.size() ; i++) 
		{
			if (vidios.getElement(i).getCountry().equals(pais))
			{
				if (buscarTag(vidios.getElement(i), tag))
				{
					videosPaisTag.addLast(vidios.getElement(i));
				}
			}
		}
		return videosPaisTag;		
	}
	public void videosConMasLikes(String pais, int numero, String tags)
	{
		ListaEncadenada<YouTubeVideo> videosDevolver = (ListaEncadenada<YouTubeVideo>) videosDelPaisTag(pais, tags);
		Ordenamiento ordenar = new Ordenamiento<>();
		ComparadorXLikes comparador = new ComparadorXLikes();
		ordenar.ordenarShellSort(videosDevolver, comparador, false);
		ListaEncadenada<YouTubeVideo> videosDeTodo = (ListaEncadenada<YouTubeVideo>) videosDevolver.subList(0, numero);
		for (int i = 1; i <= numero ;i++) 
		{
			YouTubeVideo videox = videosDeTodo.getElement(i);
			System.out.println("Titulo: " + videox.getTitle() + ", Canal: " + videox.getChannelTitle() 
								+ " Se publico en: " + videox.getPublishTime() + ", Tiene estas views: " +
								videox.getViews() + ", likes: " + videox.getLikes() + ", dislikes: "
								 + videox.getDislikes() + ", tags: " + videox.getTags());
		}
		
	}
	public ILista subListaPais(String pais)
	{
		ListaEncadenada<YouTubeVideo> listaPais = new ListaEncadenada<>();
		for (int i = 1; i <= vidios.size(); i++) 
		{
			if(vidios.getElement(i).getCountry().equals(pais))
			{
				listaPais.addLast(vidios.getElement(i));
			}
		}
		return listaPais;
	}
	public void videoConMasTrendingPais(String pais) throws ParseException
	{
		ListaEncadenada<YouTubeVideo> listaPais =new ListaEncadenada<>();
		listaPais = (ListaEncadenada<YouTubeVideo>) subListaPais(pais);
		ComparadorXDiasTendencia comparador = new ComparadorXDiasTendencia();
		Ordenamiento ordenar = new Ordenamiento<>();
		ordenar.ordenarShellSort(listaPais, comparador, false);
		YouTubeVideo video = listaPais.getElement(1);
		System.out.println(" titulo: " + video.getTitle() + " canal: " + video.getChannelTitle() +" pais: "
				+ video.getCountry() + " dias tendencia:" + video.diasEnTendencia());
	}
	public ILista subListaCategoria(String categoria)
	{
		ListaEncadenada<YouTubeVideo> listaCategoria = new ListaEncadenada<>();
		
		String pCategoria = esCategoria(categoria);
		
		for (int i = 1; i <= vidios.size(); i++) 
		{
			if(vidios.getElement(i).getCategoryID().equals(pCategoria))
			{
				listaCategoria.addLast(vidios.getElement(i));
			}
		}
		return listaCategoria;
	}
	public void videoConMasTrendingCategoria(String categoria) throws ParseException
	{
		ListaEncadenada<YouTubeVideo> listaCategoria =new ListaEncadenada<>();
		listaCategoria = (ListaEncadenada<YouTubeVideo>) subListaPais(categoria);
		ComparadorXDiasTendencia comparador = new ComparadorXDiasTendencia();
		Ordenamiento ordenar = new Ordenamiento<>();
		ordenar.ordenarShellSort(listaCategoria, comparador, false);
		YouTubeVideo video = listaCategoria.getElement(1);
		System.out.println(" titulo: " + video.getTitle() + " canal: " + video.getChannelTitle() +" category id: "
				+ video.getCategoryID() + " dias tendencia:" + video.diasEnTendencia());
	}
}
