package applicationV1.modele;

public class Tourelle extends Acteur{
	private int d�gats;
	private int port�e;

	public Tourelle(int x, int y, int deg, int port, Environnement env) {
		// Cr�ation d'une tourelle sur une position donn�e.
		super(x, y, env);
		this.d�gats = deg;
		this.port�e = port;
		// TODO Auto-generated constructor stub
	}
	
	public Tourelle(int deg, int port, Environnement env) {
		// Cr�ation d'une tourelle avec un position al�atoire
		super(env);
		this.d�gats = deg;
		this.port�e = port;
		// TODO Auto-generated constructor stub
	}
	
	public boolean aPort�e(Ennemi e) {
		// La m�thode sert � savoir si une tourelle a un ennemi donn� � port�e
		if(e.getX() < getX() + this.port�e && e.getY() < getY() + this.port�e && e.getX() > getX() - this.port�e && e.getY() > getY() - port�e) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void attaquer(Ennemi e) {
		e.perdreHp(this.d�gats);
		System.out.println();
		System.out.println(e + " a perdu " + this.d�gats + " HP");
	}

	@Override
	public String toString() {
		return "Tourelle [d�gats=" + d�gats + ", port�e=" + port�e + " " + super.toString() + "]";
	}

}
