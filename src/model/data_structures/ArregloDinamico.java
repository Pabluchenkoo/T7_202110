package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
<<<<<<< HEAD
public class ArregloDinamico <T> implements IArregloDinamico<T> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];
=======
public class ArregloDinamico<T extends Comparable<T>> implements IArregloDinamico<T> {
		/**
		 * Capacidad maxima del arreglo
		 */
        private int tamanoMax;
		/**
		 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        /**
         * Arreglo de elementos de tamaNo maximo
         */
        private T elementos[ ];
>>>>>>> 256d57d797b890e2ae0c63c890b7941d0bc088e5

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Object[max];
		tamanoMax = max;
		tamanoAct = 0;
	}
	@Override	
	public void agregar(T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T[]  copia = elementos;
			elementos = (T[]) new Object[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public T darElemento(int i) {
		// TODO implementar

<<<<<<< HEAD
		return elementos[i];
	}

	public T buscar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T buscado = null;
		for (int i = 0; i < elementos.length; i++) {
			if(elementos[i].equals(dato))
			{
				buscado=elementos[i];
			}
=======
		public T buscar(T dato) {
			// TODO implementar
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			int i =0;
			T elem =elementos[0];
			while(i<elementos.length && elem !=null)
			{
				if(elem.compareTo(dato)==0)
				{
					return (T) elem;
				}
				i++;
				elem=elementos[i];
			}
			
			return elem;
>>>>>>> 256d57d797b890e2ae0c63c890b7941d0bc088e5
		}
		return buscado;
	}

	public T eliminar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T[] copia = (T[])new Object[tamanoMax-1];
		int pos=-1;
		for (int i = 0; i < elementos.length; i++) 
		{
			if(elementos[i].equals(dato))
			{
				pos=i;
			}
		}
		for (int i = 0; i < elementos.length && pos!=-1; i++) {
			if(i!=pos )
			{
<<<<<<< HEAD
				if(i<pos)
=======
				if(elementos[i].compareTo(dato)==0)
>>>>>>> 256d57d797b890e2ae0c63c890b7941d0bc088e5
				{
					copia[i]=elementos[i];
				}
				else
				{
					copia[i-1]=elementos[i];
				}
			}
<<<<<<< HEAD
		}
		tamanoMax=tamanoMax-1;
		elementos=(T[])new Object[tamanoMax];
		elementos=copia;
		return dato;
	}
=======
			tamanoMax=tamanoMax-1;
			elementos=(T[])new Object[tamanoMax];
			copia = elementos;
			return dato;
		}
		@Override
		public void invertir() {
			// TODO Auto-generated method stub
			T[] copia = (T[]) new Object[tamanoMax];
			copia = elementos;
			elementos = (T[]) new Object[tamanoMax];
			for(int i =0; i < tamanoAct; i ++)
			{
				elementos[i] = copia[tamanoAct - i -1];
				
			}
			
			
		}
>>>>>>> 256d57d797b890e2ae0c63c890b7941d0bc088e5

	public void invertir()
	{
		T[] copia = (T[]) new Object[tamanoMax];
		copia=elementos;
		elementos=(T[]) new Object[tamanoMax];
		for (int i = 0; i < tamanoAct; i++) 
		{
			elementos[i]=copia[tamanoAct-i-1];
		}
	}
}
