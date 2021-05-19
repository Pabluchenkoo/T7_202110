package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.ILista;
import model.data_structures.ITablaSimbolos;
import model.data_structures.ListaEncadenada;
import model.data_structures.RedBlackTree;
import model.data_structures.TablaHashLinearProbing;
import model.data_structures.TablaHashSeparateChaining;
import model.data_structures.TablaSimbolos;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	private static final String VIDEO = "./data/context_content_features-small.csv";
	private static final String SENTIMENT_VALUES = "./data/sentiment_values.csv";
	private static final String HASHTA = "./data/user_track_hashtag_timestamp-small.csv";
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico<Repeticion> lista;
	private ArregloDinamico<String> caracteristicas;
	private TablaHashSeparateChaining<String, ArregloDinamico<Double>> generos;
	private TablaSimbolos<String, Double> tablaValSentimentales;
	private TablaSimbolos<String, ArregloDinamico<String>> tablaHashTags;

	public Modelo()
	{   
		lista = new ArregloDinamico<Repeticion>();
		caracteristicas = new ArregloDinamico<>();
		caracteristicas.addLast("instrumentalness");//1
		caracteristicas.addLast("liveness");//2
		caracteristicas.addLast("speechiness");//3
		caracteristicas.addLast("danceability");//4
		caracteristicas.addLast("valence");//5	
		caracteristicas.addLast("acousticness");//6
		caracteristicas.addLast("energy");//7

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
		
		tablaValSentimentales = new TablaSimbolos<>(5300);
		tablaHashTags = new TablaSimbolos<>(87810);
	}	

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
				//2014-01-01 05:56:11
				Date fechaPu = formato1.parse(created_at);

				ArregloDinamico<Double> caracteristicas = new ArregloDinamico<Double>();
				caracteristicas.addLast(Double.parseDouble(instrumentalness));//1
				caracteristicas.addLast(Double.parseDouble(liveness));//2
				caracteristicas.addLast(Double.parseDouble(speechiness));//3
				caracteristicas.addLast(Double.parseDouble(danceability));//4
				caracteristicas.addLast(Double.parseDouble(valence));//5
				caracteristicas.addLast(Double.parseDouble(acousticness));//6
				caracteristicas.addLast(Double.parseDouble(energy));//7

				Repeticion nuevo = new Repeticion(caracteristicas,Double.parseDouble(loudness), Double.parseDouble(tempo),(int) Double.parseDouble(mode), (int) Double.parseDouble(key), artist_id, tweet_lang, track_id, fechaPu, lang, time_zone, (int) Double.parseDouble(user_id), (int) Double.parseDouble(id)); 
				lista.addLast(nuevo);

				/**
		    Double llave = nuevo.darDanceability();		   		   
		    ArregloDinamico<Reproduccion> valor = arbol.get(llave);		    															
		    if(valor == null){
		    	ArregloDinamico<Reproduccion> v = new ArregloDinamico<Reproduccion>();
		    	v.addLast(nuevo);
		    	arbol.put(llave, v);
		    }
		    else{
		    	valor.addLast(nuevo);
		    	arbol.put(llave, valor);
		    }
				 */		    		  
			} 
		}
		/**
		double menor = arbol.min();
		double mayor = arbol.max();
		return " Eventos escucha: "+arbol.size()+"\n Llaves: "+arbol.keyset().size()
		+"\n Altura: "+arbol.height()
		+"\n Menor: "+ menor +":"+ arbol.get(menor).size() 
		+ "\n mayor: "+ mayor +":"+arbol.get(mayor).size();
		 **/
		
		in = new FileReader(SENTIMENT_VALUES);
		records = CSVFormat.EXCEL.parse(in);			
		for (CSVRecord record : records) {
			String hashtag = record.get(0);
			String avrgVader = record.get(4);
			//----------------------------------
			if(!hashtag.equals("hashtag")){
				if(avrgVader.equals(""))
					tablaValSentimentales.put(hashtag, 0.0);
				else
					tablaValSentimentales.put(hashtag, Double.parseDouble(avrgVader)); 
			}			
		}
		
		in = new FileReader(HASHTA);
		records = CSVFormat.EXCEL.parse(in);			
		for (CSVRecord record : records) {
			String id = record.get(1);
			String hashtag = record.get(2);
			if(!hashtag.equals("hashtag")){
				String key = id;
				int aux2 = tablaHashTags.keySet().isPresent(key);
			    if(aux2 ==-1){
			    	ArregloDinamico<String> valor = new ArregloDinamico<String>();
			    	valor.addLast(hashtag);			    	
			    	tablaHashTags.put(key, valor);			    				    	
			    }
			    else{
			    	ArregloDinamico<String> valor =  tablaHashTags.get(key);
			    	valor.addLast(hashtag);			    	
			    	tablaHashTags.put(key, valor);			    				    	
			    }
			}
		}
		return "";
	}

	public String req1(String c, double min, double max){			
		int pos = caracteristicas.isPresent(c.toLowerCase());
		RedBlackTree<Double, ArregloDinamico<Repeticion>> arbol = new RedBlackTree<Double, ArregloDinamico<Repeticion>>();
		for(int i=1;i<=lista.size();i++){
			Repeticion nuevo = lista.getElement(i);					
			double llave = nuevo.darCaracteristicas().getElement(pos); 
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
		String res = "++++++++++Req1 Resultados++++++++++"+"\n"+c+" con los valores entre: "+min+" y "+max+"\n Reproducciones totales "+todos.size()+" \n Artistas unicos: "+v.size();
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

	public String req4(String l){
		String[] gnero = l.split(",");
		RedBlackTree<Double, ArregloDinamico<Repeticion>> arbol= new RedBlackTree<Double, ArregloDinamico<Repeticion>>();

		//Generar arbol que tenga como llave el tempo
		for(int i=1;i<=lista.size();i++){
			Repeticion nuevo = lista.getElement(i);		
			double llave = nuevo.darTempo(); 
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
		//Por cada categoria recorrer el arbol		
		String salida = "";
		int totalCanciones = 0;
		for(int i=0;i<gnero.length;i++){
			int cantidadCanciones = 0;			
			ArregloDinamico<Double> num = generos.get(gnero[i]);
			//Rangos
			ILista<ArregloDinamico<Repeticion>> nuevaLista = arbol.valuesInRange(num.getElement(1), (num.getElement(2)));			
			//Arreglar el arreglo
			ArregloDinamico<Repeticion> listaArreglada = new ArregloDinamico<Repeticion>();
			for(int j=1;j<=nuevaLista.size();j++){
				ArregloDinamico<Repeticion> todos = nuevaLista.getElement(j);
				for(int k=1;k<=todos.size();k++){
					listaArreglada.addLast(todos.getElement(k));										
				}
			}
			//Actualizar la cantidad de canciones
			cantidadCanciones += listaArreglada.size();
			totalCanciones+= listaArreglada.size();
			//Agregar al String
			salida = salida +"\n==========="+gnero[i]+"==========="
					+"\n Reproducciones: "+ cantidadCanciones;
			//hash sobre los artistas
			TablaHashSeparateChaining<String, Repeticion> artistas = new TablaHashSeparateChaining<>(cantidadCanciones, 1.5);
			for(int j=1; j<=listaArreglada.size();j++){
				Repeticion nuevo = listaArreglada.getElement(j);
				String key = nuevo.darArtist_id(); 
				Repeticion aux2 =  artistas.get(key);
				if(aux2 == null){				
					artistas.put(key, nuevo);
				}
			}	
			//Lista de llaves
			ArregloDinamico<String> listaIdsArtistas = artistas.keySet(); 
			salida = salida+"\nArtistas\n";
			//Agregar al string los artistas
			for(int j=1;j<=10;j++){
				salida = salida +" \n Artista "+j+": "+listaIdsArtistas.getElement(j);
			}
		}
		salida = "+++++++REQ 4+++++++\n Total de reproducciones: "+ totalCanciones+salida;
		return salida;

	}

	public void agregarNuevoGenero(String nombre,Double minTempo, Double maxTempo){
		ArregloDinamico<Double> aux = new ArregloDinamico<Double>();
		aux.addLast(minTempo);
		aux.addLast(maxTempo);
		generos.put(nombre,aux);
	}

	public String Req5(String horaInicio, String horaFinal){
		String retorno = "++++++++++REQ 5+++++++++\n";
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
		//Obtener una lista de las reproducciones en los intervalos definidos
		ILista<ArregloDinamico<Repeticion>> reproduccionesEnIntervalo = arbolHoras.valuesInRange(horaInicio, horaFinal);
		//De la lista obtener los valores en listas individuales no anidadas
		ArregloDinamico<Repeticion> listaArreglada = new ArregloDinamico<>();
		for(int i=1;i<=reproduccionesEnIntervalo.size();i++){
			ArregloDinamico<Repeticion> listaAnidada = reproduccionesEnIntervalo.getElement(i);
			for(int j=1;j<=listaAnidada.size();j++){
				listaArreglada.addLast(listaAnidada.getElement(j));
			}
		}
		retorno = retorno+"total de eventos de escucha en el intervalo: "+listaArreglada.size()+"\n";
		//De la lista arreglada realizar un arbol del tempo
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
		//Del arbol del tempo se generar las listas de los diferentes generos, solo se guarda la mas grande
		ArregloDinamico<String> gnros = generos.keySet();  
		int valMax = 0;
		ArregloDinamico<Repeticion> mayor = new ArregloDinamico<>();
		String generoMax = "";
		for(int i=1; i<=gnros.size();i++){
			String gnroActual = gnros.getElement(i);
			ArregloDinamico<Double> tiempos = generos.get(gnroActual);
			//Generar la lista del genero
			ILista<ArregloDinamico<Repeticion>> listaTempo = arbolTempo.valuesInRange(tiempos.getElement(1), tiempos.getElement(2));
			//Arrelar la lista
			ArregloDinamico<Repeticion> listaArregladaTempo = new ArregloDinamico<>();
			for(int k=1;k<=reproduccionesEnIntervalo.size();k++){
				ArregloDinamico<Repeticion> listaAnidada = reproduccionesEnIntervalo.getElement(k);
				for(int j=1;j<=listaAnidada.size();j++){
					listaArregladaTempo.addLast(listaAnidada.getElement(j));
				}
			}
			//Comparar si es el mayor
			if(listaArregladaTempo.size()>valMax){
				valMax = listaArregladaTempo.size();
				mayor = listaArregladaTempo;
				generoMax = gnroActual;
			}
		}
		retorno = retorno+"El genero mas eschuchado fue: "+ generoMax+" Con "+valMax+" eventos de escucha\n";	
		
		//Una lista con solo las canciones unicas
		TablaHashSeparateChaining<String, Repeticion> cancionesUnicas = new TablaHashSeparateChaining<>(valMax+1, 1.5);
		for(int i=1; i<=mayor.size(); i++){
			Repeticion actual = mayor.getElement(i); 
			cancionesUnicas.put(actual.darTrack_id(), actual);			
		}
		ArregloDinamico<String> canciones = (ArregloDinamico<String>) cancionesUnicas.keySet();
		retorno = retorno+"============ ANALISIS==============\nHay: "+canciones.size()+" canciones unicas\n";		
		//De las canciones unicas obtener de la otra lista cuales son sus hashtags
		//Con los hashTags se busca en la otra lista los valores y se suman para obtener un promedio
		for(int i=1; i<=10;i++){
			String actual = canciones.getElement(i);
			ArregloDinamico<String> hashTags = tablaHashTags.get(actual);
			double sumaVader = 0;
			if(hashTags!=null){
			for(int j=1; j<=hashTags.size();j++){
				sumaVader += tablaValSentimentales.get(hashTags.getElement(j));
			}
			}
			int t = (hashTags!=null)?hashTags.size():1;
			retorno = retorno+" Cancion "+i+": "+canciones.getElement(i)+"valor VADER promedio: "+ (sumaVader/t)+"\n";
		}
		return retorno;
		
	}
}
