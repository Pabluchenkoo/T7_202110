package model.data_structures;

public class TablaSimbolos<K extends Comparable<K>, V> implements ITablaSimbolos<K, V> {

	private ArregloDinamico<NodoTabla<K,V>> tabla;
	private int tot;
	
	public TablaSimbolos(int c) {
		tot = c;
		tabla = new ArregloDinamico<NodoTabla<K,V>>(c);
	}
	
	public void put(K k, V v){
		int pos = hash(k);
		NodoTabla<K,V> nodo = new NodoTabla<K,V>(k,v);
		if(tabla.getElement(pos)==null)
			tabla.insertElement(nodo, pos);
		else
			tabla.getElement(pos).asignarValor(v);
	}

	public V get(K k){
		int pos = hash(k);
		if(tabla.getElement(pos)!=null)
		return tabla.getElement(pos).darValor();
		return null;
	}

	public V remove(K k){
		int pos = hash(k);
		V valor = tabla.getElement(pos).darValor();
		tabla.deleteElement(pos);
		return valor;
	}

	public boolean contains(K k) {
		for(int i=1; i<=tabla.size();i++){
			if(k.compareTo(tabla.getElement(i).darLlave())==0){
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna true si la tabla tiene datos
	 * Complejidad O(K)
	 */
	public boolean isEmpty() {
		return tabla.isEmpty();
	}

	/**
	 * Retorna la cantidad de duplas en la tabla
	 * Complejidad O(K)
	 */
	public int size() {
		return tabla.size();
	}

	/**
	 * 
	 */
	public ILista<K> keySet() {
		ArregloDinamico<K> llaves = new ArregloDinamico<K>(); 
		for(int i=1;i<=tabla.size();i++){
			if(tabla.getElement(i)!=null)
				llaves.addLast(tabla.getElement(i).darLlave());
		}
		
		return llaves;
	}

	public ILista<V> valueSet() {
		ArregloDinamico valores = new ArregloDinamico(); 
		for(int i=1;i<=tabla.size();i++){  
			valores.addLast((Comparable) tabla.getElement(i).darValor());
		}
		return valores;
	}

	@Override
	public int hash(K key) {
		int p = nextPrime(tabla.size());
		int m = tot;
		int h = key.hashCode();
		
		return (Math.abs(h)%m);
	}
	
	
   
    
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
