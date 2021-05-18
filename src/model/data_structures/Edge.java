package model.data_structures;

/**
 * Arco de un grafo.

 */
public class Edge<K extends Comparable<K>, V extends Comparable<V>, L extends Comparable<L>>
		implements Comparable<Edge<K, V, L>>
{
	private Vertex<K, V, L> v1;

	private Vertex<K, V, L> v2;

	private double doubleCost;

	private int integerCost;

	/**
	 * Inicializa un arco entre los dos v�rtices dados por par�metro.
	 * @param v1   V�rtice 1 del arco. v1 != null
	 * @param v2   V�rtice 2 del arco. v2 != null
	 * @param cost Costo de tipo double del arco. cost != null
	 */
	public Edge( Vertex<K, V, L> v1, Vertex<K, V, L> v2, double cost )
	{
		this.v1 = v1;
		this.v2 = v2;
		this.doubleCost = cost;
	}

	/**
	 * @return Cualquiera de los ID's de los v�rtices pertenecientes al arco.
	 */
	public int either( )
	{
		return v1.getId( );
	}

	/**
	 * @param vertex ID del v�rtice que no se quiere.
	 * @return V�rtice del ID que no es aqu�l dado por par�metro.
	 * @throws RuntimeException Si el ID del v�rtice dado no es ninguno de los 2
	 *                          v�rtices asociados al arco.
	 */
	public int other( int vertex )
	{
		if( vertex != v1.getId( ) && vertex != v2.getId( ) )
			throw new RuntimeException( "Inconsistent edge" );

		return ( v1.getId( ) == vertex ) ? v2.getId( ) : v1.getId( );
	}

	/**
	 * @return El costo de tipo double del arco.
	 */
	public double getDoubleCost( )
	{
		return doubleCost;
	}

	/**
	 * @param doubleCost El costo de tipo double a asignar.
	 */
	public void setDoubleCost( double doubleCost )
	{
		this.doubleCost = doubleCost;
	}

	/**
	 * @return El costo de tipo integer del arco.
	 */
	public int getIntegerCost( )
	{
		return integerCost;
	}

	/**
	 * @param integerCost El costo de tipo integer a asignar.
	 */
	public void setIntegerCost( int integerCost )
	{
		this.integerCost = integerCost;
	}

	@Override
	public int compareTo( Edge<K, V, L> o )
	{
		if( this.doubleCost < o.getDoubleCost( ) )
			return -1;
		else if( this.doubleCost > o.getDoubleCost( ) )
			return 1;
		else
			return 0;
	}
}