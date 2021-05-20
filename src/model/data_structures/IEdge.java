package model.data_structures;

public interface IEdge<K extends Comparable<K>, V> {
	Vertex<K,V> getSource();
	Vertex<K,V> getDestination();
	float weight();
	void setWeight(float weight);
}
