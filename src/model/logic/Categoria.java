package model.logic;

public class Categoria implements Comparable<Categoria> 
{
	private String iD;
	
	private String name;
	
	
	
	
	public Categoria(String iD, String name) {
		super();
		this.iD = iD;
		this.name = name;
	}
	
	
	
	

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}





	@Override
	public int compareTo(Categoria o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
