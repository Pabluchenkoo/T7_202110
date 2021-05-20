package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.data_structures.ILista;
import model.logic.Modelo;
import model.logic.Repeticion;
import model.logic.YouTubeVideo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public Controller () throws ParseException, IOException
	{
		view = new View();
		modelo = new Modelo();
	}


	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		Object respuesta = null;

		
		while(!fin){
			view.printMenu();
			
			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("Inicializando...");
					try {
					view.printMessage(modelo.cargar());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				break;				
				case 2:
					view.printMessage("Seleccione un requerimiento");
					int res = lector.nextInt();
					if(res==1){
						view.printMessage("Ingrese: Categoria,min,max");
						String req = lector.next();
						String[] ans = req.split(",");
						view.printMessage(modelo.requerimiento1(ans[0], Double.parseDouble(ans[1]), Double.parseDouble(ans[2])));
					}
					else if(res==2){
						view.printMessage("Ingrese: minEnergia,maxEnergia,minDance,maxDance");
						String req = lector.next();
						String[] ans = req.split(",");
						ArregloDinamico<Repeticion> este = modelo.req2(Double.parseDouble(ans[0]), 
																		 Double.parseDouble(ans[1]), 
																		 Double.parseDouble(ans[2]), 
																		 Double.parseDouble(ans[3]));
						view.imprimirVideoRequerimineto(este, este.size());
					}
					else if(res==4){
						view.printMessage("1.Req\n2.Agregar nuevo genero");
						int res2 = lector.nextInt();
						if(res2==1){
							view.printMessage("Ingrese nombre,tempo minimo, tempo maximo");
							String aux = lector.next();
							String aux2[] = aux.split(",");
							modelo.agregarNuevoGenero(aux2[0], Double.parseDouble(aux2[1]) , Double.parseDouble(aux2[2]));
							view.printMessage("Genero agregado a la lista");							
						}
						if(res2==2){
						view.printMessage("Ingrese el/los generos que desea buscar(genero1,generos2,...)");
						view.printMessage(modelo.requerimiento4(lector.next()));
						}
					}
					else if(res==5){
						view.printMessage("Ingrese los intervalos de tiempo(00:00,00:00");
						String[] aux = lector.next().split(",");
						view.printMessage(modelo.Requrimiento5(aux[0], aux[1]));
					}
				
					break;					
				case 3:
					
				case 4:
					view.printMessage("Pruebas de desempeño");
					break;
				case 5: 
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
