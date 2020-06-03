package applicationV1.modele;

import java.util.ArrayList;

public class Trajectoire {

	private ArrayList<Integer> directions;
	private static int rang = 0;
	
	public Trajectoire(int xDepart, int yDepart, int xArrive, int yArrive) {
//		this.directions = defTraj(xDepart, yDepart, xArrive, yArrive);
	}
	
	public ArrayList<Integer> defTraj(int xDepart, int yDepart, int xArrive, int yArrive){
		// le principale probl�me pour moi c'est qu'on ne peut PAS r�sumer le probl�me � la r�solution d'une equation de
		// droite y = ax + b car si le coeficient a est tr�s �lev�, le projectil aura l'air de se t�l�porter puisque sa 
		// position sera calcul�e � partir de cette equation de mani�re discr�te.
		// le plus simple semble donc de s'int�r�sser au ratio (la division de dx sur dy). Ce ratio peut donner des informations
		// sur la r�partition des d�placements � faire pour atteindre la cible. Par exemple je doit au total faire 5
		// vers la gauche pour 2 vers le haut. 5/2 = 2 -> on doit se d�placer de 2 vers la gauche a chaque 1 vers le haut.
		// On remarque qu'il manque le reste de la division, il vas donc �tre obligatoire de les ajouter pour compenser 
		// le d�calage. Pour que cela fasse naturel on pourait rajouter le reste au fur et � mesure.
		
		int dx = xArrive - xDepart;
		int dy = yArrive - yDepart;
		ArrayList<Integer> directions = new ArrayList<Integer>();
		
		if(dx >= dy) {
			int coef = dx / dy;
			int reste = dx % dy;
			for(int i = 0; i < Math.abs(dx) + Math.abs(dy); i++) {
				
			}
		}
		else {
			int coef = dy / dx;
			int reste = dy % dx;
		}
		
		return null;
	}
}
