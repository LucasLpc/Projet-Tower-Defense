package applicationV1.modele;

public class Tourelle {
	private int x,y;
	protected Environnement env;
	public static int compteur=0;
	private String id;
	private int d�gats;
	private int port�e;
	
	public Tourelle(int x, int y, int d�gats, int port�e, Environnement env) {
		this.x=x;
		this.y=y;
		this.env=env;
		this.d�gats = d�gats;
		this.port�e = port�e;
		this.id="#"+compteur;
		compteur++;
	}
	
	public boolean aPort�e(Ennemi e) {
		if(e.getX() < (getX()+getPort�e()) && e.getX() > (getX()-getPort�e()) && e.getY() < (getY()+getPort�e()) && e.getX() > (getY()-getPort�e())) {
			return true;
		}
		return false;
	}
	
	public int getDegats() {
		return this.d�gats;
	}
	
	public int getPort�e() {
		return this.port�e;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int n) {
		this.x = n;
	}
	
	public void setY(int n) {
		this.y = n;
	}
}
