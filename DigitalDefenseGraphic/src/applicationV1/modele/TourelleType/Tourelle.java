package applicationV1.modele.TourelleType;

import applicationV1.modele.Acteur;
import applicationV1.modele.Environnement;
import applicationV1.modele.EnnemiType.Ennemi;

public class Tourelle extends Acteur{
	private int degats;
	private int portee;

	public Tourelle(int x, int y, int deg, int port, Environnement env) {
		// Creation d'une tourelle sur une position donnee.
		super(x, y, env);
		this.degats = deg;
		this.portee = port;
		// TODO Auto-generated constructor stub
	}
	
	public Tourelle(int deg, int port, Environnement env) {
		// Creation d'une tourelle avec un position aleatoire
		super(env);
		this.degats = deg;
		this.portee = port;
		// TODO Auto-generated constructor stub
	}
	
	public void agir() {
		int j = 0;
		do {
			if(this.env.getNbEnnemis() > 0) {
				if (this.aPortee(this.env.getEnnemis().get(j))) {
					this.attaquer(this.env.getEnnemis().get(j));
					j = this.env.getNbEnnemis();
				}
			}
			j++;
		}while(j < this.env.getNbEnnemis());
	}
	
	public boolean aPortee(Ennemi e) {
		// La methode sert à savoir si une tourelle a un ennemi donne à portee
		if(e.getX() < getX() + this.portee && e.getY() < getY() + this.portee && e.getX() > getX() - this.portee && e.getY() > getY() - portee) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void attaquer(Ennemi e) {
		e.perdreHp(this.degats);
		System.out.println();
		System.out.println(e + " a perdu " + this.degats + " HP");
	}

	@Override
	public String toString() {
		return "Tourelle [degats=" + degats + ", portee=" + portee + " " + super.toString() + "]";
	}

}