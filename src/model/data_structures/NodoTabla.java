package model.data_structures;

public class NodoTabla<K extends Comparable<K>, V> implements Comparable<NodoTabla<K,V>> {

		private K llave;
		private V valor;
		
		public NodoTabla(K llave, V valor) {
			this.llave = llave;
			this.valor= valor;
		}
		
		public void asignarValor(V valor){
			this.valor = valor;
		}
		
		public K darLlave(){
			return llave;
		}
		
		public V darValor(){
			return valor;
		}
		public void cambiarValor ( V pValor )
	    {
	        valor = pValor;
	    }
		
		
		/** La comparación de dos NodoTS depende de sus llaves */
		public int compareTo(NodoTabla<K, V> otro) {		
			return this.llave.compareTo(otro.llave);
		}

		public void insertElement(NodoTabla<K, V> nodoTabla, int h) {
			// TODO Auto-generated method stub
			
		}
}
