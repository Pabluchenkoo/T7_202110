package view;

import model.logic.Modelo;

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
			System.out.println("1. Cargar Categorias");
			
			System.out.println("2. Cargar ArregloDinamico");
			
			System.out.println("3. Req 1");
			
			System.out.println("4. Req 2");
			
			System.out.println("5. Req 3");
			
			System.out.println("6. Req 4 ");
			
			System.out.println("7. Dar SubLista");
			
			System.out.println("8. Odenar Selección");
			
			System.out.println("9. Odenar Inserción");
			
			System.out.println("10. Ordenar ShellSort");
			
			System.out.println("11. Ordenar MergeSort");
			
			System.out.println("12. Ordenar QuickSort");
			
			System.out.println("13. Exit");
			
			
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
