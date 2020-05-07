package applicationV1.modele;

import java.util.Random;

public class Acteur {
	private int x;
	private int y;
	protected Environnement env;
	public static int compteur=0;
	private String id;
	
	public Acteur(int x, int y, Environnement env) {
		this.x = x;
		this.y = y;
		this.env = env;
		this.id="#"+compteur;
		compteur++;
	}

	public Acteur(Environnement env) {
		// M�thothe permetant de g�n�rer un acteur avec une position al�atoire
		Random random=new Random();
		this.x = random.nextInt(env.getLargeur());
		this.y = random.nextInt(env.getHauteur());
		this.env = env;
		this.id="#"+compteur;
		compteur++;
	}
	
	public void nouvellePosition() {
		// Methode permettant de reroll la position al�atoire dans le cas ou la pr�c�dante n'est pas voulue (case deja occup�e ou inaccesible).
		Random random=new Random();
		this.x = random.nextInt(env.getLargeur());
		this.y = random.nextInt(env.getHauteur());
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Acteur [x=" + x + ", y=" + y + ", env=" + env + ", id=" + id + "]";
	}
}
