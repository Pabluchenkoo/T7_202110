package model.data_structures;

public class SequentialSearchST< K extends Comparable<K>, V extends Comparable<V>> implements Comparable<SequentialSearchST<K,V>>
{
	private ArregloDinamico<NodoTabla<K,V>> nodos;
	
	public SequentialSearchST(int size)
	{
		nodos = new ArregloDinamico<NodoTabla<K,V>>(size);
	}
	
	public void agregarASequentialSearchST(NodoTabla<K,V> nodo)
	{
		int i = 0;
		for( i = i; i < nodos.size( ) && nodos.darElemento(i) != null; i++)
		{
			if(nodo.darLlave( ).equals(nodos.darElemento(i).darLlave()))
			{
				nodos.darElemento(i).cambiarValor(nodo.darValor());
				i = nodos.size( );
			}
			
		}
		
		if(i < nodos.size())
		{
			nodos.addLast(nodo);
		}	
		
	}
	
	public NodoTabla<K, V> get(K key) 
	{
		NodoTabla<K, V> seek = null;
		for(int i = 0 ; i < nodos.size() && seek == null; i++)
		{
			NodoTabla<K,V> actual = nodos.darElemento(i);
			if(actual != null && actual.darLlave().equals(key))
			{
				seek = actual;
			}
		}
		return seek;
	}
	
	public NodoTabla<K, V> remove(K key) 
	{
		
		NodoTabla<K, V> seek = null;
		for(int i = 0; i < nodos.size() && seek == null; i ++)
		{
			NodoTabla<K,V> actual = nodos.darElemento(i);
			if(actual != null && actual.darLlave().equals(key))
			{
				seek = actual;
				nodos.deleteElement(i);
			}
		}
		return null;

	}
	public ArregloDinamico<NodoTabla<K,V>> getAll()
	{
		return nodos;
	}

	/**
	 * No hace nada.
	 */
	@Override
	public int compareTo(SequentialSearchST<K, V> o) 
	{
		
		return 0;
	}



	
}
