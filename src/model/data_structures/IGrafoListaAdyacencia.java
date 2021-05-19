package model.data_structures;

public interface IGrafoListaAdyacencia<K extends Comparable<K> ,V> {

	boolean containsVertex(K id);
	int numVertices();
	int numEdges();
	void insertVertex(K id, V value);
	void addEdge(K source, K dest, float weight);
	Vertex<K,V> getVertex(K id);
	Edge<K,V> getEdge(K idS, K idD);
	ILista<Edge<K,V>> adjacentEdges(K id);
	void adjacentVertex(K id);
	int indegree(K vertex);
	int outdegree(K vertex);
	ILista<Edge<K,V>> edges();
	ILista<Vertex<K,V>> vertices();
	void unmark();
	void dfs(K id);
	void bfs(K id);
}
