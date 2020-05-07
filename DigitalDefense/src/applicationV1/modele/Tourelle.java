package applicationV1.modele;

public class Tourelle extends Acteur{
	private int dégats;
	private int portée;

	public Tourelle(int x, int y, int deg, int port, Environnement env) {
		// Création d'une tourelle sur une position donnée.
		super(x, y, env);
		this.dégats = deg;
		this.portée = port;
		// TODO Auto-generated constructor stub
	}
	
	public Tourelle(int deg, int port, Environnement env) {
		// Création d'une tourelle avec un position aléatoire
		super(env);
		this.dégats = deg;
		this.portée = port;
		// TODO Auto-generated constructor stub
	}
	
	public boolean aPortée(Ennemi e) {
		// La méthode sert à savoir si une tourelle a un ennemi donné à portée
		if(e.getX() < getX() + this.portée && e.getY() < getY() + this.portée && e.getX() > getX() - this.portée && e.getY() > getY() - portée) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void attaquer(Ennemi e) {
		e.perdreHp(this.dégats);
		System.out.println();
		System.out.println(e + " a perdu " + this.dégats + " HP");
	}

	@Override
	public String toString() {
		return "Tourelle [dégats=" + dégats + ", portée=" + portée + " " + super.toString() + "]";
	}

}
