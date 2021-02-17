package controller;

import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
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

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option)
			{
				case 1:
					view.printMessage("--------- \nCargar Lista Enlazada: ");
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
					view.printMessage("--------- \nCargar Arreglo Dinamico: ");
						
					try {
						modelo.cargarArregloDinamico();
						view.printMessage("Arreglo Dinamico cargado");
						view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");
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
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
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
					fin = true;
					break;	
					
					
				case 7:
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
