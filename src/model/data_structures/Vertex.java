  
package model.data_structures;

public class Vertex<K extends Comparable<K>, V> implements IVertex<K, V>{
	
	private K id;
	private V value;
	private boolean mark;
	private ArregloDinamico<Edge<K,V>> arcos; 	
	public Vertex(K id, V value){
		this.id = id;
		this.value = value;
		mark = false;
		arcos = new ArregloDinamico<>();
	}
	
	@Override
	public K getId() {	
		return id;
	}

	@Override
	public V getInfo() {
		return value;
	}

	@Override
	public boolean getMark() {
		return mark;
	}

	@Override
	public void addEdge(Edge<K, V> edge) {
		arcos.addLast(edge);		
	}

	@Override
	public void mark(Edge<K, V> edgeTo) {
		mark = true;
	}

	@Override
	public void unmark() {
		mark = false;
	}

	@Override
	public int outdegree() {
		
		return 0;
	}

	@Override
	public int indegree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Edge<K, V> getEdge(K vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<Vertex<K, V>> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<Edge<K, V>> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dfs(Edge<K, V> edgeTo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bfs() {
		// TODO Auto-generated method stub
		
	}

}
