package applicationV1.modele;

public class Tourelle {
	private int x,y;
	protected Environnement env;
	public static int compteur=0;
	private String id;
	private int dégats;
	private int portée;
	
	public Tourelle(int x, int y, int dégats, int portée, Environnement env) {
		this.x=x;
		this.y=y;
		this.env=env;
		this.dégats = dégats;
		this.portée = portée;
		this.id="#"+compteur;
		compteur++;
	}
	
	public boolean aPortée(Ennemi e) {
		if(e.getX() < (getX()+getPortée()) && e.getX() > (getX()-getPortée()) && e.getY() < (getY()+getPortée()) && e.getX() > (getY()-getPortée())) {
			return true;
		}
		return false;
	}
	
	public int getDegats() {
		return this.dégats;
	}
	
	public int getPortée() {
		return this.portée;
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
