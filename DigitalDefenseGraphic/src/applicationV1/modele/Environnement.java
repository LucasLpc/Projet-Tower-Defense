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
	private LinkedList<Noeud> adjacents(Noeud noeud){
		LinkedList<Noeud> file = new LinkedList<>();
		int distance = noeud.getDistance()+1;
		
//		if(noeud.getX()-1<0 || noeud.getY()-1<0 || noeud.getX()+1>this.terrain2D.length || noeud.getY()+1>this.terrain2D[0].length)
//			throw new Error("Sortie de tableau");
//		else {
//			if(this.terrain2D[noeud.getX()-1][noeud.getY()] == 'c') {
//				file.add(new Noeud(noeud.getX()-1,noeud.getY(),'c'));
//			}
//			if(this.terrain2D[noeud.getX()][noeud.getY()-1] == 'c') {
//				file.add(new Noeud(noeud.getX(),noeud.getY()-1,'c'));
//			}
//			if(this.terrain2D[noeud.getX()+1][noeud.getY()] == 'c') {
//				file.add(new Noeud(noeud.getX()+1,noeud.getY(),'c'));
//			}
//			if(this.terrain2D[noeud.getX()][noeud.getY()+1] == 'c') {
//				file.add(new Noeud(noeud.getX(),noeud.getY()+1,'c'));
//			}
//		}
		if(this.terrain2D.length > noeud.getX()+1) {
            if(this.terrain2D[noeud.getY()][noeud.getX()+1] == 'c') 
                file.add(new Noeud(noeud.getX()+1,noeud.getY(),distance));
            else System.out.println("la case X+1 n'est pas pratiquable");
        }else System.out.println("la case X+1 n'est pas valable");

        if(0 <= noeud.getX()-1) {
            if(this.terrain2D[noeud.getY()][noeud.getX()-1] == 'c') 
                file.add(new Noeud(noeud.getX()-1,noeud.getY(),distance));
            else System.out.println("la case X-1 n'est pas pratiquable");
        }else System.out.println("la case X-1 n'est pas valable");

        if(this.terrain2D[0].length > noeud.getY()+1) {
            if(this.terrain2D[noeud.getY()+1][noeud.getX()] == 'c') 
                file.add(new Noeud(noeud.getX(),noeud.getY()+1,distance));
            else System.out.println("la case Y+1 n'est pas pratiquable");
        }else System.out.println("la case Y+1 n'est pas valable");

        if(0 <= noeud.getY()-1) {
            if (this.terrain2D[noeud.getY()-1][noeud.getX()] == 'c') 
                file.add(new Noeud(noeud.getX(),noeud.getY()-1,distance));
            else System.out.println("la case Y-1 n'est pas pratiquable");
        }else System.out.println("la case Y-1 n'est pas valable");


		return file;
	}
	public ArrayList<Noeud> BFS(int x,int y){			
		ArrayList<Noeud> file = new ArrayList<Noeud>();
		Noeud noeud = new Noeud(x,y,0);
		file.add(noeud);
		while(!(noeud.getX() == 0 && noeud.getY() == 0)) {
			noeud = file.get(file.size()-1);
			noeud.adjacent = adjacents(noeud);
			for(Noeud adjNoeud : noeud.adjacent) {
				if(!adjNoeud.estDansListe(file)) {
					System.out.println(adjNoeud);					
					file.add(adjNoeud);
				}
			}
		}
		return file;
		
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
		// Cette m�thode permet le d�roul� d'un tour de jeu (attention ici un tour de jeu d�signe 1 action par acteur, d�placement ou attaquer)
		// A remarquer : cette m�thode rafraichi �galement le terrain, cela permet que le d�placement et les morts soient pris en compte par l'environnement.
		for(int i = 0; i < getNbEnnemis(); i++) {
			this.ennemis.get(i).agir();
		}
		for(int j = 0; j < getNbTourelles(); j++) {
			this.tourelles.get(j).agir();
		}
		this.nbTours += 1;
	}

	public void ajouterTourelle(Tourelle t) {
		this.tourelles.add(t);
	}
	public void ajouterEnnemi(Ennemi e) {
		this.ennemis.add(e);
	}
	public void delEnnemi(String id) {
		for(int i = getNbEnnemis()-1 ; i >= 0; i--) {
			if(getEnnemis().get(i).getId() == id) {
				this.ennemis.remove(i);
			}
		}
	}
	public void PosAleatoire(Acteur a) {
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
		// cette methode permet de savoir si un ennemi est autorise a acceder à  cette position.
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