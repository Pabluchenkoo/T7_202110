package model.data_structures;

public class ListaEncadenada <T extends Comparable<T>> implements ILista<T>
{
	private int tamanio;
	private NodoLista<T> primero;
	
	public void addFirst(T element) 
	{
		NodoLista<T> nuevoNodo = new NodoLista<T>( element);
		nuevoNodo.cambiarSiguiente(primero);
		primero=nuevoNodo;
		tamanio++;
		
	}

	@Override
	public void addLast(T element) 
	{
		NodoLista<T> auxiliar=primero;
		NodoLista<T> last=new NodoLista<T>(element);
		if(primero!=null)
		{
			while(auxiliar.darSiguiente()!=null)
			{
				auxiliar=auxiliar.darSiguiente();
			}
			auxiliar.cambiarSiguiente(last);
		}


	}

	@Override
	public void insertElement(T element, int pos) 
	{
		NodoLista<T> nuevo= new NodoLista<T>(element);
		NodoLista<T> auxiliar=primero;
		
		if(primero!=null)
		{
			for (int i = 1; i <= pos; i++) 
			{
				auxiliar=auxiliar.darSiguiente();
			}
			nuevo.cambiarSiguiente(auxiliar.darSiguiente());
			auxiliar.cambiarSiguiente(nuevo);
			
		}
		
		
	}

	@Override
	public T removeFirst() 
	{
		if(primero!=null)
		{
			NodoLista<T> remover = primero;
			primero=primero.darSiguiente();
			tamanio--;
			return remover.darInformation();
		}
		else
			return null;
		
	}

	@Override
	public T removeLast() 
	{
		NodoLista<T> auxiliar=primero;
		if(primero!=null)
		{
			while(auxiliar.darSiguiente().darSiguiente()!=null)
			{
				auxiliar=auxiliar.darSiguiente();
			}
			NodoLista<T> eliminar=auxiliar.darSiguiente();
			auxiliar.cambiarSiguiente(null);
			return eliminar.darInformation();
		}
		else
			return null;
		
	}

	@Override
	public T deleteElement(int pos) 
	{
		if(primero!=null)
		{
			NodoLista<T> auxiliar=primero;
			for (int i = 1; i < pos; i++) 
			{
				auxiliar=auxiliar.darSiguiente();
			}
			T eliminar = (T) auxiliar.darSiguiente().darInformation();
			auxiliar.cambiarSiguiente(auxiliar.darSiguiente().darSiguiente());
			return eliminar;
		}
		else
			return null;
		
	}

	@Override
	public T firstElement() {
		if(primero!=null)
		{
			return primero.darInformation();
		}
		else
			return null;
		
	}

	@Override
	public T lastElement() {
		// TODO Auto-generated method stub
		if(primero!=null)
		{
			NodoLista<T> auxiliar=primero;
			while(auxiliar.darSiguiente()!=null)
			{
				auxiliar=auxiliar.darSiguiente();
			}
			return auxiliar.darInformation();
		}
		else		
			return null;
	}

	@Override
	public T getElement(int pos) {
		if(primero!=null)
		{
			NodoLista<T> auxiliar=primero;
			for (int i = 0; i <= pos; i++) 
			{
				auxiliar=auxiliar.darSiguiente();
			}
			return auxiliar.darInformation();
		}
		else
			return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return primero==null;
	}

	@Override
	public int isPresent(T element) 
	{
		if(primero!=null)
		{
			NodoLista<T> auxiliar=primero;
			int pos =0;
			boolean centinela=false;
			while( auxiliar!=null &&!centinela)
			{
				pos++;
				if(auxiliar.darInformation().compareTo(element)==0)
				{
					centinela=true;
				}
				
			}
			if(centinela)
			{
				return pos;
			}
			else
				return -1;
		}
		else
			return -1;
	}

	@Override
	public void exchange(int pos1, int pos2) 
	{
		NodoLista<T> auxiliar1=primero;
		NodoLista<T> auxiliar2=primero;
		for (int i = 1; i <= pos1; i++) 
		{
			auxiliar1=auxiliar1.darSiguiente();
		}
		for (int i = 1; i <= pos2; i++) 
		{
			auxiliar2=auxiliar2.darSiguiente();
		}
		T info1 =auxiliar1.darInformation();
		T info2=auxiliar2.darInformation();
		auxiliar1.cambiarInformation(info2);
		auxiliar2.cambiarInformation(info1);
		
	}

	@Override
	public void changeInfo(int pos, T element) 
	{
		NodoLista<T> auxiliar1=primero;
		for (int i = 1; i <= pos; i++) 
		{
			auxiliar1=auxiliar1.darSiguiente();
		}
		auxiliar1.cambiarInformation(element);
		
	}

}
