package model.data_structures;


import java.util.Iterator;

import java.util.ArrayList;





public class TablaHashSeparateChaining <K extends Comparable<K>, V extends Comparable <V>> implements TablaSimbolos <K, V>
{
	private double factorDeCarga;
	
	private static final int MAXIMUM_LOAD_FACTOR = 5;

	private int N;

	private ArregloDinamico<SequentialSearchST<K,V>> map;
	
	private int M;
	
	private int numeroRehashes;
	
	public TablaHashSeparateChaining( int size ) 
	{
		map = new ArregloDinamico<SequentialSearchST<K,V>> (M);
		numeroRehashes = 0;
		for(int i = 0; i < map.size();i++)
		{
			map.changeInfo(i, new SequentialSearchST<K,V>(MAXIMUM_LOAD_FACTOR));
		}
	}
	
	
	
	@Override
	public void put(K pLlave, V pValor) {
		// TODO Auto-generated method stub
		if((M/N + (1/N)) >= MAXIMUM_LOAD_FACTOR)
		{
			rehash( );
		}
		int pos = hash(pLlave);
		SequentialSearchST<K,V> act = map.darElemento(pos);
		NodoTabla<K,V> nuevo = new NodoTabla<K,V>(pLlave, pValor);
		act.agregarASequentialSearchST(nuevo);
	}

	@Override
	public V get(K pLlave) {
		// TODO Auto-generated method stub
		int posicion = hash(pLlave);
		SequentialSearchST<K,V> st = map.darElemento(posicion);
		NodoTabla<K,V> buscado = st.get(pLlave);
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
	public boolean contains(K pLlave) {
		// TODO Auto-generated method stub
		boolean respuesta = false;
		if(get(pLlave) != null )
		{
			 respuesta=true;
		}
		return respuesta;
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
	private int hash(K pLlave) {
		// TODO Auto-generated method stub
		return (pLlave.hashCode() & 0x7fffffff) % M;
	}



	public void rehash() {
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
	}

}

	

