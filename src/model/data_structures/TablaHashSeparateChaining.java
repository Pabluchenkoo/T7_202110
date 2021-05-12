package model.data_structures;


import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;

import model.logic.YouTubeVideo;





public class TablaHashSeparateChaining <K extends Comparable<K>, V> implements ITablaSimbolos <K, V>
{
	private ArregloDinamico[] tablaDeHash; 
	
	private double factorDeCarga;
	
	private static final int MAXIMUM_LOAD_FACTOR = 5;

	private int N;

	private ArregloDinamico<SequentialSearchST<K,V>> map;
	
	private int M;
	
	private int numeroRehashes;
	
	private Random random = new Random();
	
	
	public TablaHashSeparateChaining( int size, double factorDeCarga) 
	{
		M = (int)((int) size/factorDeCarga);
    	M = (!isPrime(M))?nextPrime(M):M;
    	N = size;
    	tablaDeHash = new ArregloDinamico[M];
    	for(int i=0;i<M;i++){
    		ArregloDinamico<NodoTabla<K, V>> nuevo = new ArregloDinamico<NodoTabla<K, V>>();
    		tablaDeHash[i] = nuevo; 
    	}
	}
	
	
	
	@Override
	public void put(K pLlave, V pValor) {
		// TODO Auto-generated method stub
		int pos = hash(pLlave);
    	//info de la posicion
    	ArregloDinamico<NodoTabla<K, V>> info = tablaDeHash[pos];
       //Si la posicion es null    	
    	if(info.isEmpty()){
    		NodoTabla<K, V> nodo = new NodoTabla<K,V>(pLlave, pValor);
    		info.addLast(nodo);
    		tablaDeHash[pos] = info;
    	}
       //Si ya hay un bucket en la posicion
    	else{
    		boolean stop = false;
    		for(int i=1;i<=info.size()&&!stop;i++){
    			//Ese objeto es el que ya tengo
    			NodoTabla<K, V> actual = info.getElement(i);
    			if(actual.darLlave().equals(pLlave)){
    				actual.asignarValor(pValor);
    				stop=true;
    			}
    		}
    		//Es un objeto nuevo
    		if(!stop){
    			NodoTabla<K, V> nodo = new NodoTabla<K,V>(pLlave, pValor);
    			info.addLast(nodo);
    		}
    			
    	}
	}

	@Override
	public V get(K pLlave) {
		// TODO Auto-generated method stub
		int i = hash(pLlave);
        ArregloDinamico<NodoTabla<K,V>> lista = tablaDeHash[i];
        for(int j=1; j<=lista.size();j++){
        	if(lista.getElement(j).darLlave().equals(pLlave)){
        		return (V) lista.getElement(j).darValor();
        	}
        }
    	return null;
	}

	@Override
	public V remove(K pLlave) {
		// TODO Auto-generated method stub
		int posicion = hash(pLlave);
		SequentialSearchST<K,V> st = map.darElemento(posicion);
		NodoTabla<K,V> buscado = st.remove(pLlave);
		if(buscado == null)
		{
			return null;
		}
		else
		{
			return buscado.darValor();
		}
	}

	@Override
	public boolean contains(K key) {
    	return (get(key)==null)?false:true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		boolean respuesta = true;
		if(N > 0)
		{
			respuesta = false;
		}
		return respuesta;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return N;
	}

	@Override
	public ArregloDinamico<K> keySet() {
		// TODO Auto-generated method stub
		ArregloDinamico<K> respuesta = new ArregloDinamico<K>(N);
		for(int i = 0; i<M ; i++)
		{
			ArregloDinamico<NodoTabla<K,V>> temporal = map.darElemento(i).getAll();
			for(int j=0 ; j < temporal.size() ; j++)
			{
				NodoTabla<K,V> elemento = temporal.darElemento(j);
				if(elemento != null && elemento.darLlave() != null)
					respuesta.addLast(elemento.darLlave());	
			}
		}
		return respuesta;
	}

	@Override
	public ArregloDinamico<V> valueSet() {
		// TODO Auto-generated method stub
		ArregloDinamico<V> respuesta = new ArregloDinamico<V>(N);
		for(int i = 0; i<M ; i++)
		{
			ArregloDinamico<NodoTabla<K,V>> temporal = map.darElemento(i).getAll();
			for(int j=0 ; j < temporal.size() ; j++)
			{
				NodoTabla<K,V> elemento = temporal.darElemento(j);
				if(elemento != null && elemento.darLlave() != null)
					respuesta.addLast(elemento.darValor());	
			}
		}
		return respuesta;
	}
	
	public ArregloDinamico<NodoTabla<K,V>> getAll() 
	{
		ArregloDinamico<NodoTabla<K,V>> respuesta = new ArregloDinamico<NodoTabla<K,V>>(N);
		
		for( int i = 0; i < M; i++)
		{
			ArregloDinamico<NodoTabla<K,V>> temporal = map.darElemento(i).getAll();
			for(int j=0 ; j < temporal.size() ; j++)
			{
				NodoTabla<K,V> elemento = temporal.darElemento(j);
				if(elemento != null)
					respuesta.addLast(elemento);	
			}
		}
		return respuesta;
	}
	@Override
	public int hash(K pLlave) {
		int p = nextPrime(tablaDeHash.length);
		int m = tablaDeHash.length;
		int h = pLlave.hashCode();
		int a = random.nextInt(p-1);
		int b = random.nextInt(p);
		return (Math.abs(h)%m);
	}



	/*public void rehash() {
		// TODO Auto-generated method stub
		ArregloDinamico<NodoTabla<K,V>> todosLosElementos = getAll();
		map = new ArregloDinamico<SequentialSearchST<K,V>>(M);
		for ( int i = 0 ; i < map.size() ; i++ )
		{
			SequentialSearchST<K,V> elemento = new SequentialSearchST<>(5);
			map.changeInfo(i, elemento);
		}
		for ( int i = 0 ; i < todosLosElementos.size() ; i++ )
		{
			NodoTabla<K,V> actual = todosLosElementos.darElemento(i);
			put(actual.darLlave() , actual.darValor());
		}
		numeroRehashes++;
	}
	
	public int numeroRehashes()
	{
		return numeroRehashes;
	}*/


	 /*
     * Metodos enviados por el profesor
     */
    
    // Function that returns true if n
    // is prime else returns false
    static boolean isPrime(int n)
    {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;   
        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) return false;       
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;       
        return true;
    }

    // Function to return the smallest
    // prime number greater than N
    static int nextPrime(int N)
    {   
        // Base case
        if (N <= 1)
            return 2;  
        int prime = N;
        boolean found = false;

        // Loop continuously until isPrime returns
        // true for a number greater than n

        while (!found)
        {
            prime++;
            if (isPrime(prime))
                found = true;
        }
        return prime;
    }
}

	

