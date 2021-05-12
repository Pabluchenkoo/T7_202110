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
import model.data_structures.TablaSimbolos;
import model.logic.YouTubeVideo.ComparadorXLikes;
import utils.ComparadorXDiasTendencia;
import utils.ComparadorXViews;

import utils.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	
	private static final String HASHTAG = "./data/user_track_hashtag_timestamp-small.csv";
	private static final String SENTIMENT_VALUES = "./data/sentiment_values.csv";
	private static final String VIDEO = "./data/context_content_features-small.csv";
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico<Repeticion> lista;
	
	private RedBlackTree<Double, ArregloDinamico<Repeticion>> arbol;
	
	private ArregloDinamico<String> datos;
	
	private ArregloDinamico<String> caracteristicasCancion;
	
	private ListaEncadenada<YouTubeVideo> videos;
	
	private ArregloDinamico<YouTubeVideo> vidios;
	
//	private ArrayList<String> categorias;
	
	private TablaSimbolos<String, ArregloDinamico<String>> tablaTags;
	
	private ILista<YouTubeVideo> subLista;
	
	private TablaSimbolos<String, Double> valoresSentimentales;
	
	private ArrayList<Categoria> categorias;
	
	private TablaHashSeparateChaining<String, ArregloDinamico<Double>> generos;
	
	private TablaHashLinearProbing< String , String> tablaLinear;
	
	private TablaHashSeparateChaining<String , String> tablaSeparate;
	
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
		arbol = new RedBlackTree<Double, ArregloDinamico<Repeticion>>(); 
		lista = new ArregloDinamico<Repeticion>();
		caracteristicasCancion = new ArregloDinamico<>();
		caracteristicasCancion.addLast("danceability");
		caracteristicasCancion.addLast("speechiness");
		caracteristicasCancion.addLast("instrumentalness");
		caracteristicasCancion.addLast("energy");
		caracteristicasCancion.addLast("acousticness");
		caracteristicasCancion.addLast("valence");	
		caracteristicasCancion.addLast("liveness");

		generos = new TablaHashSeparateChaining<>(9, 1.5);
		ArregloDinamico<Double> a = new ArregloDinamico<Double>();
		a.addLast(60.0);
		a.addLast(90.0);
		generos.put("Reggae",a);
		ArregloDinamico<Double> b = new ArregloDinamico<Double>();
		b.addLast(70.0);
		b.addLast(100.0);
		generos.put("Down-tempo",b);
		ArregloDinamico<Double> c = new ArregloDinamico<Double>();
		c.addLast(90.0);
		c.addLast(120.0);
		generos.put("Chill-out",c);
		ArregloDinamico<Double> d = new ArregloDinamico<Double>();
		d.addLast(85.0);
		d.addLast(115.0);
		generos.put("Hip-hop",d);
		ArregloDinamico<Double> e = new ArregloDinamico<Double>();
		e.addLast(120.0);
		e.addLast(125.0);
		generos.put("Jazz and Funk",e);
		ArregloDinamico<Double> f = new ArregloDinamico<Double>();
		f.addLast(100.0);
		f.addLast(130.0);
		generos.put("Pop",f);
		ArregloDinamico<Double> g = new ArregloDinamico<Double>();
		g.addLast(60.0);
		g.addLast(80.0);
		generos.put("R&B",g);
		ArregloDinamico<Double> h = new ArregloDinamico<Double>();
		h.addLast(110.0);
		h.addLast(140.0);
		generos.put("Rock",h);
		ArregloDinamico<Double> i = new ArregloDinamico<Double>();
		i.addLast(100.0);
		i.addLast(160.0);
		generos.put("Metal",i);
		
	    valoresSentimentales = new TablaSimbolos<>(5300);
		tablaTags = new TablaSimbolos<>(87810);
	
	}
	
	
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.size();
	}

