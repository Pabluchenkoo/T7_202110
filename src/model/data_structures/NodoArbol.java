package model.data_structures;

public class NodoArbol<K extends Comparable<K>, V> implements Comparable<NodoArbol<K,V>> {
	
	private K llave;
	private V valor;
	private NodoArbol<K, V> derecho;
	private NodoArbol<K, V> izquierdo;
	private String color;
	
	public NodoArbol(K llave,V valor){
		this.llave = llave;
		this.valor = valor;
		derecho = null;
		izquierdo = null;
		color = "rojo";
	}
	
	public K darLlave(){
		return llave;
	}
	
	public V darValor(){
		return valor;
	}
	
	public void asignarValor(V valor){
		this.valor = valor;
	}
	
	public NodoArbol<K, V> darIzquierdo(){
		return izquierdo;
	}
	
	public NodoArbol<K, V> darDerecho(){
		return derecho;
	}
	
	public void asignarIzquierdo(NodoArbol<K,V> izq){
		izquierdo = izq;
	}
	
	public void asignarDerecho(NodoArbol<K, V> der){
		derecho = der;
	}
	
	public NodoArbol<K, V> get(K llave){
		if(this.darLlave().compareTo(llave)==0){
			return this;
		}
		else if(izquierdo!=null&&this.darLlave().compareTo(llave)>0){
			return izquierdo.get(llave);
		}
		else if(derecho!=null){
			return derecho.get(llave);
		}
		return null;
	}
	
	public K min(){
		K minimo = llave;
		if(izquierdo!=null){
			K minIz = izquierdo.min();
			if(minIz.compareTo(minimo)<0){
				minimo = minIz;
		}
			}
		if(derecho!=null){
			K minDer = derecho.min();
			if(minDer.compareTo(minimo)<0){
				minimo = minDer;
		}
			}
		return minimo;
	}
	
	public K max(){
		K maximo = llave;
		if(izquierdo!=null){
			K maxIz = izquierdo.max();
			if(maxIz.compareTo(maximo)>0){
				maximo = maxIz;
		}
			}
		if(derecho!=null){
			K maxDer = derecho.max();
			if(maxDer.compareTo(maximo)>0){
				maximo = maxDer;
		}
			}
		return maximo;
	}
	
	public int getHeight(){
		int alturaIz = (izquierdo!=null)?izquierdo.getHeight():0;
		int alturaDer = (derecho!=null)?derecho.getHeight():0;
		return (alturaIz>alturaDer)? 1+alturaIz: 1+alturaDer;
	}
	
	public ILista<K> keySet(ILista<K> lista){
		if(izquierdo!=null)
			lista = izquierdo.keySet(lista);
		lista.addLast(llave);
		if(derecho!=null)
			lista = derecho.keySet(lista);		
		return lista;
	}
	
	public ILista<V> valueSet(ILista<V> lista){
		if(izquierdo!=null)
			lista = izquierdo.valueSet(lista);
		lista.addLast(valor);
		if(derecho!=null)
			lista = derecho.valueSet(lista);
		return lista;
	}
	
	
	public String darColor(){
		return color;
	}
	
	public void cambiarColor(){
		if(color.equals("rojo"))
			color = "negro";
		else
			color = "rojo";
	}
	
	public void asignarColor(String color){
		this.color = color;
	}
	
	public NodoArbol<K, V> put(NodoArbol<K,V> nuevo){
		NodoArbol<K, V> nodo = this;
		if(nuevo.darLlave().compareTo(llave)<0){
			if(izquierdo!=null)
				izquierdo = izquierdo.put(nuevo);
				else
					izquierdo =nuevo;
		}
		else if (nuevo.darLlave().compareTo(llave)>0){
			if(derecho!=null)
				derecho = derecho.put(nuevo);
		     	else
		     		derecho = nuevo;
		}
		else{
			valor = nuevo.darValor();
		}
		
		if(nodo.darDerecho()!=null&&(nodo.darDerecho().darColor().equals("rojo"))){
			if (nodo.darIzquierdo()==null||(nodo.darIzquierdo()!=null&&nodo.darIzquierdo().darColor().equals("negro")))
				nodo = RL(nodo);
			}
		if((nodo.darIzquierdo()!=null&&nodo.darIzquierdo().darIzquierdo()!=null)&&(nodo.darIzquierdo().darColor().equals("rojo"))&&nodo.darIzquierdo().darIzquierdo().darColor().equals("rojo"))
			nodo = RD(nodo);
		if((nodo.darDerecho()!=null&&nodo.darIzquierdo()!=null)&&(nodo.darIzquierdo().darColor().equals("rojo"))&&(nodo.darDerecho().darColor().equals("rojo")))
			FC(nodo);
		
		return nodo;
	}
	
	public ArregloDinamico<K> keysInRange(ArregloDinamico<K> lista, K init, K end){
		int menor = end.compareTo(llave);
		int mayor = init.compareTo(llave);
		if(menor<0) izquierdo.keysInRange(lista, init, end);
		if(menor<=0 && mayor>=0) lista.addLast(llave);
		if(mayor>0) derecho.keysInRange(lista, init, end);
		return lista;
	}

	
	public NodoArbol<K,V> RL(NodoArbol<K,V> nodo){
		NodoArbol<K,V> der = nodo.darDerecho();
		nodo.asignarDerecho(der.darIzquierdo());
		der.asignarIzquierdo(nodo);
		der.asignarColor(nodo.darColor());
		nodo.asignarColor("rojo");
		return der;
	}
	
	public NodoArbol<K,V> RD(NodoArbol<K,V> nodo){
		NodoArbol<K,V> izq = nodo.darIzquierdo();
		nodo.asignarIzquierdo(izq.darDerecho());
		izq.asignarDerecho(nodo);
		izq.asignarColor(nodo.darColor());
		nodo.asignarColor("rojo");
		return izq;
	}

	public void FC(NodoArbol<K,V> nodo){
		nodo.asignarColor("rojo");
		nodo.darIzquierdo().asignarColor("negro");
		nodo.darDerecho().asignarColor("negro");
	}

	
	
	//Utilizo el metodo compareTo de la llave (que probablemte sera un int o String)
	public int compareTo(NodoArbol<K, V> o) {
		return llave.compareTo(o.darLlave());
	}

	public ArregloDinamico valuesInRange(ArregloDinamico list, K i, K f){
		
		int menor = f.compareTo(llave);
		int mayor = i.compareTo(llave);
		if((menor<0||mayor<0)&&izquierdo!=null) izquierdo.valuesInRange(list, i, f);
		if(menor>=0 && mayor<=0) list.addLast((Comparable) valor);
		if((mayor>0||menor>0)&&derecho!=null) derecho.valuesInRange(list, i, f);
		return list;
	}
}