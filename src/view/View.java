package view;

import model.data_structures.ILista;
import model.logic.Modelo;
import model.logic.Repeticion;
import model.logic.YouTubeVideo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
	    public void printMenu()
		{
			System.out.println("1. Cargar estructuras");
			System.out.println("2. Cargar Requerimientos ");
			System.out.println("3. Probar metodo .get()");
			System.out.println("4. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}
		
		/**
		 * Metodo que imprime una tabla con la informacion del video, se imprime desde el primero hasta s.
		 * @param l, lista de los videos que se quieren imprimir.
		 * @param s, numero de videos de la lista que se quieren imprimir
		 */
		public void imprimirVideoRequerimineto(ILista<Repeticion> l, int s){
			System.out.println("=====================================");
			System.out.println("LISTA VIDEOS:");
			System.out.println("=====================================");
			for(int i=1;i<=s;i++){
				Repeticion a = (Repeticion) l.getElement(i); 
				System.out.println("Rep #"+i);
				System.out.println(" ID: "+a.darId());
				System.out.println(" Energia: "+a.darCaracteristicas().getElement(7));
				System.out.println(" Dance: "+a.darCaracteristicas().getElement(4));
			}
			System.out.println("=====================================");
		}

		public void imprimirVideoRequrimiento1(ILista<YouTubeVideo> l, int s){
			System.out.println("=====================================");
			System.out.println("LISTA VIDEOS:");
			System.out.println("=====================================");
			for(int i=1;i<=s;i++){
				YouTubeVideo a = (YouTubeVideo) l.getElement(i); 
				System.out.println("Video #"+i);
				System.out.println(" title: "+a.getTitle());
				System.out.println(" cannel_title: "+a.getChannelTitle());
				System.out.println(" publish_time: "+a.getPublishTime());
				System.out.println(" views: "+a.getViews());
				System.out.println(" likes: "+a.getLikes());
				System.out.println(" dislikes: "+a.getDislikes());
			}
			System.out.println("=====================================");
		}
}

