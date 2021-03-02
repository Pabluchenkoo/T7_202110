package model.logic;

import java.io.FileReader;
import java.util.Comparator;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ListaEncadenada;
import utils.Ordenamiento;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico<String> datos;
	
	private ListaEncadenada<YouTubeVideo> videos;
	
	private ArregloDinamico<YouTubeVideo> vidios;
	
	private ILista<YouTubeVideo> subLista;
	
	private Ordenamiento<YouTubeVideo> ordenamiento;
	
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int pCapacidad)
	{
		datos = new ArregloDinamico<String>(pCapacidad);
		videos = new ListaEncadenada<YouTubeVideo>();
		vidios = new ArregloDinamico<YouTubeVideo>(9999999);
	}
	
	
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}

	public void cargarListaEnlazada()throws Exception
	{
		try
		{
			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
			
			FileReader filereader = new FileReader("./data/videos-all.csv");
		     
			 CSVReader csvReader = ( new CSVReaderBuilder(filereader))
                     .withCSVParser(parser) 
                     .build();
			 
			 csvReader.readNext();         
			 String [] data;
			 int contador =0;
		     while ((data = csvReader.readNext()) != null) 
		     {
		       
					
					int k = 0; 
					

					String videoID = data[k];
					k++;
					
					String trendingDate = data[k];
					k++;
					
					String channelTitle = data[k];
					k++;
					
					String categoryID = data[k];
					k++;
					
					String publishTime = data[k];
					k++;
					
					String tags = data[k];
					k++;
					
					String views = data[k];
					k++;
					
					String likes = data[k];
					k++;
					
					String dislikes = data[k];
					k++;
					
					String commentCount = data[k];
					k++;
					
					String link =data[k];
					k++;
					
					String commentsDisabled = data[k];
					k++;
					
					String ratingsDisabled = data[k];
					k++;
					
					String errorRemoved = data[k];
					k++;
					
					String description =data[k];
					k++;

					String Country =data[k];
					k++;
					
					
					
					
					YouTubeVideo video = new YouTubeVideo(videoID, trendingDate, channelTitle, categoryID, publishTime, 
							tags, views, likes, dislikes, commentCount, link,
							commentsDisabled, ratingsDisabled, errorRemoved, description, Country);
					
			        
					videos.addLast(video);
					contador++;
					
					System.out.println(contador);
		    	 
		     }
		     System.out.println("Numero de datos leidos: " + contador);
		
		}     
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void cargarArregloDinamico() throws Exception
	{
		
		try
		{
			CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
			
			FileReader filereader = new FileReader("./data/videos-all.csv");
		     
			 CSVReader csvReader = ( new CSVReaderBuilder(filereader))
                     .withCSVParser(parser) 
                     .build();
			 
			 csvReader.readNext();         
			 String [] data;
			 int contador = 0;
		     while ((data = csvReader.readNext()) != null) 
		     {
		       
					
					int k = 0; 
					

					String videoID = data[k];
					k++;
					
					String trendingDate = data[k];
					k++;
					
					String channelTitle = data[k];
					k++;
					
					String categoryID = data[k];
					k++;
					
					String publishTime = data[k];
					k++;
					
					String tags = data[k];
					k++;
					
					String views = data[k];
					k++;
					
					String likes = data[k];
					k++;
					
					String dislikes = data[k];
					k++;
					
					String commentCount = data[k];
					k++;
					
					String link =data[k];
					k++;
					
					String commentsDisabled = data[k];
					k++;
					
					String ratingsDisabled = data[k];
					k++;
					
					String errorRemoved = data[k];
					k++;
					
					String description =data[k];
					k++;

					String Country =data[k];
					k++;
					
					
					
					
					YouTubeVideo video = new YouTubeVideo(videoID, trendingDate, channelTitle, categoryID, publishTime, 
							tags, views, likes, dislikes, commentCount, link,
							commentsDisabled, ratingsDisabled, errorRemoved, description, Country);
					
			        
					vidios.addLast(video);
					contador++;
					
		    	 
		     }
		     System.out.println("Numero de datos leidos: " + contador);
		     
		
		}     
			catch(Exception e)
			{
				e.printStackTrace();
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
		
		Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarSeleccion(subLista, comparadorXLikes, true);
		
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
		Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
		Ordenamiento<YouTubeVideo> algsOrdenamientoVideos = new Ordenamiento<YouTubeVideo>();
		algsOrdenamientoVideos.ordenarQuickSort(subLista, comparadorXLikes, true);
		
		long stop_time = System.currentTimeMillis(); //# tiempo de referencia final (mseg)
		long elapsed_time = stop_time - start_time;
		System.out.println(elapsed_time);
	}
}
