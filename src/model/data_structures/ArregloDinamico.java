package model.data_structures;


/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico <T extends Comparable<T>> implements ILista<T>, Comparable<ArregloDinamico<T>> 
{
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

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	@SuppressWarnings("unchecked")
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}
	public ArregloDinamico()
	{
		elementos = (T[]) new Comparable[1];
		tamanoMax = 1;
		tamanoAct = 0;
	}
	public void agregar(T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T[]  copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
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

	public int size() {
		return tamanoAct;
	}

	public T darElemento(int i) {
		// TODO implementar

		return elementos[i];
	}

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
		
//		T buscado = null;
//		for (int i = 0; i < elementos.length; i++) {
//			if(elementos[i].equals(dato))
//			{
//				buscado=elementos[i];
//			}
//		}
//		return buscado;
	}

	public T eliminar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T[] copia = (T[])new Comparable[tamanoMax-1];
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
				if(i<pos)
				{
					copia[i]=elementos[i];
				}
				else
				{
					copia[i-1]=elementos[i];
				}
			}
		}
		tamanoMax=tamanoMax-1;
		elementos=(T[])new Object[tamanoMax=tamanoMax-1];
		for (int i = 0; i < copia.length; i++) {
			elementos[i]=copia[i];
		}
		return dato;
	}

	public void invertir()
	{
		T[] copia = (T[]) new Comparable[tamanoMax];
		copia=elementos;
		elementos=(T[]) new Comparable[tamanoMax];
		for (int i = 0; i < tamanoAct; i++) 
		{
			elementos[i]=copia[tamanoAct-i-1];
		}
	}
	@Override
	public void addFirst(T element) 
	{
		aumentarTamanio();
		T[] copia=(T[])new Comparable[tamanoMax];
		copia[0]=element;
		for (int i = 0; i < tamanoAct; i++) 
		{
			copia[i+1]=elementos[i];
		}
		tamanoAct++;
		elementos=(T[])new Comparable[tamanoMax];
		elementos=copia;
		
		
		
	}
	@Override
	public void addLast(T element) 
	{
		aumentarTamanio();
		elementos[tamanoAct+1]=element;
	}
	public void aumentarTamanio()
	{
		if(tamanoAct==tamanoMax)
		{
			tamanoMax*=2;
		}
		
	}
	@Override
	public void insertElement(T element, int pos) 
	{
		aumentarTamanio();
		T[] copia=(T[])new Comparable[tamanoMax];
		for (int i = 0; i < tamanoAct; i++) 
		{
			if(i<pos-1)
			{
				copia[i]=elementos[i];
			}
			else if (i==pos-1)
			{
				copia[i]=element;
			}
			else
				copia[i+1]=elementos[i];
		}
		tamanoAct++;
		elementos=(T[])new Comparable[tamanoMax];
		elementos=copia;
		
	}
	@Override
	public T removeFirst() 
	{
		T[] copia=(T[])new Comparable[tamanoMax];
		for (int i = 0; i < tamanoAct; i++) 
		{
			copia[i]=elementos[i+1];
		}
		T primero = elementos[0];
		elementos=(T[])new Comparable[tamanoMax];
		elementos=copia;
		tamanoAct--;
		return primero;
	}
	@Override
	public T removeLast() 
	{
		T borrar = elementos[tamanoAct];
		elementos[tamanoAct]=null;
		tamanoAct--;
		return borrar;
	}
	@Override
	public T deleteElement(int pos) 
	{
		T[] copia=(T[])new Comparable[tamanoMax];
		for (int i = 0; i < tamanoAct; i++) 
		{
			if(i<pos-1)
			{
				copia[i]=elementos[i];
			}
			else if(i>=pos)
			{
				copia[i]=elementos[i+1];
			}
				
		}
		T borrar = elementos[pos-1];
		tamanoAct--;
		return borrar;
	}
	@Override
	public T firstElement() {
		// TODO Auto-generated method stub
		return elementos[0	];
	}
	@Override
	public T lastElement() {
		// TODO Auto-generated method stub
		return elementos[tamanoAct-1];
	}
	@Override
	public T getElement(int pos) {
		// TODO Auto-generated method stub
		T buscado = null;
		int posReal = pos-1;
		if(posReal<=tamanoMax){
			return elementos[posReal];
		}
		return buscado;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (tamanoAct==0)?true:false;
	}
	@Override
	public int isPresent(T element) {
		// TODO Auto-generated method stub
		int encontro=-1;
		for (int i = 0; i < tamanoAct; i++) 
		{
			if(elementos[i].compareTo(element)==0)
			{
				encontro=i+1;
			}
		}
		return encontro;
	}
	@Override
	public void exchange(int pos1, int pos2) 
	{
		T copia1=elementos[pos1-1];
		T copia2=elementos[pos2-1];
		elementos[pos2-1]=copia1;
		elementos[pos1-1]=copia2;		
		
	}
	@Override
	public void changeInfo(int pos, T element) {
		// TODO Auto-generated method stub
		int posicion = pos-1;
		elementos[posicion]=element;
	}
	@Override
	public ILista<T> subList(int principio, int fin) 
	{
		int tamanio=fin-principio;
		ArregloDinamico<T> arregloDevolver = new ArregloDinamico<T>(tamanio);
		for (int i=principio; i <= fin; i++) 
		{
			arregloDevolver.addLast(elementos[i]);
		}
		return arregloDevolver;
	}
	@Override
	public int compareTo(ArregloDinamico<T> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ILista<T> subList2(int numElement) {
		ArregloDinamico<T> copia =  new ArregloDinamico<T>();
		if(numElement >= tamanoAct){
			copia = this;
		}
		else{
			for(int i = 1; i<=numElement;i++)
			{
				T x = getElement(i);
				copia.addLast(x);
			}
		}
		return copia;
	}
}
