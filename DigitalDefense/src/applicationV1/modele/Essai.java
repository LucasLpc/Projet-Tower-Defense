package applicationV1.modele;

public class Essai {

	public static void main(String[] args) {
		Environnement e = new Environnement(10,10);
		e.initTerrain();
		e.afficherTerrain();
		Ennemi e0 = new Ennemi(10, 10, e);
		Tourelle t0 = new Tourelle(10, 4, e);
		e.ajouterEnnemi(e0);
		e.ajouterTourelle(t0);
		System.out.println(e);
		e.afficherTerrain();
		for(int i = 0; i < 15; i++) {
			e.afficherTerrain();
			e.unTour();
		}
		e.afficherTerrain();
	}
}