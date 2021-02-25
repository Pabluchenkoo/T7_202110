package model.data_structures;

public class NodoLista <T extends Comparable <T>>
{
	private NodoLista siguiente;
	
	private T information;
	
	public NodoLista (T info)
	{
		information=info;
		siguiente=null;
	}
	public void cambiarSiguiente(NodoLista pSiguiente)
	{
		siguiente=pSiguiente;
	}
	public NodoLista darSiguiente()
	{
		return siguiente;
	}
	public T darInformation()
	{
		return information;
	}
	public void cambiarInformation(T pInformation)
	{
		information=pInformation;
	}
	
}
