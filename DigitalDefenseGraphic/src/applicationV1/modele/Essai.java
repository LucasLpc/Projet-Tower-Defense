package applicationV1.modele;

import applicationV1.modele.EnnemiType.Ennemi;
import applicationV1.modele.TourelleType.Tourelle;

public class Essai {

	public static void main(String[] args) {
		Environnement e = new Environnement();
		Ennemi e0 = new Ennemi(10, 30, e);
		Ennemi e1 = new Ennemi(10, 40, e);
		Tourelle t0 = new Tourelle(10, 5, e);
		e.ajouterEnnemi(e0);
		e.ajouterEnnemi(e1);
		e.ajouterTourelle(t0);
		System.out.println(e);
		for(int i = 0; i < 15; i++) {
			e.unTour();
		}
	}
}