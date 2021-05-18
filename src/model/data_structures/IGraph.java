package model.data_structures;

import java.util.Iterator;

/**
 * Interfaz que debe implementar la clase UndirectedGraph.

 * @param <K> Tipo de llave que se guardar� en las tablas de hash con la
 *            informaci�n.
 * @param <V> Tipo de valor o item que se guardar� en cada vertice.
 */
public interface IGraph<K extends Comparable<K>, V extends Comparable<V>, L extends Comparable<L>> extends Iterable<String>
{
	public int numberOfVertices( );

	public int numberOfEdges( );

	/**
	 * Adds the undirected edge v-w to this graph.
	 * @param v one vertex in the edge.
	 * @param w the other vertex in the edge.
	 * @throws IllegalArgumentException if any of them is not a valid vertex.
	 */
	public void addEdge( int v, int w, double cost );

	/**
	 * @param v Uno de los vertices del arco.
	 * @param w Otro vertice del arco.
	 * @return Costo del arco entre los vertices dados por par�metros.
	 */
	public double getEdgeDoubleCost( int v, int w );

	/**
	 * @param v Uno de los v�rtices del arco.
	 * @param w Otro v�rtice del arco.
	 * @return Costo de tipo integer del arco entre los v�rtices dados por
	 *         par�metros.
	 * @throws IllegalArgumentException Si alguno de los v�rtices no son v�lidos.
	 */
	public int getEdgeIntegerCost( int v, int w ) throws IllegalArgumentException;

	/**
	 * Returns the degree of the given vertex.
	 * @param v the vertex.
	 * @return the degree of given vertex.
	 * @throws IllegalArgumentException if given vertex is not valid.
	 */
	public int degreeOf( int v );

	/**
	 * Le asigna al vertice el item (ambos dados por par�metro). El vertice es
	 * ubicado dentro de la tabla de hash y a dicha llave se le a�ade el valor que
	 * corresponde al item.
	 * @param v    vertice en cuesti�n.
	 * @param item Nuevo item.
	 */
	public void insertVertexItem( int v, K key, V item );

	/**
	 * Le asigna al vertice la informaci�n (ambos dados por par�metro). El vertice
	 * es ubicado dentro de la tabla de hash y a dicha llave se le a�ade el valor
	 * que corresponde al item.
	 * @param v    vertice en cuesti�n.
	 * @param info Informaci�n del vertice.
	 */
	public void setVertexInfo( int v, String info );

	/**
	 * metodo para obtener el vertice basado en el numero
	 * @param v
	 * @return vertice solicitado.
	 */
	public Vertex<K, V, L> getVertex( int v );

	/**
	 * @param v Vertice.
	 * @return Informaci�n del vertice.
	 */
	public String getVertexInfo( int v );

	/**
	 * Iterador sobre todos los items asociados al vertice.
	 * @param v id del vertice.
	 * @return Iterador sobre todos los valores dentro de la tabla de hash con llave
	 *         igual al id del vertice.
	 */
	public Iterator<V> vertexItems( int v );

	/**
	 * Iterador sobre todos los ID's de los v�rtices adyacentes al v�rtice cuyo ID
	 * es dado por par�metro.
	 * @param v ID del v�rtice a chequear sus adyacentes.
	 * @return V�rtices adyacentes al dado por par�metro en forma de iterador.
	 * @throws IllegalArgumentException Si el v�rtice no es v�lido.
	 */
	public Iterator<Integer> vertexAdjacentTo( int v ) throws IllegalArgumentException;

	/**
	 * Iterador sobre todos los arcos adyacentes al v�rtice cuyo ID es dado por
	 * par�metro.
	 * @param v ID del v�rtice a chequear sus arcos adyacentes.
	 * @return Arcos adyacentes al dado por par�metro en forma de iterable.
	 * @throws IllegalArgumentException Si el v�rtice no es v�lido.
	 */
	public Iterable<Edge<K, V, L>> edgesAdjacentTo( int v ) throws IllegalArgumentException;
	
	/**
	 * Iterador sobre todos los arcos existentes en forma de cadena de los ID's de
	 * los v�rtices del arco concatenados con un "-".
	 * @return Iterador sobre todos los arcos v-w existentes.
	 */
	public Iterator<String> edges( );
}