package applicationV1.modele;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Environnement {

	private ArrayList<Ennemi> ennemis;
	private ArrayList<Tourelle> tourelles;
	private int nbTours;
	private char terrain[] = {'c','v','v','v','v','v','v','v','v','v',
							'c','v','v','v','v','v','v','v','v','v',
							'c','c','c','v','v','c','c','c','v','v',
							'v','t','c','v','v','c','t','c','v','v',
							'v','v','c','c','c','c','v','c','v','v',
							'v','v','v','v','v','v','v','c','v','v',
							'v','v','v','v','v','v','v','c','v','v',
							'v','v','v','v','v','v','v','c','c','v',
							'v','v','v','v','v','v','v','t','c','v',
							'v','v','v','v','v','v','v','v','c','c'};
	private char terrain2D[][] = tab2D(terrain,10,10);
	
	public Environnement() {
		this.ennemis = new ArrayList<Ennemi>();
		this.tourelles = new ArrayList<Tourelle>();
	}
	public void BFS(int x,int y){
		Noeud[][] terrain = null;
		
		for(int i=0; i<this.terrain2D.length; i++) 
			for(int j=0;j<this.terrain2D[j].length; j++) 
				terrain[i][j] = new Noeud(i,j);
			
		Queue<Noeud> file = new LinkedList();
		while(!file.isEmpty()) {
			file.remove();
			if(this.terrain2D[x-1][y] == 'c') {
				file.add(new Noeud(x-1,y));
			}
			if(this.terrain2D[x][y-1] == 'c') {
				file.add(new Noeud(x,y-1));
			}
			if(this.terrain2D[x+1][y] == 'c') {
				file.add(new Noeud(x+1,y));
			}
			if(this.terrain2D[x][y+1] == 'c') {
				file.add(new Noeud(x,y+1));
			}
		}
		
	}
	private char[][] tab2D(char[] n, int ligne, int colonne) {
		char[][] tab = new char[ligne][colonne];
		int indice =0;
		for(int i = 0; i < ligne; i++){
			for(int j=0; j< colonne; j++ ){
				tab[i][j] = n[indice];
	          	indice++;
			}
		}
		return tab;
	 }
	public void unTour() {
		// Cette méthode permet le déroulé d'un tour de jeu (attention ici un tour de jeu désigne 1 action par acteur, déplacement ou attaquer)
		// A remarquer : cette méthode rafraichi également le terrain, cela permet que le déplacement et les morts soient pris en compte par l'environnement.
		for(int i = 0; i < this.ennemis.size(); i++) {
			if(this.ennemis.get(i).estMort()) {
				System.out.println(this.ennemis.get(i) + " est mort");
				this.ennemis.remove(i);
			}
			else {
				this.ennemis.get(i).seDéplacer();
			}
		}
		for(int i = 0; i < getNbTourelles(); i++) {
			for(int j = 0; j < getNbEnnemis(); j++) {
				if(this.tourelles.get(i).aPortée(this.ennemis.get(j))) {
					this.tourelles.get(i).attaquer(this.ennemis.get(j));
				}
			}
		}
		this.nbTours += 1;
	}

	public void ajouterTourelle(Tourelle t) {
		this.tourelles.add(t);
	}
	public void ajouterEnnemi(Ennemi e) {
		this.ennemis.add(e);
	}
	public void PosAléatoire(Acteur a) {
		if(a instanceof Ennemi) {
			do {
				a.nouvellePosition();
			}while(this.terrain2D[a.getX()][a.getY()] != 'v');
		}
		if(a instanceof Tourelle) {
			do {
				a.nouvellePosition();
			}while(this.terrain2D[a.getX()][a.getY()] != 'c');
		}
	}

	public boolean positionValableEnnemi(int x, int y) {
		// cette méthode permet de savoir si un ennemi est autorisé a accéder à  cette position.
		if(this.terrain2D[x][y] != 'c') {
			return false;
		}
		return true;
	}

	public int getLargeur() {
		return this.terrain2D.length;
	}

	public int getHauteur() {
		return this.terrain2D[0].length;
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
	
	public char getCase(int x, int y) {
		return this.terrain2D[x][y];
	}
	
	public char[][] getTerrain(){
		return this.terrain2D;
	}
	
	@Override
	public String toString() {
		return "Environnement [acteurs=" + getNbActeurs() + ", nbTours=" + nbTours + "]";
	}
}