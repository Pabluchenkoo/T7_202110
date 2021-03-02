package controller;

import java.util.Comparator;
import java.util.Scanner;

import model.logic.Modelo;
import model.logic.YouTubeVideo;
import utils.Ordenamiento;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	Comparator<YouTubeVideo> comparadorXLikes = new YouTubeVideo.ComparadorXLikes();
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo(10000);
		
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		int answer=0;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option)
			{
				case 1:
					view.printMessage("--------- \nCargando Lista Enlazada: ");
					try {
						modelo.cargarListaEnlazada(); 
					    view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");	
						view.printMessage("datos cargados");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 					
					break;

				case 2:
//					view.printMessage("--------- \nCargar Arreglo Dinamico: ");
						
					try {
						view.printMessage("--------- \nCargando Arreglo Dinamico... ");
						modelo.cargarArregloDinamico();
						view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");
						view.printMessage("Arreglo Dinamico cargado");
//						view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 		
					break;

				case 3:
					view.printMessage("--------- \nDar cadena (simple) a buscar: ");
					dato = lector.next();
					respuesta = modelo.buscar(dato);
					if ( respuesta != null)
					{
						view.printMessage("Dato encontrado: "+ respuesta);
					}
					else
					{
						view.printMessage("Dato NO encontrado");
					}
//					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 4:
					view.printMessage("--------- \nDar cadena (simple) a eliminar: ");
					dato = lector.next();
					respuesta = modelo.eliminar(dato);
					if ( respuesta != null)
					{
						view.printMessage("Dato eliminado "+ respuesta);
					}
					else
					{
						view.printMessage("Dato NO eliminado");							
					}
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;

				case 5: 
					view.printMessage("--------- \nContenido del Arreglo: ");
					view.printModelo(modelo);
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;	
					
				case 6: 
					view.printMessage("--------- \nDar arreglo invertido\n---------");
					dato = lector.next();
					modelo.invertir();
					lector.close();
					break;	
					
				case 7:
					Scanner myInput = new Scanner( System.in );
					view.printMessage("--------- \nDar SubLista:\n---------");
					System.out.print( "Enter an integer: " );
					int a = myInput.nextInt();
//					modelo.muestraDadaListaEncadenada(a);
					modelo.muestraDadaArregloDinamico(a);
//					System.out.println(answer);
//					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
					break;
				
				case 8: 
					view.printMessage("--------- \nAplicar Selección :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorSeleccion(comparadorXLikes , true);
					lector.close();

					break;
					
				case 9: 
					view.printMessage("--------- \nAplicar Inserción :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorInsercion(comparadorXLikes , true);
					lector.close();

					break;
				case 10: 
					view.printMessage("--------- \nAplicar ShellSort :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorShellSort(comparadorXLikes , true);
					lector.close();

					break;
				case 11: 
					view.printMessage("--------- \nAplicar MergeSort :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorMergeSort(comparadorXLikes , true);
					lector.close();

					break;
				case 12: 
					view.printMessage("--------- \nAplicar QuickSort :\n---------");
					dato = lector.next();
					
					modelo.ordenarPorQuickSort(comparadorXLikes , true);
					lector.close();

					break;
				case 13:
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