//	public void cargarListaEnlazada()throws Exception
//	{
//		try
//		{
////			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
////			
////			FileReader filereader = new FileReader("./data/videos-all.csv");
////		     
////			 CSVReader csvReader = ( new CSVReaderBuilder(filereader))
////                     .withCSVParser(parser) 
////                     .build();
//			 
//			 csvReader.readNext();         
//			 String [] data;
//			 int contador =0;
//		     while ((data = csvReader.readNext()) != null) 
//		     {
//		       
//					
//					int k = 0; 
//					
//
//					String videoID = data[k];
//					k++;
//					
//					String trendingDate = data[k];
//					k++;
//					
//					String channelTitle = data[k];
//					k++;
//					
//					String categoryID = data[k];
//					k++;
//					
//					String publishTime = data[k];
//					k++;
//					
//					String tags = data[k];
//					k++;
//					
//					String views = data[k];
//					k++;
//					
//					String likes = data[k];
//					k++;
//					
//					String dislikes = data[k];
//					k++;
//					
//					String commentCount = data[k];
//					k++;
//					
//					String link =data[k];
//					k++;
//					
//					String commentsDisabled = data[k];
//					k++;
//					
//					String ratingsDisabled = data[k];
//					k++;
//					
//					String errorRemoved = data[k];
//					k++;
//					
//					String description =data[k];
//					k++;
//
//					String Country =data[k];
//					k++;
//					
//					
//					
//					
//					YouTubeVideo video = new YouTubeVideo(videoID, trendingDate, channelTitle, categoryID, publishTime, 
//							tags, views, likes, dislikes, commentCount, link,
//							commentsDisabled, ratingsDisabled, errorRemoved, description, Country);
//					
//			        
//					videos.addLast(video);
//					contador++;
//					
//					System.out.println(contador);
//		    	 
//		     }
//		     System.out.println("Numero de datos leidos: " + contador);
//		
//		}     
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//	}
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
		Iterable<CSVRecord> grabaciones = CSVFormat.EXCEL.parse(in);	
		int i = 0;
		for (CSVRecord record : grabaciones) {
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
		    ArregloDinamico<Double> caracteristicas = new ArregloDinamico<Double>();
		    caracteristicas.addLast(Double.parseDouble(danceability));
		    
		    caracteristicas.addLast(Double.parseDouble(speechiness));
		    
			caracteristicas.addLast(Double.parseDouble(instrumentalness));
			
			caracteristicas.addLast(Double.parseDouble(energy));
			
			caracteristicas.addLast(Double.parseDouble(acousticness));
			
			caracteristicas.addLast(Double.parseDouble(valence));
			
			caracteristicas.addLast(Double.parseDouble(liveness));
			

			Repeticion nuevo = new Repeticion(caracteristicas,Double.parseDouble(loudness), Double.parseDouble(tempo),(int) Double.parseDouble(mode), (int) Double.parseDouble(key), artist_id, tweet_lang, track_id, fechaPu, lang, time_zone, (int) Double.parseDouble(user_id), (int) Double.parseDouble(id)); 
			lista.addLast(nuevo);
		    }}
		    
	in = new FileReader(SENTIMENT_VALUES);
	grabaciones = CSVFormat.EXCEL.parse(in);			
	for (CSVRecord grabado : grabaciones) {
		String tag = grabado.get(0);
		String promedio = grabado.get(4);
		//----------------------------------
		if(!tag.equals("hashtag")){
			if(promedio.equals(""))
				valoresSentimentales.put(tag, 0.0);
			else
				valoresSentimentales.put(tag, Double.parseDouble(promedio)); 
		}			
	}
	in = new FileReader(HASHTAG);
	grabaciones = CSVFormat.EXCEL.parse(in);			
	for (CSVRecord grabado : grabaciones) {
		String id = grabado.get(1);
		String hashtag = grabado.get(2);
		if(!hashtag.equals("hashtag")){
			String k = id;
			int aux2 = tablaTags.keySet().isPresent(k);
		    if(aux2 ==-1){
		    	ArregloDinamico<String> v = new ArregloDinamico<String>();
		    	v.addLast(hashtag);			    	
		    	tablaTags.put(k, v);			    				    	
		    }
		    else{
		    	ArregloDinamico<String> valor =  tablaTags.get(k);
		    	valor.addLast(hashtag);			    	
		    	tablaTags.put(k, valor);			    				    	
		    }
		}
	}
	return "";
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
	
	public String requerimiento1(String c, double min, double max){			
		int pos = caracteristicasCancion.isPresent(c.toLowerCase());
		RedBlackTree<Double, ArregloDinamico<Repeticion>> arbol = new RedBlackTree<Double, ArregloDinamico<Repeticion>>();
		for(int i=1;i<=lista.size();i++){
			Repeticion nuevo = lista.getElement(i);					
			double key = nuevo.darCaracteristicas().getElement(pos); 
			ArregloDinamico<Repeticion> value = arbol.get(key);		    															
			if(value == null){
				ArregloDinamico<Repeticion> v = new ArregloDinamico<Repeticion>();
				v.addLast(nuevo);
				arbol.put(key, v);
			}
			else{
				value.addLast(nuevo);
				arbol.put(key, value);
			}
		}
		ILista<ArregloDinamico<Repeticion>> totales = arbol.valuesInRange(min, max);
		ArregloDinamico<Repeticion> todos = new ArregloDinamico<Repeticion>();
		for(int i=1;i<=totales.size();i++){
			ArregloDinamico<Repeticion> lista = totales.getElement(i);
			for(int j=1; j<=lista.size();j++){
				todos.addLast(lista.getElement(j));
			}
		}
		TablaHashSeparateChaining<String, Repeticion> unicos = new TablaHashSeparateChaining<>(todos.size(), 1.5);
		for(int i=1; i<todos.size();i++){
			Repeticion nuevo = todos.getElement(i);
			String key = nuevo.darArtist_id(); 
			Repeticion aux2 =  unicos.get(key);
			if(aux2 == null){				
				unicos.put(key, nuevo);
			}		
		}
		ArregloDinamico<Repeticion> v = (ArregloDinamico<Repeticion>) unicos.valueSet();
		int x = unicos.size();
		String res = "++++++++++Requerimiento1 Resultados++++++++++"+"\n"+c+" para los valores siguientes: "+min+" y "+max+"\n Total de Reproducciones "+todos.size()+" \n Artistas unicos: "+v.size();
		return res;
	}

	public ArregloDinamico<Repeticion> req2(double minE, double maxE, double minD, double maxD){
		TablaHashSeparateChaining<String, Repeticion> unicos = new TablaHashSeparateChaining<>(lista.size(), 1.5);
		for(int i=1; i<lista.size();i++){
			Repeticion nuevo = lista.getElement(i);
			String key = nuevo.darArtist_id(); 
			Repeticion aux2 =  unicos.get(key);
			if(aux2 == null){				
				unicos.put(key, nuevo);
			}		
		}
		RedBlackTree<Double, ArregloDinamico<Repeticion>> arbol = new RedBlackTree<Double, ArregloDinamico<Repeticion>>();
		ArregloDinamico<Repeticion> val = (ArregloDinamico<Repeticion>) unicos.valueSet();
		for(int i=1; i<=val.size();i++){
			Repeticion nuevo = lista.getElement(i);
			if(nuevo.darCaracteristicas().getElement(4)<=maxD&&minD<=nuevo.darCaracteristicas().getElement(4)){				
				double llave = nuevo.darCaracteristicas().getElement(7); 
				ArregloDinamico<Repeticion> valor = arbol.get(llave);		    																	   
				if(valor == null){
					ArregloDinamico<Repeticion> v = new ArregloDinamico<Repeticion>();
					v.addLast(nuevo);
					arbol.put(llave, v);
				}
				else{
					valor.addLast(nuevo);
					arbol.put(llave, valor);
				}
			}
		}
		ILista<ArregloDinamico<Repeticion>> totales = arbol.valuesInRange(minE, maxE);
		ArregloDinamico<Repeticion> todos = new ArregloDinamico<Repeticion>();
		for(int i=1;i<=totales.size();i++){
			ArregloDinamico<Repeticion> lista = totales.getElement(i);
			for(int j=1; j<=lista.size();j++){
				todos.addLast(lista.getElement(j));
			}
		}
		return (ArregloDinamico<Repeticion>) todos.subList2(5);
	}



	public String requerimiento4(String texto){
		String[] genero = texto.split(",");
		RedBlackTree<Double, ArregloDinamico<Repeticion>> arbol= new RedBlackTree<Double, ArregloDinamico<Repeticion>>();

		for(int i=1;i<=lista.size();i++){
			Repeticion nuevo = lista.getElement(i);		
			double k = nuevo.darTempo(); 
			ArregloDinamico<Repeticion> v = arbol.get(k);		    																	   
			if(v == null){
				ArregloDinamico<Repeticion> va = new ArregloDinamico<Repeticion>();
				va.addLast(nuevo);
				arbol.put(k, v);
			}
			else{
				v.addLast(nuevo);
				arbol.put(k, v);
			}			
		}	
		String salida = "";
		int totalCanciones = 0;
		for(int i=0;i<genero.length;i++){
			int cantidadCanciones = 0;			
			ArregloDinamico<Double> num = generos.get(genero[i]);
			ILista<ArregloDinamico<Repeticion>> newLista = arbol.valuesInRange(num.getElement(1), (num.getElement(2)));			
			ArregloDinamico<Repeticion> listaArreglada = new ArregloDinamico<Repeticion>();
			for(int j=1;j<=newLista.size();j++){
				ArregloDinamico<Repeticion> todos = newLista.getElement(j);
				for(int k=1;k<=todos.size();k++){
					listaArreglada.addLast(todos.getElement(k));										
				}
			}
			cantidadCanciones += listaArreglada.size();
			totalCanciones+= listaArreglada.size();
			salida = salida +"\n==========="+genero[i]+"==========="
					+"\n Repeticiones: "+ cantidadCanciones;
			TablaHashSeparateChaining<String, Repeticion> artistas = new TablaHashSeparateChaining<>(cantidadCanciones, 1.5);
			for(int j=1; j<=listaArreglada.size();j++){
				Repeticion nuevo = listaArreglada.getElement(j);
				String key = nuevo.darArtist_id(); 
				Repeticion aux2 =  artistas.get(key);
				if(aux2 == null){				
					artistas.put(key, nuevo);
				}
			}	
			ArregloDinamico<String> listaIdsArtistas = artistas.keySet(); 
			salida = salida+"\nArtistas\n";
			for(int j=1;j<=10;j++){
				salida = salida +" \n Artista "+j+": "+listaIdsArtistas.getElement(j);
			}
		}
		salida = "+++++++Requerimiento 4+++++++\n Total de reproducciones: "+ totalCanciones+salida;
		return salida;

	}
	
	public void agregarNuevoGenero(String nombre,Double minTempo, Double maxTempo){
		ArregloDinamico<Double> aux = new ArregloDinamico<Double>();
		aux.addLast(minTempo);
		aux.addLast(maxTempo);
		generos.put(nombre,aux);
	}



	public String Requrimiento5(String horaIni, String horaFin) {
			String retorno = "++++++++++Requerimiento 5+++++++++\n";
			RedBlackTree<String, ArregloDinamico<Repeticion>> arbolHoras = new RedBlackTree<>();
			//Generar el arbol de horas/fechas
			for(int i=1;i<=lista.size();i++){
				Repeticion nuevo = lista.getElement(i);		
				String llave = nuevo.darCreated_at().getHours()+":"+nuevo.darCreated_at().getMinutes(); 
				ArregloDinamico<Repeticion> valor = arbolHoras.get(llave);		    																	   
				if(valor == null){
					ArregloDinamico<Repeticion> v = new ArregloDinamico<Repeticion>();
					v.addLast(nuevo);
					arbolHoras.put(llave, v);
				}
				else{
					valor.addLast(nuevo);
					arbolHoras.put(llave, valor);
				}
			}
			ILista<ArregloDinamico<Repeticion>> reproduccionesEnIntervalo = arbolHoras.valuesInRange(horaIni, horaFin);
			ArregloDinamico<Repeticion> listaArreglada = new ArregloDinamico<>();
			for(int i=1;i<=reproduccionesEnIntervalo.size();i++){
				ArregloDinamico<Repeticion> listaAnidada = reproduccionesEnIntervalo.getElement(i);
				for(int j=1;j<=listaAnidada.size();j++){
					listaArreglada.addLast(listaAnidada.getElement(j));
				}
			}
			retorno = retorno+"total de eventos de escucha en el intervalo: "+listaArreglada.size()+"\n";
			RedBlackTree<Double, ArregloDinamico<Repeticion>> arbolTempo= new RedBlackTree<Double, ArregloDinamico<Repeticion>>();
			for(int i=1;i<=listaArreglada.size();i++){
				Repeticion nuevo = listaArreglada.getElement(i);		
				double llave = nuevo.darTempo(); 
				ArregloDinamico<Repeticion> valor = arbolTempo.get(llave);		    																	   
				if(valor == null){
					ArregloDinamico<Repeticion> v = new ArregloDinamico<Repeticion>();
					v.addLast(nuevo);
					arbolTempo.put(llave, v);
				}
				else{
					valor.addLast(nuevo);
					arbolTempo.put(llave, valor);
				}			
			}
			ArregloDinamico<String> gnros = generos.keySet();  
			int valMax = 0;
			ArregloDinamico<Repeticion> mayor = new ArregloDinamico<>();
			String generoMax = "";
			for(int i=1; i<=gnros.size();i++){
				String gnroActual = gnros.getElement(i);
				ArregloDinamico<Double> tiempos = generos.get(gnroActual);
				ILista<ArregloDinamico<Repeticion>> listaTempo = arbolTempo.valuesInRange(tiempos.getElement(1), tiempos.getElement(2));
				ArregloDinamico<Repeticion> listaArregladaTempo = new ArregloDinamico<>();
				for(int k=1;k<=reproduccionesEnIntervalo.size();k++){
					ArregloDinamico<Repeticion> listaAnidada = reproduccionesEnIntervalo.getElement(k);
					for(int j=1;j<=listaAnidada.size();j++){
						listaArregladaTempo.addLast(listaAnidada.getElement(j));
					}
				}
				if(listaArregladaTempo.size()>valMax){
					valMax = listaArregladaTempo.size();
					mayor = listaArregladaTempo;
					generoMax = gnroActual;
				}
			}
			retorno = retorno+"El genero mas eschuchado fue: "+ generoMax+" Con "+valMax+" eventos de escucha\n";	
			
			TablaHashSeparateChaining<String, Repeticion> cancionesUnicas = new TablaHashSeparateChaining<>(valMax+1, 1.5);
			for(int i=1; i<=mayor.size(); i++){
				Repeticion actual = mayor.getElement(i); 
				cancionesUnicas.put(actual.darTrack_id(), actual);			
			}
			ArregloDinamico<String> canciones = (ArregloDinamico<String>) cancionesUnicas.keySet();
			retorno = retorno+"============ ANALISIS==============\nHay: "+canciones.size()+" canciones unicas\n";
			for(int i=1; i<=10;i++){
				String actual = canciones.getElement(i);
				ArregloDinamico<String> tags = tablaTags.get(actual);
				double sumaVader = 0;
				if(tags!=null){
				for(int j=1; j<=tags.size();j++){
					sumaVader += valoresSentimentales.get(tags.getElement(j));
				}
				}
				int t = (tags!=null)?tags.size():1;
				retorno = retorno+" Cancion "+i+": "+canciones.getElement(i)+"promedio de vader: "+ (sumaVader/t)+"\n";
			}
			return retorno;
	}
}
