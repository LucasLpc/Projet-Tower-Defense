package applicationV1.modele;

public class Tourelle extends Acteur{
	
	private int portée;

	public Tourelle(int x, int y, int dégats, int portée, Environnement env) {
		// Création d'une tourelle sur une position donnée.
		super(x, y, env, dégats);
		this.portée = portée;
		// TODO Auto-generated constructor stub
	}
	
	public Tourelle(int deg, int port, Environnement env) {
		// Création d'une tourelle avec un position aléatoire
		super(env);
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
		e.perdreHp(getDégats());
		System.out.println();
		System.out.println(e + " a perdu " + getDégats() + " HP");
	}

	@Override
	public String toString() {
		return "Tourelle [dégats=" + getDégats() + ", portée=" + portée + " " + super.toString() + "]";
	}

}
