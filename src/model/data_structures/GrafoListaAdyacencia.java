package model.data_structures;

public class GrafoListaAdyacencia <K extends Comparable<K> ,V>
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
	public HashTable<Integer, Vertex<K, V, L>> vertex;

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

		this.vertex = new HashTable<>( 24007, true );

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
	
	
}
