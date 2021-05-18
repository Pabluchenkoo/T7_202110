package model.data_structures;

import java.util.Iterator;

public class GrafoListaAdyacencia <K extends Comparable<K>, V extends Comparable<V>, L extends Comparable<L>>
implements IGraph<K, V, L>
{
	/**
	 * Costo que se asigna a un arco que no existe.
	 */
	public final static double INFINITY = -1000000000000000.0;

	/**
	 * Número de vértices.
	 */
	private final int V;

	/**
	 * Número de arcos.
	 */
	private int E;

	/**
	 * Número de items guardados en vértices.
	 */
	private int S;

	/**
	 * Número de items distintivos guardados en vértices.
	 */
	private int D;

	/**
	 * Lista de adyacencia.
	 */
	private Bag<Edge<K, V, L>>[] adj;

	/**
	 * Tabla de hash que contiene los vértices. La llave es el ID del vértice y el
	 * valor un objeto de tipo Vertex.
	 */
	public TablaHashLinearProbing<Integer, Vertex<K, V, L>> vertex;

	/**
	 * Tipo de costo que retornar cuando se itera sobre los arcos. DOUBLE es el
	 * predeterminado.
	 */
	public CostType t = CostType.DOUBLE;

	/**
	 * Inicializa un grafo con el número de vértices dados por parámetro y 0 arcos.
	 * @param numberOfVertices Número de vértices.
	 * @throws IllegalArgumentException Si el número de vértices es menor a 0.
	 */
	public GrafoListaAdyacencia( int numberOfVertices )
	{
		if( numberOfVertices < 0 )
			throw new IllegalArgumentException( "Number of vertices must be nonnegative" );

		this.V = numberOfVertices;
		this.E = 0;
		this.D = 0;
		this.S = 0;

		this.vertex = new TablaHashLinearProbing<>( 24007, true );

		adj = ( Bag<Edge<K, V, L>>[] ) new Bag[numberOfVertices];
		for( int v = 0; v < numberOfVertices; v++ )
		{
			vertex.put( v, new Vertex<>( v ) );
			adj[v] = new Bag<Edge<K, V, L>>( );
		}
	}
	
	/**
	 * @return Número de vértices en el grafo.
	 */
	public int numberOfVertices( )
	{
		return V;
	}

	/**
	 * @return Número de arcos en el grafo.
	 */
	public int numberOfEdges( )
	{
		return E;
	}
	
	public void addEdge( int v, int w, float weight ) throws IllegalArgumentException
	{
		validateVertex( v );
		validateVertex( w );

		Edge<K, V, L> e = new Edge<K, V, L>( vertex.get( v ), vertex.get( w ), weight );
		adj[v].add( e );
		adj[w].add( e );
		E++;
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
	
	
}
