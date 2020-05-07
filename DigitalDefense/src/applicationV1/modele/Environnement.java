package applicationV1.modele;

import java.util.ArrayList;

public class Environnement {

	private ArrayList<Ennemi> ennemis;
	private ArrayList<Tourelle> tourelles;
	private int nbTours;
	private int terrain[][];
	
	public Environnement(int x, int y) {
		this.nbTours = 0 ;
		this.ennemis = new ArrayList<Ennemi>();
		this.tourelles = new ArrayList<Tourelle>();
		this.terrain = new int[x][y];
	}
	
	public void initTerrain() {
		// Remplissage du tableau qui représente le terrain (ici les bords sont des murs).
		for(int i=0; i<this.terrain.length;i++) {
			for(int j=0; j<this.terrain[i].length;j++) {
				if(i!=0 && i!=this.terrain.length-1 && j!=this.terrain[i].length-1 && j!=0) 
					this.terrain[i][j]=1;	
				else this.terrain[i][j]=0;
			}
		}
	}
	
	public void afficherTerrain() {
		for(int i = 0; i < getLargeur(); i++) {
			System.out.println();
			for(int j = 0; j < getHauteur(); j++) {
				System.out.print(this.terrain[i][j] + " ");
			}
		}
		System.out.println();
	}
	
	public void unTour() {
		// Cette méthode permet le déroulé d'un tour de jeu (attention ici un tour de jeu désigne 1 action par acteur, déplacement ou attaquer)
		// A remarquer : cette méthode rafraichi également le terrain, cela permet que le déplacement et les morts soient pris en compte par l'environnement.
		for(int i = 0; i < getNbEnnemis(); i++) {
			if(this.ennemis.get(i).estMort()) {
				this.terrain[this.ennemis.get(i).getX()][this.ennemis.get(i).getY()] = 1;
				System.out.println();
				System.out.println(this.ennemis.get(i) + " est mort");
				this.ennemis.remove(i);
			}
			else {
				this.terrain[this.ennemis.get(i).getX()][this.ennemis.get(i).getY()] = 1;
				this.ennemis.get(i).seDéplacer();
				this.terrain[this.ennemis.get(i).getX()][this.ennemis.get(i).getY()] = 2;
			}
		}
		for(int i = 0; i < getNbTourelles(); i++) {
			for(int j = 0; j < getNbEnnemis(); j++) {
				if(this.tourelles.get(i).aPortée(this.ennemis.get(j))) {
					this.tourelles.get(i).attaquer(this.ennemis.get(j));
				}
			}
		}
	}
	
	public void ajouterTourelle(Tourelle t) {
		// Ici on ajoute une boucle qui oblige le nouvel objet à se positioner sur une case accesible.
		do {
			t.nouvellePosition();
		}while(this.terrain[t.getX()][t.getY()] != 1);
		this.tourelles.add(t);
		this.terrain[t.getX()][t.getY()] = 3;
	}
	
	public void ajouterEnnemi(Ennemi e) {
		// Ici on ajoute une boucle qui oblige le nouvel objet à se positioner sur une case accesible.
		do {
			e.nouvellePosition();
		}while(this.terrain[e.getX()][e.getY()] != 1);
		this.ennemis.add(e);
		this.terrain[e.getX()][e.getY()] = 2;
	}
	
	public boolean positionValable(int x, int y) {
		// cette méthode permet de savoir si un ennemi est autorisé a accéder à  cette position.
		if(this.terrain[x][y] != 1) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int getLargeur() {
		return this.terrain.length;
	}

	public int getHauteur() {
		return this.terrain[0].length;
	}
	
	public int getNbActeurs(){
		return ennemis.size() + tourelles.size();
	}
	
	public int getNbEnnemis() {
		return this.ennemis.size();
	}
	
	public int getNbTourelles() {
		return this.tourelles.size();
	}
	
	public ArrayList<Ennemi> getEnnemis() {
		return this.ennemis;
	}
	
	public ArrayList<Tourelle> getTourelles(){
		return this.tourelles;
	}
	
	@Override
	public String toString() {
		return "Environnement [acteurs=" + getNbActeurs() + ", nbTours=" + nbTours + "]";
	}
}
