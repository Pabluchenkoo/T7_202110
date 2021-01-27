package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico <T extends Comparable<T>> implements IArregloDinamico {
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
    private Object elementos[ ];

    /**
     * Construir un arreglo con la capacidad maxima inicial.
     * @param max Capacidad maxima inicial
     */
	public ArregloDinamico( int max )
    {
           elementos = new Object[max];
           tamanoMax = max;
           tamanoAct = 0;
    }
    
	public void agregar( Object dato )
    {
           if ( tamanoAct == tamanoMax )
           {  // caso de arreglo lleno (aumentar tamaNo)
                tamanoMax = 2 * tamanoMax;
                Object [ ] copy = elementos;
                elementos = new Object[tamanoMax];
                for ( int i = 0; i < tamanoAct; i++)
                {
                 	 elementos[i] = copy[i];
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

	public Object darElemento(int i) {
		// TODO implementar
		if(i<0 || i>=tamanoAct)
		{
			return null;
		}
		else
		{
			return elementos[i];
		}
		
	}

	public Object buscar(Object dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		
		boolean encontro = true;
		for (int i = 0; i < elementos.length && encontro; i++) 
		{
				if(((Comparable<T>) elementos[i]).compareTo((T) dato)==0)
				{
					encontro = false;
					return elementos[i];
				}
		}
		return null;
	}

	public Object eliminar(Object dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		boolean encontro = true;
		
		Object respuesta = null;
		
		for (int i = 0; i < elementos.length && encontro; i++) 
		{
				if(((Comparable<T>) elementos[i]).compareTo((T) dato)==0)
				{
					encontro = false;
					
					respuesta = elementos[i];
					
					elementos[i]=null;
				}
		}
		return respuesta;
	}

	

}
