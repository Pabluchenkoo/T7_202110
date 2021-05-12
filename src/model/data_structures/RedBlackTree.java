package model.data_structures;

public class RedBlackTree<K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolos<K,V> {
	//Numero de duplas almacenadas en el arbol
	int n;
	NodoArbol<K, V> raiz;
	public RedBlackTree() {
		n = 0;
		raiz = null;
	}
	
	/**
	 * Retorna el numero de duplas
	 */
	public int size() {
		return n;
	}
	
	public boolean isEmpty() {
		
		return n==0;
	}

	public V get(K k) {
		if(raiz == null){
			return null;
		}
		else{
			NodoArbol<K,V> nodo = raiz.get(k);
			return (nodo!=null)?nodo.darValor():null;
		}
	}

	public int getHeight(K k){
		NodoArbol<K, V> buscado = raiz.get(k);
		return (buscado==null)?-1:buscado.getHeight();
	}

	public boolean contains(K k) {
		
		return get(k)!=null;
	}

	
	public void put(K k, V v) {
		NodoArbol<K, V> nuevo = new NodoArbol<K,V>(k, v);
		if(raiz==null){
			raiz = nuevo;
		}
		else{
			raiz = raiz.put(nuevo);
		}
		n++;
		raiz.asignarColor("negro");
		
	}
	

	public int height() {		
		return raiz.getHeight();
	}


	public K min() {		
		return (raiz==null)?null:raiz.min();
	}


	public K max() {
		return (raiz==null)?null:raiz.max();
	}

	public ILista<K> keySet() {
		ArregloDinamico<K> listaLlaves = new ArregloDinamico<K>();
		return raiz.keySet(listaLlaves);
	}

	public ILista<V> valueSet() {
		ArregloDinamico<V> listaValores = new ArregloDinamico<V>();
		return raiz.valueSet(listaValores);
	}


	@Override
	public V remove(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	public ILista<K> keysInRange(K init, K end) {
			ArregloDinamico<K> listaLlaves = new ArregloDinamico<K>();
			if (raiz!=null)
				raiz.keysInRange(listaLlaves, init, end);
			return listaLlaves;
	}

	public ILista<V> valuesInRange(K init, K end) {
		ArregloDinamico<V> listaValores = new ArregloDinamico<V>();
		if (raiz!=null)
			raiz.valuesInRange(listaValores, init, end);
		return listaValores;
	}

	@Override
	public int hash(K key) {
		// TODO Auto-generated method stub
		return 0;
	}

}
