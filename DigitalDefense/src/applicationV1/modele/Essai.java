package applicationV1.modele;

public class Essai {

	public static void main(String[] args) {
		Environnement env = new Environnement(5,5);
		env.initTerrain();
		env.afficherTerrain();
		System.out.println();
		Ennemi e = new EnnemiTypeBasic(2,2,env);
		Ennemi e2 = new EnnemiTypeBasic(3,2,env);
		env.ajouter(e);
		env.ajouter(e2);
		
		for(int i=0;i<5;i++) {
			env.refresh();
			System.out.println();
		}
		
		
		
	}

}
