package model.data_structures;

import java.util.Iterator;

public class GrafoListaAdyacencia<K extends Comparable<K>, V> implements IGrafoListaAdyacencia<K, V>
{
	/**
	 * Costo que se asigna a un arco que no existe.
	 */
	public final static double INFINITY = -1000000000000000.0;

	@Override
	public boolean containsVertex(K id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int numVertices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertVertex(K id, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(K source, K dest, float weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vertex<K, V> getVertex(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge<K, V> getEdge(K idS, K idD) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ILista<Edge<K, V>> adjacentEdges(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void adjacentVertex(K id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int indegree(K vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int outdegree(K vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ILista<Vertex<K, V>> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEdge(int v, int w, double cost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getEdgeDoubleCost(int v, int w) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEdgeIntegerCost(int v, int w) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int degreeOf(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertVertexItem(int v, K key, V item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVertexInfo(int v, String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vertex<K, V, L> getVertex(int v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVertexInfo(int v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> vertexItems(int v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> vertexAdjacentTo(int v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Edge<K, V, L>> edgesAdjacentTo(int v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<String> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unmark() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dfs(K id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bfs(K id) {
		// TODO Auto-generated method stub
		
	}
	
	
}