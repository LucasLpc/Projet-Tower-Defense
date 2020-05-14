package applicationV1.modele;

public class GameLoop {
	private Environnement env;
	private boolean partieEnCours;
	
	public GameLoop() {
		this.env = new Environnement ();
		this.partieEnCours = false;
	}
	
	public void run() {
		nouvelleVague();
		while(this.partieEnCours) {
			env.unTour();
		}
	}
	
	public boolean etatPartie() {
		if(this.env.getNbEnnemis() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void refreshPartieEnCours() {
		if(etatPartie()) {
			this.partieEnCours = true;
		}
		else {
			this.partieEnCours = false;
		}
	}
	
	public void nouvelleVague() {
		for(int i = 0; i < 6; i++) {
			Ennemi e = new Ennemi(10,10, this.env);
			this.env.ajouterEnnemi(e);
		}
	}
}
